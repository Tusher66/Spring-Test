package com.example.demoMVC.mainController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demoMVC.model.Programmer;
import com.example.demoMVC.repository.ProgrammerRepo;


@Controller
@ControllerAdvice
public class MainController {
	
	@Autowired
	ProgrammerRepo pr;
	
//	@RequestMapping("/")
	
	@GetMapping("/home")
	public String homePage() {
		
		return "index.html";
//		return "HomePage.html";
	}
	
	
	
	@PostMapping("/addProgrammer")
//	public ModelAndView addProgrammer(@ModelAttribute ("p") Programmer programmer) {
	public String addProgrammer(@ModelAttribute ("p") Programmer programmer) {
		
		
//		ModelAndView mn= new ModelAndView();
//		mn.setViewName("ProgrammerInfo.html");
//		return mn;
		pr.save(programmer);
//		return "ProgrammerInfo.html";
		return "redirect:/home";
	}
	
	

	@PostMapping("/findById")
	public String findById(@RequestParam int pId, Model model) {
		
		System.out.println(pId);
		
		Optional<Programmer> q=pr.findById(pId);

		model.addAttribute("programmer",q.get());
		
		return "ProgrammerInfo.html";
	}
	
	@GetMapping("/deleteProgrammer")
	public String deleteProgrammer(@RequestParam int pId) {
		
		pr.deleteById(pId);
		
		return "redirect:/home";
	}
	
	
	@PostMapping("/updateProgrammer")

	public String updateProgrammer(@ModelAttribute Programmer programmer) {
		
//		Optional<Programmer> w=pr.findById(programmer.getpId());
//		w.setpName(programmer.getpName());
		Programmer p=pr.getOne(programmer.getpId());
		p.setpName(programmer.getpName());
		p.setpLang(programmer.getpLang());
		
		pr.save(p);
		return "ProgrammerInfo.html";
	
	}
	
//	@RequestMapping("/addProgrammer")
//	public String addProgrammer(@RequestParam int pId,@RequestParam String pName, 
//			@RequestParam String pLang , Model model) {
//		
//		
//		System.out.println(pId+pName+pLang);
//		
//		model.addAttribute("pId",pId);
//		model.addAttribute("pName",pName);
//		model.addAttribute("pLang",pLang);
//
//		return "ProgrammerInfo.html";
//	}
	
	
//	@RequestMapping("/addProgrammer")
//	public ModelAndView addProgrammer(@RequestParam ("pId") int i,@RequestParam ("pName") String n, 
//			@RequestParam("pLang") String l) {
//		
//		System.out.println(i+" "+n+" "+l);
//		
//		ModelAndView mn= new ModelAndView();
//		mn.setViewName("ProgrammerInfo.html");
//		mn.addObject("pId",i);
//		mn.addObject("pName",n);
//		mn.addObject("pLang",l);
//		
//		return mn;
//	}
	
// ModelAttrebuit sob data ka alada vaba na pathia akta object a pathanor niom

//	@RequestMapping(value="/addProgrammer", method=RequestMethod.POST)
	
	
	
//	@GetMapping ("/allProgrammer")
//	public String allProgrammer(Model m) {
//		List<Programmer> p=new ArrayList<Programmer>();
//		
//		p.add(new Programmer(101,"Tusher","Java"));
//		p.add(new Programmer(102,"Rana","Angular"));
//		p.add(new Programmer(103,"Sifat","React"));
//		
//		m.addAttribute("programmers", p);
//		
//		
//		return "allProgrammers.html";
//	}
//	
	
	
	
	
}
