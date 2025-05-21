package com.report.runner;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.report.entity.CitizenPlan;
import com.report.repository.CitizenPlanRepository;


@Component
public class DataLoader implements ApplicationRunner{

	@Autowired
	private CitizenPlanRepository repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		CitizenPlan p1 = new CitizenPlan();
		 p1.setCitizenName("Sunil");
	        p1.setGender("Male");
	        p1.setPlanName("Food Assistance");
	        p1.setPlanStatus("Approved");
	        p1.setPlanStartDate(LocalDate.now());
	        p1.setPlanEndDate(LocalDate.now().plusMonths(6));
	        p1.setBenefitAmount(5000.0);

	        CitizenPlan p2 = new CitizenPlan();
	        p2.setCitizenName("Anjali");
	        p2.setGender("Female");
	        p2.setPlanName("Medical Aid");
	        p2.setPlanStatus("Denied");
	        p2.setDenialReason("Income too high");

	        CitizenPlan p3 = new CitizenPlan();
	        p3.setCitizenName("Ravi");
	        p3.setGender("Male");
	        p3.setPlanName("Education Grant");
	        p3.setPlanStatus("Terminated");
	        p3.setPlanStartDate(LocalDate.now().minusMonths(3));
	        p3.setPlanEndDate(LocalDate.now().plusMonths(3));
	        p3.setTerminatedDate(LocalDate.now());
	        p3.setTerminationRsn("Policy breach");

	        CitizenPlan p4 = new CitizenPlan();
	        p4.setCitizenName("Neha");
	        p4.setGender("Female");
	        p4.setPlanName("Housing Scheme");
	        p4.setPlanStatus("Approved");
	        p4.setPlanStartDate(LocalDate.now());
	        p4.setPlanEndDate(LocalDate.now().plusMonths(12));
	        p4.setBenefitAmount(15000.0);

	        CitizenPlan p5 = new CitizenPlan();
	        p5.setCitizenName("Ajay");
	        p5.setGender("Male");
	        p5.setPlanName("Unemployment Aid");
	        p5.setPlanStatus("Denied");
	        p5.setDenialReason("Incomplete documents");

	        CitizenPlan p6 = new CitizenPlan();
	        p6.setCitizenName("Priya");
	        p6.setGender("Female");
	        p6.setPlanName("Child Care Support");
	        p6.setPlanStatus("Approved");
	        p6.setPlanStartDate(LocalDate.now());
	        p6.setPlanEndDate(LocalDate.now().plusMonths(18));
	        p6.setBenefitAmount(8000.0);

	        CitizenPlan p7 = new CitizenPlan();
	        p7.setCitizenName("Rahul");
	        p7.setGender("Male");
	        p7.setPlanName("Medical Aid");
	        p7.setPlanStatus("Terminated");
	        p7.setPlanStartDate(LocalDate.now().minusMonths(2));
	        p7.setPlanEndDate(LocalDate.now().plusMonths(4));
	        p7.setTerminatedDate(LocalDate.now());
	        p7.setTerminationRsn("Moved to another state");

	        CitizenPlan p8 = new CitizenPlan();
	        p8.setCitizenName("Sita");
	        p8.setGender("Female");
	        p8.setPlanName("Old Age Pension");
	        p8.setPlanStatus("Approved");
	        p8.setPlanStartDate(LocalDate.now());
	        p8.setPlanEndDate(LocalDate.now().plusYears(1));
	        p8.setBenefitAmount(10000.0);

	        CitizenPlan p9 = new CitizenPlan();
	        p9.setCitizenName("Vikas");
	        p9.setGender("Male");
	        p9.setPlanName("Youth Empowerment");
	        p9.setPlanStatus("Denied");
	        p9.setDenialReason("Not eligible by age");

	        CitizenPlan p10 = new CitizenPlan();
	        p10.setCitizenName("Kavita");
	        p10.setGender("Female");
	        p10.setPlanName("Housing Scheme");
	        p10.setPlanStatus("Approved");
	        p10.setPlanStartDate(LocalDate.now());
	        p10.setPlanEndDate(LocalDate.now().plusMonths(9));
	        p10.setBenefitAmount(12000.0);

	        repo.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
		
	}

}
