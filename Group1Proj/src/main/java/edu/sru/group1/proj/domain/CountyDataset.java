package edu.sru.group1.proj.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;


public class CountyDataset {
	
	//Reads the excel files then stores and the unique county names into a Vector. 
	public static Vector<String> countyFile() {
		Vector<String> countyNames = new Vector<String>();
		try {
			
			//Reads the excel file
			File countiesFile = new File("PASchoolsCounties.xlsx");
			FileInputStream fis = new FileInputStream(countiesFile);
			
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0); 
			XSSFRow row = null;
			
			
			int iterator = 1;
			//Iterates over the file - row by row
			while((row = sheet.getRow(iterator)) != null) {
				
				if(countyNames.contains(row.getCell(2).toString())) { 
					//Do nothing
				}
				//If the county name is unique, the else will store value in CountyNames
				else {
					countyNames.addElement(row.getCell(2).toString());
				}
				iterator++;
			}
			
			workbook.close();
			fis.close();
			
		}
		catch (IOException e) {
			System.out.println("Could not find file");
		}
		 Collections.sort(countyNames);
		
		return countyNames;
	}
	
	
	//Taking input of county name and returns a vector containing the districts that belong to the given county.
	public static Vector<String> getDistricts(String countyName) {
		Vector<String> districts = new Vector<String>();
		
		try {
			
			//Reads the excel file
			File countiesFile = new File("PASchoolsCounties.xlsx");
			FileInputStream fis = new FileInputStream(countiesFile);
			
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0); 
			XSSFRow row = null;
			
			
			//Iterates over the file - row by row
			int iterator = 1;
			while((row = sheet.getRow(iterator)) != null) {
				
				//If the countyName string is equal to county name at current row, store district name
				if( countyName.compareTo(row.getCell(2).toString()) == 0) { 
					districts.addElement(row.getCell(0).toString());
				}
				else {
					//Do nothing
				}
				iterator++;
			}
			workbook.close();
			fis.close();
			
		}
		catch (IOException e) {
			System.out.println("Could not find file");
		}
		
		return districts;
	}
	
	
	public static void main(String[] args) {
		
		//countyFile();
		
	}
}




