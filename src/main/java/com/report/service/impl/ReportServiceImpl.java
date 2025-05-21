package com.report.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.catalina.filters.ExpiresFilter.XHttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.report.entity.CitizenPlan;
import com.report.repository.CitizenPlanRepository;
import com.report.request.SearchRequest;
import com.report.service.ReportService;
import com.report.util.EmailUtils;
import com.report.util.ExcelGenerator;
import com.report.util.PdfGenerator;

import jakarta.servlet.ServletOutputStream;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	private CitizenPlanRepository repo;
	
	@Autowired
	private EmailUtils email;
	
	@Autowired
	private PdfGenerator pdf;
	
	@Autowired
	private ExcelGenerator excel;
	
	@Override
	public List<String> getPlanNames() {
		// TODO Auto-generated method stub
		return repo.getAllPlans();
	}

	@Override
	public List<String> getPlanStatuses() {
		// TODO Auto-generated method stub
		return repo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
	    CitizenPlan entity = new CitizenPlan();

	    if (request.getPlanName() != null && !"".equals(request.getPlanName())) {
	        entity.setPlanName(request.getPlanName());
	    }
	    if (request.getPlanStatus() != null && !"".equals(request.getPlanStatus())) {
	        entity.setPlanStatus(request.getPlanStatus());
	    }
	    if (request.getGender() != null && !"".equals(request.getGender())) {
	        entity.setGender(request.getGender());
	    }

	    if (request.getStartDate() != null && !"".equals(request.getStartDate())) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate startDate = LocalDate.parse(request.getStartDate(), formatter);
	        entity.setPlanStartDate(startDate);
	    }

	    if (request.getEndDate() != null && !"".equals(request.getEndDate())) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate endDate = LocalDate.parse(request.getEndDate(), formatter);
	        entity.setPlanEndDate(endDate);
	    }

	    return repo.findAll(Example.of(entity));
	}

	
	@Override
	public boolean exportExcel(XHttpServletResponse response) throws Exception{
		
		File f=new File("Plans.xls");
		List<CitizenPlan> plans=repo.findAll();
		excel.generate(response,plans,f);
		String subject="Test Mail Subject";
		String body="<h1>Test mail body</h1>";
		String to="sunilkmr.java.dev1611@gmail.com";
		
		email.sendSimpleMessage(to, subject, body,f);
		f.delete();
	    
	        return true;
	}

	@Override
	public boolean exportPdf(XHttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		File f=new File("Plans.pdf");
		List<CitizenPlan> plans=repo.findAll();
		pdf.exportPdf(response, plans,f);
		String subject="Test Mail Subject";
		String body="<h1>Test mail body</h1>";
		String to="sunilkmr.java.dev1611@gmail.com";
		
		email.sendSimpleMessage(to, subject, body,f);
		f.delete();
		return true;
	}

}
