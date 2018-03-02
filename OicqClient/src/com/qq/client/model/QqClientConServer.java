package com.qq.client.model;

//Back end
import java.util.*;
import java.net.*;
import java.io.*;
import com.qq.common.*;
import com.qq.client.tools.*;

public class QqClientConServer {
	
	
	public Socket s;
	//send first request
	public boolean sendLoginInfoToServer(Object o) {
		
		boolean b =false;
		try {
			
			s =new Socket("192.168.1.116", 9999);
			// i/o stream
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			ObjectInputStream ois =new ObjectInputStream(s.getInputStream());
			
			Message ms=(Message)ois.readObject();
			
			if(ms.getMesType().equals("1"))
			{
				//create a uid and a thread connected qq and server
				ClientConServerThread ccst = new ClientConServerThread(s);
				ccst.start();
				
				ManageClientConServerThread.addClientConServerThread(((User)o).getUserId(), ccst);
				
				System.out.println(ms.getMesType().equals("1"));
				System.out.println("the ms get type is "+ ms.getMesType());
				b=true;
			}
			
			//check
			System.out.println("okkk");
			
			//s.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			
		}
		
		return b;
	}
	

}
