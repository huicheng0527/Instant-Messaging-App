/**
 * manage the content of chat
 */
package com.qq.client.tools;

import java.util.*;
import com.qq.client.view.*;


public class ManageQqChat {
	
	private static HashMap hm=new HashMap<String, QqChat>();
	
	public static void addQqChat(String LoginIfAndFriendId, QqChat qc) {
		
		hm.put(LoginIfAndFriendId, qc);
		
	}
	
	public static QqChat getQqChat(String LoginIfAndFriendId) {
		
		return (QqChat)hm.get(LoginIfAndFriendId);
	}
}
