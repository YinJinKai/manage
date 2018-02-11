package com.question.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.question.model.QuestionQaModel;
import com.question.service.QuestionQaService;
import com.utill.Constant;
import com.utill.Tools;

import net.sf.json.JSONObject;
import st.tool.FormatEmpty;

@Controller
@RequestMapping("/qa")
public class QuestionQaAction {
	@Autowired
	private QuestionQaService<QuestionQaModel> questionQaService;
	@RequestMapping("/select.shtml")
	public ModelAndView toQuestionPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView m = new ModelAndView();
		m.setViewName("/page/question/questionRoom");
		return m;

	}
	@RequestMapping("/selectQa.do")
	public void selectQa(QuestionQaModel m, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int count = questionQaService.selectCount(m);
		m.getPager().setRowCount(count);
		List<QuestionQaModel> list = questionQaService.selectAll(m);
		for(QuestionQaModel p:list) {
			String type=p.getTmtype();
			if("1".equals(type)) {
				p.setTmtype(Constant.objective);
				
			}else if("2".equals(type)){
				p.setTmtype(Constant.subjective);
				
			}
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		jsonMap.put("total", count);
		jsonMap.put("rows", list);
		JSONObject result = JSONObject.fromObject(jsonMap);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result.toString());
		out.flush();
		out.close();
	}
	@RequestMapping("/insertqn.do")
	public void insertqn(QuestionQaModel m, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(FormatEmpty.isEmpty(m.getTm())) {
			return;
		}
		if(FormatEmpty.isEmpty(m.getTmtype())) {
			return;
		}
		m.setQuestionQaCode("tm"+Tools.datecode());
		questionQaService.insert(m);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("0");
		out.flush();
		out.close();
	}
	@RequestMapping("/updateqn.do")
	public void updateqn(QuestionQaModel m, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(FormatEmpty.isEmpty(m.getTm())) {
			return;
		}
		questionQaService.update(m);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("0");
		out.flush();
		out.close();
	}
	@RequestMapping("/deleteqn.do")
	public void deleteqn(QuestionQaModel m, HttpServletRequest request, HttpServletResponse response) throws Exception {
		questionQaService.delete(m.getId());
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("0");
		out.flush();
		out.close();
	}
}
