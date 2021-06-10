package com.zzpj.utils;

import java.io.IOException;
import java.io.InputStream;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.util.Properties;

public class EmailSender {

    private static final Properties prop = new Properties();

    public static void sendEmail(String recipientName, String recipientEmailAddress, String subject, String text) throws IOException {
        InputStream input = EmailSender.class.getClassLoader().getResourceAsStream("mail.properties");

            prop.load(input);

            String username = prop.getProperty("mail.username");
            String password = prop.getProperty("mail.password");
            String emailAddress = prop.getProperty("mail.address");

            Email email = EmailBuilder.startingBlank()
                    .from("Recipes", emailAddress)
                    .to(recipientName, "flowerka99@gmail.com")
                    .withSubject(subject)
                    .withHTMLText(text)
                    .buildEmail();

            Mailer mailer = MailerBuilder
                    .withSMTPServer("in-v3.mailjet.com", 587, username, password)
                    .withTransportStrategy(TransportStrategy.SMTP)
                    .buildMailer();

            mailer.sendMail(email);

    }
}
