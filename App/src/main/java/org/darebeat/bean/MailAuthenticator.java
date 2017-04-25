package org.darebeat.bean;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 服务器邮箱登录验证
 * 用作登录校验的，以确保你对该邮箱有发送邮件的权利。
 * @author Darebeat
 */
public class MailAuthenticator extends Authenticator {
    private String username; // 用户名（登录邮箱）
    private String password; // 密码

    /**
     * 初始化邮箱和密码
     * @param username 邮箱
     * @param password 密码
     */
    public MailAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    String getPassword() {
        return password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }

    String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
