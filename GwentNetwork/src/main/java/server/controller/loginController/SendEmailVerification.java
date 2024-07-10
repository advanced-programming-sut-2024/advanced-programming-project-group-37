package server.controller.loginController;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendEmailVerification {
    public static void sendEmail(String emailAddress, int radNum) {
        // Recipient's email ID needs to be mentioned.
        String to = emailAddress;

        // Sender's email ID needs to be mentioned
        String from = "userasddsa22@gmail.com";
        final String username = "userasddsa22@gmail.com"; // change accordingly
        final String password = "ecoy zspp tmuj srfd"; // change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", host);

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("verify your email");

            // Now set the actual message
            message.setText("Hi,\nPlease enter the following code to verify your email.\nyour code: " + radNum);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
