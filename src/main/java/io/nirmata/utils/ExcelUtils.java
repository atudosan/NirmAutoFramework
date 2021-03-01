package io.nirmata.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.nirmata.constants.FrameworkConstants;
import io.nirmata.exceptions.InvalidFilePathException;
import io.nirmata.exceptions.ReadingYourFileException;

public final class ExcelUtils {

	private ExcelUtils() {

	}

	public static List<Map<String, String>> getTestDetails(String sheetName){
		List<Map<String, String>> list = null;

		try(FileInputStream fs = new FileInputStream(FrameworkConstants.getXlsxFilePath())) {

			@SuppressWarnings("resource")
			XSSFWorkbook wb = new XSSFWorkbook(fs);
			XSSFSheet sh = wb.getSheet(sheetName);

			int lastRowNum = sh.getLastRowNum();
			int lastColNum = sh.getRow(0).getLastCellNum();

			Map<String, String> map = null;
			list= new ArrayList<>();

			for (int i = 1; i < lastRowNum; i++) {
				map = new HashMap<>();
				for (int j = 0; j < lastColNum; j++) {
					String key = sh.getRow(0).getCell(j).getStringCellValue();
					String value = sh.getRow(i).getCell(j).getStringCellValue();
					map.put(key, value);
				}
				list.add(map);
			}

		} catch (FileNotFoundException e) {
			throw new InvalidFilePathException("Excel File that you trying to read is not found");
		} catch (IOException e) {
			throw new ReadingYourFileException("File IO exception happened while reading excel file");	}
		return list;

	}

}
