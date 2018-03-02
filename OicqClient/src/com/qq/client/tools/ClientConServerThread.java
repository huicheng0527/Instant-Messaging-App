/**
 * it is a connection thread between client and server
 */
package com.qq.client.tools;

import java.io.*;
import java.net.*;

import com.qq.client.view.QqChat;
import com.qq.client.view.*;
import com.qq.common.*;



public class ClientConServerThread extends Thread{

	private Socket s;
	
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public ClientConServerThread(Socket s) {
		this.s=s;
		
	}
	
	public void run() {
		
		while(true) {
			
			try {
				
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message ms=(Message)ois.readObject();
				
				
				//System.out.println("read from server info" + ms.getContent() + " from " + ms.getSender() +" to "+ ms.getGetter());
				
				//determine message type
				if(ms.getMesType().equals(MessageType.message_comm)) {
					//show info in chat windows
					QqChat qc=ManageQqChat.getQqChat(ms.getGetter()+" "+ms.getSender());
					qc.showMessage(ms);
				}else if(ms.getMesType().equals(MessageType.message_ret_onlineClinet)) {
					
					
					System.out.println("read from server info" + ms.getContent() + " from " + ms.getSender() +" to "+ ms.getGetter());
					
					String con=ms.getContent();
					String friends[]=con.split(" ");
					String getter=ms.getGetter();
					
					//modify correlated friend list
					QqFriendList qfl=ManageQqFriendList.getQqFriendList(getter);
					
					if(qfl!=null) {
						qfl.updateFriend(ms);
					}
					
					
				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}
}
