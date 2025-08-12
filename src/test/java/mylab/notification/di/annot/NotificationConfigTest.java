package mylab.notification.di.annot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NotificationConfig.class)
public class NotificationConfigTest {

    @Autowired
    private NotificationManager notificationManager;

    private EmailNotificationService emailService;
    private SmsNotificationService smsService;

    // 각 테스트가 실행되기 전에 공통적으로 필요한 객체를 설정합니다.
    @BeforeEach
    void setUp() {
        // NotificationManager로부터 각 서비스의 구현체 객체를 가져옵니다.
        // 구체적인 메서드(getSmtpServer 등)를 테스트하려면 인터페이스가 아닌 구체 클래스로 캐스팅해야 합니다.
        this.emailService = (EmailNotificationService) notificationManager.getEmailService();
        this.smsService = (SmsNotificationService) notificationManager.getSmsService();
    }

    @Test
    @DisplayName("NotificationManager와 주입된 서비스 전체 검증")
    void testFullDependencyInjectionAndProperties() {
        // [검증 1] NotificationManager 주입 검증
        System.out.println("1. NotificationManager 주입 검증");
        assertNotNull(notificationManager, "NotificationManager should be injected and not null.");
        System.out.println("-> NotificationManager 주입 성공\n");

        // [검증 2] 이메일 서비스 검증
        System.out.println("2. 이메일 서비스(EmailService) 검증");
        // 2-a. EmailService Not Null 검증
        assertNotNull(emailService, "EmailService should not be null.");
        System.out.println("-> EmailService Not Null 검증 성공");

        // 2-b. SMTP 서버 주소 값 비교 검증
        assertEquals("smtp.gmail.com", emailService.getSmtpServer(), "SMTP Server should be 'smtp.gmail.com'");
        System.out.println("-> SMTP 서버 주소 검증 성공: " + emailService.getSmtpServer());

        // 2-c. 포트 번호 값 비교 검증
        assertEquals(587, emailService.getPort(), "Port should be 587");
        System.out.println("-> 포트 번호 검증 성공: " + emailService.getPort() + "\n");

        // [검증 3] SMS 서비스 검증
        System.out.println("3. SMS 서비스(SmsService) 검증");
        // 3-a. SmsService Not Null 검증
        assertNotNull(smsService, "SmsService should not be null.");
        System.out.println("-> SmsService Not Null 검증 성공");

        // 3-b. SMS 제공업체 값 비교 검증
        assertEquals("SKT", smsService.getProvider(), "SMS Provider should be 'SKT'");
        System.out.println("-> SMS 제공업체 검증 성공: " + smsService.getProvider() + "\n");
        
        // [검증 4] NotificationManager 메서드 실행 테스트
        System.out.println("4. NotificationManager 메서드 실행");
        notificationManager.sendNotificationByEmail("테스트 이메일");
        notificationManager.sendNotificationBySms("테스트 SMS");
        System.out.println("-> 메서드 실행 테스트 완료");
    }
}