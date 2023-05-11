package in.swathi.impl;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.swathi.entity.CitizenPlan;
import in.swathi.reportservice.ReportService;
import in.swathi.repository.CitizenPlanRepository;
import in.swathi.searchrequest.SearchRequest;
import in.swathi.utils.EMailGenerator;
import in.swathi.utils.ExcelGenerator;
import in.swathi.utils.PDFGenerator;

@Service
public class ReportServiceImpl  implements ReportService {

	@Autowired
	private CitizenPlanRepository repo;

	@Autowired
	private ExcelGenerator egenerator;

	@Autowired
	private EMailGenerator utils;

	@Autowired
	private PDFGenerator pgenerator;

	@Override
	public List<CitizenPlan> search(SearchRequest request) {

		CitizenPlan c1 = new CitizenPlan();

		if(null != request.getPlanName() && !"".equals(request.getPlanName())) {
			c1.setPlanName(request.getPlanName());
		}

		if(null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			c1.setPlanStatus(request.getPlanStatus());
		}

		if(null != request.getGender() && !"".equals(request.getGender())) {
			c1.setGender(request.getGender());
		}

		if(null != request.getStartDate() && !"".equals(request.getStartDate())) {
			String date = request.getStartDate();
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(date,dateTimeFormatter);
			c1.setPlanStartDate(localDate);
		}

		if(null != request.getEndDate() && !"".equals(request.getEndDate())) {
			String date = request.getEndDate();
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(date,dateTimeFormatter);
			c1.setPlanEndDate(localDate);
		}

		Example<CitizenPlan> exmp = Example.of(c1);
		List<CitizenPlan> plans = repo.findAll(exmp);
		return plans;
	}

	@Override
	public List<String> getplanNames() {
		List<String> planNames = repo.getplaname();
		return planNames;
	}

	@Override
	public List<String> getplanStatuses(){		
	  List<String> statusNames = repo.getplanStatus();
		return statusNames;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {		
	File f = new File("Plans.pdf");
	//	String filePath = "C:\\jrtp\\PlansData.pdf";
		try {
			List<CitizenPlan> plans = repo.findAll();
			FileOutputStream fos = new FileOutputStream(f);
			pgenerator.generate(response, plans,f);
			utils.sendEmailWithAttachment("swathidil358@gmail.com",
					"Citizen Plans Report","Here is the pdf of plans.", f);
			fos.close();
			f.delete();
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) {
		File f = new File("Plans.xls");
		//String filePath = "D:\\19-JFSD\\Java RealTime Project\\Mini project Workspace\\PlansData.xls";
		try {

			List<CitizenPlan> plans = repo.findAll();
			FileOutputStream fos = new FileOutputStream(f);
			egenerator.generateExcel(fos, plans,response);
			utils.sendEmailWithAttachment("swathidil358@gmail.com",
					"Citizen Plans Report","Here is the excelsheet of plans.", f);
			
			fos.close();
			f.delete();

		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
		return true;
	}

}
