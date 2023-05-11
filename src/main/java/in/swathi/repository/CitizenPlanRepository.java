package in.swathi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.swathi.entity.CitizenPlan;

public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Integer> {
		@Query(value=" select distinct(planName) from CitizenPlan")
		public List<String>getplaname();
		
		@Query(value="select distinct(planStatus)from CitizenPlan")
		public List <String>getplanStatus();
		
	}
