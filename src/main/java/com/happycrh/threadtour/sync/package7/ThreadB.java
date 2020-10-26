package com.happycrh.threadtour.sync.package7;
 
public class ThreadB extends Thread {
	private ObjectService objectService;
 
	public ThreadB(ObjectService objectService) {
		super();
		this.objectService = objectService;
	}
	@Override
	public void run() {
		objectService.methodB();
	}
}