package arca;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public
class ExcelHandler
{

	static
	void writeExcel(Map<Integer, Object[]> data, String sheetName)
	{
		// Create a file object
		// for the path of existing Excel file
		// Give the path of the file as parameter
		// from where file is to be read
		File file = new File("StatisticheMyArca.xlsx");

		// Create a FileInputStream object
		// for getting the information of the file
		FileInputStream fip = null;
		XSSFWorkbook workbook = null;
		try
		{
			fip = new FileInputStream(file);

			// Getting the workbook instance for XLSX file
			workbook = new XSSFWorkbook(fip);

			// Ensure if file exist or not
			if (file.isFile() && file.exists())
			{
				System.out.println("StatisticheMyArca.xlsx open");
			}
			else
			{
				System.out.println("StatisticheMyArca.xlsx either not exist" + " or can't open");
			}

		}
		catch (FileNotFoundException e)
		{
			workbook = new XSSFWorkbook();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		//Create a blank sheet
		XSSFSheet sheet = workbook.createSheet(sheetName);

		//Iterate over data and write to sheet
		Set<Integer> keyset = data.keySet();
		int rownum = 0;
		for (Integer key : keyset)
		{
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr)
			{
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
				{
					cell.setCellValue((String) obj);
				}
				else if (obj instanceof Integer)
				{
					cell.setCellValue((Integer) obj);
				}
			}
		}
		try
		{
			//Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("StatisticheMyArca.xlsx"));
			workbook.write(out);
			out.close();

			System.out.println(sheetName + " written successfully on disk.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}



}
