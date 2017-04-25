package org.darebeat.bean;

public class Mail {
    private String subject;
    private String content;
    private String attachFile;

	public String getSubject() {
		return this.subject;
	}

    public String getContent() {
		return this.content;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
