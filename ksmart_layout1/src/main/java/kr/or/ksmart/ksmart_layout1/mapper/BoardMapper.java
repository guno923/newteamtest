package kr.or.ksmart.ksmart_layout1.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ksmart.ksmart_layout1.vo.Board;

@Mapper
public interface BoardMapper {

	public int addBoard(Board board);
	
	public List<Board> boardList(Map<String, Object> map);
	
	public int getBoardAllCount();
	
	public Board boardUpNo(int boardNo);
	
	public int boardUpd(Board board);

	public int boardDel(Board board);
}
