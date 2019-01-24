package com.example.demo.dao.reader.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.example.demo.dao.reader.ReadPDFDao;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

/**
 * @author Shyam
 *
 */
@Component(value="IText")
public class ReadPDFDaoITextImpl implements ReadPDFDao{

	/* (non-Javadoc)
	 * @see com.example.demo.dao.reader.ReadPDFDao#getPDFData(java.io.File)
	 */
	@Override
	public String[] getPDFData(File file) {
		 PdfReader reader = null;
		 String lines[] = null;
	        try {
	            reader = new PdfReader(file.getAbsolutePath());
	            String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);
	            lines = textFromPage.split("\\r?\\n");
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	        	reader.close();
	        }
	        return lines;
	}

}
