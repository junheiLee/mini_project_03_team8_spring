package com.team8.shopping.controller.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.team8.shopping.service.QnaService;
import com.team8.shopping.service.admin.AdminQnaService;
import com.team8.shopping.vo.MemberVO;
import com.team8.shopping.vo.QnaVO;

@Controller
@RequestMapping("/admin/qnas")
public class AdminQnaController {

	@Autowired
	private AdminQnaService adminQnaService;

	/** 목록보기 **/
	@RequestMapping(value = "/qnaList", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView adminQnaList(){ 
		List<QnaVO> qnaList = adminQnaService.selectAllQnas();
		ModelAndView mav = new ModelAndView("admin/qna/qnaList");
		mav.addObject("qnaList", qnaList);
        return mav;
	}
	
	/** 상세화면  **/
	@RequestMapping(value = "/qnaDetail", method = RequestMethod.POST)
	 public ModelAndView adminQnaDetail(@RequestParam("qseq") int qseq) {
		System.out.println(qseq);
		QnaVO qna = adminQnaService.getQna(qseq);
        ModelAndView mav = new ModelAndView("/admin/qna/qnaDetail");
        mav.addObject("qnaVO", qna);
        return mav;
    }
	

	/** qna 댓글 작성 **/
	@RequestMapping(value = "/qnaRepsave", method = RequestMethod.POST)
	public ModelAndView adminQnaWrite(
            @RequestParam("reply") String reply,
            @RequestParam("qseq") int qseq){
		System.out.println("문의번호" + qseq);
		QnaVO qna = new QnaVO();
		qna.setReply(reply);
		qna.setQseq(qseq);
		adminQnaService.resaveQna(qna);
		List<QnaVO> qnaList = adminQnaService.selectAllQnas();
		ModelAndView mav = new ModelAndView("admin/qna/qnaList");
		mav.addObject("qnaList", qnaList);
        return mav;
    }
	
		
}