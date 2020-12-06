package com.coder8.recaptcha.app.login;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginForm implements Serializable {

	/**
	 * シリアル番号ID
	 */
	private static final long serialVersionUID = -723755331854045600L;

	private String email;

	private String password;

	private String recaptchaResponse;

	private String recaptchaSiteKey;

	private String recaptchav3Js;
	/**
	 * 処理結果番号.
	 */
	private int resultNo;

	/**
	 * メッセ時情報.
	 */
	private List<String> messages;

}