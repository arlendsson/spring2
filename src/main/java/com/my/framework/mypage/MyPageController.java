package com.my.framework.mypage;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.framework.util.SessionUtil;

@Controller
@RequestMapping(value = "/mypage")
public class MyPageController {

	private Logger log = LoggerFactory.getLogger(MyPageController.class);

	@Resource(name = "configuration")
	private Properties configuration;

	@Autowired
	private MyPageService service;

	public boolean validationHasErrors(BindingResult bindingResult) throws Exception {
		//validation
		if(bindingResult.hasErrors()) {
			for (Object object : bindingResult.getAllErrors()) {
			    if(object instanceof FieldError) {
			        FieldError fieldError = (FieldError) object;
			        log.warn("########## fieldError : " + fieldError.toString());
			    }

			    if(object instanceof ObjectError) {
			        ObjectError objectError = (ObjectError) object;
			        log.warn("########## objectError : " + objectError.toString());
			    }
			}
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("/myPage")
	public String myPage(HttpServletRequest request, HttpServletResponse response
			, Model model
			, BoardVo param
			) throws Exception {
		log.debug("########## " + request.getRequestURI());

		log.debug(service.selectDual("지금은 "));

		MyPageVo result = service.selectUser(new MyPageVo(SessionUtil.getLoginUser(request.getSession()).getId()));

		log.debug("########## name : " + result.getName());
		log.debug("########## param.getCurPage() : " + param.getCurPage());

		// TODO 페이징
		BoardVo pagingParam = new BoardVo(service.selectBoardCount(param), param.getCurPage() == 0 ? 1 : param.getCurPage());
		log.debug("########## pagingParam : " + pagingParam.toString());

		List<BoardVo> boardList = service.selectBoardList(pagingParam);

		model.addAttribute("boardVo", pagingParam);
		model.addAttribute("boardList", boardList);

		return "mypage/myPage";
	}

	@RequestMapping("/ajax/data")
	@ResponseBody
	public MyPageVo data(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("########## " + request.getRequestURI());

		return new MyPageVo("hello");
	}

	@RequestMapping("/ajax/insertBoard")
	@ResponseBody
	public BoardVo insertBoard(HttpServletRequest request, HttpServletResponse response, BoardVo param) throws Exception {
		log.debug("########## " + request.getRequestURI());
		log.debug("########## 1: " + param.toString());

		param.setRegId(SessionUtil.getLoginUser(request.getSession()).getId());

		service.insertBoard(param);

		log.debug("########## 2: " + param.toString());

		return param;
	}

	@RequestMapping("/ajax/updateBoard")
	@ResponseBody
	public BoardVo updateBoard(HttpServletRequest request, HttpServletResponse response, BoardVo param) throws Exception {
		log.debug("########## " + request.getRequestURI());
		log.debug("########## 1: " + param.toString());

		param.setRegId(SessionUtil.getLoginUser(request.getSession()).getId());
		param.setModId(SessionUtil.getLoginUser(request.getSession()).getId());

		service.updateBoard(param);

		log.debug("########## 2: " + param.toString());

		return param;
	}
}
