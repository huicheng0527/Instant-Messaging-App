package com.qq.server.model;
//qq server; listen qq connect 

import java.net.*;
import java.io.*;
import java.util.*;

import com.qq.common.Message;
import com.qq.common.User;

public class MyServer {

	public MyServer() {
			
		
		try {
			
			System.out.println("Server ok in port9999");
			//in port9999 listen
			ServerSocket ss=new ServerSocket(9999); 
			//wait for client connect
			while(true) {
				Socket s=ss.accept();
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User u=(User)ois.readObject();
				System.out.println("server receive id is "+ u.getUserId()+" password is "+u.getPasswd());
				
				Message m=new Message();
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());

				
				if(u.getPasswd().equals("123456")) {
					//return sucessuly download
					System.out.println("set to 1");
					System.out.println(u.getPasswd().equals("123456"));
					m.setMesType("1");
					oos.writeObject(m);
					
					//new a thread
					SerConClientThread scct=new SerConClientThread(s);
					ManageClientThread.addClientThread(u.getUserId(), scct);
					scct.start();
					
					//and let online user know someone is login
					scct.notifyOther(u.getUserId());
					

				}else {
					System.out.println("set to 2");
					m.setMesType("2");
					System.out.println(m.getMesType());
					oos.writeObject(m);
					s.close();


				}
				
				//ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				//oos.writeObject(m);
				//s.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
