package com.qq.client.view;

import javax.swing.*;

import com.qq.client.model.*;
import com.qq.common.User;

import java.awt.*;
import java.awt.event.*;

import java.io.*;
import com.qq.client.tools.*;
import com.qq.common.*;

public class QqClientLogin extends JFrame implements ActionListener {
	
	//define north component
	JLabel jbl1;
	
	//define center, there are 3 jpanle, which controled my itemcard
	JTabbedPane jtp;
	JPanel jp2,jp3,jp4;
	
	JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3,jp2_jbl4;
	JButton jp2_jb1;
	JTextField jp2_jfg;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1,jp2_jcb2;

	//define south
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	public static void main(String arg0[]) {
		QqClientLogin qq=new QqClientLogin();
	}

	public QqClientLogin() throws HeadlessException {
		
		//deal with north
		jbl1=new JLabel(new ImageIcon("image/1.png"));
		
		//deal with center
		jp2=new JPanel(new GridLayout(3, 3));
		jp3=new JPanel();
		jp4=new JPanel();
		
		jp2_jbl1=new JLabel("QQ number",JLabel.CENTER);
		jp2_jbl2=new JLabel("QQ passwo",JLabel.CENTER);
		jp2_jbl3=new JLabel("Forgot passwo",JLabel.CENTER);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jbl4=new JLabel("Apply protect",JLabel.CENTER);
		
		jp2_jb1=new JButton("Delete QQ");
		
		jp2_jfg=new JTextField();
		jp2_jpf=new JPasswordField();
		
		jp2_jcb1=new JCheckBox("yinshen login");
		jp2_jcb2=new JCheckBox("remeber passw");
		
		//add componet
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jfg);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jbl4);
		
		//add to jtp
		jtp = new JTabbedPane();
		jtp.add("QQ login",jp2);
		jtp.add("phone login",jp3);
		jtp.add("mail login",jp4);
		
		//deal with south
		jp1=new JPanel();
		jp1_jb1=new JButton("Login");
		jp1_jb1.addActionListener(this);
		
		
		jp1_jb2=new JButton("Logout");
		jp1_jb3=new JButton("Regist");
		//put button in jpanel
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		
		//initiated jframe
		this.add(jbl1,"North");
		this.add(jtp);
		this.add(jp1,"South");
		this.setSize(350, 240);
		this.setLocation(700, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jp1_jb1) {
			//connect qq server
			QqClientUser qqClientUser=new QqClientUser();
			User u=new User();
			u.setUserId(jp2_jfg.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			
			if(qqClientUser.checkUser(u)) {
				
				//request the info of onlineClient
				try {
					
					QqFriendList qfl=new QqFriendList(u.getUserId());
					ManageQqFriendList.addQqFriendList(u.getUserId(), qfl);
					
					ObjectOutputStream oos=new ObjectOutputStream
							(ManageClientConServerThread.getClientConServerThread(u.getUserId()).getS().getOutputStream());
					
					Message m=new Message();
					m.setMesType(MessageType.message_get_onlineClinet);
					
					//indicate want this id's firends
					m.setSender(u.getUserId());
					
					oos.writeObject(m);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(this,"user name or password wrong");
			}
		}
	}
	
	
	
}
