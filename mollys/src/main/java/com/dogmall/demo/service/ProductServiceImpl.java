package com.dogmall.demo.service;

import org.springframework.stereotype.Service;

import com.dogmall.demo.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductMapper productMapper;
}
