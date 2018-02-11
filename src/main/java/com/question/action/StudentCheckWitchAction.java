package com.question.action;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.question.model.StudentAnswerModel;
import com.question.model.StudentCheckQuestionModel;
import com.question.model.StudentCheckWitchModel;
import com.question.service.StudentAnswerService;
import com.question.service.StudentCheckQuestionService;
import com.question.service.StudentCheckWitchService;

import st.core.session.HtmlUtil;
import st.core.session.JSONUtil;
import st.tool.FormatEmpty;

@Controller
@RequestMapping("/checkWitch")
public class StudentCheckWitchAction {
	@Autowired
	private StudentCheckWitchService<StudentCheckWitchModel> studentCheckWitchService;
	@Autowired
	private StudentAnswerService<StudentAnswerModel> studentAnswerService;
	@Autowired
	private StudentCheckQuestionService<StudentCheckQuestionModel> studentCheckQuestionService;

	@RequestMapping("/checkisre.do")
	public void checkisre(StudentCheckWitchModel m, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (FormatEmpty.isEmpty(m.getUserName())) {
			return;
		}
		if (FormatEmpty.isEmpty(m.getTitleid())) {
			return;
		}
		if (FormatEmpty.isEmpty(m.getCreateQuestionCode())) {
			return;
		}
		List<StudentCheckWitchModel> answerisre = studentCheckWitchService.selectAll(m);// 检验该学生答过这个标题的试卷了吗
		if (!FormatEmpty.isEmpty(answerisre)) {
			List<String> fanhui = new ArrayList<String>();
			fanhui.add(0, "1");// 已回答
			HtmlUtil.writerJson(response, fanhui);
		} else {
			List<String> fanhui = new ArrayList<String>();
			fanhui.add(0, "0");// 没回答
			HtmlUtil.writerJson(response, fanhui);
		}
	}

	@RequestMapping("/insert.do")
	public void jiaojuan(StudentCheckWitchModel m, StudentAnswerModel am, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (FormatEmpty.isEmpty(m.getUserName())) {
			return;
		}
		if (FormatEmpty.isEmpty(m.getTitleid())) {
			return;
		}
		if (FormatEmpty.isEmpty(m.getCreateQuestionCode())) {
			return;
		}
		StudentCheckWitchModel insertWitchQuestion = new StudentCheckWitchModel();
		insertWitchQuestion.setUserName(m.getUserName());
		insertWitchQuestion.setTitleid(m.getTitleid());
		insertWitchQuestion.setCreateQuestionCode(m.getCreateQuestionCode());
		List<StudentCheckWitchModel> answerisre = studentCheckWitchService.selectAll(insertWitchQuestion);// 检验该学生答过这个标题的试卷了吗
		if (FormatEmpty.isEmpty(answerisre)) {
			studentCheckWitchService.insert(insertWitchQuestion);// 插入学生回答的哪套试卷的表
		} else {
			return;
		}

		List<StudentCheckWitchModel> qureyId = studentCheckWitchService.selectAll(insertWitchQuestion);
		if (FormatEmpty.isEmpty(qureyId)) {
			return;
		}
		Integer witchId = qureyId.get(0).getId();
		StudentAnswerModel insertAnswer = new StudentAnswerModel();
		String questionQaCodel = m.getQuestionQaCodelist();
		String daNamel = m.getDaNamelist();
		String[] questionQaCode = questionQaCodel.split(",");
		String[] daName = daNamel.split(",");
		List<StudentAnswerModel> AnswerList = new ArrayList<StudentAnswerModel>();
		for (int i = 0; i < questionQaCode.length; i++) {
			StudentAnswerModel answer = new StudentAnswerModel();
			answer.setWitchId(witchId);
			answer.setQuestionQaCode(questionQaCode[i]);
			for (int k = 0; k < daName.length; k++) {
				answer.setDaName(daName[i]);
				break;
			}
			AnswerList.add(answer);
		}
		for (StudentAnswerModel an : AnswerList) {
			studentAnswerService.insert(an);
		}
		List<StudentAnswerModel> fanhui = new ArrayList<StudentAnswerModel>();
		HtmlUtil.writerJson(response, fanhui);
	}

	@RequestMapping("/tosuccess.shtml")
	public ModelAndView tosuccessPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView m = new ModelAndView();
		m.setViewName("/page/question/tijiaosuccee");
		return m;

	}

	// 问卷联动查标题
	@RequestMapping("/loadtitle.do")
	public void loadtitle(StudentCheckQuestionModel m, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<StudentCheckQuestionModel> list = studentCheckQuestionService.selectAll(m);
		if (FormatEmpty.isEmpty(list)) {
			ArrayList<Map<String, Object>> List = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("titletext", "未发布");
			map.put("titleid", "-1");//随便值后台查不到就行
			List.add(map);
			List.get(0).put("selected", true);
			HtmlUtil.writerJson(response, List);
			return;
		}
		ArrayList<Map<String, Object>> List = new ArrayList<Map<String, Object>>();
		for (StudentCheckQuestionModel m1 : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("titletext", m1.getTitle());
			map.put("titleid", m1.getId());
			List.add(map);
		}
		List.get(0).put("selected", true);
		HtmlUtil.writerJson(response, List);
	}
	// 问卷联动查标题 联动查询姓名
	@RequestMapping("/loadtitleusername.do")
	public void loadusername(StudentCheckWitchModel m, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<StudentCheckWitchModel> list = studentCheckWitchService.selectAll(m);
		if (FormatEmpty.isEmpty(list)) {
			ArrayList<Map<String, Object>> List = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("trueName", "无人答题");
			map.put("userName", "-1");//随便值后台查不到就行
			List.add(map);
			List.get(0).put("selected", true);
			HtmlUtil.writerJson(response, List);
			return;
		}
		ArrayList<Map<String, Object>> List = new ArrayList<Map<String, Object>>();
		for (StudentCheckWitchModel m1 : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("trueName", m1.getUserInfor().getTrueName());
			map.put("userName", m1.getUserName());
			List.add(map);
		}
		List.get(0).put("selected", true);
		HtmlUtil.writerJson(response, List);
	}
	@RequestMapping("/selectAll.do")
	public void selectAllXX(StudentCheckWitchModel m, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<StudentCheckWitchModel> list = studentCheckWitchService.selectAll(m);
		if(FormatEmpty.isEmpty(list)) {
			HtmlUtil.writerJson(response, list);
			return;
		}
		HtmlUtil.writerJson(response, list);
	}
	@RequestMapping("/update.do")
	public void updatelevel(StudentCheckWitchModel m, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(FormatEmpty.isEmpty(m.getId())) {
			return;
		}
		studentCheckWitchService.update(m);
		List<String> list=new ArrayList<String>();
		HtmlUtil.writerJson(response, list);
	}
}
