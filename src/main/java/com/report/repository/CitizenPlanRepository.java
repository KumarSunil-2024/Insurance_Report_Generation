package com.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.report.entity.CitizenPlan;

public interface CitizenPlanRepository extends JpaRepository<CitizenPlan,Integer>{
	
	@Query("select distinct(planName) from CitizenPlan")
	public List<String> getAllPlans();
	
	@Query("select distinct(planStatus) from CitizenPlan")
	public List<String> getPlanStatus();

}
