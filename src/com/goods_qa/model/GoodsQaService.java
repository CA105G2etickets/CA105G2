package com.goods_qa.model;

import java.sql.Timestamp;
import java.util.List;

public class GoodsQaService {

	private GoodsQaDAO_interface goodsQaDAO;

	public GoodsQaService() {

		goodsQaDAO = new GoodsQaDAO();
	}

	public GoodsQaVO addGoodsQa(String goods_no, String member_no, String administrator_no, String questions_content,
			String answer_content, Timestamp questions_date, Timestamp answer_date) {

		GoodsQaVO goodsQaVO = new GoodsQaVO();

		goodsQaVO.setGoods_no(goods_no);
		goodsQaVO.setMember_no(member_no);
		goodsQaVO.setAdministrator_no(administrator_no);
		goodsQaVO.setQuestions_content(questions_content);
		goodsQaVO.setAnswer_content(answer_content);
		goodsQaVO.setQuestions_date(questions_date);
		goodsQaVO.setAnswer_date(answer_date);

		goodsQaDAO.insert(goodsQaVO);

		return goodsQaVO;
	}

	public GoodsQaVO updateGoodsQa(String gfaq_no, String goods_no, String member_no, String administrator_no,
			String questions_content, String answer_content, Timestamp questions_date, Timestamp answer_date) {

		GoodsQaVO goodsQaVO = new GoodsQaVO();

		goodsQaVO.setGfaq_no(gfaq_no);
		goodsQaVO.setGoods_no(goods_no);
		goodsQaVO.setMember_no(member_no);
		goodsQaVO.setAdministrator_no(administrator_no);
		goodsQaVO.setQuestions_content(questions_content);
		goodsQaVO.setAnswer_content(answer_content);
		goodsQaVO.setQuestions_date(questions_date);
		goodsQaVO.setAnswer_date(answer_date);

		goodsQaDAO.insert(goodsQaVO);

		return goodsQaVO;
	}

	public void deleteGoodsQa(String gfaq_no) {
		goodsQaDAO.delete(gfaq_no);
	}

	public GoodsQaVO getOneGoodsQa(String gfaq_no) {
		return goodsQaDAO.findByPrimaryKey(gfaq_no);
	}

	public List<GoodsQaVO> getAll() {
		return goodsQaDAO.getAll();
	}
}
