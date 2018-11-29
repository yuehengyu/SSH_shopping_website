package pers.yhy.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.handlers.message_rfc822;

/**
 * tool class of sending email
 * 
 * @author Hengyu Yue
 *
 */
public class MailUtils {
	/**
	 * send emeil function
	 * 
	 * @param to
	 * @param code
	 */
	public static void sendMail(String to, String code) {

		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("service@desktop-53f4d6j.com", "111");
			}

		});

		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("service@desktop-53f4d6j.com"));
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			message.setSubject("This is the official activation email from YHY shopping website");
			message.setContent(
					"<h1>Congratulations on your registration, click on the link below to activate</h1><h3><a href='http://192.168.2.184:8080/SSH_shopping_website/user_active.action?code="
							+ code + "'>http://192.168.2.184:8080/SSH_shopping_website/user_active.action?code=" + code
							+ "</a></h3>",
					"text/html;charset=UTF-8");
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}
