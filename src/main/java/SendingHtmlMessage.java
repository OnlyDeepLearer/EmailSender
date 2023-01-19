import io.github.cdimascio.dotenv.Dotenv;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author Bekpulatov Shoxruh, Mon 4:15 PM. 1/31/2022
 */
public class SendingHtmlMessage {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().directory("./assert/").filename("file").load();

        String to = "bekpulatovshoxruh9@gmail.com";
        String from = "bekpulatovshoxruh9@gmail.com";
        String password = "xxxxx";


        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.mailtrap.io");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");


        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("24fc3eba9653e2", "6a00d7681c26d4");
            }
        });
//        Session session = Session.getDefaultInstance(properties,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(,password);
//                    }
//                });

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("Thank you!");
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/file.html"));
            String data = reader.lines().collect(Collectors.joining());
            message.setContent(data, "text/html");

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (IOException | MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
