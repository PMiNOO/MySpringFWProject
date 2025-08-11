package myspring.di.xml.config;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import myspring.di.xml.Hello;
import myspring.di.xml.Printer;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HelloConfig.class)
public class HelloConfigTest {
	@Autowired
	Hello hello;
	
	@Resource(name="stringPrinter")
	Printer printer;
	
	@Test
	void hello전략32() {
		//setName() 테스트
		System.out.println(hello.sayHello());
		
		//setPrint() 테스트
		hello.print();
		System.out.println(printer.toString());
		
		//setName() 테스트
		for(String name:hello.getNames()) {
			System.out.println(name);
		}
	}
}