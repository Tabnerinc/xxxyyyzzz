/**
 * This is the javascript for the signin page  
 */

function signin(){
	var form = $("#signinformid");
	var formdata = form.serializeArray();
	console.log(formdata);
	var jsonstring = jsonstringifying(formdata);
	$.ajax({
		type:"POST",
		url:"/signinvalidate",
		data:
			{
			formdata : jsonstring
			},
		success: function(data){
			document.write(data);
		}
	});
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