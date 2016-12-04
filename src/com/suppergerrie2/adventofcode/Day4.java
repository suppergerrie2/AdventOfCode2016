package com.suppergerrie2.adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day4 {

	static List<String> fileContents = new ArrayList<String>();

	static int valid;

	public static void main(String[] args){
		fileContents = getFileContents("day4.txt");
		for(int i = 0; i < fileContents.size(); i++){
			checkValid(fileContents.get(i));
		}
		System.out.println(valid);
	}

	public static void checkValid(String room){
		int[] count = new int[64];
		String checksum = room.split("\\[")[1].split("\\]")[0];
		String[] notchecksum = room.split("\\[")[0].split("-");
		String[] values = new String[notchecksum.length-1];
		for(int n = 0; n < notchecksum.length-1; n++){
			values[n] = notchecksum[n];
		}

		for(int q = 0; q < values.length; q++){
			for(int i = 0; i < values[q].length(); i++){
				count[(int)values[q].charAt(i)-97]+=1;
			}
		}

		String newCheck = "";

		for(int z = 0; z<5; z++){
			int id = -1;
			int biggest = -1;
			for(int i = 0; i < count.length; i++){
				if(count[i]>biggest){
					biggest = count[i];
					id = i;
				}
			}
			count[id] = -1;
			newCheck+=(char)(id+97);
		}

		if(checksum.equals(newCheck)){
			String validRoom = "";
			for(int i = 0; i < values.length; i++){
				validRoom+=values[i] + "-";
			}
			shift(validRoom.substring(0, validRoom.length()-1), Integer.valueOf(notchecksum[notchecksum.length-1]));
			valid+=Integer.valueOf(notchecksum[notchecksum.length-1]);
		}
	}

	public static void shift(String validRoom, int id){
		String newName = "";
		for(int i = 0; i < validRoom.length(); i++){

			if(String.valueOf(validRoom.charAt(i)).equals("-")){
				newName+=" ";
			} else {
				String chara = String.valueOf(validRoom.charAt(i));
				for(int q = 0; q<id; q++){
					if(chara.equals("z")){
						chara="a";
					} else {
						chara=String.valueOf((char)((int)(chara.charAt(0))+1));
					}
				}
				newName+=chara;
			}
		}
		if(newName.contains("north")){
			System.out.println(newName + " id= " + id);
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
