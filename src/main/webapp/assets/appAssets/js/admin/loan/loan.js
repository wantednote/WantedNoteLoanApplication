$(document).ready(function() {
	$("#pageHeaderNav").addClass('white-bg');
	$("#quickLinksDiv").hide();
	
	for(var i=1; i<=4; i++){
		if(i==1){
			$("#tab" + i).show();
			$("#"+1).addClass("folder-list-selected");
			tabOneData();
		}else{
			$("#tab" + i).hide();
		}
	}
	/* setTimeout(function() {
        toastr.options = {
            closeButton: true,
            progressBar: true,
            showMethod: 'slideDown',
            timeOut: 4000
        };
        toastr.success('WantedNote', 'View Several Syatem Actors');
    }, 1300); */
});
function changeSelection(li){
	 alert("Li " + li);
 	 for(var i=1; i<=4; i++){
 		 if(li == i){
 			 $("#tab" + i).show();
 			$("#" + i).addClass("folder-list-selected");
 		 }else{
 			$("#tab" + i).hide();
 			$("#" + i).removeClass("folder-list-selected");
 		 }
 	 }
 	 if(li == 1){
 		tabOneData();
 	 }else if(li == 2){
 		tabTwoData();
 	 }else if(li == 3){
 		 
 	 }else if(li == 4){
 		 
 	 }
}