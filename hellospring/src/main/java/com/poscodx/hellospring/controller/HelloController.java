package com.poscodx.hellospring.controller;



import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	

	@RequestMapping("/hello")
	public String hello() {
		return "/WEB-INF/views/hello.jsp";
	}
	
	@RequestMapping("/hello2")
	public String hello2(String name) {
		System.out.println("name: "+ name);
		return "/WEB-INF/views/hello.jsp";
	}
	
	
	@RequestMapping("/hello3")
	public ModelAndView hello3(String name) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("name",name);
		mav.setViewName("/WEB-INF/views/hello3.jsp");
		return mav;
	}

	
	@RequestMapping("/hello4")
	public String hello4(String name, Model model) {
		model.addAttribute("name",name);
		//ModelAndView mav = new ModelAndView();
		//mav.addObject("name2",name);
		
		//없으면 널
		return "/WEB-INF/views/hello3.jsp";
	}
	
	@ResponseBody
	@RequestMapping("/hello5")
	public String hello5() {
		return "<h1>마우 doesnt working shit</h1>";
	}
	
	@RequestMapping("/hello6")
	public String hello6() {
		return "redirect:/hello";
	}
	
	
	public void hello7(HttpServletResponse response) throws IOException {
		response.getWriter().print("<h1>http response</h1>)");
	}
}


