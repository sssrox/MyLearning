package com.example.demo.controller;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ReadPDFService;
import com.example.demo.service.WriteExcelService;

@RestController
@RequestMapping("/api")
public class PdfToXlsController {
	
	
	@Autowired
	private ReadPDFService pdfReader;
	
	@Autowired
	private WriteExcelService xlsWriter;
	
	@RequestMapping( value ="process",  method=RequestMethod.GET)
	public boolean process() throws InvalidPasswordException, IOException {
		File dir = new File("files");
		FileFilter fileFilter = new WildcardFileFilter("*.pdf");
		File[] files = dir.listFiles(fileFilter);
		List<String[]> pdfFiles = new ArrayList<String[]>();
		for(File pdfFile : files) {
			pdfFiles.add(pdfReader.getPDFData(pdfFile));
		}
		return xlsWriter.writeExcel(pdfFiles);
	}

}
