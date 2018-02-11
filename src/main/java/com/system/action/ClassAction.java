package com.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.system.model.ClassModel;
import com.system.model.RoleModel;
import com.system.model.UserModel;
import com.system.service.ClassService;
import com.system.service.RoleService;
import com.system.service.UserService;
import com.utill.Tools;

import st.core.session.HtmlUtil;
import st.core.session.JSONUtil;
import st.tool.FormatEmpty;

@Controller
@RequestMapping("userclass")
public class ClassAction {
	@Autowired
	private ClassService<ClassModel> classService;
	@Autowired
	private UserService<UserModel> userService;
	@Autowired
	private RoleService<RoleModel> roleService;

	// 跳转班级管理
	@RequestMapping("/select.shtml")
	public ModelAndView adminPage(ClassModel classm, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ModelAndView m = new ModelAndView();
		m.setViewName("page/adminClass");
		return m;

	}

	@RequestMapping("/selectAll.do")
	public void selectAllClass(ClassModel classm, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<ClassModel> list = classService.selectAll(classm);
		HtmlUtil.writerJson(response, list);
	}

	// 讲师下拉框
	@RequestMapping("/comboxteacher.do")
	public void adminClassSelect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RoleModel role = new RoleModel();
		role.setRoleName("讲师");// 为了防止1清除数据库数据，不要用code
		List<RoleModel> list = roleService.selectAll(role);
		if (FormatEmpty.isEmpty(list)) {
			return;
		}
		UserModel user = new UserModel();
		user.setRoleCode(list.get(0).getId());
		List<UserModel> userlist = userService.selectAll(user);
		ArrayList<Map<String, Object>> List = new ArrayList<Map<String, Object>>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("userName", "");
		map2.put("trueName", "请选择");
		List.add(map2);
		for (UserModel userm : userlist) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName", userm.getUserName());
			map.put("trueName", userm.getTrueName());
			List.add(map);
		}
		List.get(0).put("selected", true);
		HtmlUtil.writerJson(response, List);

	}

	// 查询班级人数
	@RequestMapping("/selectcount.do")
	@ResponseBody
	public String selectcount(ClassModel classm, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int count = classService.selectCount(classm);
		String stcunt = count + "";
		return stcunt;

	}

	// 插入班级信息
	@RequestMapping("/insertClass.do")
	@ResponseBody
	public String insertClass(ClassModel classm, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userName=classm.getUserName();
		if(!FormatEmpty.isEmpty(userName)) {
			UserModel user=new UserModel();
			user.setUserName(userName);
			List<UserModel> list = userService.selectAll(user);//判断该教师是否已分配班级
			if(FormatEmpty.isEmpty(list)) {
				return "-1";
			}
			if(!FormatEmpty.isEmpty(list.get(0).getClassCode())) {
				return "-2"; //该老师已分配班级
			}
		}
		classm.setClassCode("class"+Tools.datecode());
		classService.insert(classm);
		return "0";

	}

	// 修改班级信息
	@RequestMapping("/updateClass.do")
	@ResponseBody
	public String updateClass(ClassModel classm, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(FormatEmpty.isEmpty(classm.getId())) {
			return "1";
		}
		String userName=classm.getUserName();
		if(!FormatEmpty.isEmpty(userName)) {
			UserModel user=new UserModel();
			user.setUserName(userName);
			List<UserModel> list = userService.selectAll(user);//判断该教师是否已分配班级
			if(FormatEmpty.isEmpty(list)) {
				return "-1";
			}
			if(!FormatEmpty.isEmpty(list.get(0).getClassCode())) {
				return "-2"; //该老师已分配班级
			}
		}
		classService.update(classm);
		return "0";

	}
	// 展示统计数据
	@RequestMapping("/statistics.do")
	public void showStatistics(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 Map<String, Object> data = new HashMap<String, Object>();
		ClassModel cm=new ClassModel();
		List<ClassModel> classlist = classService.selectAll(cm);
		List<String> classNamelist=new ArrayList<String>();
		ArrayList<Map<String, Object>> List = new ArrayList<Map<String, Object>>();
		
		for(ClassModel m:classlist) {
			classNamelist.add(m.getClassName());//得到className
		}
		for(ClassModel m2:classlist) {
			Map<String, Object> dataScorp = new HashMap<String, Object>();
			String className = m2.getClassName();
			ClassModel selectcount=new ClassModel();
			selectcount.setClassCode(m2.getClassCode());
			int classValue = classService.selectCount(selectcount);
			dataScorp.put("value", classValue);
			dataScorp.put("name", className);
			List.add(dataScorp);
		}

		data.put("className", classNamelist);
		data.put("classCountValue", List);
		HtmlUtil.writerJson(response, data);
	}

}
