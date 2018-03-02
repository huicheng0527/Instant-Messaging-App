/**
 * manage friendlist class
 */

package com.qq.client.tools;

import java.util.*;
import com.qq.client.view.*;
import java.io.*;


public class ManageQqFriendList {
	
	private static HashMap hm= new HashMap<String, QqFriendList>();
	
	public static void addQqFriendList(String uid, QqFriendList qfl) {
		
		hm.put(uid, qfl);
	}
	
	public static QqFriendList getQqFriendList(String uid) {
		return (QqFriendList)hm.get(uid);
	}
}
