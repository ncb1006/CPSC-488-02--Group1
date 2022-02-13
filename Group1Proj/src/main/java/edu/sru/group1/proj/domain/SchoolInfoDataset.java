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


public class SchoolInfoDataset {
	
	//Reads the excel, requires string of a district name, returns Vector of type SchoolInformation. Contains information about the schools in the given district
	public static Vector<SchoolInformation> schoolsData(String schoolDistrictName) {
		Vector<SchoolInformation> schoolsInfo = new Vector<SchoolInformation>();
		try {
			
			//Reads the excel file
			File districtsFile = new File("PASchoolDistrictsInfo.xlsx");
			FileInputStream fis = new FileInputStream(districtsFile);
			
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0); 
			XSSFRow row = null;
			int iterator = 0;
			
			//Modifies the string given to match the setup in the PASchoolDistrictsInfo.xlsx file
			String modifiedSchoolDistrictName = schoolDistrictName.replace(" School District", " SD");
	
			//while loop that iterators over each row.
			while((row = sheet.getRow(iterator)) != null) {
					//If the current row at column index 8 is not null
					if(row.getCell(8) != null) {
						//If the districtName matches the district at the current row...
						if(modifiedSchoolDistrictName.contains( row.getCell(7).toString() )) { 
							//Creates an instance of SchoolInformation, adds information, stores information in schoolsInfo vector.
							SchoolInformation school = new SchoolInformation();
							
							school.setSchoolName(row.getCell(6).toString());
							school.setAddress(row.getCell(8).toString());
							
							String modifiedNumStu = row.getCell(18).toString().replace(".00000", "");
							school.setNumStudents(modifiedNumStu);
							school.setLowGrade(row.getCell(4).toString());
							school.setHighGrade(row.getCell(5).toString());		
							
							schoolsInfo.addElement(school);
						}
				}
				
				iterator++;
			}
			
			workbook.close();
			fis.close();
			
		}
		catch (IOException e) {
			System.out.println("Could not find file" );
		}
		
		return schoolsInfo;
	}

	
	
	public static void main(String[] args) {
		
		 //schoolsData("Franklin Area School District");
		
	
		
	}
}




