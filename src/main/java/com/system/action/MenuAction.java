package com.system.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.system.model.MenuModel;
import com.system.model.RoleModel;
import com.system.model.RolePermissionRelModel;
import com.system.model.UserModel;
import com.system.service.MenuService;
import com.system.service.RolePermissionRelService;
import com.system.service.RoleService;
import com.utill.Tools;

import net.sf.json.JSONArray;
import st.core.session.HtmlUtil;
import st.core.session.JSONUtil;
import st.tool.FormatEmpty;

@Controller
@RequestMapping("/menu")
public class MenuAction {
	@Autowired
	private MenuService<MenuModel> menuService;
	@Autowired
	private RoleService<RoleModel> roleService;
	@Autowired
	private RolePermissionRelService<RolePermissionRelModel> rolePermissionRelService;

	@RequestMapping("/insert.do")
	@ResponseBody
	public String insertNewMenu(MenuModel menu, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MenuModel checkre=new MenuModel();
		checkre.setPermissionName(menu.getPermissionName());
		List<MenuModel> relist = menuService.selectAll(checkre);
		if(!FormatEmpty.isEmpty(relist)) {
			return "0";            //菜单名已存在
		}
		String code = "per" + Tools.datecode();
		menu.setMenuCode(code);
		menu.setPcCode("0");
		menuService.insert(menu);
		Integer rolecode = menu.getRoleCode();
		if (rolecode == -1) {// 全部都有该权限
			RoleModel role = new RoleModel();
			List<RoleModel> roleid = roleService.selectAll(role);
			for (RoleModel r : roleid) {
				RolePermissionRelModel rel = new RolePermissionRelModel();
				rel.setRoleCode(r.getId());
				rel.setMenuCode(code);
				rolePermissionRelService.insert(rel);
			}
		} else {
			RolePermissionRelModel rel = new RolePermissionRelModel();
			rel.setRoleCode(rolecode);
			rel.setMenuCode(code);
			rolePermissionRelService.insert(rel);
		}
		return "1";

	}

	@RequestMapping("/delete.do")
	@ResponseBody
	public String deleteMenu(MenuModel menu, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List<MenuModel> list = menuService.selectAll(menu);
		list.get(0).getMenuCode();
		menuService.delete(menu.getId());//删除菜单
		RolePermissionRelModel rel = new RolePermissionRelModel();

		rel.setMenuCode(list.get(0).getMenuCode());
		List<RolePermissionRelModel> rellist = rolePermissionRelService.selectAll(rel);
		if (FormatEmpty.isEmpty(rellist)) {
              return "0";
		}
		for (RolePermissionRelModel r : rellist) {
			rolePermissionRelService.delete(r.getId()); // 删除关联表的菜单
		}
		return "1";

	}

	@RequestMapping("/loadMenu.do")
	public void loadMenu(MenuModel menu, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer r = menu.getRoleCode();
		if (FormatEmpty.isEmpty(r)) {
			return;
		}
		MenuModel role = new MenuModel();
		role.setRoleCode(r);
		List<MenuModel> menuList = menuService.selectAll(role);
		HtmlUtil.writerJson(response, menuList);

	}

	@RequestMapping("/loadAllMenu.do")
	public void loadAllMenu(MenuModel menu, HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<MenuModel> menuList = menuService.selectAllmenu(menu);
		
		HtmlUtil.writerJson(response, menuList);

	}
	@RequestMapping("/selectAllMenu.do")
	public void selectAllMenu(MenuModel menu, HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<MenuModel> menuList = menuService.selectAll(menu);
		
		HtmlUtil.writerJson(response, menuList);

	}
	@RequestMapping("/updateMenu.do")
	@ResponseBody
	public String updatemenu(MenuModel menu, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(FormatEmpty.isEmpty(menu.getId())) {
			return "0";
		}
		MenuModel mm=new MenuModel();
		mm.setPermissionName(menu.getPermissionName());
		List<MenuModel> relist = menuService.selectAll(mm);
		if(!FormatEmpty.isEmpty(relist)) {
			Integer reid = relist.get(0).getId();
			if(reid.equals(menu.getId())) {
				menuService.update(menu);
				updateRel(menu,menu.getRoleCode());
				return "1";
			}
			return"2"; //修改的菜单名重复
		}
		menuService.update(menu);
		
		return "1";
		
	}
	private void updateRel(MenuModel menu,Integer roleCode) throws Exception {
	    if(roleCode==-1) {//修改为全部 单个修改为多个
	    	RoleModel role = new RoleModel();
			List<RoleModel> roleid = roleService.selectAll(role);//得到所有权限code
			MenuModel me=new MenuModel();
			me.setId(menu.getId());
			List<MenuModel> list = menuService.selectAll(me);//得到菜单code
			if(FormatEmpty.isEmpty(list)) {
				return;
			}
			RolePermissionRelModel rel=new RolePermissionRelModel();
			rel.setMenuCode(list.get(0).getMenuCode());//得到menuCode
			List<RolePermissionRelModel> idlist = rolePermissionRelService.selectAll(rel);//得到要修改的id
			for(RolePermissionRelModel idcore: idlist) {
				rolePermissionRelService.delete(idcore.getId());//删除
			}
			for (RoleModel r : roleid) {
				RolePermissionRelModel newrel = new RolePermissionRelModel();
				rel.setRoleCode(r.getId());
				rel.setMenuCode(list.get(0).getMenuCode());
				rolePermissionRelService.insert(rel); //删除后再插入
			}
	    }else {
	    	//单个修改 2种情况   单个修改为单个  多个修改为单个
	    	MenuModel me=new MenuModel();
			me.setId(menu.getId());
			List<MenuModel> list = menuService.selectAll(me);//得到菜单code
			if(FormatEmpty.isEmpty(list)) {
				return;
			}
			RolePermissionRelModel rel=new RolePermissionRelModel();
			rel.setMenuCode(list.get(0).getMenuCode());//得到menuCode
			List<RolePermissionRelModel> idlist = rolePermissionRelService.selectAll(rel);//得到要修改的id
			for(RolePermissionRelModel idcore: idlist) {
				rolePermissionRelService.delete(idcore.getId());//删除
			}
			RolePermissionRelModel insertagainrel=new RolePermissionRelModel();
			insertagainrel.setRoleCode(roleCode);
			insertagainrel.setMenuCode(list.get(0).getMenuCode());
	    	rolePermissionRelService.insert(insertagainrel); 
	    }
		
		
	
	}

}
