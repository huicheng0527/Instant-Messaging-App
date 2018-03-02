package com.qq.client.tools;

import java.util.*;


public class ManageClientConServerThread {
	
	private static HashMap hm=new HashMap<String, ClientConServerThread>();
	
	public static void addClientConServerThread(String uid, ClientConServerThread ccst) {
		
		hm.put(uid, ccst);
	}
	
	public static ClientConServerThread getClientConServerThread(String uid) {
		return (ClientConServerThread)hm.get(uid);
	}
}
