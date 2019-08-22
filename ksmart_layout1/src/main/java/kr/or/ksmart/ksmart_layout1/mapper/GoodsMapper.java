package kr.or.ksmart.ksmart_layout1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ksmart.ksmart_layout1.vo.Goods;

@Mapper
public interface GoodsMapper {
	public List<Goods> getGoodsList();
	
	public List<Goods> getGoodsSearch(String sk, String sv, String gp1, String gp2);

	public int getGoodsCodeMax();
	
	public int addGoods(Goods goods);
	
	public int addGoods1(Goods goods);
	
	public Goods goodsUpd(String goodsCode);
	
	public int goodsDel(String goodsCode, String memberId, String memberPw);
}
