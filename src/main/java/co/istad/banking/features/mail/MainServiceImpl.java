package co.istad.banking.features.mail;

import co.istad.banking.features.mail.dto.MailRequest;
import co.istad.banking.features.mail.dto.MailResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Template;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainServiceImpl implements MailService{

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Override
    public MailResponse send(MailRequest mailRequest) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        Context context = new Context();
        context.setVariable("name", "ISTAD");
        //templateEngine = new TemplateEngine();
        String htmlTemplate = templateEngine.process("mail/sample", context);

        try {
            mimeMessageHelper.setTo(mailRequest.to());
            mimeMessageHelper.setSubject(mailRequest.subject());
            mimeMessageHelper.setText(htmlTemplate, true);
//            Path imagePath = Paths.get("D:\\Anime Pic\\fanart1.jpg");
//            byte[] imageBytes = Files.readAllBytes(imagePath);

            // Attach image
           //mimeMessageHelper.addAttachment("image.jpg", new ByteArrayResource(imageBytes), "image/jpeg");


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        javaMailSender.send(mimeMessage);
        return new MailResponse("Mail has been sand...!");
    }

}
