

$(function(){
	$.ajax({
		url:'https://iphunter.coding.me/remote.json',
		dataType: 'json',
		success: function(data){
			//$('#version').text("版本：" + data.NewVersion + "(build "+data.VersionCode+")");
			//$('#fileSize').text("大小："+data.TargetSize);
			//var tmpstring = data.UpdateLog.split("\n");
			//for(var i=0; i<tmpstring.length; i++){
			//	$('#updateExplain').append(tmpstring[i]);
			//	$('#updateExplain').append("<br />");
			//}
			
			
			for(var o in data)
			{
				//alert(item);
				$('#updateExplain').append("<a href=" + 'https://bslapp.me/comments/' + data[o].InnerName + '.html target="_blank">' + data[o].Name + "</a>");
				$('#updateExplain').append("\t\t").append(data[o].Description);
				$('#updateExplain').append("<br />");
			}
		}
	});
});