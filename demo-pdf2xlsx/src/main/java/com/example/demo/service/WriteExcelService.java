package com.example.demo.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class WriteExcelService {
	@Value("${excel.headers}")
	private String headerDelim;

	@Value("${searchString}")
	private String searchString;

	@Value("${xlsFileName}")
	private String xlsFileName;

	public boolean writeExcel(List<String[]> files) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			XSSFSheet sheet = workbook.createSheet();
			List<String> headers = Arrays.asList(StringUtils.delimitedListToStringArray(headerDelim, "|"));
			Row headerRow = sheet.createRow(0);
			for (int i = 0; i < headers.size(); i++) {
				headerRow.createCell(i).setCellValue((String) headers.get(i));
			}

			List<String> searchArr = Arrays.asList(StringUtils.delimitedListToStringArray(searchString, "|"));
			for (int j = 0; j < files.size(); j++) {
				Row nextRow = sheet.createRow(j);
				for (int i = 0; i < searchArr.size(); i++) {
					for (String line : files.get(j)) {
						if (line.contains(searchArr.get(i))) {
							nextRow.createCell(i).setCellValue(
									line.substring(line.lastIndexOf(searchArr.get(i)) + searchArr.get(i).length()));
							break;
						}
					}
				}
			}
			for (int i = 0; i < headers.size(); i++) {
				sheet.autoSizeColumn(i);
			}

			FileOutputStream outputStream = new FileOutputStream(xlsFileName);

			workbook.write(outputStream);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			workbook.close();
		}
		return true;
	}
}
