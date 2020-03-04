

$(function(){
	$.ajax({
		url:'https://api.bslapp.me:39900/mode/version.json',
		dataType: 'json',
		success: function(data){
			$('#version').text("版本：" + data.NewVersion + "(build "+data.VersionCode+")");
			$('#fileSize').text("大小："+data.TargetSize);
			var tmpstring = data.UpdateLog.split("\n");
			for(var i=0; i<tmpstring.length; i++){
				$('#updateExplain').append(tmpstring[i]);
				$('#updateExplain').append("<br />");
			}
		}
	});
});