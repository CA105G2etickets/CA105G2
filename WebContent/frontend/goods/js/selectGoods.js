// datetimepicker
$("#launchdate").datetimepicker({
	format:'Y-m-d',
	timepicker: false,
	onShow:function(){
		this.setOptions({maxDate: $("#offdate").val() ? $("#offdate").val() : false})}
});
	 
$("#offdate").datetimepicker({
	format:'Y-m-d',
	timepicker: false,
	onShow:function(){
		this.setOptions({minDate:$("#launchdate").val() ? $("#launchdate").val() : false})}
}); 