
$(function(){
	//Google reCAPTCHA v3
/*	$(document).ajaxSend(function(e, xhr, options){
		//let token = $("meta[name='_csrf']").token;
		//let token = $("meta[name='_csrf']").headerName;
		let token = $("meta[name='_csrf']").attr("content");
  		let header = $("meta[name='_csrf_header']").attr("content");
		xhr.setRequestHeader(header, token);
	  });*/

	$("#login").on('click',function(event){
		//デフォルトの動作（送信）を停止
		event.preventDefault();
		grecaptcha.ready(function() {
			var recaptchaSiteKey = $("#recaptchaSiteKey").val();
		  grecaptcha.execute( recaptchaSiteKey, {action: 'homepage'}).then(function(token) {
			$("#recaptchaResponse").val(token);
			console.log(token);
			let url= '/login/validate';

			let param = {
		      'recaptchaResponse': token,
		    };
		  let cfg = {
		      data: JSON.stringify(param),
		  }
				$.ajax(url,cfg)
				.done(function (data, textStatus, jqXHR ) {
			     console.log(data);
				if(data.result.robot){
				alert("ローボードですので、再登録してください。")
				}else{
					$("#loginForm").submit()
				}
			   })
			   .fail(function (jqXHR, textStatus, errorThrown) {
			     console.log(jqXHR.status + ':' + textStatus + ':(' + errorThrown +')');
			   })


			});
		});

	})

})

