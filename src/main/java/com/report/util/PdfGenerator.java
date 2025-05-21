package com.report.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.catalina.filters.ExpiresFilter.XHttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.report.entity.CitizenPlan;
import com.report.repository.CitizenPlanRepository;


@Component
public class PdfGenerator {
	
	@Autowired
	private CitizenPlanRepository repo;

	public void exportPdf(XHttpServletResponse response,List<CitizenPlan> records,File f) throws Exception{
		
		Document document=new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f));
		document.open();
		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTitle.setSize(20);
		Paragraph p=new Paragraph("Citizen Plan Info", fontTitle);
		PdfPTable table=new PdfPTable(6);
		table.addCell("Id");
		table.addCell("Citizen Name");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Gender");
		table.addCell("Start Date");
		table.addCell("End Date");
		
		List<CitizenPlan> plans=repo.findAll();
		for(CitizenPlan plan:plans) {
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			table.addCell(plan.getGender());
			table.addCell(plan.getPlanStartDate()+"");
			table.addCell(plan.getPlanEndDate()+"");
		}
		
		document.add(p);
		document.close();
	}
}
