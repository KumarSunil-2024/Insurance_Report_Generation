package com.report.controller;

import java.util.List;

import org.apache.catalina.filters.ExpiresFilter.XHttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.report.entity.CitizenPlan;
import com.report.request.SearchRequest;
import com.report.service.ReportService;
import com.report.util.EmailUtils;


@CrossOrigin("*")
@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private ReportService ser;
	
	
	@GetMapping("/pdf")
	public void PdfExport(XHttpServletResponse response) throws Exception{
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment; filename=plans.pdf");
		ser.exportPdf(response); 
	}
	
	@GetMapping("/excel")
	public void ExcelExport(XHttpServletResponse response) throws Exception{
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment; filename=plans.xls");
		ser.exportExcel(response);
	}
	
	@GetMapping("/plans")
	public ResponseEntity<List<String>> getAllPlans(){
		return ResponseEntity.ok(ser.getPlanNames());
	}
	
	@GetMapping("/statuses")
    public ResponseEntity<List<String>> getStatuses() {
        return ResponseEntity.ok(ser.getPlanStatuses());
    }
	
	@PostMapping("/search")
	public ResponseEntity<List<CitizenPlan>> search(@RequestBody SearchRequest req){
		return ResponseEntity.ok(ser.search(req));
	}
	
}
