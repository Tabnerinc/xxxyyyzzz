/**.
 * 
 */

function saveMongo(){
	var form = $("#homepagelogin");
	var formdata = form.serializeArray();
	console.log(formdata);
	var jsonstring = jsonstringifying(formdata);
	console.log(jsonstring);
	$.ajax({
		type:"POST",
		url:"/saveuserasjson",
		data : {
		formdata : jsonstring
		},
		success : function(data){
	     document.write(data);
		}
	})
}


function jsonstringifying(formdata){
	var string = "{";
	for(var i=0;i<formdata.length;i++){
		string = string+"\""+formdata[i].name+"\""+":"+"\""+formdata[i].value+"\"";
		if(i<formdata.length-1){
			string=string+",";
		}
	}
	string = string+"}";
	return string;
}