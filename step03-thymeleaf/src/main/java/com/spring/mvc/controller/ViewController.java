package com.spring.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.mvc.dto.Student;
import com.spring.mvc.dto.Student.Grade;

@Controller
public class ViewController {
	
    @GetMapping(value = "/view-basic")
    public String viewBasic(Model model) {
        
    	model.addAttribute("sid", 1);
    	model.addAttribute("sname", "thyme");
    	model.addAttribute("grade",Grade.JUNIOR);
    	model.addAttribute("tag", "<b>Bold Tag</b>");
    	
        return "view-basic";
    	
    }
    
    @GetMapping(value = "/view-object")
    public String viewObject(Model model) {
    	
        Student thyme = Student.builder()
        						.sid(20246001)
        						.sname("thyme")
        						.build();
        						
        Student leaf = Student.builder()
								.sid(20246002)
								.sname("leaf")
								.build(); 
        
        // Object
        model.addAttribute("thyme", thyme);
        
        // List
        List<Student> students = List.of(thyme, leaf);
        model.addAttribute("students", students);
        
        // Map
        Map<String, Student> studentMap = new HashMap<>();
        studentMap.put("thyme", thyme);
        studentMap.put("leaf", leaf);
        model.addAttribute("studentMap", studentMap);
        
        return "view-object";
    }
	
    @GetMapping(value = "/view-flow")
    public String viewFlow() {
    	
    	//
        Student thyme = Student.builder()
								.sid(20246001)
								.sname("thyme")
								.grade(Grade.JUNIOR)
								.build();
								
		Student leaf = Student.builder()
								.sid(20246002)
								.sname("leaf")
								.grade(Grade.JUNIOR)
								.build(); 
		
		Student view = Student.builder()
								.sid(20246003)
								.sname("view")
								.grade(Grade.SENIOR)
								.build();
        
        return "view-flow";
    }
    
    @GetMapping(value = "/view-apply")
    public String viewApply() {
    	
        Student thyme = Student.builder()
								.sid(20246001)
								.sname("thyme")
								.grade(Grade.JUNIOR)
								.build();
        
        return "view-apply";
    }
    
}

