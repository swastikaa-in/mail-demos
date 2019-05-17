package com.mail.testmail;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mail.sendmail.MailService;
import com.mail.sendmail.model.Mail;

@SpringBootApplication
@RestController
public class TestMailApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestMailApplication.class);

	@Autowired
	MailService mailService;

	public static void main(String[] args) {
		SpringApplication.run(TestMailApplication.class, args);
	}

	@GetMapping("/sendmail")
	public String sendMail() {
		Mail mail = new Mail();
		mail.setFromEmail("s@s.com");
		mail.setFromName("munnazhaghi");
		mail.setToEmail("sridharb.in@gmail.com");
		mail.setSubject("Coming For Date?");
		mail.setMailTemplate("email-template.ftl");

		Map<String, Object> templateData = new HashMap<>();
		templateData.put("Name", "Kokila");
		templateData.put("location", "Salem, India");

		mail.setTemplateData(templateData);
		LOGGER.debug(mail.toString());
		mailService.sendMail(mail);
		return "Mail Sent Successfully";
	}

}
