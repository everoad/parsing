package parsing.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;

import parsing.mapper.ParsingMapper;
import parsing.vo.BoardVO;
import parsing.vo.LteVO;



public class ParsingService {

	
	
	@Autowired
	private ParsingMapper parsingMapper;
	
	
	public List<LteVO> parsingExcelFile() {
		
		List<BoardVO> list = parsingMapper.getMainList();

		for (BoardVO vo : list) {
			System.out.println(vo.getTitle());
		}
		
		return null;
	}
	
	
	
	
	//수정사항.
	public void test() throws IOException, EncryptedDocumentException, InvalidFormatException {

		FileInputStream fis = new FileInputStream(new File("C:/java/test.xlsx"));

		Workbook wb = WorkbookFactory.create(fis);

		Sheet sheet = wb.getSheetAt(0);

		if (sheet != null) {
			
			int rows = sheet.getPhysicalNumberOfRows();
			
			for (int r = 0; r < rows; r++) {
				
				Row row = sheet.getRow(r);
				
				if (row != null) {
					
					int cells = row.getPhysicalNumberOfCells();
					
					for (int c = 0; c < cells; c++) {
						
						Cell cell = row.getCell(c);

						switch (cell.getCellTypeEnum()) {
						
						case STRING:
							System.out.println("String : " + cell.getStringCellValue());
							break;
							
						case NUMERIC:
							System.out.println("Integer : " + cell.getNumericCellValue());
							break;
							
						default:
							break;
						}
					}
				}
			}
		}

	}
	
	
}
