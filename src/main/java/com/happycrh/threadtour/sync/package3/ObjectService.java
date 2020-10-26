package com.happycrh.threadtour.sync.package3;
 
public class ObjectService {
	private String uname;
	private String pwd;
	String lock=new String();
	public void setUserNamePassWord(String userName,String passWord){
		try {
			synchronized (lock) {
				System.out.println("thread name="+Thread.currentThread().getName()
						+" 进入代码快:"+System.currentTimeMillis());
				uname=userName;
				Thread.sleep(3000);
				pwd=passWord;
				System.out.println("thread name="+Thread.currentThread().getName()
						+" 进入代码快:"+System.currentTimeMillis()+"入参uname:"+uname+"入参pwd:"+pwd);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}