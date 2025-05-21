package com.report.service;

import java.util.List;

import org.apache.catalina.filters.ExpiresFilter.XHttpServletResponse;

import com.report.entity.CitizenPlan;
import com.report.request.SearchRequest;

public interface ReportService {

	public List<String> getPlanNames();
	
	public List<String> getPlanStatuses();
	
	public List<CitizenPlan> search(SearchRequest request);
	
	public boolean exportExcel(XHttpServletResponse response) throws Exception;
	
	public boolean exportPdf(XHttpServletResponse response) throws Exception;
}
