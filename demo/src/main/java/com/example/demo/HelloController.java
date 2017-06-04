package com.example.demo;



import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiParam;

@Controller
public class HelloController {
	
	@RequestMapping(  value = "/upload", method=RequestMethod.POST, consumes ="multipart/form-data")
	public @ResponseBody  String sayHello(@ApiParam @RequestPart (required = true) MultipartFile file, @RequestParam(required = true) String filePath) throws IOException, EncryptedDocumentException, InvalidFormatException {
		String fileName = file.getOriginalFilename();
		String uploadedFileLocation = filePath + file.getOriginalFilename();
		if(!file.getOriginalFilename().contains(".xls"))
			return "invalid file format. Upload Xls file";
		
		System.out.println("file loc: "+uploadedFileLocation );
		
//		 InputStream excelFile = new FileInputStream(new File(uploadedFileLocation));
		 Workbook workbook = WorkbookFactory.create(new FileInputStream(uploadedFileLocation));
//         Workbook workbook = new XSSFWorkbook(excelFile);
         Sheet datatypeSheet = workbook.getSheetAt(0);
         Iterator<Row> iterator = datatypeSheet.iterator();
         
         while (iterator.hasNext()) {

             Row currentRow = iterator.next();
             Iterator<Cell> cellIterator = currentRow.iterator();

             while (cellIterator.hasNext()) {

                 Cell currentCell = cellIterator.next();
                 //getCellTypeEnum shown as deprecated for version 3.15
                 //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                 if (currentCell.getCellTypeEnum() == CellType.STRING) {
                     System.out.print(currentCell.getStringCellValue() + "--");
                 } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                     System.out.print(currentCell.getNumericCellValue() + "--");
                 }

             }
             workbook.close();
             System.out.println();

         }
		return "Successfully processed";
	}
}
