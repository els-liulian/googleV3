
package com.coder8.recaptcha.domain.service.login;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;

@Service
public class RecaptchaV3ServiceImpl implements RecaptchaV3Service {

	@Value("${google.api.recaptchav3.url}")
	private String recaptchav3Url;

	@Value("${google.api.recaptchav3.param.secret}")
	private String secret;

	@Value("${google.api.recaptchav3.result.threshold}")
	private float threshold;

	@Value("${google.api.recaptchav3.error.locktime}")
	private int locktime;

	@Autowired
	MessageSource messageSource;

	@Override
	public RecaptchaV3Output doRecaptchaV3Output(RecaptchaV3Input input) {

		RecaptchaV3Output output = new RecaptchaV3Output();
		List<String> messages = new ArrayList<String>();
		try {
			RestTemplate restTemplate = new RestTemplate();

			RequestEntity<String> requestEntity = RequestEntity.post(URI.create(recaptchav3Url))
					.contentType(new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("utf-8")))
					.body(createRequestBody(input));

			RecaptchaResult result = restTemplate.postForObject(recaptchav3Url, requestEntity, RecaptchaResult.class);

			if (result.isSuccess()) {
				if (threshold <= result.getScore()) {
					// ロボットではない、
					output.setRobot(false);
					output.setResultNo(0);
				} else {

					messages.add("ロボットですので、再登録してください。");
					output.setMessages(messages);
					output.setResultNo(100);
					output.setRobot(true);
				}
			} else {
				messages.add("reCaptchaに接続失敗した。");
				output.setMessages(messages);
				output.setResultNo(100);
			}
		} catch (Exception e) {

			messages.add("reCaptchaに接続失敗した。");
			output.setMessages(messages);
			output.setResultNo(100);
		}
		return output;
	}

	/**
	 * RequestBodyを作成.
	 *
	 * @param input input
	 * @return
	 */
	private String createRequestBody(RecaptchaV3Input input) {
		StringBuilder sb = new StringBuilder();
		sb.append("secret=").append(secret).append("&");
		sb.append("response=").append(input.getRecaptchaResponse()).append("");
		return sb.toString();
	}
}
