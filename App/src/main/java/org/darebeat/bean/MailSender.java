package org.darebeat.bean;


import org.darebeat.utils.StringUtil;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

public class MailSender {
    private final transient Properties props = System.getProperties(); // 发送邮件的props文件
    private transient MailAuthenticator authenticator; // 邮件服务器登录验证
    private transient Session session; // 邮箱session
    private static MailSender service = null; // 服务邮箱

    /**
     * 获取邮箱服务
     * 工厂模式
     * @return 邮箱服务
     */
    public static MailSender getSender(final String username, final String password) {
        if (service == null) {
            service = new MailSender(username, password);
        }
        return service;
    }

    /**
     * 初始化邮件发送器
     * @param smtpHostName SMTP邮件服务器地址
     * @param username 发送邮件的用户名(地址)
     * @param password 发送邮件的密码
     */
    public MailSender(final String smtpHostName, final String username, final String password) {
        init(username, password, smtpHostName);
    }

    /**
     * 初始化邮件发送器
     * @param username 发送邮件的用户名(地址)，并以此解析SMTP服务器地址
     * @param password 发送邮件的密码
     */
    public MailSender(final String username, final String password) {
        //通过邮箱地址解析出smtp服务器，对大多数邮箱都管用
        final String smtpHostName = "smtp." + username.split("@")[1];
        init(username, password, smtpHostName);
    }

    /**
     * 初始化
     * @param username 发送邮件的用户名(地址)
     * @param password 密码
     * @param smtpHostName SMTP主机地址
     */
    private void init(String username, String password, String smtpHostName) {
        // 初始化props
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", smtpHostName);
        // 验证
        authenticator = new MailAuthenticator(username, password);
        // 创建session
        session = Session.getInstance(props, authenticator);
    }

    /**
     * 发送邮件
     * @param recipient 收件人邮箱地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @throws AddressException
     * @throws MessagingException
     */
    public void send(String recipient, String subject, Object content) throws AddressException, MessagingException {
        // 创建mime类型邮件
        final MimeMessage message = new MimeMessage(session);
        // 设置发信人
        message.setFrom(new InternetAddress(authenticator.getUsername()));
        // 设置收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        // 设置主题
        message.setSubject(subject);
        // 设置邮件内容
        message.setContent(content.toString(), "text/html;charset=utf-8");
        // 发送
        System.out.println("开始发送邮件……");
        Transport.send(message);
        System.out.println("发送成功！");
    }

    /**
     * 群发邮件
     * @param recipients 收件人们
     * @param subject 主题
     * @param content 内容
     * @throws AddressException
     * @throws MessagingException
     */
    public void send(List<String> recipients, String subject, Object content) throws AddressException, MessagingException {
        // 创建mime类型邮件
        final MimeMessage message = new MimeMessage(session);
        // 设置发信人
        message.setFrom(new InternetAddress(authenticator.getUsername()));
        // 设置收件人
        final int num = recipients.size();
        InternetAddress[] addresses = new InternetAddress[num];

        for (int i = 0; i < num; i++) {
            addresses[i] = new InternetAddress(recipients.get(i));
        }

        message.setRecipients(Message.RecipientType.TO, addresses);
        // 设置主题
        message.setSubject(subject);
        // 设置邮件内容
        message.setContent(content.toString(), "text/html;charset=utf-8");
        // 发送
        System.out.println("开始发送邮件……");
        Transport.send(message);
        System.out.println("发送成功！");
    }

    /**
     * 发送邮件
     * @param mail 邮件对象
     * @throws AddressException
     * @throws MessagingException
     */
    public void send(Mail mail) throws AddressException, MessagingException, UnsupportedEncodingException {
        String subject = mail.getSubject();
        String from = mail.getFrom();
        String content = mail.getContent();
        String[] to = mail.getTo();
        String[] cc = mail.getCc();
        String[] bcc = mail.getBcc();
        File[] attachFile = mail.getAttachFile();

        // 创建mime类型邮件
        final MimeMessage message = new MimeMessage(session);

        if (null != from && from.contains("@")) {
            message.setFrom(new InternetAddress(from)); // 设置发信人
        }else{
            message.setFrom(new InternetAddress(authenticator.getUsername())); // 设置发信人
        }

        // 设置多个收件人地址
        setAddress(to, message,Message.RecipientType.TO);

        // 设置多个抄送地址
        setAddress(cc, message,Message.RecipientType.CC);

        // 设置多个密送地址
        setAddress(bcc, message,Message.RecipientType.BCC);

        message.setSubject(subject); // 邮件主题

        Multipart mp = new MimeMultipart(); // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        BodyPart bp = new MimeBodyPart(); // 添加邮件正文

        bp.setContent(content,"text/html;charset=utf-8");
        mp.addBodyPart(bp);

        // 设置多个附件
        if (!StringUtil.isEmptyArray(attachFile)) {
           MimeBodyPart mbp = null;
           for (File file : attachFile) {
               mbp = new MimeBodyPart();

               DataSource source = new FileDataSource(file);
               mbp.setDataHandler(new DataHandler(source));
               //MimeUtility.encodeWord可以避免文件名乱码
               mbp.setFileName(MimeUtility.encodeWord(file.getName()));
               mp.addBodyPart(mbp);
           }
        }
        // 将multipart对象放到message中
        message.setContent(mp);
        message.saveChanges();

        System.out.println("开始发送邮件……");
        Transport.send(message);
        System.out.println("发送成功！");
    }

    private void setAddress(String[] cc, MimeMessage message, Message.RecipientType type) throws MessagingException {
        if(!StringUtil.isEmptyArray(cc)){
            InternetAddress[] iaCc = new InternetAddress[cc.length];
            for (int j = 0; j < cc.length; j++) {
                iaCc[j] = new InternetAddress(cc[j]);
            }
            message.setRecipients(type, iaCc);
        }
    }

    /**
     * 发送邮件
     * @param recipient 收件人邮箱地址
     * @param mail 邮件对象
     * @throws AddressException
     * @throws MessagingException
     */
    public void send(String recipient, Mail mail) throws AddressException, MessagingException {
        send(recipient, mail.getSubject(), mail.getContent());
    }

    /**
     * 群发邮件
     * @param recipients 收件人列表
     * @param mail 邮件对象
     * @throws AddressException
     * @throws MessagingException
     */
    public void send(List<String> recipients, Mail mail) throws AddressException, MessagingException {
        send(recipients, mail.getSubject(), mail.getContent());
    }
}
