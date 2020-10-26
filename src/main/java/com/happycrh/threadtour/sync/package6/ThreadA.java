package com.happycrh.threadtour.sync.package6;
 
public class ThreadA extends Thread {
 
	@Override
	public void run() {
		ObjectService.methodA();
	}
}