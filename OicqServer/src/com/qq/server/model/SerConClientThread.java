/**
 * function is server to connect a client thread.
 * @author luhui
 *
 */
package com.qq.server.model;

import java.net.*;
import java.util.*;
import com.qq.common.Message;
import com.qq.common.MessageType;

import java.io.*;




public class SerConClientThread extends Thread{

	Socket s;
	
	public SerConClientThread(Socket s) {
		// TODO Auto-generated constructor stub
		this.s=s;
	}
	
	//inform other user
	public void notifyOther(String who) {
		//get all onlineuser
		HashMap hm=ManageClientThread.hm;
		
		Iterator it=hm.keySet().iterator();
		
		while(it.hasNext()) {
			
			Message m=new Message();
			m.setContent(who);
			m.setMesType(MessageType.message_ret_onlineClinet);
			
			String onLineUserId=it.next().toString();
			try {
				
				ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				oos.writeObject(m);
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
	}
	
	
	
	public void run() {
		
		while(true) {
			
			try {
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message ms=(Message)ois.readObject();
				
				
				System.out.println(ms.getSender()+" send message " + ms.getContent() + " to " +ms.getGetter());
				
				//
				if(ms.getMesType().equals(MessageType.message_comm)) {
					//forward to getter;
					//get the hashmap so that socket and id
					SerConClientThread sc=ManageClientThread.getClientThread(ms.getGetter());
					ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(ms);
					
					
				}else if(ms.getMesType().equals(MessageType.message_get_onlineClinet)) {
					
					//return friend list
					String res=ManageClientThread.getAllOnlineUserId();
					Message m=new Message();
					m.setMesType(MessageType.message_ret_onlineClinet);
					m.setContent(res);
					m.setGetter(ms.getSender());
					
					ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m);
					
				}
				
				
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	
}
