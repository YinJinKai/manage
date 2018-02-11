package com.attendance.action;

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

import com.attendance.model.LeaveModel;
import com.attendance.model.WorkModel;
import com.attendance.service.LeaveService;
import com.system.model.ClassModel;
import com.system.model.UserModel;
import com.system.service.ClassService;
import com.system.service.UserService;
import com.utill.Constant;
import com.utill.LoadStudentName;
import com.utill.Tools;

import net.sf.json.JSONObject;
import st.core.session.HtmlUtil;
import st.core.session.JSONUtil;
import st.tool.FormatCalendar;
import st.tool.FormatEmpty;

@Controller
@RequestMapping("/leave")
public class LeaveAction {
	@Autowired
	private LeaveService<LeaveModel> leaveService;
	@Autowired
	private ClassService<ClassModel> classService;
	@Autowired
	private UserService<UserModel> userService;

	// 跳学生请假页面
	@RequestMapping("/select.shtml")
	public ModelAndView toLeavePage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView m = new ModelAndView();
		m.setViewName("/page/leave");
		return m;

	}

	// 学生查询请假内容
	@RequestMapping("/selectLeave.do")
	public void selectAll(LeaveModel leave, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userName = (String) request.getSession().getAttribute("userName");
		leave.setUserName(userName);
		List<LeaveModel> leaveList = leaveService.selectAll(leave);
		if (FormatEmpty.isEmpty(leaveList)) {
			HtmlUtil.writerJson(response, leaveList);
			return;
		}
		for (LeaveModel p : leaveList) {
			String st = p.getStatus();
			if("1".equals(st)) {
				p.setStatusName(Constant.teacherconfirm);
			}
			if("t1".equals(st)) {
				p.setStatusName(Constant.teachernopass);
			}
			if("t2".equals(st)) {
				p.setStatusName(Constant.adminconfirm);
			}
			if("a1".equals(st)) {
				p.setStatusName(Constant.adminnopass);
			}
			if("a2".equals(st)) {
				p.setStatusName(Constant.adminpass);
			}
		}
		HtmlUtil.writerJson(response, leaveList);
	}

	// 学生申请
	@RequestMapping("/addNewLeave.do")
	public void insert(LeaveModel leave, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userName = (String) request.getSession().getAttribute("userName");
		leave.setUserName(userName);
		leave.setCreateTime(Tools.dateFormate());
		leave.setStatus("1");
		leaveService.insert(leave);
	}

	// 跳请假老师管理页面
	@RequestMapping("/teacherselect.shtml")
	public ModelAndView toTeacherAdminLeavePage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView m = new ModelAndView();
		m.setViewName("/page/adminLeave");
		return m;

	}
	// 跳请假教务管理页面
		@RequestMapping("/adminselect.shtml")
		public ModelAndView toadminAdminLeavePage(HttpServletRequest request, HttpServletResponse response) {
			ModelAndView m = new ModelAndView();
			m.setViewName("/page/adadminLeave");
			return m;

		}

	// 老师查询请假表
	@RequestMapping("/teacheradminselect.do")
	public void teacheradminleave(LeaveModel leave,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String userName=(String) request.getSession().getAttribute("userName");
		leave.setTeacherName(userName);
		String startTime = leave.getStartTime();
		String endTime = leave.getEndTime();
	
		if(FormatEmpty.isEmpty(startTime)) {
			startTime="0000-00-00 00:00:00";
		}
        if(FormatEmpty.isEmpty(endTime)) {
        	endTime="9999-99-99 00:00:00";
		}
        leave.setStartTime(startTime);
        leave.setEndTime(endTime);
        int count = leaveService.selectCount(leave);
        leave.getPager().setRowCount(count);
		List<LeaveModel> list = leaveService.selectByteacher(leave);
		if(FormatEmpty.isEmpty(list)) {
			HtmlUtil.writerJson(response, list);
			return;
		}
		for(LeaveModel p:list) {
			String st = p.getStatus();
			if("1".equals(st)) {
				p.setStatusName(Constant.teacherconfirm);
			}
			if("t1".equals(st)) {
				p.setStatusName(Constant.teachernopass);
			}
			if("t2".equals(st)) {
				p.setStatusName(Constant.adminconfirm);
			}
			if("a1".equals(st)) {
				p.setStatusName(Constant.adminnopass);
			}
			if("a2".equals(st)) {
				p.setStatusName(Constant.adminpass);
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

	// (老师管理)加载学生姓名
	@RequestMapping("/loadStudent.do")
	public void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String userName = (String) request.getSession().getAttribute("userName");
		LeaveModel leave = new LeaveModel();
		leave.setTeacherName(userName);
		List<LeaveModel> list = leaveService.selectByteacher(leave);
		ArrayList<LoadStudentName> nameList = new ArrayList<LoadStudentName>();
		for (LeaveModel p : list) {
			LoadStudentName name = new LoadStudentName();
			name.setTrueName(p.getTrueName());
			name.setUserName(p.getUserName());
			nameList.add(name);
		}
		HashSet<String> hh = new HashSet<String>();
		for (LoadStudentName a : nameList) {
			hh.add(a.getTrueName() + "," + a.getUserName());
		}
		ArrayList<Map<String, Object>> List = new ArrayList<Map<String, Object>>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("userName", "");
		map2.put("trueName", "查询全部");
		List.add(map2);
		for (String q : hh) {
			Map<String, Object> map = new HashMap<String, Object>();
			String[] s = q.split(",");
			map.put("userName", s[1]);
			map.put("trueName", s[0]);
			List.add(map);
		}
		HtmlUtil.writerJson(response, List);
	}
//   老师处理申请
   @RequestMapping("/updateLeave.do")
   public void updateLeave(LeaveModel leave,HttpServletRequest request,HttpServletResponse response) throws Exception {
	   PrintWriter out=response.getWriter(); 
	   String idlist=leave.getIdlist();
	    if(FormatEmpty.isEmpty(idlist)) {
	    	return;
	    }
	    
	    String[] id = idlist.split(",");
	    for(String a:id) {
	    	leave.setId(Integer.parseInt(a));
	    	leaveService.update(leave);
	    }
	    out.println("1");
	    out.flush();
	    out.close();
	  }
	// 教务查询请假表
	@RequestMapping("/adminadminselect.do")
	public void adminadminleave(LeaveModel leave,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String userName=(String) request.getSession().getAttribute("userName");
		String startTime = leave.getStartTime();
		String endTime = leave.getEndTime();
	
		if(FormatEmpty.isEmpty(startTime)) {
			startTime="0000-00-00 00:00:00";
		}
       if(FormatEmpty.isEmpty(endTime)) {
       	endTime="9999-99-99 00:00:00";
		}
       leave.setStartTime(startTime);
       leave.setEndTime(endTime);
       int count = leaveService.selectCount(leave);
       leave.getPager().setRowCount(count);
		List<LeaveModel> list = leaveService.selectAll(leave);
		if(FormatEmpty.isEmpty(list)) {
			HtmlUtil.writerJson(response, list);
			return;
		}
		for(LeaveModel p:list) {
			String st = p.getStatus();
			if("1".equals(st)) {
				p.setStatusName(Constant.teacherconfirm);
			}
			if("t1".equals(st)) {
				p.setStatusName(Constant.teachernopass);
			}
			if("t2".equals(st)) {
				p.setStatusName(Constant.adminconfirm);
			}
			if("a1".equals(st)) {
				p.setStatusName(Constant.adminnopass);
			}
			if("a2".equals(st)) {
				p.setStatusName(Constant.adminpass);
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
//教务查询班级下拉框
	@RequestMapping("/adminSelectClass.do")
	public void adminClassSelect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		ClassModel model=new ClassModel();
		List<ClassModel> list = classService.selectAll(model);
		ArrayList<Map<String, Object>> List = new ArrayList<Map<String, Object>>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("classCode", "");
		map2.put("className","全部");
		List.add(map2);
		for(ClassModel classm : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("classCode", classm.getClassCode());
			map.put("className", classm.getClassName());
			List.add(map);
		}
		List.get(0).put("selected", true);
		HtmlUtil.writerJson(response, List);
		
	}
	//班级联动查询学生姓名
	@RequestMapping("/adminSelectClassStName.do")
	public void liandongSelectName(ClassModel model,HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		UserModel user=new UserModel();
		user.setClassCode(model.getClassCode());
		List<UserModel> userNameList = userService.selectAll(user);
		ArrayList<UserModel> stList = new ArrayList<UserModel>();
		for(UserModel u:userNameList) {  //去掉非学生用户
			String classcode = u.getClassCode();
			if(!FormatEmpty.isEmpty(classcode)) {
				stList.add(u);
			}
		}
		ArrayList<Map<String, Object>> List = new ArrayList<Map<String, Object>>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("userName", "");
		map2.put("trueName","全部");
		List.add(map2);
		for(UserModel userName : stList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName", userName.getUserName());
			map.put("trueName", userName.getTrueName());
			List.add(map);
		}
		List.get(0).put("selected", true);
		HtmlUtil.writerJson(response, List);
		
	}
}
