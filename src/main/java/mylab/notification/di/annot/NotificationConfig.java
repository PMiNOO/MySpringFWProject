package mylab.notification.di.annot;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

    /**
     * EmailNotificationService 빈을 생성합니다.
     * "emailService"라는 식별자를 부여합니다.
     * @return EmailNotificationService 인스턴스
     */
    @Bean
    @Qualifier("emailService") // 1. Qualifier로 빈의 식별자(이름) 지정
    public NotificationService emailNotificationService() {
        return new EmailNotificationService("smtp.gmail.com", 587);
    }

    /**
     * SmsNotificationService 빈을 생성합니다.
     * "smsService"라는 식별자를 부여합니다.
     * @return SmsNotificationService 인스턴스
     */
    @Bean
    @Qualifier("smsService") // 1. Qualifier로 빈의 식별자(이름) 지정
    public NotificationService smsNotificationService() {
        return new SmsNotificationService("SKT");
    }

    /**
     * NotificationManager 빈을 생성합니다.
     * @Qualifier를 사용하여 주입받을 빈을 명확하게 지정합니다.
     * @param emailService "emailService"로 지정된 빈
     * @param smsService "smsService"로 지정된 빈
     * @return NotificationManager 인스턴스
     */
    @Bean
    public NotificationManager notificationManager(
            @Qualifier("emailService") NotificationService emailService, // 2. "emailService" 빈을 주입
            @Qualifier("smsService") NotificationService smsService      // 2. "smsService" 빈을 주입
    ) {
        return new NotificationManager(emailService, smsService);
    }
}