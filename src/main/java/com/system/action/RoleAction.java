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
import org.springframework.web.servlet.ModelAndView;

import com.system.mapper.RoleMapper;
import com.system.model.RoleModel;
import com.system.model.UserModel;
import com.system.service.RoleService;

import st.core.session.HtmlUtil;

@Controller
@RequestMapping("role")
public class RoleAction {
@Autowired
private RoleService<RoleModel> roleService;
//菜单管理
@RequestMapping("/select.shtml")
public ModelAndView adminPage(UserModel user, HttpServletRequest request, HttpServletResponse response)
		throws Exception {

	ModelAndView m = new ModelAndView();
	m.setViewName("page/adminMenu");
	return m;

}
@RequestMapping("/loadper.do")
public void loadperssion(RoleModel m,HttpServletRequest request,HttpServletResponse response) throws Exception {
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	List<RoleModel> rolelist = roleService.selectAll(m);
	ArrayList<Map<String, Object>> List = new ArrayList<Map<String, Object>>();
	Map<String, Object> map2 = new HashMap<String, Object>();
	map2.put("rolevalue", "");
	map2.put("roletext","全部");
	List.add(map2);
	for(RoleModel role: rolelist) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rolevalue", role.getId());
		map.put("roletext", role.getRoleName());
		List.add(map);
	}
	List.get(0).put("selected", true);
	HtmlUtil.writerJson(response, List);
}
}
