package myspring.di.annot;

import org.springframework.stereotype.Component;

@Component("stringPrinterBean")
public class StringPrinterBean implements PrinterBean {
	private StringBuffer buffer = new StringBuffer();
	
	public StringPrinterBean() {
		System.out.println(this.getClass().getName() + " 생성자가 호출됨");
	}
	
	public void print(String message) {
		this.buffer.append(message);
	}

	public String toString() {
		return this.buffer.toString();
	}
}
