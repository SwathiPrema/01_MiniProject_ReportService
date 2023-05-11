package in.swathi.reportservice;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import in.swathi.entity.CitizenPlan;
import in.swathi.searchrequest.SearchRequest;

@Service
public interface ReportService {

public List<String> getplanNames();

	public List<String> getplanStatuses();
	
	public List<CitizenPlan> search(SearchRequest request);
	
	public boolean exportExcel(HttpServletResponse response) throws Exception;
	
	public boolean exportPdf(HttpServletResponse response) throws Exception;
}


