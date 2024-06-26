package com.poscodx.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/*
 * RequestMapping 클래스+매소드
 * 
 * */


@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(value="/joinform", method=RequestMethod.GET)
	public String joinform() {
		return "/WEB-INF/views/joinform.jsp";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVo vo) {
		System.out.println(vo);
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public String update(@RequestParam("n") String name) {
		/*
		 * 만일 n이라는 이름의 파라미터가 없으면
		 * 400 Bad Request Erorr가 발생한다.
		 * 
		 * String, Model, View 만 움직여야해.
		 * n의 값을 입력하지 않으면 
		 * */
		return "usercontroller.update("+name+")";
	}
	
	@ResponseBody
	@RequestMapping("/update2")
	public String update2(@RequestParam(value="n", required=true, defaultValue="") String name)  {
		return "usercontroller.update22("+name+")";
	}
	
	@ResponseBody
	@RequestMapping("/")
	public String list(@RequestParam(value="p", required=true, defaultValue="1") int pageNo)  {
		return "usercontroller.list("+pageNo+")";
	}	
}
