package com.poscodx.aoptest.service;

import org.springframework.stereotype.Service;

import com.poscodx.aoptest.vo.ProductVo;

@Service
public class ProductService {
	public ProductVo find(String name) {
		System.out.println("[ProductService] finding...");
		if(1-1==0) {
			throw new RuntimeException("ProductService.find() Exception");
			//exception 나면 return을 안함
		}
		return new ProductVo(name);
		
	}
}
