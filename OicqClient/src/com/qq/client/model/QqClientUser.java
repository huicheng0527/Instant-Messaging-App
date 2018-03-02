package com.qq.client.model;

import com.qq.common.*;

public class QqClientUser {
	
	public boolean checkUser(User u)
	{
		System.out.println("check is run");
		boolean d = new QqClientConServer().sendLoginInfoToServer(u);
		System.out.println(d);
		return d;
		
	}
}
