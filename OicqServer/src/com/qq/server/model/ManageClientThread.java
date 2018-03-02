package com.qq.server.model;

import java.util.*;


public class ManageClientThread {

	public static HashMap hm=new HashMap<String, SerConClientThread>();
	
	//put socket and id into hashmap
	public static void addClientThread(String uid, SerConClientThread ct) {
		hm.put(uid, ct);
	}
	
	public static SerConClientThread getClientThread(String uid) {
		return (SerConClientThread)hm.get(uid);
	}
	
	
	//create a method to return current user id
	public static String getAllOnlineUserId() {
		
		Iterator it=hm.keySet().iterator();
		
		String res="";
		
		while(it.hasNext()){
			
			res+=it.next().toString()+" ";
		}
		return res;
	}
	
}
