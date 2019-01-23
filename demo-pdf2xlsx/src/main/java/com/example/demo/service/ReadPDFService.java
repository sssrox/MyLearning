package com.example.demo.service;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

@Service
public class ReadPDFService {
	
//	@Value("${pdfFileName}")
//	private String pdfFileName;
//	
	public String[] getPDFData(File file) throws InvalidPasswordException, IOException {
		PDDocument document = PDDocument.load(file);
		// PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		PDFTextStripper pdfStripper = new PDFTextStripper();
		pdfStripper.setSortByPosition(true);
		String text = pdfStripper.getText(document);
		document.close();
		String lines[] = text.split("\\r?\\n");
		return lines;
	}
}
