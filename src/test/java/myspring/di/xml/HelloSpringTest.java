package myspring.di.xml;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//static import
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class HelloSpringTest {
	@Autowired
	@Qualifier("helloC")
	Hello hello;
	
	@Test
	void helloBeanByConstructor() {
		System.out.println(hello.sayHello());
		assertEquals("Hello 생성자", hello.sayHello());
		
		hello.print();
	}
	
}