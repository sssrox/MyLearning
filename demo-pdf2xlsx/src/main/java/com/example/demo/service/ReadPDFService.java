package com.example.demo.service;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dao.reader.ReadPDFDao;

/**
 * @author Shyam
 *
 */
@Service
public class ReadPDFService {
	
	@Value("${pdfDir}")
	private String pdfDir;
	
	@Qualifier(value="PDFBox")
	@Autowired
	private ReadPDFDao radPDF;
	
	/**
	 * @return List<String[]> 
	 * List of all PDF Data as array of string from all files in Dir 
	 */
	public List<String[]> getPDFData()  {
		
		File dir = new File(pdfDir);
		FileFilter fileFilter = new WildcardFileFilter("*.pdf");
		File[] files = dir.listFiles(fileFilter);
		List<String[]> pdfFiles = new ArrayList<String[]>();
		for(File pdfFile : files) {
			pdfFiles.add(radPDF.getPDFData(pdfFile));
		}
		
		return pdfFiles;
	}
}
