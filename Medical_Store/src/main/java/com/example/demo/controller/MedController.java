package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.demo.Model.Meds;
import com.example.demo.Repository.MedRepository;
@Controller
@RequestMapping("/home")
public class MedController {
	
@Autowired
private MedRepository medRepository;


@GetMapping("/list")
public String getAllMeds(Model model) {
	Iterable<Meds> meds = medRepository.findAll();
	model.addAttribute("meds",meds);
	return "list";
}

@GetMapping("/add")
public String addNewMeds(Model model) {
	model.addAttribute("message", "Enter new medicine details");
	return "new";
}


@PostMapping("/add")
public String addingNewMeds(Meds newMeds,Model model) {

	Meds n = new Meds();
	n.setName(newMeds.getName());
	n.setAmount(newMeds.getAmount());
	
	medRepository.save(n);

	model.addAttribute("message", "New meds " +newMeds.getName() +" have been added successfully");
	return "redirect:list";
}



	
@GetMapping("/delete")
public String deleteUser(Model model) {  
	model.addAttribute("message", "Enter the medicine you want to delete");
	

	return "delete";
}

@PostMapping("/delete")
public String deleteUser(@RequestParam(name="name") String name,Model model) {    
	Optional<Meds> meddetails = medRepository.findByName(name);
	if (meddetails.isPresent()) {
	    Meds meditemdetails = meddetails.get();
	    medRepository.delete(meditemdetails);
	    
	} else {
	    return "error";
	}
	return "redirect:list";
	}


	
	@GetMapping("/search")
	public String searchUser(Model model, @RequestParam(name="name") String name) {  
		
		
		
		
		Optional<Meds> meddetails = medRepository.findByName(name);
		if (meddetails.isPresent()) {
		    Meds meditem = meddetails.get();
		    
		   model.addAttribute("meditem",meditem);
		    
		} 
		else 
		{
		    return "error";
		}
		return "found";
		}

		
	
	@GetMapping("/edit")
	public String updateUser(Model model) {  
		model.addAttribute("message", "Enter the medicine you want to update");
		

		return "update";
	}

	@PostMapping("/edit")	
	
	
	public String updateMeds(@RequestParam String name,@RequestParam Long amount,Model model) {

		Optional<Meds> meddetails = medRepository.findByName(name);
		Meds newdetails = meddetails .get();
		newdetails.setName(name);
		newdetails.setAmount(amount);
		
		medRepository.save(newdetails);
		return "redirect:list";
	}
	
	
	
	
	
	
	
	


}