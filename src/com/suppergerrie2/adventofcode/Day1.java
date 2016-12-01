package com.suppergerrie2.adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day1 {
	
	static List<String> fileContents = new ArrayList<String>();

	//0 = n 1 = e 2 = s 3 = w	
	static int direction = 0;
	
	//|p1-q1|+|p2-q2|=antwoord
	
	public static void main(String[] args){
		fileContents = getFileContents("day1.txt");
		for(int i = 0; i < fileContents.size(); i++){
			System.out.println(fileContents.get(i));
		}
		int[] coords = calculateCoords(fileContents.get(0));
		System.out.println(Math.abs(coords[0]-0) + Math.abs(coords[1] - 0));
	}
	
	public static int[] calculateCoords(String input){
		int x = 0;
		int y = 0;
		String[] commands = input.split(", ");
		for(int i = 0; i < commands.length; i++){
			if(commands[i].contains("R")){
				direction++;
				if(direction==4){
					direction = 0;
				}
				int amount = Integer.valueOf(commands[i].substring(1));
				if(direction == 0){
					y+=amount;
				} else if(direction==1){
					x+=amount;
				} else if(direction==2){
					y-=amount;
				} else if(direction==3){
					x-=amount;
				}
				System.out.println("R:" + amount);
				
			} else if(commands[i].contains("L")) {
				direction--;
				if(direction==-1){
					direction = 3;
				}
				int amount = Integer.valueOf(commands[i].substring(1));
				if(direction == 0){
					y+=amount;
				} else if(direction==1){
					x+=amount;
				} else if(direction==2){
					y-=amount;
				} else if(direction==3){
					x-=amount;
				}
				System.out.println("L:" + amount);
			}
		}
		System.out.println("(" + x + "," + y + ")");
		int[] coords = new int[2];
		coords[0] = x;
		coords[1] = y;
		return coords;
	}
	
	public static List<String> getFileContents(String path){
		List<String> contents = new ArrayList<String>();
		File file = new File(path);
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    //StringBuilder sb = new StringBuilder();
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
