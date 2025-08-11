package myspring.di.xml.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import myspring.di.xml.ConsolePrinter;
import myspring.di.xml.Hello;
import myspring.di.xml.Printer;
import myspring.di.xml.StringPrinter;

@Configuration
@PropertySource(value = "classpath:values.properties")
public class HelloConfig {
	@Autowired
	Environment environment;
	
	@Bean
	public List<String> nameList() {
	    return Arrays.asList("Java", "SpringFW", "SpringBoot");
	}
	
	@Bean
	public Printer stringPrinter() {
		return new StringPrinter();
	}
	
	@Bean
	public Printer consolePrinter() {
		return new ConsolePrinter();
	}
	
	@Bean
	public Hello hello() {
		Hello hello = new Hello();
		//스프링 == environment.getProperty("myname11")
		hello.setName(environment.getProperty("myname11")); 
		hello.setPrinter(stringPrinter());
		hello.setNames(nameList());
		return hello;
	}
}