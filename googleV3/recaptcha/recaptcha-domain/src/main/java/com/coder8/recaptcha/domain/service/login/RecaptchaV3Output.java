
package com.coder8.recaptcha.domain.service.login;

import java.util.List;
import org.joda.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecaptchaV3Output {

	private boolean robot;

	private LocalDateTime unlockDtByRecaptcha;

	/**
	 * 処理結果番号.
	 */
	private int resultNo;

	/**
	 * メッセ時情報.
	 */
	private List<String> messages;
}
