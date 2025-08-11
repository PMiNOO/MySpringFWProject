package myspring.di.annot;

import org.springframework.stereotype.Component;

@Component("ConsolePrinterBean")
public class ConsolePrinterBean implements PrinterBean {
	public ConsolePrinterBean() {
		System.out.println(this.getClass().getName() + " 생성자가 호출됨");
	}
	
	public void print(String message) {
		System.out.println(message);
	}
}
