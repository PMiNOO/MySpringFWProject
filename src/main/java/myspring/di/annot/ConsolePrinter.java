package myspring.di.annot;

public class ConsolePrinter implements Printer {
	public ConsolePrinter() {
		System.out.println(this.getClass().getName() + " 생성자가 호출됨");
	}
	
	public void print(String message) {
		System.out.println(message);
	}
}
