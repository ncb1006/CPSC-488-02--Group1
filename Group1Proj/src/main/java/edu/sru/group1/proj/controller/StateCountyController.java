package edu.sru.group1.proj.controller;

import java.util.*;
import org.springframework.stereotype.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



import edu.sru.group1.proj.domain.StateCounty;
import edu.sru.group1.proj.domain.CountyDataset;
import edu.sru.group1.proj.domain.SchoolInfoDataset;
import edu.sru.group1.proj.domain.SchoolInformation;
import edu.sru.group1.proj.repository.StateCountyRepository;

@Controller
public class StateCountyController {
	
	//Stores the id value for the selected county (used in "/county/{schoolDistrict}" page)
	private long currentId;
	//Setter and Getter for currentId.
	public long getCurrentId() {
		return currentId;
	}

	public void storeCurrentId(long id) {
		this.currentId = id;
	}

	//set up a StateCountyRepository variable
	private StateCountyRepository stateCountyRepository;
	//private StateCountyRepository userRepository2;
    
	//create an StateCountyRepository instance - instantiation (new) is done by Spring
    public StateCountyController(StateCountyRepository stateCountyRepository) {
		this.stateCountyRepository = stateCountyRepository;
		
		//Creates a vector that stores all of the unique counties. 
		CountyDataset data = new CountyDataset();
		Vector<String> counties = data.countyFile();
		
		//While loop - loops thought the counties vector, storing county information (States, county name, and district(s) )
					//Saves each StateCounty instance into stateCountyRepository
		int i = 0;
		while(i < counties.size()) {
			StateCounty county = new StateCounty();
			county.setState("Pennsylvania");
			county.setCounty(counties.get(i));
			//Vector<String> dists = data.getDistrict(counties.get(i));
			county.setDistrict( data.getDistricts(counties.get(i)) );
			this.stateCountyRepository.save(county);
			i++;
		}
	} 
    
    //Default mapping to select county.
    @RequestMapping({"/"})
    public String showUserList(Model model) {
        model.addAttribute("counties", stateCountyRepository.findAll());
        return "county";
    }
    
    //Mapping to select school district
    @RequestMapping({"/{id}"})
    public String selectDistrict(@PathVariable("id") long id, Model model) {
    	StateCounty county = stateCountyRepository.findById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("districts", county);
        storeCurrentId(id);
        return "district";
    }
    
    
    //Mapping that displays school district information.
    @RequestMapping({"/county/{schoolDistrict}"})
    public String selectDistrict(@PathVariable("schoolDistrict") String district, Model model) {
    	StateCounty county = stateCountyRepository.findById( getCurrentId() )
    			.orElseThrow(() -> new IllegalArgumentException("Invalid district:" + district));
        //Attribute stores an instance of StateCounty pertaining to selected district
    	model.addAttribute("districts", county);
    	//Attribute stores string of school district
        model.addAttribute("districtName", district);
        
        SchoolInfoDataset data = new SchoolInfoDataset();
        
        //Takes the string input of school district from mapping, calls method in SchoolInfoDataset.
        	//Returns the a vector of type contains SchoolInformation which contains each schools information.
		Vector<SchoolInformation> schoolsData = data.schoolsData(district);
		schoolsData.remove(0);
		model.addAttribute("schoolsInfo", schoolsData);
        
        return "district-info";
    }
  

}