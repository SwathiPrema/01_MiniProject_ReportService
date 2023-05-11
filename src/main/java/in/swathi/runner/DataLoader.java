package in.swathi.runner;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import antlr.collections.List;
import in.swathi.entity.CitizenPlan;
import in.swathi.repository.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private CitizenPlanRepository repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		CitizenPlan P1 = new CitizenPlan();
		P1.setCitizenName("madhu");
		P1.setGender("male");
		P1.setPlanName("cash");
		P1.setPlanStatus("Approved");
		P1.setPlanStartDate(LocalDate.now());
		P1.setPlanEndDate(LocalDate.now().plusMonths(6));
		P1.setBenifitAmt(5000.0);
		
		CitizenPlan P2 = new CitizenPlan();
		P2.setCitizenName("swathi");
		P2.setGender("female") ;
		P2.setPlanName("cash");
		P2.setPlanStatus("Denied");
		P2.setDanialReason("Rental income");
		
		
		CitizenPlan P3 = new CitizenPlan();
		P3.setCitizenName("Tinku");
		P3.setGender("male") ;
		P3.setPlanName("cash");
		P3.setPlanStatus("Terminated");
		P3.setPlanStartDate(LocalDate.now());
		P3.setPlanEndDate(LocalDate.now().plusMonths(6));
		P3.setBenifitAmt(5000.0);
		P3.setTerminatedDate(LocalDate.now());
		P3.setTerminationRsn("Employed");
		
		
		// food plan Data 
		
		CitizenPlan P4 = new CitizenPlan();
		P4.setCitizenName("sri");
		P4.setGender("female") ;
		P4.setPlanName("Food");
		P4.setPlanStatus("Approved");
		P4.setPlanStartDate(LocalDate.now());
		P4.setPlanEndDate(LocalDate.now().plusMonths(6));
		P4.setBenifitAmt(6000.0);
		
		
		
		CitizenPlan P5 = new CitizenPlan();
		P5.setCitizenName("siri");
		P5.setGender("female") ;
		P5.setPlanName("Food");
		P5.setPlanStatus("Denied");
		P5.setDanialReason("property income");
		
		
		CitizenPlan P6 = new CitizenPlan();
		P6.setCitizenName("Chintu");
		P6.setGender("male") ;
		P6.setPlanName("Food");
		P6.setPlanStatus("Terminated");
		P6.setPlanStartDate(LocalDate.now());
		P6.setPlanEndDate(LocalDate.now().plusMonths(6));
		P6.setBenifitAmt(5000.0);
		P6.setTerminatedDate(LocalDate.now());
		P6.setTerminationRsn("Employed");
		
		
		// medical plan Data 
		
		CitizenPlan P7 = new CitizenPlan();
		P7.setCitizenName("madhu");
		P7.setGender("female") ;
		P7.setPlanName("medical");
		P7.setPlanStatus("Approved");
		P7.setPlanStartDate(LocalDate.now());
		P7.setPlanEndDate(LocalDate.now().plusMonths(6));
		P7.setBenifitAmt(5000.0);
		
		
		
		CitizenPlan P8 = new CitizenPlan();
		P8.setCitizenName("swathi");
		P8.setGender("female") ;
		P8.setPlanName("medical");
		P8.setPlanStatus("Denied");
		P8.setDanialReason("Rental income");
		
		
		CitizenPlan P9 = new CitizenPlan();
		P9.setCitizenName("Bharath");
		P9.setGender("male") ;
		P9.setPlanName("medical");
		P9.setPlanStatus("Terminated");
		P9.setPlanStartDate(LocalDate.now());
		P9.setPlanEndDate(LocalDate.now().plusMonths(6));
		P9.setBenifitAmt(5000.0);
		P9.setTerminatedDate(LocalDate.now());
		P9.setTerminationRsn("Govt job");
		
		
		
		// Employement plan Data 
		
		CitizenPlan P10 = new CitizenPlan();
		P10.setCitizenName("Anitha");
		P10.setGender("female") ;
		P10.setPlanName("Employement");
		P10.setPlanStatus("Approved");
		P10.setPlanStartDate(LocalDate.now());
		P10.setPlanEndDate(LocalDate.now().plusMonths(6));
		P10.setBenifitAmt(5000.0);
		
		
		
		CitizenPlan P11 = new CitizenPlan();
		P11.setCitizenName("Sunitha");
		P11.setGender("female") ;
		P11.setPlanName("Employement");
		P11.setPlanStatus("Denied");
		P11.setDanialReason("Rental income");
		
		
		CitizenPlan P12 = new CitizenPlan();
		P12.setCitizenName("Arun");
		P12.setGender("male") ;
		P12.setPlanName("Employement");
		P12.setPlanStatus("Terminated");
		P12.setPlanStartDate(LocalDate.now());
		P12.setPlanEndDate(LocalDate.now().plusMonths(6));
		P12.setBenifitAmt(5000.0);
		P12.setTerminatedDate(LocalDate.now());
		P12.setTerminationRsn("un-Employed");
		

   java.util.List<CitizenPlan> list = Arrays.asList(P1,P2,P3,P4,P5,P6,P7,P8,P9,P10,P11,P12);
  repo.saveAll(list);
    
	}
	
		
}	
		
	
	
	


