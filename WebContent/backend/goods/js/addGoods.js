// datetimepicker
$.datetimepicker.setLocale('zh');

 $("#launchdate").datetimepicker({
	format:'Y-m-d',
	minDate: 0,
	timepicker: false,
    changeMonth: true,
    changeYear: true,
	onShow:function(){
		this.setOptions({maxDate:$("#offdate").val() ? $("#offdate").val() : false})},
 });
  
 $("#offdate").datetimepicker({
	format:'Y-m-d',
	minDate: 0,
	timepicker: false,
    changeMonth: true,
    changeYear: true,
	onShow:function(){
		this.setOptions({minDate:$("#launchdate").val() ? $("#launchdate").val() : false})},
 });

 function imagesPreview(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function(e) {
	            $("#goods_picture1").attr("src", e.target.result);           
	        }        
	        reader.readAsDataURL(input.files[0]);
	    }
	}
 
 function imagesPreview(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function(e) {
	            $("#goods_picture2").attr("src", e.target.result);           
	        }        
	        reader.readAsDataURL(input.files[0]);
	    }
	}

function imagesPreview(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $("#goods_picture3").attr("src", e.target.result);           
        }        
        reader.readAsDataURL(input.files[0]);
    }
}