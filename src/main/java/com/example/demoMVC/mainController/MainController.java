package com.example.demoMVC.mainController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

import com.example.demoMVC.model.Programmer;
import com.example.demoMVC.repository.ProgrammerRepo;


@Controller
@ControllerAdvice
public class MainController {
	
	@Autowired
	ProgrammerRepo pr;
	

	@GetMapping("/home1")
//	@GetMapping({"/home","/"})
	public String homePage1() {
		
//		return "index.html";
		return "allProgrammers.html";
	}
	
	@GetMapping({"/home","/"})
//	@GetMapping("/home")
	public ModelAndView homePage() {
		
		ModelAndView mav=new ModelAndView("index.html");
		mav.addObject("programmer",pr.findAll());
		return mav;
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
	
	@PostMapping("/addProgrammer1")
	public String addProgrammer1(@ModelAttribute Programmer programmer) {
		
		pr.save(programmer);

		return "redirect:/";
	}
	
	

	@PostMapping("/findById")
	public String findById(@RequestParam int pId, Model model) {
		
		
		
		Optional<Programmer> q=pr.findById(pId);

		System.out.println(q);
		
		model.addAttribute("programmer",q.get());
		
		return "ProgrammerInfo.html";
	}
	
	@GetMapping("edit/{pId}")
	public String showEditData(@PathVariable("pId") int pId, Model model) {

		Optional<Programmer> pro=pr.findById(pId);
		
		model.addAttribute("programmer",pro);
		System.out.println(pro);
		
		return "allProgrammers.html";
	}
	
	
	@PostMapping("update/{pId}")
	public String updateData(@PathVariable("pId") int pId, @Valid Programmer programmer, BindingResult result,
	        Model model) {
	        if (result.hasErrors()) {
	        	programmer.setpId(pId);
	            return "allProgrammers.html";
	        }

	        pr.save(programmer);
	        model.addAttribute("programmer", pr.findAll());
		
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
		return "redirect:/home";
	
	}
	
	
	
	
	@GetMapping("edit/directHome")
	public String directHome() {
		return "redirect:/home";
	}
	
//	@GetMapping("/deleteProgrammer")
//	public String deleteProgrammer(@RequestParam int pId) {
//		
//		pr.deleteById(pId);
//		
//		return "redirect:/home";
//	}
	
	@GetMapping("/deleteProgrammer1/{pId}")
	public String deleteProgrammer1(@PathVariable int pId) {
		
		pr.deleteById(pId);
		
		return "redirect:/home";
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
