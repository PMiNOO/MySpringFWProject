package mylab.user.di.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension; 

@ExtendWith(SpringExtension.class)
// Spring 설정 파일의 위치를 지정합니다.
@ContextConfiguration(locations = "classpath:mylab-user-di.xml")
public class UserServiceTest {

    // Spring 컨테이너로부터 UserService Bean을 주입받습니다.
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("DI 설정 및 서비스 기능 통합 테스트")
    void testDiAndFunctionality() {
        // 1. UserService를 주입 받았는지 검증 (Not Null)
        System.out.println("1. userService 검증 (not null)");
        assertNotNull(userService, "UserService가 주입되지 않았습니다.");

        // 2. userService.getUserRepository() 가 Not Null 인지 검증
        System.out.println("2. userRepository 검증 (not null)");
        assertNotNull(userService.getUserRepository(), "UserRepository가 주입되지 않았습니다.");

        // 3. userService.getUserRepository().getDbType() 값이 MySQL 인지 값을 비교하기
        System.out.println("3. dbType 값 검증 (MySQL)");
        assertEquals("MySQL", userService.getUserRepository().getDbType(), "dbType이 'MySQL'이 아닙니다.");

        // 4. userService.getSecurityService() 가 Not Null 인지 검증
        System.out.println("4. securityService 검증 (not null)");
        assertNotNull(userService.getSecurityService(), "SecurityService가 주입되지 않았습니다.");

        // 5. userService.registerUser() 메서드가 True 인지 검증
        System.out.println("5. registerUser() 메소드 동작 검증");
        boolean result = userService.registerUser("spring_user", "홍길동", "password123");
        assertTrue(result, "registerUser 메소드가 false를 반환했습니다.");

        System.out.println("\n테스트 성공: 모든 의존성 주입 및 기능이 정상적으로 동작합니다.");
    }
}