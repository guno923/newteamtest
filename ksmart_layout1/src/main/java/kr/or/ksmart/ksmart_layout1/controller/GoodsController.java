package kr.or.ksmart.ksmart_layout1.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ksmart.ksmart_layout1.service.GoodsService;
import kr.or.ksmart.ksmart_layout1.vo.Goods;

@Controller
public class GoodsController {

	@Autowired private GoodsService goodsService;
	
	@GetMapping("/goodsList")
	public String getGoodsList(Model model) {

		model.addAttribute("goodsList", goodsService.getGoodsList());

		return "/goods/glist/goodsList";
	}
	
	@PostMapping("/getGoodsSearch")
	public String getGoodsSearch( @RequestParam(value="sk") String sk
								 ,@RequestParam(value="sv")	String sv
								 ,@RequestParam(value="gp1") String gp1
								 ,@RequestParam(value="gp2") String gp2
								 ,Model model) {

		model.addAttribute("goodsList", goodsService.getGoodsSearch(sk, sv, gp1, gp2));
		return "/goods/glist/goodsList";
	}
	
	@GetMapping("/addGoods")
	public String addGoods() {
		
		return "/goods/gInsert/addGoods";
	}
	
	@PostMapping("/addGoods")
	public String addGoods(Goods goods, HttpSession session) {
		
		goodsService.addGoods(goods, session);
		
		return "redirect:/goodsList";
	}
	
	/*
	 * @PostMapping("/addGoods") public String addGoods(Goods goods, HttpSession
	 * session) {
	 * 
	 * goodsService.addGoods1(goods, session);
	 * 
	 * return "redirect:/goodsList"; }
	 */
	
	@GetMapping("/goodsUpd")
	public String goodsUpd(@RequestParam(value="goodsCode") String goodsCode, Model model) {
		model.addAttribute("goods", goodsService.goodsUpd(goodsCode));
		return "/goods/gupdate/modifyGoods";
	}

	@GetMapping("/goodsDel")
	public String goodsDel(@RequestParam(value="goodsCode") String goodsCode, Model model) {
		model.addAttribute("goodsCode", goodsCode);
		return "/goods/gdelete/delGoods";
	}
	
	@PostMapping("/delGoods")
	public String goodsDel(Goods goods,Model model) {
		System.out.println(goods.toString() + "삭제할 상품");
		int result = goodsService.deleteGoods(goods.getGoodsCode(), goods.getMemberId(), goods.getMemberPw());
		
		if(result == 0) {
			model.addAttribute("result", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("goodsCode", goods.getGoodsCode());
			return "goods/gdelete/delGoods";
		}
		return "redirect:/goodsList";
	}
	
}
