package com.poscodx.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @RequestMapping 클래스 단독맵핑
 * SpringMVC 4.x 지원
 * 
 * */


//@Controller
@RequestMapping("/guestbook/")
public class GuestbookController {
	
	@ResponseBody
	@RequestMapping
	public String list() {
		return "GuestbookContorller.list()";
	}
	
	@ResponseBody
	@RequestMapping
	public String delete() {
		return "GuestbookContorller.delte()";
	}
}
