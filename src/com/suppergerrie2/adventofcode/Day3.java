package com.suppergerrie2.adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

	static List<String> fileContents = new ArrayList<String>();
	private static int answer = 0;

	private static boolean task2 = false;

	private static String[] collums = new String[3];


	public static void main(String[] args){
		fileContents = getFileContents("day3.txt");
		for(int q = 0; q < collums.length; q++){
			collums[q]="";
		}
		for(int i = 0; i < fileContents.size(); i++){
			if(task2){ 
				checkValid2(fileContents.get(i));
			} else {
				checkValid(fileContents.get(i));
			}
		}
		if(task2){
			for(int q = 0; q < collums.length; q++){
				String[] test = collums[q].split(" ");
				for(int i = 0; i < test.length; i++){
					if((i+1)%3==0){						
						checkValid(test[i-2] + " " + test[i-1] + " " + test[i]);
					}
				}
			}
		}
		System.out.println("Answer: " + answer);
	}

	public static void checkValid2(String question){
		String[] parts = question.split(" ");
		int a = -9;
		int b = -9;
		int c = -9;
		for(int i = 0; i < parts.length; i++){
			if(isInteger(parts[i])){
				if(a == -9){
					a=Integer.valueOf(parts[i]);
				} else if(b == -9){
					b=Integer.valueOf(parts[i]);
				} else if(c == -9){
					c=Integer.valueOf(parts[i]);
				}
			}
		}
		collums[0] += a+" ";
		collums[1] += b+" ";
		collums[2] += c+" ";
	}

	public static void checkValid(String question){
		String[] parts = question.split(" ");
		int a = -9;
		int b = -9;
		int c = -9;
		for(int i = 0; i < parts.length; i++){
			if(isInteger(parts[i])){
				if(a == -9){
					a=Integer.valueOf(parts[i]);
				} else if(b == -9){
					b=Integer.valueOf(parts[i]);
				} else if(c == -9){
					c=Integer.valueOf(parts[i]);
				}
			}
		}
		if((a+b)>c){
			if ((a+c)>b){
				if((b+c)>a){					 
					answer++;
				}
			}
		} 
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
