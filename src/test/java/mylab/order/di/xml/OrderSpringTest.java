package mylab.order.di.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
public class OrderSpringTest {

    // ShoppingCart와 OrderService 클래스를 Injection 받습니다.
    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    private OrderService orderService;

    /**
     * ShoppingCart 스프링Bean을 테스트 하는 메서드
     */
    @Test
    void testShoppingCart() {
        // shoppingCart 객체가 Null 이 아닌지 검증하세요.
        assertNotNull(shoppingCart, "ShoppingCart Bean이 null입니다.");
        System.out.println("주입된 ShoppingCart: " + shoppingCart);

        // shoppingCart.getProducts().size() 를 검증하세요.
        assertEquals(2, shoppingCart.getProducts().size(), "Product 리스트의 개수가 2가 아닙니다.");
        System.out.println("포함된 상품 개수: " + shoppingCart.getProducts().size());
        
        // shoppingCart.getProducts().get(0).getName() 이 "노트북" 인지 검증하세요.
        assertEquals("노트북", shoppingCart.getProducts().get(0).getName(), "첫 번째 상품의 이름이 일치하지 않습니다.");
        System.out.println("첫 번째 상품 이름: " + shoppingCart.getProducts().get(0).getName());
        
        // shoppingCart.getProducts().get(1).getName() 이 "스마트폰" 인지 검증하세요.
        assertEquals("스마트폰", shoppingCart.getProducts().get(1).getName(), "두 번째 상품의 이름이 일치하지 않습니다.");
        System.out.println("두 번째 상품 이름: " + shoppingCart.getProducts().get(1).getName());
    }

    /**
     * OrderService 스프링Bean을 테스트 하는 메서드
     */
    @Test
    void testOrderService() {
        // OrderService Bean 주입 검증
        assertNotNull(orderService, "OrderService Bean이 null입니다.");
        
        // OrderService가 ShoppingCart를 제대로 주입받았는지 검증
        assertNotNull(orderService.getShoppingCart(), "OrderService의 shoppingCart가 null입니다.");
        
        // 주문 총액 계산 기능 검증
        double expectedTotalPrice = 1500000.0 + 800000.0;
        double actualTotalPrice = orderService.calculateOrderTotal();
        assertEquals(expectedTotalPrice, actualTotalPrice, "주문 총액이 일치하지 않습니다.");
        System.out.println("계산된 주문 총액: " + actualTotalPrice);
    }
}