package kr.or.ksmart.ksmart_layout1.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ksmart.ksmart_layout1.mapper.GoodsMapper;
import kr.or.ksmart.ksmart_layout1.vo.Goods;

@Service
@Transactional
public class GoodsService {
	@Autowired private GoodsMapper goodsMapper;
	
	public List<Goods> getGoodsList(){
		return goodsMapper.getGoodsList();
	}
	
	public List<Goods> getGoodsSearch(String sk, String sv, String gp1, String gp2){
		return goodsMapper.getGoodsSearch(sk, sv, gp1, gp2);
	}
	
	public int addGoods(Goods goods, HttpSession session) {
		int max = goodsMapper.getGoodsCodeMax()+1;
		String tempCode = "goods_";
		
		goods.setMemberId((String) session.getAttribute("SID"));
		goods.setGoodsCode(tempCode+max);
		
		return goodsMapper.addGoods(goods);
	}
	
	public int addGoods1(Goods goods, HttpSession session) {
		
		goods.setMemberId((String) session.getAttribute("SID"));
		
		return goodsMapper.addGoods1(goods);
	}
	
	public Goods goodsUpd(String goodsCode) {
		
		return goodsMapper.goodsUpd(goodsCode);
	}
	
	public int deleteGoods(String goodsCode, String memberId, String memberPw) {
		
		return goodsMapper.goodsDel(goodsCode, memberId, memberPw);
	}
}
