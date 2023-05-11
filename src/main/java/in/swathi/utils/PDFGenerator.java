
package in.swathi.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.swathi.entity.CitizenPlan;
import in.swathi.repository.CitizenPlanRepository;

@Component
public class PDFGenerator {
	
	@Autowired
	private CitizenPlanRepository repo;
	
	public void generate (HttpServletResponse Response,List<CitizenPlan> Plans,File f) throws Exception {
		
	Document document=new Document(PageSize.A4);
	 PdfWriter.getInstance(document, Response.getOutputStream());
	PdfWriter.getInstance(document, new FileOutputStream(f));
	document.open();
	com.lowagie.text.Font title = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	title.setSize(25);
	
	Paragraph paragraph=new Paragraph("Citizen plans",title);
	paragraph.setAlignment(Paragraph.ALIGN_CENTER);
	document.add(paragraph);
	PdfPTable table= new PdfPTable(6);
	table.addCell("ID");
	table.addCell("citizenName");
	table.addCell("planName");
	table.addCell("planStatus");
	table.addCell("planStartDate");
	table.addCell("planEndDate");
	
	List<CitizenPlan> plans = repo.findAll();
	for(CitizenPlan plan:plans) {
		table.addCell(String.valueOf(plan.getCitizenId()));
		table.addCell(plan.getCitizenName());
		table.addCell(plan.getPlanName());
		table.addCell(plan.getPlanStatus());
	   table.addCell(plan.getPlanStartDate()+"");
	   table.addCell(plan.getPlanEndDate()+"");
	   
	}
	document.add(table);
	document.close();
	
	
}
	
	}