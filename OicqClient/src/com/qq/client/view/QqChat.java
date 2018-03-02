/**
 * since the chat panel always read to send message so to make it runnable
 */

package com.qq.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.qq.common.*;
import java.util.*;
import com.qq.client.model.*;
import java.io.*;
import com.qq.client.tools.*;

import java.net.*;

public class QqChat extends JFrame implements ActionListener {
	
	JTextArea jta;
	JTextField jft;
	JButton jb;
	JPanel jp;
	String ownerId;
	String friendId;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//QqChat qc=new QqChat("1");
	}

	public QqChat(String ownerId, String friend) {
			
		
		this.ownerId=ownerId;
		this.friendId=friend;
		
		jta=new JTextArea();
		
		jft=new JTextField(15);
		jb=new JButton("forward");
		jb.addActionListener(this);
		jp=new JPanel();
		jp.add(jft);
		jp.add(jb);
		
		this.add(jta,"Center");
		this.add(jp, "South");
		
		
		this.setSize(400,300);
		this.setLocation(700, 200);
		this.setTitle(ownerId + " are chatting with " +friend);
		this.setIconImage((new ImageIcon("image/5.jpg")).getImage());
		this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb) {
			//if user click forward
			Message ms=new Message();
			ms.setMesType(MessageType.message_comm);
			ms.setSender(ownerId);
			ms.setGetter(friendId);
			ms.setContent(jft.getText());
			ms.setSendTime(new Date().toString());
			String info=ms.getSender()+" send to "+ms.getGetter() + " content is "+ ms.getContent()+"\r\n";
			this.jta.append(info);
			//send to server
			
			try {
				
				ObjectOutputStream oos=new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(ms);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	//function to show info
	public void showMessage(Message ms) {
		String info=ms.getSender()+" send to "+ms.getGetter() + " content is "+ ms.getContent()+"\r\n";
		this.jta.append(info);
	}
	
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		while(true) {
//			//write input
//			try {
//				ObjectInputStream ois=new ObjectInputStream(QqClientConServer.s.getInputStream());
//				
//				Message ms=(Message)ois.readObject();
//				
//				String info=ms.getSender()+" send to "+ms.getGetter() + " content is "+ ms.getContent()+"\r\n";
//				this.jta.append(info);
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//	}
}
