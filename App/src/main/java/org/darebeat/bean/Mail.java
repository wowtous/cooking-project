package org.darebeat.bean;

import java.io.File;

public class Mail {
    private String subject; // 邮件主题
    private String header;  // 邮件标题
    private String from;    // 发信人地址
    private String content; // 邮件内容
    private String[] to;    // 收件人地址
    private String[] cc;    // 抄送地址
    private String[] bcc;   // 密送
    private File[] attachFile; // 附件地址

	public String getSubject() {
		return this.subject;
	}

	public String getHeader() {
		return this.header;
	}

	public String getFrom() {
		return this.from;
	}

	public String getContent() {
		return this.content;
	}

	public String[] getTo() {
		return this.to;
	}

	public String[] getCc() {
		return this.cc;
	}

    public String[] getBcc() {
		return this.bcc;
	}

	public File[] getAttachFile() {
		return this.attachFile;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

    public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public void setAttachFile(File[] attachFile) {
		this.attachFile = attachFile;
	}
}
