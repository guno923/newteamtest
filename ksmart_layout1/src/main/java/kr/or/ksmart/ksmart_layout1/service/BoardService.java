package kr.or.ksmart.ksmart_layout1.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ksmart.ksmart_layout1.mapper.BoardMapper;
import kr.or.ksmart.ksmart_layout1.vo.Board;

@Service
@Transactional
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	public int addBoard(Board board) {
		
		return boardMapper.addBoard(board);
	}
	
	public Map<String, Object> boardList(int currentPage) {
		//페이지 구성 할 행의 갯수
		final int ROW_PER_PAGE = 10;
		//보여줄 첫번째 페이지번호 초기화
		int startPageNum = 1;
		//보여줄 페이지번호의 갯수 초기화
		int lastPageNum = ROW_PER_PAGE;
		
		if(currentPage > (ROW_PER_PAGE/2)) {
			startPageNum = currentPage - ((lastPageNum/2)-1);
			lastPageNum += (startPageNum-1);
		}
		// limit 적용될 값 startRow, 상수 ROW_PER_PAGE
		Map<String, Object> map = new HashMap<String, Object>();
		
		int startRow = (currentPage-1)*ROW_PER_PAGE;
		
		map.put("startRow", startRow);
		map.put("rowPerPage", ROW_PER_PAGE);
		
		//전체행의 갯수를 가져오는 쿼리
		double boardCount = boardMapper.getBoardAllCount();
							//올림함수 소수점이있으면 무조건 올림
		int lastPage = (int)(Math.ceil(boardCount/ROW_PER_PAGE));
		
		if(currentPage >= (lastPage-4)) {
			lastPageNum = lastPage;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", boardMapper.boardList(map));
		resultMap.put("currentPage", currentPage);
		resultMap.put("lastPage", lastPage);
		resultMap.put("startPageNum", startPageNum);
		resultMap.put("lastPageNum", lastPageNum);
		return resultMap;
	}
	
	public Board boardUpNo(int boardNo) {
		
		return boardMapper.boardUpNo(boardNo);
	}
	public int boardUpd(Board board) {
		return boardMapper.boardUpd(board);
	}
	
	public int boardDel(Board board) {
		return boardMapper.boardDel(board);
	}
}
