package com.system.action;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.system.model.ClassModel;
import com.system.model.DataModel;
import com.system.model.RoleModel;
import com.system.model.UserModel;
import com.system.service.ClassService;
import com.system.service.RoleService;
import com.system.service.UserService;
import com.utill.LoadData;

import st.core.session.JSONUtil;
import st.tool.FormatEmpty;

@Controller
@RequestMapping("addUser")
public class AddUserAction {
	@Autowired
	private UserService<UserModel> userService;
	@Autowired
	private RoleService<RoleModel> roleService;
	@Autowired
	private ClassService<ClassModel> classService;
    
	@RequestMapping("/select.shtml")
	public ModelAndView toaddUserPage(UserModel user, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ModelAndView m = new ModelAndView();
		m.setViewName("/page/adminAddUser");
		return m;

	}
    @RequestMapping("/exportAll.do")
	public @ResponseBody String exportAll(UserModel m,HttpServletRequest request,HttpServletResponse response) {
    	System.out.println(m.getRoleCode());
		response.setContentType("application/binary;charset=UTF-8");
		try {
			ServletOutputStream out = response.getOutputStream();
			String fileName = new String(
					("UserInfo " + new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getBytes(), "UTF-8");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
			exportall(out,m.getRoleCode());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "导出信息失败";
		}
	}
    private void exportall(ServletOutputStream out,Integer roleCode) throws Exception {
        try {
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow hssfRow = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
            // 居中样式
            hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFCell hssfCell = null;
            String[] titles = {"Id", "用户账号", "用户姓名", "用户密码", "角色","班级"};
            for (int i = 0; i < titles.length; i++) {
                hssfCell = hssfRow.createCell(i);// 列索引从0开始
                hssfCell.setCellValue(titles[i]);// 列名1
                hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
            }

            // 第五步，写入实体数据
            
            UserModel user=new UserModel();
            user.setRoleCode(roleCode);
            user.setMysqlQueryCondition("");
            List<UserModel> list = userService.selectAll(user);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // if (users != null && !users.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                hssfRow = hssfSheet.createRow(i + 1);
                UserModel userinfor = list.get(i);

                // 第六步，创建单元格，并设置值
                int userid = 0;
                if (userinfor.getId() != 0) {
                    userid = userinfor.getId();
                }
                hssfRow.createCell(0).setCellValue(userid);
                String username = "";
                if (userinfor.getUserName() != null) {
                    username = userinfor.getUserName();
                }
                hssfRow.createCell(1).setCellValue(username);
                String truename = "";
                if (userinfor.getTrueName() != null) {
                	truename = userinfor.getTrueName();
                }
                hssfRow.createCell(2).setCellValue(truename);
                String password = "";
                if (userinfor.getPassword() != "") {
                	password = userinfor.getPassword();
                }
                hssfRow.createCell(3).setCellValue(password);
                String rolename="";
                if(userinfor.getRoleName()!="") {
                	rolename=userinfor.getRoleName();
                }
                hssfRow.createCell(4).setCellValue(rolename);
                String classname="";
                if(userinfor.getClassName()!="") {
                	classname=userinfor.getClassName();
                }
                hssfRow.createCell(5).setCellValue(classname);
            }
            // }

            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("导出信息失败！");
        }
    }
	@RequestMapping("/export.do")
	public @ResponseBody String export(HttpServletResponse response) {
		response.setContentType("application/binary;charset=UTF-8");
		try {
			ServletOutputStream out = response.getOutputStream();
			String fileName = new String(
					("UserInfo " + new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getBytes(), "UTF-8");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
			export(out);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "导出信息失败";
		}
	}

	// 创建学生录入表
	private void export(ServletOutputStream out) throws Exception {
		try {
			// 第一步，创建一个workbook，对应一个Excel文件
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet hssfSheet = workbook.createSheet("sheet1");
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow hssfRow = hssfSheet.createRow(0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
			// 居中样式
			hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFCell hssfCell = null;
			String[] titles = { "用户账号", "用户姓名", "用户密码", "所在班级","属性" };
			for (int i = 0; i < titles.length; i++) {
				hssfCell = hssfRow.createCell(i);// 列索引从0开始
				hssfCell.setCellValue(titles[i]);// 列名1
				hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
			}

			// 第七步，将文件输出到客户端浏览器
			try {
				workbook.write(out);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("导出信息失败！");
		}
	}

	// 导入
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String batchimport(@RequestParam(value = "filename") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		// 判断文件是否为空
		if (file == null)
			return null;
		// 获取文件名
		String name = file.getOriginalFilename();
		// 进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
		long size = file.getSize();
		if (name == null || ("").equals(name) && size == 0)
			return null;

		// 批量导入。参数：文件名，文件。
		String b = batchImport(name, file);
		if ("1".equals(b)) {
			String Msg = "批量导入EXCEL成功！";
			String tip = "1";
			request.getSession().setAttribute("msg", Msg);
			request.getSession().setAttribute("tip", tip);
		} else {
			String Msg = "批量导入EXCEL失败！";
			String Msg2 = b;
			String tip = "0";
			request.getSession().setAttribute("msg", Msg);
			request.getSession().setAttribute("msg2", Msg2);
			request.getSession().setAttribute("tip", tip);
		}
		model.addAttribute("fileUrl", "aaa");
		return "/page/question/addUserSuccess";
	}

	// 批量导入客户
	private String batchImport(String name, MultipartFile file) throws Exception {
		String b = "0";
		try {
			// 也可以用request获取上传文件
			// MultipartFile fileFile = request.getFile("file"); //这里是页面的name属性
			// 转换成输入流
			InputStream is = file.getInputStream();
			// 得到excel
			HSSFWorkbook workbook = new HSSFWorkbook(is);
			// 得到sheet
			HSSFSheet sheet = workbook.getSheetAt(0);
			// 得到行数
			int firstRow = sheet.getFirstRowNum();
			int lastRow = sheet.getLastRowNum();
			List<List<String>> list = new ArrayList<List<String>>();// 封装信息
			for (int i = firstRow + 1; i < lastRow + 1; i++) {

				HSSFRow row = sheet.getRow(i);
				int firstCell = row.getFirstCellNum();
				int lastCell = row.getLastCellNum();
				List<String> celllist = new ArrayList<String>();// 读取信息
				for (int j = firstCell; j < lastCell; j++) {

					HSSFCell cell = row.getCell(j);

					if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					}
					String val = cell.getStringCellValue();
					celllist.add(val);

				}
				list.add(celllist);
			}
			List<UserModel> userlist = new ArrayList<UserModel>();
			boolean a=true;
			String isreuser="";
			for (List<String> m : list) {
				UserModel user = new UserModel();
				user.setUserName(m.get(0));
				List<UserModel> qureisre = userService.selectAll(user);
				if(!FormatEmpty.isEmpty(qureisre)) {
					isreuser=qureisre.get(0).getUserName();
					a=false;
					break;
				}
				user.setTrueName(m.get(1));
				user.setPassword(m.get(2));
				user.setClassName(m.get(3));
				user.setRoleName(m.get(4));
				userlist.add(user);
			}
			if(!a) {
				return isreuser;//检验账号重复 返回错误信息
			}
			
			for (UserModel u : userlist) {
				RoleModel role=new RoleModel();
				role.setRoleName(u.getRoleName());
				List<RoleModel> rolelist = roleService.selectAll(role);//获得rolecode
				Integer id = rolelist.get(0).getId();
				ClassModel classcode=new ClassModel();
				classcode.setClassName(u.getClassName());
				List<ClassModel> classcodelist = classService.selectAll(classcode);//获得classcode
				String clcode = classcodelist.get(0).getClassCode();
				u.setRoleCode(id);
				u.setClassCode(clcode);
				userService.insert(u);	// 添加到数据库
			}

		
			b = "1";//上传成功
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}
}
