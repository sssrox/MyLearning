package com.example.demo.dao.reader.impl;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import com.example.demo.dao.reader.ReadPDFDao;

@Component(value="PDFBox")
public class ReadPDFDaoPDFBoxImpl implements ReadPDFDao {

	/* (non-Javadoc)
	 * @see com.example.demo.dao.reader.ReadPDFDao#getPDFData(java.io.File)
	 */
	@Override
	public String[] getPDFData(File file) {
		PDDocument document = null;
		String lines[] = null;
		try {
			document = PDDocument.load(file);
		
		// PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		PDFTextStripper pdfStripper = new PDFTextStripper();
		pdfStripper.setSortByPosition(true);
		String text = pdfStripper.getText(document);
		lines = text.split("\\r?\\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		try {
			document.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return lines;
	}

}
