package com.question.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.question.model.StudentAnswerModel;
import com.question.service.StudentAnswerService;

@Controller
@RequestMapping("/answer")
public class StudentAnswerAction {
@Autowired
private StudentAnswerService<StudentAnswerModel> studentAnswerService;
@RequestMapping("/select.shtml")
public ModelAndView toAnswerPage(HttpServletRequest request, HttpServletResponse response) {
	ModelAndView m = new ModelAndView();
	m.setViewName("/page/question/stGrade");
	return m;

}
}
