package com.question.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.question.model.CreateQuestionModel;
import com.question.service.CreateQuestionService;

import st.core.session.HtmlUtil;
import st.tool.FormatEmpty;

@Controller
@RequestMapping("/question")
public class CreateQuestionAction {
@Autowired
private CreateQuestionService<CreateQuestionModel> createQuestionService;
//跳到问卷管理页面
@RequestMapping("/select.shtml")
public ModelAndView toadminQuestionPage(HttpServletRequest request, HttpServletResponse response) {
	ModelAndView m = new ModelAndView();
	m.setViewName("/page/adminQuestion");
	return m;

}
//跳到创建问卷页面
@RequestMapping("/createquestion.shtml")
public ModelAndView tocreateQuestionPage(HttpServletRequest request, HttpServletResponse response) {
	ModelAndView m = new ModelAndView();
	m.setViewName("/page/question/createQuestion");
	return m;

}
//检查试卷名
@RequestMapping("/checkName.do")
public void checkName(CreateQuestionModel m,HttpServletRequest request, HttpServletResponse response) throws Exception {
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	List<CreateQuestionModel> list = createQuestionService.selectAll(m);
	if(FormatEmpty.isEmpty(list)) {
	    out.print("1");//没重复
		out.flush();
		out.close();
		return;
	}
	out.print("0");//已有
	out.flush();
	out.close();
}
//加载已有问卷名
@RequestMapping("/loadQuestionName.do")
public void loadQuestionName(CreateQuestionModel m,HttpServletRequest request, HttpServletResponse response) throws Exception {
	List<CreateQuestionModel> list = createQuestionService.selectAll(m);
	ArrayList<Map<String, Object>> List = new ArrayList<Map<String, Object>>();
	for(CreateQuestionModel m1:list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("questionText",m1.getQuestionName());
		map.put("questionvalue", m1.getCreateQuestionCode());
		List.add(map);
	}
	List.get(0).put("selected", true);
	HtmlUtil.writerJson(response, List);

   }
//加载已有问卷名
@RequestMapping("/loadQuestionName2.do")
public void loadQuestionName2(CreateQuestionModel m,HttpServletRequest request, HttpServletResponse response) throws Exception {
	List<CreateQuestionModel> list = createQuestionService.selectAll(m);
	ArrayList<Map<String, Object>> List = new ArrayList<Map<String, Object>>();
	for(CreateQuestionModel m1:list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("questionText",m1.getQuestionName());
		map.put("questionvalue", m1.getCreateQuestionCode());
		List.add(map);
	}
	HtmlUtil.writerJson(response, List);

 }
}

