package com.faq.model;

import java.util.List;

public class FaqService {

	private FaqDAO_interface dao;

	public FaqService() {
		dao = new FaqDAO();
	}

	public FaqVO addFaq(String question, String answer, String faq_classification) {

		FaqVO faqVO = new FaqVO();

		faqVO.setQuestion(question);
		faqVO.setAnswer(answer);
		faqVO.setFaq_classification(faq_classification);
		dao.insert(faqVO);

		return faqVO;
	}

	public FaqVO updateFaq(String faq_no, String question, String answer, String faq_classification) {

		FaqVO faqVO = new FaqVO();

		
		faqVO.setFaq_no(faq_no);
		faqVO.setQuestion(question);
		faqVO.setAnswer(answer);
		faqVO.setFaq_classification(faq_classification);
		dao.update(faqVO);

		return faqVO;
	}

	public void deleteFaq(String faq_no) {
		dao.delete(faq_no);
	}

	public FaqVO getOneFaq(String faq_no) {
		return dao.findByPrimaryKey(faq_no);
	}

	public List<FaqVO> getAll() {
		return dao.getAll();
	}
	
	public List<String> getAllForNotRepeat() {
		return dao.getAllForNotRepeat();
	}
	
}
