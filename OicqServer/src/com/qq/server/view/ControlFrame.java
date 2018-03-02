package com.qq.server.view;

import javax.swing.*;

import com.qq.server.model.MyServer;

import java.awt.*;
import java.awt.event.*;

//Control panel in Server, start server and close, manage users and delete

public class ControlFrame extends JFrame implements ActionListener{
	
	JPanel jp1;
	JButton jb1,jb2;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ControlFrame cf=new ControlFrame();
	}
	
	public ControlFrame() {
		
		jp1=new JPanel();
		jb1=new JButton("open server");
		jb1.addActionListener(this);
		jb2=new JButton("close server");
		jp1.add(jb1);
		jp1.add(jb2);
		
		this.add(jp1);
		this.setSize(500,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb1) {
			new MyServer();
			System.out.println("Open server");
		}
	}

}
