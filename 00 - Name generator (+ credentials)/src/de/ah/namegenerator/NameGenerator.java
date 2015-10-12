package de.ah.namegenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * List of names taken from: http://www.quietaffiliate.com/free-first-name-and-last-name-databases-csv-and-sql/
 * 
 * 
 * @author Alex
 *
 */

public class NameGenerator {
	private final static String PATH_TO_FIRST_NAMES = "external/CSV_Database_of_First_Names.csv";
	private final static String PATH_TO_LAST_NAMES = "external/CSV_Database_of_Last_Names.csv";
	
	private static String[] firstNames;
	private static String[] lastNames;
	
	public static String generate(){
		if(firstNames == null || lastNames == null){
			readNamesFromCSVFiles();
		}
		
		String name = "";
		name += firstNames[(int)(firstNames.length*Math.random())];
		name += " ";
		name += lastNames[(int)(lastNames.length*Math.random())];
		return name;
	}
	
	private static void readNamesFromCSVFiles() {
		firstNames = readNamesFromCSVFile(PATH_TO_FIRST_NAMES);
		lastNames = readNamesFromCSVFile(PATH_TO_LAST_NAMES);
	}
	
	private static String[] readNamesFromCSVFile(String pathToCSVFile){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(pathToCSVFile))));
			
			ArrayList<String> names = new ArrayList<String>();
			while(br.ready()){
				names.add(br.readLine());
			}
			
			br.close();
			return names.toArray(new String[names.size()]);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new String[]{"undefined"};
		} catch (IOException e) {
			e.printStackTrace();
			return new String[]{"undefined"};
		}
	}

	public static void main(String[] args){
		System.out.println("Your random name is: " + NameGenerator.generate());
	}
}

