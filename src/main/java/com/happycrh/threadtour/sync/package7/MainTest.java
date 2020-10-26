package com.happycrh.threadtour.sync.package7;
 
public class MainTest {
	public static void main(String[] args) {
		ObjectService service=new ObjectService();
		ThreadA a=new ThreadA(service);
		a.setName("A");
		a.start();
		ThreadB b=new ThreadB(service);
		b.setName("B");
		b.start();
	}
}