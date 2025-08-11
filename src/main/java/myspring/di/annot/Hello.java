package myspring.di.annot;

import java.util.List;

public class Hello {
	String name;
	Printer printer;
	List<String> names;

	public Hello() {
		System.out.println(this.getClass().getName() + " 생성자가 호출됨");
	}

	public Hello(String name, Printer printer) {
		System.out.println(this.getClass().getName() + " Overloaded 생성자가 호출됨");
		this.name = name;
		this.printer = printer;
	}

	public List<String> getNames() {
		return this.names;
	}

	public void setNames(List<String> list) {
		this.names = list;
	}

	//setFirstName
	public void setName(String name) {
		System.out.println(this.getClass().getName() + "setName() 호출됨 " + name);
		this.name = name;
	}

	public void setPrinter(Printer printer) {
		System.out.println(this.getClass().getName() + "setPrinter() 호출됨 " + 
				printer.getClass().getName());
		this.printer = printer;
	}

	public String sayHello() {
		return "Hello " + name;
	}

	public void print() {
		this.printer.print(sayHello());
	}

}