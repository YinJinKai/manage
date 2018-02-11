package com.system.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.system.model.DataModel;
import com.system.model.RoleModel;
import com.system.model.UserModel;
import com.system.service.DataService;
import com.system.service.RoleService;
import com.system.service.UserService;
import com.utill.LoadData;

import net.sf.json.JSONObject;
import st.core.session.HtmlUtil;
import st.core.session.JSONUtil;
import st.tool.FormatEmpty;

@Controller
@RequestMapping("/user")
public class UserAction {
	@Autowired
	private UserService<UserModel> userService;
	@Autowired
	private DataService<DataModel> dataService;
	@Autowired
	private RoleService<RoleModel> roleService;
	@RequestMapping(value="/login.do",produces = "application/text;charset=utf-8")
	@ResponseBody
	public String login(UserModel user,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String userName=user.getUserName();
		String password=user.getPassword();
		Integer roleCode=user.getRoleCode();

		if(FormatEmpty.isEmpty(userName)) {
			return null;
		}
		if(FormatEmpty.isEmpty(password)) {
            return null;
		}
		UserModel selectUser=new UserModel();
		selectUser.setUserName(userName);
		selectUser.setPassword(password);
		List<UserModel> qureyUser = userService.selectAll(selectUser);
		int length = qureyUser.size();
	   
		if(FormatEmpty.isEmpty(qureyUser)) {
			return "0";
		}
		if(length>1) {
		    return null;
		}
		Integer role2=qureyUser.get(0).getRoleCode();
		if(!role2.equals(roleCode)) { //判断登录角色
			return "a";
		}
		String rol=role2+"";
		String role=String.valueOf(rol);
		request.getSession().setAttribute("userName", userName);
		request.getSession().setAttribute("trueName", qureyUser.get(0).getTrueName());
		request.getSession().setAttribute("role", role);
		return role;
		
		
	}
	@RequestMapping("/getlogininguser.do")
	public void getLoginingUser(HttpServletRequest request,HttpServletResponse response) {
		ServletContext context = request.getSession().getServletContext();  
		String count = context.getAttribute("lineCount")+"";
		Map<String, Object> data = new HashMap<String, Object>();
		ArrayList<Map<String, Object>> List = new ArrayList<Map<String, Object>>();
		Map<String, Object> dataScorp = new HashMap<String, Object>();
		dataScorp.put("value", count);
		dataScorp.put("name", "在线人数");
		List.add(dataScorp);
		data.put("userdata", List);
		HtmlUtil.writerJson(response, data);
		
	}
	@RequestMapping("/getAlllogininguser.do") //没用
	public String getAllLoginingUser(HttpServletRequest request,HttpServletResponse response) {
		ServletContext context = request.getSession().getServletContext();  
		String count = (String) context.getAttribute("alllineCount");
		System.out.println("总访问量"+count);
		return count;
		
	}
	@RequestMapping("/getSession.do")
	
