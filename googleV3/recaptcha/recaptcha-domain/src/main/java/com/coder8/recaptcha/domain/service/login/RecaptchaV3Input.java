
package com.coder8.recaptcha.domain.service.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecaptchaV3Input {

	private String email;

	private String password;

	private String recaptchaResponse;

	private String recaptchaSiteKey;

	private String recaptchav3Js;
}
