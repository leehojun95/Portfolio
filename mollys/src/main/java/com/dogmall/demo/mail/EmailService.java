package com.dogmall.demo.mail;

import java.util.Random;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailService{

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    
    public void sendMail(String type, EmailDTO dto, String authcode) {
        
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        
        try {
            
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            
            mimeMessageHelper.setTo(dto.getReceiverMail());
            mimeMessageHelper.setFrom(new InternetAddress(dto.getSenderMail(), dto.getSenderName()));
            mimeMessageHelper.setSubject(dto.getSubject());
            mimeMessageHelper.setText(setContext(authcode, type), true);
            
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void sendMail(EmailDTO dto, String[] emailArr) {
		
		//메일구성정보 담당(받는사람, 보내는 사람, 받는사람 메일주소, 본문내용)
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			
			// 메일템플릿으로 타임리프 사용목적으로 아래코드가 구성됨.
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            
//			InternetAddress[] inetAddresses = InternetAddress.parse(dto.getReceiverMail());
			
			mimeMessageHelper.setTo(emailArr); // 메일 수신자
            
            
            
            mimeMessageHelper.setFrom(new InternetAddress(dto.getSenderMail(), dto.getSenderName()));
            mimeMessageHelper.setSubject(dto.getSubject()); // 메일 제목
            mimeMessageHelper.setText(dto.getMessage(), true); // 메일 본문 내용, HTML 여부
			
			// 메일발송기능
			mailSender.send(mimeMessage);
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    public String createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(4);

            switch (index) {
                case 0: key.append((char) ((int) random.nextInt(26) + 97)); break;
                case 1: key.append((char) ((int) random.nextInt(26) + 65)); break;
                default: key.append(random.nextInt(9));
            }
        }
        return key.toString();
    }
    


    public String setContext(String authcode, String type) {
        Context context = new Context();
        context.setVariable("authcode", authcode);
        return templateEngine.process(type, context);
    }
}
