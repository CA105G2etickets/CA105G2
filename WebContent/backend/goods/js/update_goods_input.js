// datetimepicker
        $.datetimepicker.setLocale('zh');
        $('#launchdate').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:true,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '<%=goodsVO.getLaunchdate()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#offdate').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:true,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '<%=goodsVO.getOffdate()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
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