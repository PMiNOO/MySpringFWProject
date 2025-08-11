package myspring.di.annot.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import myspring.di.annot.HelloBean;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
		classes = HelloBeanConfig.class,
		loader=AnnotationConfigContextLoader.class)
//@Configuration을 위한 특별한 컨테이너 클래스인 AnnotationConfigApplicationCentext 객체를 로딩해주는 역할
public class HelloBeanConfigTest {
		
	@Autowired
	HelloBean hello;
	
	@Test
	void helloBean전략31() {
		System.out.println(hello.sayHello());
	}
}