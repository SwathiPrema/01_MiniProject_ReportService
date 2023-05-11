package in.swathi.utils;

import java.io.FileOutputStream;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import in.swathi.entity.CitizenPlan;

@Component
public class ExcelGenerator {
	
	public void generateExcel(FileOutputStream fos,List<CitizenPlan> lst,HttpServletResponse response) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("plan_data");
		HSSFRow row = sheet.createRow(0);
		
		try {
			row.createCell(0).setCellValue("citizenId");
			row.createCell(1).setCellValue("citizenName");
			row.createCell(2).setCellValue("gender");
			row.createCell(3).setCellValue("planName");
			row.createCell(4).setCellValue("planStatus");
			row.createCell(5).setCellValue("planStartdate");
			row.createCell(6).setCellValue("planEnddate");
			row.createCell(7).setCellValue("BenifitAmt");
			row.createCell(8).setCellValue("denialReason");
			row.createCell(9).setCellValue("terminatedDate");
			row.createCell(10).setCellValue("terminationRsn");
			
			
			int dataRowIndex = 1;
			
			for(CitizenPlan c : lst) {
				HSSFRow dataRow = sheet.createRow(dataRowIndex);
				dataRow.createCell(0).setCellValue(c.getCitizenId());
				dataRow.createCell(1).setCellValue(c.getCitizenName());
				dataRow.createCell(2).setCellValue(c.getGender());
				dataRow.createCell(3).setCellValue(c.getPlanName());
				dataRow.createCell(4).setCellValue(c.getPlanStatus());
				
				if(c.getPlanStartDate() != null) {
					LocalDate localDate = c.getPlanStartDate();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
					String date1 = localDate.format(formatter);
					dataRow.createCell(5).setCellValue(date1);
				}
				if(c.getPlanEndDate() != null) {
					LocalDate localDate = c.getPlanEndDate();
					DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd LLLL yyyy");
					String date1 = localDate.format(formatter1);
					dataRow.createCell(6).setCellValue(date1);
				}
				
				if(c.getBenifitAmt() != null) {
					dataRow.createCell(7).setCellValue(c.getBenifitAmt());
				}
				
				if(c.getDanialReason() != null) {
					dataRow.createCell(8).setCellValue(c.getDanialReason());
				}
				
				if(c.getTerminatedDate() != null) {
					LocalDate localDate2 = c.getTerminatedDate();
					DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd LLLL yyyy");
					String date2 = localDate2.format(formatter2);
					dataRow.createCell(9).setCellValue(date2);		
				}
				if(c.getTerminationRsn() != null) {
					dataRow.createCell(10).setCellValue(c.getTerminationRsn());
				}
				dataRowIndex++;
			}
			workbook.write(fos);
			ServletOutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
			workbook.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}