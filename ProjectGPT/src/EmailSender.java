import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    public static void sendOTP(String toEmail, String otp) {
        String appPassword = "mhqu mwbt lpwa snvf";
        String email = "librarysystem.otp@gmail.com";
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, appPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Library System - OTP Code");

            String htmlContent = "<div style='font-family: Times; font-size: 14px; color: #000000;font-weight: bold;'>"
                    + "<p>Dear " + toEmail + " ,</p>"
                    + "<p>We received your request for a single-use code to use with your Library Account.</p>"
                    + "<p>Your single-use code is: <span style='font-weight: bold; color: #ff0000;'>" + otp + "</span></p>"
                    + "<p>If you didn't request this code, you can safely ignore this email. Someone else might have typed your email address by mistake.</p>"
                    + "<p>Thanks,<br>"
                    + "<span style='font-style: italic;font-size: 12px;'>Library System Management Team</span></p>"
                    + "</div>";

            message.setContent(htmlContent, "text/html");

            Transport.send(message);

            System.out.println("OTP sent successfully to " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
