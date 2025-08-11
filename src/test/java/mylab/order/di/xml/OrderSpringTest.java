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

    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private Product product1;

    /**
     * ShoppingCart 스프링Bean을 테스트 하는 메서드
     */
    @Test
    public void testShoppingCart() {
        assertNotNull(shoppingCart, "ShoppingCart Bean이 null입니다.");
        System.out.println("주입된 ShoppingCart: " + shoppingCart);

        // 장바구니에 상품 2개가 들어있는지 확인하는 것은 동일합니다.
        assertEquals(2, shoppingCart.getProducts().size(), "Product 리스트의 개수가 2가 아닙니다.");
        System.out.println("포함된 상품 개수: " + shoppingCart.getProducts().size());
        
        // 주입된 product1의 이름이 "노트북"인지 추가로 확인해 볼 수 있습니다.
        assertEquals("노트북", product1.getName());
        System.out.println("주입된 Product1: " + product1);
    }

    /**
     * OrderService 스프링Bean을 테스트 하는 메서드
     */
    @Test
    public void testOrderService() {
        assertNotNull(orderService, "OrderService Bean이 null입니다.");
        System.out.println("주입된 OrderService: " + orderService);

        assertNotNull(orderService.getShoppingCart(), "OrderService의 shoppingCart가 null입니다.");
        System.out.println("OrderService가 참조하는 ShoppingCart: " + orderService.getShoppingCart());
        
        // *** 이 부분이 수정되었습니다 ***
        // 주문 총액 계산이 새로운 가격에 맞게 동작하는지 확인합니다.
        // 노트북(1500000) + 스마트폰(800000) = 2300000.0
        double expectedTotalPrice = 1500000.0 + 800000.0;
        double actualTotalPrice = orderService.calculateOrderTotal();

        assertEquals(expectedTotalPrice, actualTotalPrice, "주문 총액이 일치하지 않습니다.");
        System.out.println("계산된 주문 총액: " + actualTotalPrice);
    }
}