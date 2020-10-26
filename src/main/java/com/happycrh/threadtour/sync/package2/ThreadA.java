package com.happycrh.threadtour.sync.package2;
 
public class ThreadA extends Thread {
	private ObjectService objectService;
 
	public ThreadA(ObjectService objectService) {
		super();
		this.objectService = objectService;
	}
	@Override
	public void run() {
		super.run();
		objectService.objectMethodA();
	}
}