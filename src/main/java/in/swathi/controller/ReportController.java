package in.swathi.controller;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.swathi.entity.CitizenPlan;
import in.swathi.reportservice.ReportService;
import in.swathi.searchrequest.SearchRequest;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/Pdf")
	public void pdfExport(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		response.setHeader("content_Disposition", "attachment;filename=plans.pdf");
		service.exportPdf(response);
	}
	
	@GetMapping("/excel")
	public void exportExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		response.setHeader("Content_Disposition", "attachment;filename=plans.xls");
		//response.addHeader("Content_Disposition", "attachment;filename=plans.xls");
		service.exportExcel(response);
	}
	
	@PostMapping("/plan")
	public String HandleSearch(@ModelAttribute("Search") SearchRequest request, Model model) {
	java.util.List<CitizenPlan> plans = service.search(request);
	    model.addAttribute("plans", plans);
	    init(model);
	    return "index";
	}
	
	@GetMapping("/")
	public String indexpage(Model model) {
		model.addAttribute("Search", new SearchRequest());

		init(model);
		return "index";
	}
	
	private void init(Model model) {
		//model.addAttribute("Search", new SearchRequest());
		model.addAttribute("names", service.getplanNames());
		java.util.List<String> planstatus= service.getplanStatuses();
		model.addAttribute("status", planstatus);
	}	
}
