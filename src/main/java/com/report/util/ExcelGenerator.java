package com.report.util;

import java.io.FileOutputStream;
import java.io.File;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.report.entity.CitizenPlan;
import com.report.repository.CitizenPlanRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelGenerator {

	@Autowired
	private CitizenPlanRepository repo;
	
	public void generate(HttpServletResponse response,List<CitizenPlan> record,File file) throws Exception{
		
		Workbook workbook=new HSSFWorkbook();
		Sheet sheet=workbook.createSheet("plans_data");
		Row headerRow=sheet.createRow(0);
		headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Citizen Name");
        headerRow.createCell(2).setCellValue("Plan Name");
        headerRow.createCell(3).setCellValue("Plan Status");
        headerRow.createCell(4).setCellValue("Gender");
        headerRow.createCell(5).setCellValue("Start Date");
        headerRow.createCell(6).setCellValue("End Date");
        headerRow.createCell(7).setCellValue("Benefit Amount");
		
		List<CitizenPlan> records=repo.findAll();
		int dataRowIndex=1;
		for(CitizenPlan plan:records) {
			Row dataRow=sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
            dataRow.createCell(1).setCellValue(plan.getCitizenName());
            dataRow.createCell(2).setCellValue(plan.getPlanName());
            dataRow.createCell(3).setCellValue(plan.getPlanStatus());
            dataRow.createCell(4).setCellValue(plan.getGender());
            if(null!=plan.getPlanStartDate()) {
            	dataRow.createCell(5).setCellValue(plan.getPlanStartDate()+" ");
            }else {
            	dataRow.createCell(5).setCellValue("N/A");
            }
            if(null!=plan.getPlanEndDate()) {
            	dataRow.createCell(6).setCellValue(plan.getPlanEndDate()+" ");
            }else {
            	dataRow.createCell(6).setCellValue("N/A");
            }
            if(null!=plan.getBenefitAmount()) {
            	dataRow.createCell(7).setCellValue(plan.getBenefitAmount());
            }else {
            	dataRow.createCell(7).setCellValue("N/A");
            }
			dataRowIndex++;
		}
		FileOutputStream fos=new FileOutputStream((file));
		workbook.write(fos);
		fos.close();
		ServletOutputStream outputStream=response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
	        
	}
}
