<?php
//获取网址参数
$query_str = $_SERVER["QUERY_STRING"];

//resove params
$params = parseUrlParam($query_str);
$service = $params["service"];

//1252142997
//SecretId: AKIDmRs3yeun4D7mCMi3WjgOs87qJVR6FZR4
//SecretKey: WnshMnzvb1B7oGTRAdv0qVw96rNejZAW

$appid = "1252142997";
$secret_id = "AKIDmRs3yeun4D7mCMi3WjgOs87qJVR6FZR4";
$secret_key = "WnshMnzvb1B7oGTRAdv0qVw96rNejZAW";
// "bucket=" +bucket + "&service=cos&expired=0&path=" + fileId;
// String cgi = "http://203.195.194.28/cosv4/getsignv4.php?" + "bucket=" + bucket + "&service=video";

//multi
if($service == "video")
{
    $bucket = $params["bucket"];
    $expired = time() + 32400000;
    $onceExpired = 0;
    $current = time();
    $rdm = rand();

    $multi_effect_signature = 'a='.$appid.'&b='.$bucket.'&k='.$secret_id.'&e='.$expired.'&t='.$current.'&r='.$rdm.'&f=';
    $multi_effect_signature = base64_encode(hash_hmac('SHA1', $multi_effect_signature, $secret_key, true).$multi_effect_signature);
    $result["sign"] = $multi_effect_signature;
    echo json_encode($result);
    echo "<br>";

}
else if($service == "cos")
{
    $bucket = $params["bucket"];
    $onceExpired = 0;
    $current = time();
    $rdm = rand();
    $fileid = $params["path"];

    $once_signature=
        'a='.$appid.'&b='.$bucket.'&k='.$secret_id.'&e='.$onceExpired.'&t='.$current.'&r='.$rdm.'&f='.$fileid;
    $once_signature = base64_encode(hash_hmac('SHA1',$once_signature,$secret_key, true).$once_signature);
    $result["sign"] = $once_signature;
    echo json_encode($result);
    echo "<br>";

}else
{
    echo  "wrong params\n";
}

//解析URL参数
function parseUrlParam($query){
    $queryArr = explode('&', $query);
    $params = array();
    if($queryArr[0] !== ''){
        foreach( $queryArr as $param ){
            list($name, $value) = explode('=', $param);
            $params[urldecode($name)] = urldecode($value);
        }       
    }
    return $params;
}

//设置URL参数数组
function setUrlParams($cparams, $url = ''){
  $parse_url = $url === '' ? parse_url($_SERVER["REQUEST_URI"]) : parse_url($url);
  $query = isset($parse_url['query']) ? $parse_url['query'] : '';
  $params = parseUrlParam($query);
  foreach( $cparams as $key => $value ){
    $params[$key] = $value;
  }
  return $parse_url['path'].'?'.http_build_query($params);
}

//获取URL参数
function getUrlParam($cparam, $url = ''){
    $parse_url = $url === '' ? parse_url($_SERVER["REQUEST_URI"]) : parse_url($url);
    $query = isset($parse_url['query']) ? $parse_url['query'] : '';
    $params = parseUrlParam($query);
    return isset($params[$cparam]) ? $params[$cparam] : '';
}