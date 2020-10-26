package com.happycrh.threadtour.sync.package1;
 
public class MainTest {
	public static void main(String[] args) {
		ObjectService service=new ObjectService();
		//ObjectService service2=new ObjectService();
		ThreadA a=new ThreadA(service);
		a.setName("a");
		a.start();
		ThreadB b=new ThreadB(service);
		//ThreadB b=new ThreadB(service2);
		b.setName("b");
		b.start();
	}
}