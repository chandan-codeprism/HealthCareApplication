package in.nareshit.raghu.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import in.nareshit.raghu.entity.Doctor;

public class DoctorExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws Exception {
		
		/*
		 * 1. Define your own excel file name
		 */
		
		response.addHeader("Content-Disposition", "attachment;filename=DOCTOR.xlsx");
		
		/*
		 * 2. Read data given by controller
		 */
		
		@SuppressWarnings("unchecked")
		List<Doctor> list=(List<Doctor>) model.get("list");
		
		/*
		 * 3. Create one sheet
		 */
		
		Sheet sheet=workbook.createSheet("DOCTOR");
		
		/*
		 * 4. Create row#0 as header
		 */
		
		setHead(sheet);
		
		/*
		 * 5. Create row#1 onwards from List<T>
		 */
		
		setBody(sheet,list);

	}

	
	private void setHead(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("FNAME");
		row.createCell(2).setCellValue("LNAME");
		row.createCell(3).setCellValue("EMAIL");
		row.createCell(4).setCellValue("ADDRESS");
		row.createCell(5).setCellValue("GENDER");
		row.createCell(6).setCellValue("MOBILE");
		row.createCell(7).setCellValue("NOTE");
		row.createCell(8).setCellValue("PHOTOS");
		row.createCell(9).setCellValue("IMGLOC");
		
	}
	
	private void setBody(Sheet sheet, List<Doctor> list) {
		int rowNum = 1;
		for(Doctor spec : list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(spec.getId());
			row.createCell(1).setCellValue(spec.getFirstName());
			row.createCell(2).setCellValue(spec.getLastName());
			row.createCell(3).setCellValue(spec.getEmail());
			row.createCell(4).setCellValue(spec.getAddress());
			row.createCell(5).setCellValue(spec.getGender());
			row.createCell(6).setCellValue(spec.getMobile());
			row.createCell(7).setCellValue(spec.getNote());
			row.createCell(8).setCellValue(spec.getPhotos());
			row.createCell(9).setCellValue(spec.getImgLoc());
		}
		
		
	}


}
