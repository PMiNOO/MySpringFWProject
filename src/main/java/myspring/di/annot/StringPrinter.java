package myspring.di.annot;

public class StringPrinter implements Printer {
	private StringBuffer buffer = new StringBuffer();
	
	public StringPrinter() {
		System.out.println(this.getClass().getName() + " 생성자가 호출됨");
	}
	
	public void print(String message) {
		this.buffer.append(message);
	}

	public String toString() {
		return this.buffer.toString();
	}
}
