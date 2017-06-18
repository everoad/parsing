package parsing.service;

import java.util.List;

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
	
	
}
