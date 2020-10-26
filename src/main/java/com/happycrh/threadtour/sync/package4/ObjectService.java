package com.happycrh.threadtour.sync.package4;
 
public class ObjectService {
	private String lock=new String();
	public void methodA(){
		try {
			synchronized (lock) {
				System.out.println("a begin");
				Thread.sleep(3000);
				System.out.println("a   end");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public synchronized void methodB(){
		System.out.println("b begin");
		System.out.println("b   end");
	}
}