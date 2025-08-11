package mylab.notification.di.annot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class) // JUnit5에서 Spring Test 기능을 사용하기 위한 설정
@ContextConfiguration(classes = NotificationConfig.class) // Spring 설정 클래스 지정
public class NotificationConfigTest {

    @Autowired
    private NotificationManager notificationManager; // Spring 컨테이너로부터 NotificationManager 빈을 주입받음

    @Test
    @DisplayName("Dependency Injection 테스트")
    void testDependencyInjection() {
        // 1. NotificationManager가 정상적으로 주입되었는지 검증 (Not Null)
        assertNotNull(notificationManager, "NotificationManager should not be null.");
        System.out.println("NotificationManager 주입 성공");

        // 2. NotificationManager가 참조하는 EmailService가 정상적으로 주입되었는지 검증 (Not Null)
        assertNotNull(notificationManager.getEmailService(), "EmailService should not be null.");
        System.out.println("EmailService 주입 성공: " + notificationManager.getEmailService());

        // 3. NotificationManager가 참조하는 SmsService가 정상적으로 주입되었는지 검증 (Not Null)
        // (요청사항의 'userService.getSecurityService() Not Null' 검증을 대체)
        assertNotNull(notificationManager.getSmsService(), "SmsService should not be null.");
        System.out.println("SmsService 주입 성공: " + notificationManager.getSmsService());

        // 4. SmsService의 provider 값이 "SKT"인지 비교하여 검증
        // (요청사항의 'userService.getUserRepository().getDbType()가 MySQL'인지 검증을 대체)
        // 구체 클래스의 getter를 사용하기 위해 다운캐스팅 필요
        SmsNotificationService smsService = (SmsNotificationService) notificationManager.getSmsService();
        assertEquals("SKT", smsService.getProvider(), "SMS provider should be SKT.");
        System.out.println("SmsService의 provider 값 검증 성공: " + smsService.getProvider());

        // 추가: 각 서비스의 메서드 실행 테스트
        System.out.println("\n--- 서비스 기능 테스트 ---");
        notificationManager.sendNotificationByEmail("테스트 이메일 메시지");
        notificationManager.sendNotificationBySms("테스트 SMS 메시지");
    }
}