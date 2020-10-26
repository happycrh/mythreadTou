package com.happycrh.threadtour.sync.package7;
 
public class MainTest_2 {
	public static void main(String[] args) {
		ObjectService service1=new ObjectService();
		ObjectService service2=new ObjectService();
		ThreadA a=new ThreadA(service1);
		a.setName("A");
		a.start();
		ThreadB b=new ThreadB(service2);
		b.setName("B");
		b.start();

	}
}