package green.teamproject.tentrental.email.controller;

import green.teamproject.tentrental.email.sender.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @PreAuthorize("permitAll()")
    @PostMapping("/sendEmail")
    public String sendEmail(
            @RequestParam("name") String name,
            @RequestParam("emailForReply") String emailForReply,
            //@RequestParam("senderEmail") String senderEmail, //사용자로부터 이메일을 받아서 사용자의 이메일로 메일을 보내도록 처리하고 싶을 때 활용
            //@RequestParam("senderPassword") String senderPassword,
            @RequestParam("subject") String subject,
            @RequestParam("text") String text
    ) {
        MailSender mailSender = new MailSender(name, subject, text);
        mailSender.send(name, emailForReply, subject, text);

        // Create a SimpleMailMessage
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setText(subject);
//        message.setFrom(name);
//        message.setTo("shb0801@naver.com"); // Your email address
//        message.setSubject(subject);
//        message.setText("From: " + name + "\nEmail: " + email + "\n\n" + subject);

        // Send the email
//        javaMailSender.send(message);

        // Redirect to a thank you page or another appropriate page
        return "redirect:home/main"; // You can create a "thankyou.html" template
    }
}
