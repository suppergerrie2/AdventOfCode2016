package com.suppergerrie2.adventofcode;

import java.security.MessageDigest;

public class Day5 {

	static String _INPUT_ = "ojvtpuvg";

	static String password = "";
	static String[] password2 = new String[8];

	static boolean task2 = true;

	public static void main(String[] args){
		int i = 0;
		boolean running = true;
		while(running){
			if(password.length()>=8){
				running = false;
			} else {
				running = false;
				for(int q = 0; q < password2.length; q++){
					if(password2[q]==null){
						running = true;
					}
				}
			}
			String hash = MD5(_INPUT_ + i);
			if(hash.startsWith("00000")){
				if(!task2){
					password+=hash.charAt(5);
				}
				if(task2){
					if(isInteger(String.valueOf(hash.charAt(5)))){
						int position = Integer.valueOf(String.valueOf(hash.charAt(5)));
						if(position<8 && position >= 0){
							if(password2[position] == null){
								password2[position] = String.valueOf(hash.charAt(6));
							}
						}
					}
				}
			}

			/* Uncomment to see them falling into place :P
			if(i%1000==0){
				System.out.println(i);
				for(int q = 0; q < password2.length; q++){
					System.out.println(password2[q]);
				}
			}
			*/
			i++;
		}
		for(int q = 0; q < password2.length; q++){
			System.out.print(password2[q]);
		}
		System.out.println(password);
	}



	public static String MD5(String encode){
		String hash="";
		try{
			byte[] bytesOfMessage = encode.getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);

			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < thedigest.length; i++){
				sb.append(Integer.toHexString((thedigest[i] & 0xFF) | 0x100).substring(1,3));
			}
			hash=sb.toString();
		} catch(Exception e) {
			return null;
		}
		return hash;
	}

	public static boolean isInteger(String s) {
		return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
		if(s.isEmpty()) return false;
		for(int i = 0; i < s.length(); i++) {
			if(i == 0 && s.charAt(i) == '-') {
				if(s.length() == 1) return false;
				else continue;
			}
			if(Character.digit(s.charAt(i),radix) < 0) return false;
		}
		return true;
	}
}
