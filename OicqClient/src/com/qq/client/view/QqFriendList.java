package com.qq.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.qq.client.tools.*;
import com.qq.common.Message;


public class QqFriendList extends JFrame implements ActionListener, MouseListener{

	//after analysing we can determine the pattern card1 friend.
	JPanel jphy1,jphy2,jphy3;
	JButton jphy_jb1,jphy_jb2,jphy_jb3;
	JScrollPane jsp1;
	JLabel[] jbls;
	String owner;
	
	//card2 strangers
	JPanel jpst1,jpst2,jpst3;
	JButton jpst_jb1,jpst_jb2,jpst_jb3;
	JScrollPane jsp2;
	JLabel[] jbls2;
	
	//card3 blacklist
	JPanel jpbl1,jpbl2,jpbl3;
	JButton jpbl_jb1,jpbl_jb2,jpbl_jb3;
	JScrollPane jsp3;
	JLabel[] jbls3;
	
	//set cardlayout
	CardLayout cl;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//QqFriendList qfl=new QqFriendList();
	}
	
	//color or shade the picture of friend list
	public void updateFriend(Message m) {
		
		String onlineFriend[] = m.getContent().split(" ");
		
		for(int i=0;i<onlineFriend.length;i++) {
			
			jbls[Integer.parseInt(onlineFriend[i])].setEnabled(true);
		}
		
	}
	

	public QqFriendList(String ownerId) {
		
		this.owner = ownerId;
		dealcard1();
		dealcard2();
		dealcard3();
		
		cl=new CardLayout();
		this.setLayout(cl);
		this.add(jphy1, "1");
		this.add(jpst1, "2");
		this.add(jpbl1, "3");
		
		//show id on the top of windows
		this.setTitle(ownerId);
		
		this.setSize(250, 600);
		this.setLocation(1000, 50);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public void dealcard1() {
		jphy_jb1=new JButton("My Friends");
		jphy_jb2=new JButton("Strangers ");
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton("Black List");
		jphy_jb3.addActionListener(this);
		//panel 1
		jphy1=new JPanel(new BorderLayout());
		
		//panel 2
		jphy2=new JPanel(new GridLayout(50, 1, 4, 4));
		//initiated 50 friedns;
		jbls=new JLabel[50];
		
		for(int i=0;i<jbls.length;i++) {
			
			jbls[i]=new JLabel(i+"", new ImageIcon("image/5.jpg"),JLabel.LEFT);
			jbls[i].setEnabled(false);
			if(jbls[i].getText().equals(owner)){
				jbls[i].setEnabled(true);
			}
			
			jbls[i].addMouseListener(this);
			
			jphy2.add(jbls[i]);
		}
		
		//panel 3
		jphy3=new JPanel(new GridLayout(2, 1));
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		
		//
		jsp1=new JScrollPane(jphy2);
		
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1);
		jphy1.add(jphy3,"South");
	}
	
	public void dealcard2() {
		jpst_jb2=new JButton("My Friends");
		jpst_jb2.addActionListener(this);
		jpst_jb1=new JButton("Strangers ");
		
		jpst_jb3=new JButton("Black List");
		jpst_jb3.addActionListener(this);
		//panel 1
		jpst1=new JPanel(new BorderLayout());
		
		//panel 2
		jpst2=new JPanel(new GridLayout(20, 1, 4, 4));
		//initiated 50 friedns;
		jbls2=new JLabel[20];
		
		for(int i=0;i<jbls2.length;i++) {
			jbls2[i]=new JLabel(i+"", new ImageIcon("image/6.jpg"),JLabel.LEFT);
			jbls2[i].addMouseListener(this);
			jpst2.add(jbls2[i]);
		}
		
		//panel 3
		jpst3=new JPanel(new GridLayout(2, 1));
		jpst3.add(jpst_jb2);
		jpst3.add(jpst_jb1);
		
		//add jpst2 in jsp1
		jsp2=new JScrollPane(jpst2);
		
		jpst1.add(jpst_jb3,"South");
		jpst1.add(jsp2);
		jpst1.add(jpst3,"North");
	}
	
	public void dealcard3() {
		jpbl_jb2=new JButton("My Friends");
		jpbl_jb2.addActionListener(this);
		jpbl_jb1=new JButton("Strangers ");
		jpbl_jb1.addActionListener(this);
		jpbl_jb3=new JButton("Black List");
		
		//panel 1
		jpbl1=new JPanel(new BorderLayout());
		
		//panel 2
		jpbl2=new JPanel(new GridLayout(10, 1, 4, 4));
		//initiated 50 friedns;
		jbls3=new JLabel[10];
		
		for(int i=0;i<jbls3.length;i++) {
			jbls3[i]=new JLabel(i+"", new ImageIcon("image/7.jpg"),JLabel.LEFT);
			jbls3[i].addMouseListener(this);
			jpbl2.add(jbls3[i]);
		}
		
		//panel 3
		jpbl3=new JPanel(new GridLayout(3, 1));
		jpbl3.add(jpbl_jb2);
		jpbl3.add(jpbl_jb1);
		jpbl3.add(jpbl_jb3);
		
		//add jpbl2 in jsp1
		jsp3=new JScrollPane(jpbl2);
		
		jpbl1.add(jsp3);
		jpbl1.add(jpbl3,"North");
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jphy_jb2) {
			cl.show(this.getContentPane(),"2");
		}
		else if(arg0.getSource()==jphy_jb3) {
			cl.show(this.getContentPane(),"3");
		}
		else if(arg0.getSource()==jpst_jb2) {
			cl.show(this.getContentPane(),"1");
		}
		else if(arg0.getSource()==jpst_jb3) {
			cl.show(this.getContentPane(),"3");
		}
		else if(arg0.getSource()==jpbl_jb2) {
			cl.show(this.getContentPane(),"1");
		}
		else if(arg0.getSource()==jpbl_jb1) {
			cl.show(this.getContentPane(),"2");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		//respond double click, and get the qq number of who was clicked
		if(arg0.getClickCount()==2) {
			//get the qq number
			String friendNum=((JLabel)arg0.getSource()).getText();
			System.out.println("you click number is "+ friendNum);
			QqChat qc=new QqChat(owner,friendNum);
			
			//put chat into ManageQqChat
			ManageQqChat.addQqChat(this.owner+" "+friendNum, qc);
			
			//Thread t=new Thread(qc);
			//t.start();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)arg0.getSource();
		jl.setForeground(Color.RED);
	
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)arg0.getSource();
		jl.setForeground(Color.black);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
