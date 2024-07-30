package com.dogmall.demo.admin.staticanalysis;

import java.util.List;
import java.util.Map;

public interface StaticAnalysisMapper {
	
	List<Map<String, Object>> monthlySalesStatusByTopCategory(String ord_date);
}
