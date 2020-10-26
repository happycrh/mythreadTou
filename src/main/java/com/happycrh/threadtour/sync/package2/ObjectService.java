package com.happycrh.threadtour.sync.package2;
 
public class ObjectService {
	public void objectMethodA(){
		System.out.println("run----objectMethodA");
	}
	/*public synchronized void objectMethodA(){
		System.out.println("run----objectMethodA");
	}*/
	public void objectMethodB(){
		synchronized (this) {
			try {
				for (int i = 1; i <= 10; i++) {
					System.out.println("synchronized thread name:"+Thread.currentThread().getName()+"-->i="+i);
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
		}
	}
}