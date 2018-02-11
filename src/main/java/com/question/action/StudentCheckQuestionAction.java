package com.question.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.question.model.StudentCheckQuestionModel;
import com.question.service.StudentCheckQuestionService;

import st.core.session.HtmlUtil;
import st.core.session.JSONUtil;
import st.tool.FormatEmpty;

@Controller
@RequestMapping("/stcheck")
public class StudentCheckQuestionAction {
	@Autowired
	private StudentCheckQuestionService<StudentCheckQuestionModel> studentCheckQuestionService;
	//跳到学生答卷页面
	@RequestMapping("/select.shtml")
	public ModelAndView toadminQuestionPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView m = new ModelAndView();
		m.setViewName("/page/question/studentcheck");
		return m;

	}
	@RequestMapping("/insert.do")
	public void insertStQn(StudentCheckQuestionModel m, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		studentCheckQuestionService.insert(m);
		List<StudentCheckQuestionModel> returne = new ArrayList<StudentCheckQuestionModel>();// 空的返回用
		HtmlUtil.writerJson(response, returne);
	}

	@RequestMapping("/checkTitle.do")
	public void checkTitle(StudentCheckQuestionModel m, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		StudentCheckQuestionModel mm = new StudentCheckQuestionModel();
		mm.setCreateQuestionCode(m.getCreateQuestionCode());
		mm.setTitle(m.getTitle());
		List<StudentCheckQuestionModel> list = studentCheckQuestionService.selectAll(mm);// 检查该问卷的这个标题已是否已添加
		if (FormatEmpty.isEmpty(list)) {
			out.print("1");
			return;
		}
		out.print("0");//已存在
	}
	@RequestMapping("loadQninfor.do")
	public void loadQninfor(StudentCheckQuestionModel m, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<StudentCheckQuestionModel> list = studentCheckQuestionService.selectAll(m);
		HtmlUtil.writerJson(response, list);
		
		
		
		
		
		
	}
}
