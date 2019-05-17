package com.mail.sendmail;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.mail.sendmail.model.Mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class MailService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

	private static final String HOST = "smtp.gmail.com";
	private static final int PORT = 465;
	private static final boolean SSL_FLAG = true;
	private static final String USERNAME = "";
	private static final String PASSWORD = "";

	@Autowired
	private Configuration config;
	
	public void sendMail(Mail mail) {
		String content = getHtmlFromTemplateAndModel(mail.getMailTemplate(), mail.getTemplateData());
		sendSimpleEmail(mail.getFromEmail(), mail.getFromName(), mail.getToEmail(), mail.getSubject(), content);
	}

	private String getHtmlFromTemplateAndModel(String template, Map<String, Object> model) {
		Template t;
		String html = "";
		LOGGER.debug("Received template:{},model: {}", template, model);
		try {
			t = config.getTemplate(template);
			html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
			LOGGER.debug("Got HTML Content:{}", html);
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}

		return html;
	}

	private void sendSimpleEmail(String fromAddress, String fromName, String toAddress, String subject, String html) {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName(HOST);
			email.setSmtpPort(PORT);
			email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
			email.setSSLOnConnect(SSL_FLAG);
			if (null != fromName && fromName.trim().length() > 0) {
				email.setFrom(fromAddress, fromName);
			} else {
				email.setFrom(fromAddress, "Admin");
			}
			email.setSubject(subject);
			email.addTo(toAddress);
			email.setHtmlMsg(html);
			email.send();
		} catch (Exception ex) {
			LOGGER.error(ex.toString());
		}
	}
}
