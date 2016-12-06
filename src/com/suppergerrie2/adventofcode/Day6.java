package com.suppergerrie2.adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day6 {

	static List<String> fileContents = new ArrayList<String>();
	static int[][] charAmount;
	static String[] positionString;

	public static void main(String[] args){
		fileContents = getFileContents("day6.txt");
		positionString = new String[fileContents.get(0).length()];
		for(int i = 0; i < positionString.length; i++){
			positionString[i] = "";
		}
		for(int i = 0; i < fileContents.size(); i++){
			splitInPosition(fileContents.get(i));
		}
		charAmount = new int[fileContents.get(0).length()][64];
		for(int i = 0; i < positionString.length; i++){
			countChars(positionString[i], i);
		}

		decodeTask1();
		decodeTask2();
	}

	public static void decodeTask1(){
		String newMessage = "";
		for(int z = 0; z<charAmount.length; z++){
			int id = -1;
			int biggest = -1;

			for(int i = 0; i < charAmount[z].length; i++){
				if(charAmount[z][i]>biggest){
					biggest=charAmount[z][i];
					id=i;
				}
			}

			newMessage+=(char)(id+97);
		}
		System.out.println("Task1: " + newMessage);
	}

	public static void decodeTask2(){
		String newMessage = "";
		for(int z = 0; z<charAmount.length; z++){
			int id = -1;
			int smallest = 1000;

			for(int i = 0; i < charAmount[z].length; i++){
				if(charAmount[z][i]>0){
					if(charAmount[z][i]<smallest){
						smallest=charAmount[z][i];
						id=i;
					}
				}
			}
			newMessage+=(char)(id+97);
		}
		System.out.println("Task2: " + newMessage);
	}

	public static void splitInPosition(String toSplit){
		for(int i = 0; i < toSplit.length(); i++){
			positionString[i] += toSplit.charAt(i);
		}
	}

	public static void countChars(String s, int i){
		for(int z = 0; z < s.length(); z++){
			charAmount[i][(int)s.charAt(z)-97] += 1;
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
}
