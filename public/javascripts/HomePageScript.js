/**.
 * 
 */

function signup(){
	var form = $("#homepagelogin");
	$.ajax({
		type : "GET",
		url : "/saveuser",
		data : form.serialize(),
		success : function(data){
			document.write(data);
			console.log(data);
		}
	});
	
}