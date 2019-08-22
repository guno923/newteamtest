package kr.or.ksmart.ksmart_layout1.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ksmart.ksmart_layout1.service.BoardService;
import kr.or.ksmart.ksmart_layout1.vo.Board;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/addBoard")
	public String boardInsert() {
		return "/board/bInsert/addBoard";
	}
	
	@PostMapping("/addBoard")
	public String boardInsert(Board board) {
		boardService.addBoard(board);
		return "redirect:/boardList";
	}
	
	@GetMapping("/boardList")
	public String boardList(Model model
							, @RequestParam(value="currentPage"
							, required = false
							, defaultValue = "1") int currentPage) {
		Map<String, Object> map = boardService.boardList(currentPage);
		
		model.addAttribute("boardList", map.get("list"));
		model.addAttribute("currentPage", map.get("currentPage"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("startPageNum", map.get("startPageNum"));
		model.addAttribute("lastPageNum", map.get("lastPageNum"));
		return "/board/blist/boardList";
	}
	
	@GetMapping("/boardUpNo")
	public String boardUpd(@RequestParam(value="boardNo") int boardNo, Model model) {
		model.addAttribute("boardUpNo", boardService.boardUpNo(boardNo));
		return "/board/bUpdate/boardUpdate";
	}
	
	@PostMapping("/boardUpd")
	public String boardUpd(Board board) {
		System.out.println(board+"<--수정처리controller");
		boardService.boardUpd(board);
		return "redirect:/boardList";
	}
	
	@GetMapping("/boardDel")
	public String boardDel(@RequestParam(value="boardNo") int boardNo, Model model) {
		model.addAttribute("boardD", boardService.boardUpNo(boardNo));
		return "/board/bDelete/boardDelete";
	}
	
	@PostMapping("/boardDel")
	public String boardDel(Board board, Model model) {
		System.out.println(board + "<--삭제처리");
		int result = boardService.boardDel(board);
		if(result == 0) {
			model.addAttribute("result", "비밀번호 불일치");
			model.addAttribute("boardD", board);
			return "/board/bDelete/boardDelete";
		}
		return "redirect:/boardList";
	}
}
