package com.happycrh.threadtour.sync.package6;
 
public class ThreadB extends Thread {
	@Override
	public void run() {
		ObjectService.methodB();
	}
}