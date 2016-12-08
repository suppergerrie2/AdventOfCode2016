package com.suppergerrie2.adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day7 {

	static List<String> fileContents = new ArrayList<String>();

	static List<String> testSplit = new ArrayList<String>();

	static int validIPs;
	
	public static void main(String[] args){
		fileContents = getFileContents("day7.txt");
		for(int i = 0; i < fileContents.size(); i++){
			checkTLS(fileContents.get(i));
		}
		System.out.println(validIPs);
	}

	public static void checkTLS(String ipv7){
		String[] s = ipv7.split("\\[|\\]");
		boolean valid = false;
		for(int i = 0; i < s.length; i++){
			if(i%2>0){
				s[i]+="]";
			}			
			
			boolean checked = checkABBA(s[i],s[i].contains("]"));
			
			if(checked && s[i].contains("]")){
				return;
			}
			if(!valid){
				valid=checked;
			}
			//System.out.println(s[i]);
		}	
		if(valid){
			validIPs++;
		}
	}

	private static boolean checkABBA(String string, boolean isHypernet) {
		char a = 0;
		char b = 0;
		if(isHypernet){
			string = string.substring(0, string.length()-1);
		}
		System.out.println(string);
		for(int i = 0; i < string.length(); i++){
			if(a==0){
				a=string.charAt(i);
			} else if (b==0){
				b=string.charAt(i);
			} else {
				if(a==b){
					a=b;
					b=string.charAt(i);
				} else { 
					if (i<string.length()-1){
						if(string.charAt(i)==b && string.charAt(i+1)==a){
							System.out.println(a + " " + b + " are abba");
							System.out.println(true);
							return true;
						} else {
							a=b;
							b=string.charAt(i);
						}
					}
				}
			}
		}
		return false;
	}

	public static List<String> getFileContents(String path){
		List<String> contents = new ArrayList<String>();
		File file = new File(path);
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			while (line != null) {
				contents.add(line);
				line = br.readLine();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return contents;
	}		
}
