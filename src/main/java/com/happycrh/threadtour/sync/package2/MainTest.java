package com.happycrh.threadtour.sync.package2;
 
public class MainTest {
	public static void main(String[] args) throws InterruptedException {
		ObjectService service=new ObjectService();
		ThreadB b=new ThreadB(service);
		b.start();
		Thread.sleep(2000);
		ThreadA a=new ThreadA(service);
		a.start();
	}
}