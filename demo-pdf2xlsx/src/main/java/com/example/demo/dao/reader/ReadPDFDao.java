package com.example.demo.dao.reader;

import java.io.File;

/**
 * @author Shyam
 *
 */
public interface ReadPDFDao {
	/**
	 * @param PDF file 
	 * @return Array of String of each line in File
	 */
	public String[] getPDFData(File file);
}
