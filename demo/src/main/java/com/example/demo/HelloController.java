package com.example.demo;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.validation.Valid;
import javax.ws.rs.core.Response;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@Controller
@RequestMapping("/test")
public class HelloController {
	@Value("${some.value}")
	private String propValue;
	
	@ApiOperation(authorizations = {@Authorization(value="basicAuth")}, value = "Upload File")
	@RequestMapping(  value = "/upload", method=RequestMethod.POST, consumes ="multipart/form-data")
	public @ResponseBody  String sayHello(@FormDataParam("file") InputStream uploadInputStream, @FormDataParam("file")FormDataContentDisposition fileDetail) throws IOException, EncryptedDocumentException, InvalidFormatException {
		if(!fileDetail.getName().contains(".xls"))
			return "invalid file format. Upload Xls file";
		
		
//		 InputStream excelFile = new FileInputStream(new File(uploadedFileLocation));
		 Workbook workbook = WorkbookFactory.create(uploadInputStream);
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
	
	
	@RequestMapping(value="/test",method=RequestMethod.POST)
	@ApiOperation(value="User's last name")
	public @ResponseBody  Response sayHello(@Valid @RequestBody VO vo){
		System.out.println("propValue is "+propValue);
		return Response.ok(vo.getName()).build();
	}
	
	@RequestMapping(value="/readWord",method=RequestMethod.GET)
	public @ResponseBody Response readWord() throws FileNotFoundException, IOException{
//		XWPFDocument docx = new XWPFDocument(new FileInputStream("C:\\Users\\Shyam\\Desktop\\Hello World.doc"));
		FileInputStream docx = new FileInputStream(new File("C:\\Users\\Shyam\\Desktop\\Hello World.doc"));

//		HWPFDocument wordDoc = new HWPFDocument(docx);
		WordExtractor wordExtractor = new WordExtractor(docx);
		//using XWPFWordExtractor Class
		for(String paragraph:wordExtractor.getParagraphText()){
			  System.out.println(paragraph);
			}
		return Response.ok("ok").build();
	}
}
