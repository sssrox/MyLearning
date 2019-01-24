package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ReadPDFService;
import com.example.demo.service.WriteExcelService;

/**
 * @author Shyam
 *
 */
@RestController
@RequestMapping("/api")
public class PdfToXlsController {
	
	
	@Autowired
	private ReadPDFService pdfReader;
	
	@Autowired
	private WriteExcelService xlsWriter;
	
	/**
	 * @return
	 */
	@RequestMapping( value ="process",  method=RequestMethod.GET)
	public boolean process()  {
		return xlsWriter.writeExcel(pdfReader.getPDFData());
	}

}
