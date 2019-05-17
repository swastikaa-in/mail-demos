package com.mail.sendmail.model;

import java.util.Map;

public class Mail {

	String mailTemplate;
	
	String fromEmail;
	String fromName;
	String toEmail;
	String subject;
	
	
	Map<String,Object> templateData;

	

	public String getMailTemplate() {
		return mailTemplate;
	}

	public void setMailTemplate(String mailTemplate) {
		this.mailTemplate = mailTemplate;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	

	@Override
	public String toString() {
		return "Mail [mailTemplate=" + mailTemplate + ", fromEmail=" + fromEmail + ", fromName=" + fromName
				+ ", toEmail=" + toEmail + ", subject=" + subject + ", templateData=" + templateData + "]";
	}

	public Map<String, Object> getTemplateData() {
		return templateData;
	}

	public void setTemplateData(Map<String, Object> templateData) {
		this.templateData = templateData;
	};
}
