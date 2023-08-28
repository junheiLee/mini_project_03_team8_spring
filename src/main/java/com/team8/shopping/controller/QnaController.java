package com.team8.shopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.team8.shopping.service.QnaService;
import com.team8.shopping.vo.QnaVO;

@Controller
@RequestMapping("/qnas")
public class QnaController {

	@Autowired
	private QnaService qnaService;

	
	/** 목록보기 **/
	@RequestMapping(value = "/qnaList", method = RequestMethod.GET)
	public ModelAndView qnaList(@RequestParam("id") String id) {
		List<QnaVO> qnaList = qnaService.listQna(id);
        ModelAndView mav = new ModelAndView("qna/qnaList");
        mav.addObject("qnaList", qnaList);
        return mav;
	}
	
	/** 상세화면  **/
	@RequestMapping(value = "/qnaView", method = RequestMethod.GET)
	 public ModelAndView qnaView(@RequestParam("qseq") int qseq) {
		QnaVO qna = qnaService.detailQna(qseq);
        ModelAndView mav = new ModelAndView("qna/qnaView");
        mav.addObject("qnaVO", qna);
        return mav;
    }
	
	/** qna 작성폼 **/
	@RequestMapping(value = "/qnaWriteForm")
	public String qnaWrite() {
		return "qna/qnaWrite";
	}
	
	/** qna 새글 작성 **/
	@RequestMapping(value = "/qnaWrite", method = RequestMethod.POST)
	 public ModelAndView qnaWrite(@ModelAttribute("formm") QnaVO qnaVO) {
		int result = qnaService.addQna(qnaVO);
		List<QnaVO> qnaList = qnaService.listQna(qnaVO.getId());
        ModelAndView mav = new ModelAndView("qna/qnaList");
        mav.addObject("qnaList", qnaList);
        return mav;
    }
		
		
}