	public void getSession(UserModel user,HttpServletRequest request,HttpServletResponse response) throws Exception {
	 String userinfor = (String) request.getSession().getAttribute("userName");
	 user.setUserName(userinfor);
	 List<UserModel> qureyUser = userService.selectAll(user);
	 HtmlUtil.writerJson(response, qureyUser);
		
	}
	@RequestMapping("/index.shtml")
	public ModelAndView toindexPage(UserModel user,HttpServletRequest request,HttpServletResponse response) throws Exception {
		DataModel data =new DataModel();
		Object trueName = request.getSession().getAttribute("trueName");
		List<DataModel> baseDataList = dataService.selectAll(data);
		LoadData.Load(baseDataList);//加载字典表
		ModelAndView m = new ModelAndView();
		Integer roleCode=user.getRoleCode();
		m.addObject("roleCode", roleCode);
		m.addObject("trueName", trueName);
		m.setViewName("index");
		return m;
		
	}
	@RequestMapping("/loadinfor.do")
	public void loaduserifor(UserModel user,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String userName=(String) request.getSession().getAttribute("userName");
		user.setUserName(userName);
		List<UserModel> userifor = userService.selectAll(user);
		HtmlUtil.writerJson(response, userifor);
	}
	@RequestMapping("/selectAllUser.do")
	public void selectAllUser(UserModel user,HttpServletRequest request,HttpServletResponse response) throws Exception {
	int count = userService.selectCount(user);
	user.getPager().setRowCount(count);
		List<UserModel> userlist = userService.selectAll(user);
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		jsonMap.put("total", count);
		jsonMap.put("rows", userlist);
		JSONObject result = JSONObject.fromObject(jsonMap);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result.toString());
		out.flush();
		out.close();
	}
	//退出登录
	@RequestMapping("/loginout.shtml")
	public ModelAndView loginout(UserModel user,HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute("userName");
		ModelAndView m = new ModelAndView();
		m.setViewName("login");
		return m;
	}
	//跳转修改页
	@RequestMapping("/select.shtml")
	public ModelAndView userupdate(UserModel user,HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute("userName");
		ModelAndView m = new ModelAndView();
		m.setViewName("page/userUpdate");
		return m;
	}
	@RequestMapping("/updateuser.do")
	public void updateuser(UserModel user,HttpServletRequest request,HttpServletResponse response) throws Exception {
		userService.update(user);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("1");
		out.flush();
		out.close();
	}
	@RequestMapping("/statistics.do")
	public void statisticsuser(UserModel user,HttpServletRequest request,HttpServletResponse response) throws Exception {
		int count = userService.selectCount(user);
		System.out.println(count);
		RoleModel role=new RoleModel();
		List<RoleModel> rolelist = roleService.selectAll(role);
		List<UserModel> countlist=new ArrayList<UserModel>();
		for(RoleModel r:rolelist) {
			UserModel userinfor=new UserModel();
			userinfor.setRoleCode(r.getId());
			userinfor.setRoleName(r.getRoleName());
			int childcount = userService.selectCount(userinfor);
			userinfor.setUserCount(childcount);
			countlist.add(userinfor);
		}
		 // 创建一个数值格式化对象  
		NumberFormat numberFormat = NumberFormat.getInstance();  
		  // 设置精确到小数点后2位  
  	    numberFormat.setMaximumFractionDigits(2);  
  	    Map<String, Object> data = new HashMap<String, Object>();
		ArrayList<Map<String, Object>> xueshengList = new ArrayList<Map<String, Object>>();
		ArrayList<Map<String, Object>> jiangshiList = new ArrayList<Map<String, Object>>();
		ArrayList<Map<String, Object>> jiaowuList = new ArrayList<Map<String, Object>>();
  	    List<String> resultCount=new ArrayList<String>();
        for(UserModel i:countlist) {
        	 String result = numberFormat.format((float) i.getUserCount() / (float) count * 100); 
        	 resultCount.add(result+"%"+i.getRoleName());
    		Map<String, Object> dataScorp = new HashMap<String, Object>();
        	Map<String, Object> dataScorp2 = new HashMap<String, Object>();
            	if(i.getRoleCode()==1) {
            		dataScorp.put("value",result);
            		dataScorp.put("name", result+"%"+i.getRoleName());
            		dataScorp2.put("value", 100-Integer.parseInt(result));
            		dataScorp2.put("name", "其他角色");
            		dataScorp2.put("itemStyle", "placeHolderStyle");
            		xueshengList.add(dataScorp);
            		xueshengList.add(dataScorp2);
            	}
            	if(i.getRoleCode()==2) {
            		dataScorp.put("value",result);
            		dataScorp.put("name", result+"%"+i.getRoleName());
            		dataScorp2.put("value", 100-Integer.parseInt(result));
            		dataScorp2.put("name", "其他角色");
            		dataScorp2.put("itemStyle", "placeHolderStyle");
            		jiangshiList.add(dataScorp);
            		jiangshiList.add(dataScorp2);
            	}
            	if(i.getRoleCode()==3) {
            		dataScorp.put("value",result);
            		dataScorp.put("name", result+"%"+i.getRoleName());
            		dataScorp2.put("value", 100-Integer.parseInt(result));
            		dataScorp2.put("name", "其他角色");
            		dataScorp2.put("itemStyle", "placeHolderStyle");
            		jiaowuList.add(dataScorp);
            		jiaowuList.add(dataScorp2);
            	}
        }
		data.put("userCountInfor", resultCount);
		data.put("xuesheng", xueshengList);
		data.put("jiangshi", jiangshiList);
		data.put("jiaowu", jiaowuList);
		System.out.println(JSONUtil.toJSONString(data));
		HtmlUtil.writerJson(response, data);
		
		
		
		
		
		
		
		
		
		
		
	}
}
