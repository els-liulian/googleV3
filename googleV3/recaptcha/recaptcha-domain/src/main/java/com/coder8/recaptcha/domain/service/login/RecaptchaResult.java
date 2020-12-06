package com.coder8.recaptcha.domain.service.login;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RecaptchaResult implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private boolean success;
	private String challengets;
	private String hostname;
	private float score;
	private String action;
	private List<String> errorCodes;

}
