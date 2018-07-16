$(document).ready(function() {
	$("#loanSubMenuDiv").show();
	$("#pageHeaderNav").addClass('white-bg');
	$("#quickLinksDiv").hide();
	
	for(var i=1; i<=7; i++){
		if(i==1){
			$("#tab" + i).show();
			$("#"+1).addClass("active");
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
 	 for(var i=1; i<=7; i++){
 		 if(li == i){
 			 $("#tab" + i).show();
 			$("#" + i).addClass("active");
 		 }else{
 			$("#tab" + i).hide();
 			$("#" + i).removeClass("active");
 		 }
 	 }
 	 if(li == 1){
 		tabOneData();
 	 }else if(li == 2){
 		tabTwoData();
 	 }else if(li == 3){
 		tabThreeData();
 	 }else if(li == 4){
 		 tabFourData();
 	 }else if(li == 5){
 		 tabFiveData();
 	 }else if(li == 6){
 		 tabSixData();
 	 }else if(li == 7){
 		 
 	 }
}