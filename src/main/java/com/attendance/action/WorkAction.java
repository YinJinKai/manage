package com.attendance.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.attendance.model.WorkModel;
import com.attendance.service.WorkService;
import com.system.model.RoleModel;
import com.system.model.UserModel;
import com.system.service.RoleService;
import com.system.service.UserService;
import com.utill.Constant;
import com.utill.JudgeCheck;
import com.utill.SignModel;
import com.utill.Tools;

import net.sf.json.JSONObject;
import st.core.session.HtmlUtil;
import st.core.session.JSONUtil;
import st.tool.FormatEmpty;

@Controller
@RequestMapping("/attendance")
public class WorkAction {
	@Autowired
	private WorkService<WorkModel> workService;
	@Autowired
	private UserService<UserModel> userService;
	@Autowired
	private RoleService<RoleModel> roleService;
	//统计考勤、
	@RequestMapping("/selecttable.do")
	public void selectWorkCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		WorkModel w=new WorkModel();
		List<WorkModel> worklist = workService.selectAll(w);
		if(FormatEmpty.isEmpty(worklist)) {
			return;
		}
		HashSet<String> hash=new HashSet<String>();//日期去重
		for(WorkModel m:worklist) {
			hash.add(m.getTime());
		}
		List<String> timelist=new ArrayList<String>();//日期集合
		for(String m:hash) {
			timelist.add(m);
		}
		List<String> queqinlist=new ArrayList<String>(); //每日缺勤人数
		List<String> zhengchanglist=new ArrayList<String>();//每日正常出勤人数
		List<String> qingjialist=new ArrayList<String>();//每日请假人数
		for(String m:timelist) {
			WorkModel w2=new WorkModel();
			w2.setTime(m);//一天的时间
			List<WorkModel> alllist = workService.selectAll(w2);
			Map<String, Object> resul =getData(alllist);
			String queqin = resul.get("queqin")+"";
			String zhengchang =resul.get("zhengchang")+"";
			String qingjia = resul.get("qingjia")+"";
			queqinlist.add(queqin);
			zhengchanglist.add(zhengchang);
			qingjialist.add(qingjia);
		}
		
		
		data.put("timeList", timelist);
		data.put("queqinlist", queqinlist);
		data.put("zhengchanglist", zhengchanglist);
		data.put("qingjialist", qingjialist);
		HtmlUtil.writerJson(response,data);
	
	}
	//获得每天的考勤统计
	private Map<String,Object> getData(List<WorkModel> list){
		if(FormatEmpty.isEmpty(list)) {
			return null;
		}
		int queqinCount=0;
		int zhengchangCount=0;
		int qingjiaCount=0;
		for (WorkModel m:list) {
			String one = m.getOneCheck();
			String two = m.getTwoCheck();
			String three = m.getThreeCheck();
			String four = m.getFourCheck();
			String five = m.getFiveCheck();
			String six = m.getSixCheck();
			List<String> panduan=new ArrayList<String>();
			panduan.add(one);
			panduan.add(two);
			panduan.add(three);
			panduan.add(four);
			panduan.add(five);
			panduan.add(six);
			int zc=0;
            for(String m2:panduan) { //一条记录的考勤值
				zc=0;
            	if("0".equals(m2)) {
					queqinCount+=1;
					break;
				}
				if("2".equals(m2)) {
					qingjiaCount+=1;
					break;
				}
				if("1".equals(m2)) {
					zc+=1;
					continue;
				}
			}
            zhengchangCount+=zc;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("queqin", queqinCount);
		map.put("zhengchang", zhengchangCount);
		map.put("qingjia", qingjiaCount);
		return map;
		
		
	}
	// 学生跳转到考勤页面
	@RequestMapping("/select.shtml")
	public ModelAndView toWorkPage(HttpServletRequest request, HttpServletResponse response) {
		String userName = (String) request.getSession().getAttribute("userName");
		ModelAndView m = new ModelAndView();
		m.addObject("userName", userName);
		m.setViewName("/page/studentAttendance");
		return m;

	}
	// 学生跳转到考勤页面
	@RequestMapping("/teachselect.shtml")
	public ModelAndView toTeachWorkPage(HttpServletRequest request, HttpServletResponse response) {
		String userName = (String) request.getSession().getAttribute("userName");
		ModelAndView m = new ModelAndView();
		m.addObject("userName", userName);
		m.setViewName("/page/teachadminAttendance");
		return m;

	}
	// 加载学生个人考勤信息
	@RequestMapping("/loadinfor.do")
	public void loadStudentWork(WorkModel work, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userName = (String) request.getSession().getAttribute("userName");
		WorkModel user = new WorkModel();
		user.setUserName(userName);
		user.setTime(work.getTime());
		List<WorkModel> inforList = workService.selectAll(user);
		if (FormatEmpty.isEmpty(inforList)) {
			HtmlUtil.writerJson(response, inforList);
			return;
		}
		for (WorkModel m : inforList) {
			String one = m.getOneCheck();
			String two = m.getTwoCheck();
			String three = m.getThreeCheck();
			String four = m.getFourCheck();
			String five = m.getFiveCheck();
			String six = m.getSixCheck();
			if ("1".equals(one)) {
				m.setOneCheck(Constant.attend_Y);
			} else if("0".equals(one)){
				m.setOneCheck(Constant.attend_N);
			}else if("2".equals(one)) {
				m.setOneCheck(Constant.qingjia);
			}
			
			if ("1".equals(two)) {
				m.setTwoCheck(Constant.attend_Y);
			} else if("0".equals(two)){
				m.setTwoCheck(Constant.attend_N);
			}else if("2".equals(one)) {
				m.setTwoCheck(Constant.qingjia);
			}
			
			if ("1".equals(three)) {
				m.setThreeCheck(Constant.attend_Y);
			} else if("0".equals(three)){
				m.setThreeCheck(Constant.attend_N);
			}else if("2".equals(three)) {
				m.setThreeCheck(Constant.qingjia);
			}
			if ("1".equals(four)) {
				m.setFourCheck(Constant.attend_Y);
			} else if("0".equals(four)){
				m.setFourCheck(Constant.attend_N);
			}else if("2".equals(four)) {
				m.setFourCheck(Constant.qingjia);
			}
			if ("1".equals(five)) {
				m.setFiveCheck(Constant.attend_Y);
			} else if("0".equals(five)){
				m.setFiveCheck(Constant.attend_N);
			}else if("2".equals(five)) {
				m.setFiveCheck(Constant.qingjia);
			}
			if ("1".equals(six)) {
				m.setSixCheck(Constant.attend_Y);
			} else if("0".equals(six)){
				m.setSixCheck(Constant.attend_N);
			}else if("2".equals(six)) {
				m.setSixCheck(Constant.qingjia);
			}
		}
		HtmlUtil.writerJson(response, inforList);
	}
   //教务查询学生考勤
	@RequestMapping("/adminloadinfor.do")
	public void adminloadStudentWork(WorkModel work, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String startTime = work.getStartTime();
		String endTime = work.getEndTime();
	
		if(FormatEmpty.isEmpty(startTime)) {
			startTime="0000-00-00 00:00:00";
		}
       if(FormatEmpty.isEmpty(endTime)) {
       	endTime="9999-99-99 00:00:00";
		}
       work.setStartTime(startTime);
       work.setEndTime(endTime);
		List<WorkModel> inforList = workService.selectAll(work);
		if (FormatEmpty.isEmpty(inforList)) {
			HtmlUtil.writerJson(response, inforList);
			return;
		}
		for (WorkModel m : inforList) {
			String one = m.getOneCheck();
			String two = m.getTwoCheck();
			String three = m.getThreeCheck();
			String four = m.getFourCheck();
			String five = m.getFiveCheck();
			String six = m.getSixCheck();
			if ("1".equals(one)) {
				m.setOneCheck(Constant.attend_Y);
			} else if("0".equals(one)){
				m.setOneCheck(Constant.attend_N);
			}else if("2".equals(one)) {
				m.setOneCheck(Constant.qingjia);
			}
			
			if ("1".equals(two)) {
				m.setTwoCheck(Constant.attend_Y);
			} else if("0".equals(two)){
				m.setTwoCheck(Constant.attend_N);
			}else if("2".equals(one)) {
				m.setTwoCheck(Constant.qingjia);
			}
			
			if ("1".equals(three)) {
				m.setThreeCheck(Constant.attend_Y);
			} else if("0".equals(three)){
				m.setThreeCheck(Constant.attend_N);
			}else if("2".equals(three)) {
				m.setThreeCheck(Constant.qingjia);
			}
			if ("1".equals(four)) {
				m.setFourCheck(Constant.attend_Y);
			} else if("0".equals(four)){
				m.setFourCheck(Constant.attend_N);
			}else if("2".equals(four)) {
				m.setFourCheck(Constant.qingjia);
			}
			if ("1".equals(five)) {
				m.setFiveCheck(Constant.attend_Y);
			} else if("0".equals(five)){
				m.setFiveCheck(Constant.attend_N);
			}else if("2".equals(five)) {
				m.setFiveCheck(Constant.qingjia);
			}
			if ("1".equals(six)) {
				m.setSixCheck(Constant.attend_Y);
			} else if("0".equals(six)){
				m.setSixCheck(Constant.attend_N);
			}else if("2".equals(six)) {
				m.setSixCheck(Constant.qingjia);
			}
		}
		HtmlUtil.writerJson(response, inforList);
	}
	// 加载时间的下拉框
	@RequestMapping("/loadTime.do")
	public void loadTime(WorkModel work, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String userName = (String) request.getSession().getAttribute("userName");
		WorkModel user = new WorkModel();
		user.setUserName(userName);
		List<WorkModel> inforList = workService.selectAll(user);
		ArrayList<Map<String, Object>> List = new ArrayList<Map<String, Object>>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("timeValue", "");
		map2.put("timeText", "查询全部");
		List.add(map2);
		for (WorkModel p : inforList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("timeValue", p.getTime());
			map.put("timeText", p.getTime());
			List.add(map);
		}
		HtmlUtil.writerJson(response, List);
	}
//	用户签到
	@RequestMapping("/insertSign.do")
	public void insertSign(SignModel sign,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String userName = (String) request.getSession().getAttribute("userName");
		String nowDoday = Tools.todaydate();
		String checkTime=sign.getTimeName();//判断签到时间
		if(FormatEmpty.isEmpty(checkTime)) {
			return;                      //解决加载页面时如果已签到 调用onchange时间出现的问题
		}
		String status=sign.getStatus();//状态
		WorkModel work=new WorkModel();
		work.setUserName(userName);
		work.setTime(nowDoday);//当天时间
		List<WorkModel> check = workService.selectAll(work);//检查今天签没签到
		if(FormatEmpty.isEmpty(check)) {
		  WorkModel signWork = JudgeCheck.judgeCheck(checkTime, status);//判断签到时间传值
		  signWork.setUserName(userName);
		  signWork.setTime(nowDoday);
		  signWork.setScore(Integer.parseInt(Constant.workscore));//初始得分
		  workService.insert(signWork);
			 return;
		}
		
		Integer score = check.get(0).getScore();
		score+=Integer.parseInt(Constant.workscore);//签到一次加10分
		Integer sighId = check.get(0).getId();
		if(FormatEmpty.isEmpty(sighId)) {
			return;
		}
		WorkModel signWork = JudgeCheck.updateJudgeCheck(checkTime, status);
		signWork.setId(sighId);
		signWork.setScore(score);
		workService.update(signWork);
		 return;

	}
	// 创建今天的考勤
		@RequestMapping("/createAll.do")
		@ResponseBody
		public String createAllWork(HttpServletRequest request,HttpServletResponse response) throws Exception {
			String time = Tools.todaydate();
			UserModel user=new UserModel();
			WorkModel wm=new WorkModel();
			wm.setTime(time);
			wm.setType(1);
			wm.setOneCheck("0");
			wm.setTwoCheck("0");
			wm.setThreeCheck("0");
			wm.setFourCheck("0");
			wm.setFiveCheck("0");
			wm.setSixCheck("0");
			wm.setScore(0);
			List<UserModel> list = userService.selectAllst(user);
			if(FormatEmpty.isEmpty(list)) {
				return "0";
			}
			for(UserModel um: list ) {
				wm.setUserName(um.getUserName());
				WorkModel isre=new WorkModel();
				isre.setUserName(um.getUserName());
				isre.setTime(time);
				List<WorkModel> islist = workService.selectAll(isre);//检查同学是否提前打卡
				if(FormatEmpty.isEmpty(islist)) {
					workService.insert(wm);
				}
				
			}
			
			
			
			
			
			
			
			
			
			return "1";
			
			
			
			
			
		}
	@RequestMapping("/updatestateTime.do")
	@ResponseBody
	public String updatestateTime(WorkModel m,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(FormatEmpty.isEmpty(m.getId())) {
			return "0";
		}
		m.setOneCheck(m.getQingjia());
		m.setTwoCheck(m.getQingjia());
		m.setThreeCheck(m.getQingjia());
		m.setFourCheck(m.getQingjia());
		m.setFiveCheck(m.getQingjia());
		m.setSixCheck(m.getQingjia());
		workService.update(m);
		return "1";
	}
	// 加载签到时间
	@RequestMapping("/loadQianTime.do")
	public void loadQianTime(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String status="";
		String userName = (String) request.getSession().getAttribute("userName");
		WorkModel work=new WorkModel();
		String nowDoday = Tools.todaydate();
		work.setUserName(userName);
		work.setTime(nowDoday);//用于查询签没签到
		ArrayList<SignModel> List = new ArrayList<SignModel>();
		SignModel sign = new SignModel();
		String first = "08:00:00";
		String amfirst = "08:30:00";
		String amSecond1 = "12:00:00";
		String amSecond2 = "12:30:00";
		String pmfirst = "13:30:00";
		String pmSecond1 = "17:30:00";
		String pmSecond2 = "18:00:00";
		String ngfirst = "19:00:00";
		String ngSecond1 = "21:00:00";
		String now = Tools.shortdate();
		int firstSign = now.compareTo(first);
		if(firstSign < 0) {
			sign.setStatus(status);
			sign.setSignName("打卡时间未到...");
			sign.setTimeName("");
			List.add(sign);
			HtmlUtil.writerJson(response, List);
			return;
		}
		int firstScore = now.compareTo(amfirst);// 上午第一次8:00:00开始  08:30:00 之前
		if (firstScore < 0 && firstSign > 0) {
			List<WorkModel> check = workService.selectAll(work);
			if(!FormatEmpty.isEmpty(check)) {
				 status = check.get(0).getOneCheck();
			}
			sign.setStatus(status);
			sign.setSignName("上午第一次");
			sign.setTimeName("oneCheck");
			List.add(sign);
			HtmlUtil.writerJson(response, List);
			return;

		}
		int first2Score = now.compareTo(amSecond2);// 上午第二次12:00:00之后 12:30:00 之前
		if (first2Score < 0) {
			int first1 = now.compareTo(amSecond1);
			int first2 = now.compareTo(amSecond2);
			if (first1 >= 0 && first2 <= 0) {
				List<WorkModel> check = workService.selectAll(work);
				if(!FormatEmpty.isEmpty(check)) {
					 status = check.get(0).getTwoCheck();
				}
				sign.setStatus(status);
				sign.setSignName("上午第二次");
				sign.setTimeName("twoCheck");
				List.add(sign);
				HtmlUtil.writerJson(response, List);
				return;
			}else {
				sign.setStatus("0");
				sign.setSignName("打卡时间已过");
				sign.setTimeName("");
				List.add(sign);
				HtmlUtil.writerJson(response, List);
				return;
			}
		}
		int secondScore = now.compareTo(pmfirst);// 下午第一次 13:30:00 之前
		if (secondScore < 0) {
			List<WorkModel> check = workService.selectAll(work);
			if(!FormatEmpty.isEmpty(check)) {
				 status = check.get(0).getThreeCheck();
			}
			sign.setStatus(status);
			sign.setSignName("下午第一次");
			sign.setTimeName("threeCheck");
			List.add(sign);
			
			HtmlUtil.writerJson(response, List);
			return;
		}
		int second2Score = now.compareTo(pmSecond2);// 下午第二次 18:00:00 之前
		if(second2Score<0) {
			int second1 = now.compareTo(pmSecond1);
			int second2 = now.compareTo(pmSecond2);
			if (second1 >= 0 && second2 <= 0) {
				List<WorkModel> check = workService.selectAll(work);
				if(!FormatEmpty.isEmpty(check)) {
					 status = check.get(0).getFourCheck();
				}
				sign.setStatus(status);
				sign.setSignName("下午第二次");
				sign.setTimeName("fourCheck");
				List.add(sign);
				HtmlUtil.writerJson(response, List);
				return;
			}else {
				sign.setStatus("0");
				sign.setSignName("打卡时间已过");
				sign.setTimeName("");
				List.add(sign);
				HtmlUtil.writerJson(response, List);
				return;
			}
		}
	
		int threeScore = now.compareTo(ngfirst); // 晚上第一次  19:00:00 之前
		if (threeScore< 0) {
			List<WorkModel> check = workService.selectAll(work);
			if(!FormatEmpty.isEmpty(check)) {
				 status = check.get(0).getFiveCheck();
			}
			sign.setStatus(status);
			sign.setSignName("晚上第一次");
			sign.setTimeName("fiveCheck");
			List.add(sign);
			HtmlUtil.writerJson(response, List);
			return;
		}
		int three1 = now.compareTo(ngSecond1);// 晚上第二次  21:00:00之后
		if (three1>0) {
			List<WorkModel> check = workService.selectAll(work);
			if(!FormatEmpty.isEmpty(check)) {
				 status = check.get(0).getSixCheck();
			}
			sign.setStatus(status);
			sign.setSignName("晚上第二次");
			sign.setTimeName("sixCheck");
			List.add(sign);
			HtmlUtil.writerJson(response, List);
			return;
		}else {
			sign.setStatus("0");
			sign.setSignName("打卡时间已过");
			sign.setTimeName("");
			List.add(sign);
			HtmlUtil.writerJson(response, List);
			return;
		}
	

	}

}
