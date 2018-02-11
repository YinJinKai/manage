package com.question.action;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.question.model.CreateQuestionModel;
import com.question.model.QuestionNameQaRelModel;
import com.question.model.QuestionQaModel;
import com.question.service.CreateQuestionService;
import com.question.service.QuestionNameQaRelService;
import com.utill.Constant;
import com.utill.Tools;

import st.core.session.HtmlUtil;
import st.core.session.JSONUtil;
import st.tool.FormatEmpty;

@Controller
@RequestMapping("/qnqr")
public class QuestionNameQaRelAction {
	@Autowired
	private QuestionNameQaRelService<QuestionNameQaRelModel> questionNameQaRelService;
	@Autowired
	private CreateQuestionService<CreateQuestionModel> createQuestionService;
   
	// 跳到查看问卷库界面
	@RequestMapping("/select.shtml")
	public ModelAndView toshowQuestionPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView m = new ModelAndView();
		m.setViewName("/page/question/showQuestionText");
		return m;

	}

	@RequestMapping("/insert.do")
	public void insert(QuestionNameQaRelModel m, CreateQuestionModel cm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<CreateQuestionModel> list = createQuestionService.selectAll(cm);// 判断是新的还是旧的
		String userName = (String) request.getSession().getAttribute("userName");
		m.setOrderBy(0.00);
		if (FormatEmpty.isEmpty(list)) {// 第二次添加不走
			String cqc = "cq" + Tools.datecode();
			cm.setCreateQuestionCode(cqc);
			cm.setUserName(userName);
			createQuestionService.insert(cm);// 如果是新问卷插入创建问卷表(先加入问卷名再匹配题目)
			m.setCreateQuestionCode(cqc);
			String code = m.getCodelist();
			String[] idcode = code.split(",");
			for (String a : idcode) {
				m.setQuestionQaCode(a);
				List<QuestionNameQaRelModel> qurey = questionNameQaRelService.selectAll(m);
				if (FormatEmpty.isEmpty(qurey)) {
					questionNameQaRelService.insert(m);

				}
			}
			// 更新 修改orderBy
			QuestionNameQaRelModel ss=new QuestionNameQaRelModel();
			ss.setCreateQuestionCode(m.getCreateQuestionCode());
			List<QuestionNameQaRelModel> alist = questionNameQaRelService.selectAll(ss);
			ArrayList<QuestionNameQaRelModel> List = new ArrayList<QuestionNameQaRelModel>();// 得到id和生成的排序值

			int a = 0;// 生成orderBy
			for (QuestionNameQaRelModel p : alist) {
				QuestionNameQaRelModel check0 = new QuestionNameQaRelModel();
				a += 1;
				Double b = (double) a;
				check0.setId(p.getId());
				check0.setOrderBy(b);
				List.add(check0);
			}
			for (QuestionNameQaRelModel qq : List) {
				QuestionNameQaRelModel checkre = new QuestionNameQaRelModel();
				checkre.setId(qq.getId());
				checkre.setOrderBy(qq.getOrderBy());
				questionNameQaRelService.update(checkre);
			}
			ArrayList<QuestionNameQaRelModel> errorList = new ArrayList<QuestionNameQaRelModel>();// 用于返回
			HtmlUtil.writerJson(response, errorList);
			return;
		}
		String cqcode = m.getCreateQuestionCode();
		if (FormatEmpty.isEmpty(cqcode)) {// 解决如果添加一个新的问卷，添加第一次之后紧接着添加第二次（合理化判断）
			m.setCreateQuestionCode(list.get(0).getCreateQuestionCode());
		}

		// 因为orderBy的值先查一下，得到最后的orderBy值，进行累加
		m.setOrder("ASC");
		m.setSort("order_by");
		List<QuestionNameQaRelModel> alist = questionNameQaRelService.selectAll(m);
		Double order;
		if(FormatEmpty.isEmpty(alist)) {
			order=0.0;//关系表没有值
		}else {
			order = alist.get(alist.size() - 1).getOrderBy();
		}
		int ac = order.intValue();
		String code = m.getCodelist();
		String[] idcode = code.split(",");
		List<QuestionNameQaRelModel> isreList = new ArrayList<QuestionNameQaRelModel>();
		QuestionNameQaRelModel vm = new QuestionNameQaRelModel();// 检查重复题用
		vm.setCreateQuestionCode(m.getCreateQuestionCode());
		for (String a : idcode) {
			vm.setQuestionQaCode(a);
			List<QuestionNameQaRelModel> qurey = questionNameQaRelService.selectAll(vm);//
			ac += 1;
			Double b = (double) ac;
			m.setOrderBy(b);
			m.setQuestionQaCode(a);
			if (FormatEmpty.isEmpty(qurey)) {
				questionNameQaRelService.insert(m);
				continue;
			}
			isreList.add(qurey.get(0));// 重复的题目
		}

		HtmlUtil.writerJson(response, isreList);
	}

	@RequestMapping("selectQn.do")
	public void selectAll(QuestionNameQaRelModel m, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String co = m.getCreateQuestionCode(); // 都是对一个问卷的操作
		if (FormatEmpty.isEmpty(co)) {
			return;
		}
		m.setOrder("ASC");
		m.setSort("order_by");
		List<QuestionNameQaRelModel> list = questionNameQaRelService.selectAll(m);
		
		for (QuestionNameQaRelModel p : list) {
			String type = p.getQuestionQaInfor().getTmtype();
			if ("1".equals(type)) {
				p.getQuestionQaInfor().setTmtype(Constant.objective);

			} else{
				p.getQuestionQaInfor().setTmtype(Constant.subjective);

			}
		}
		for (QuestionNameQaRelModel p : list) {
			Double type = p.getOrderBy();
			p.setOrderByName("第" + type.intValue() + "项");
		}
		HtmlUtil.writerJson(response, list);
	}

	@RequestMapping("/deleteQn.do")
	public void delete(QuestionNameQaRelModel m, HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id = m.getIdlist();
		String[] idl = id.split(",");
		if (FormatEmpty.isEmpty(idl)) {
			return;
		}
		for (String i : idl) {
			questionNameQaRelService.delete(Integer.parseInt(i));
		}
		out.print("1");
		out.flush();
		out.close();
	}

	@RequestMapping("/update.do")
	public void update(QuestionNameQaRelModel m, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id = m.getIdlist();
		String orderby = m.getOrderBylist();
		String[] idl = id.split(",");
		String[] orderbyl = orderby.split(",");
		if (FormatEmpty.isEmpty(orderbyl)) {
			return;
		}
		if (FormatEmpty.isEmpty(idl)) {
			return;
		}
		ArrayList<QuestionNameQaRelModel> List = new ArrayList<QuestionNameQaRelModel>();
		QuestionNameQaRelModel newm = new QuestionNameQaRelModel();
		newm.setId(Integer.parseInt(idl[0]));
		newm.setOrderBy(Double.parseDouble(idl[1]));
		List.add(newm);
		QuestionNameQaRelModel oldm = new QuestionNameQaRelModel();
		oldm.setId(Integer.parseInt(orderbyl[0]));
		oldm.setOrderBy(Double.parseDouble(orderbyl[1]));
		List.add(oldm);
		for (QuestionNameQaRelModel mm : List) {
			questionNameQaRelService.update(mm);
		}

		out.print("1");
		out.flush();
		out.close();
	}

	// 根据问卷有多少道题生成题目顺序
	@RequestMapping("/selectQnCount.do")
	public void qnCount(QuestionNameQaRelModel m, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<QuestionNameQaRelModel> list = questionNameQaRelService.selectAll(m);
		ArrayList<Map<String, Object>> List = new ArrayList<Map<String, Object>>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		int a = 0;
		for (QuestionNameQaRelModel mc : list) {
			a += 1;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderbyvalue", a + "");
			map.put("orderbyText", "第" + a + "项");
			List.add(map);
		}
		HtmlUtil.writerJson(response, List);
	}

	@RequestMapping("/reOrderBy.do")
	public void reOrderBy(QuestionNameQaRelModel m, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		m.setOrder("ASC");
		m.setSort("order_by");
		List<QuestionNameQaRelModel> list = questionNameQaRelService.selectAll(m);
		ArrayList<QuestionNameQaRelModel> List = new ArrayList<QuestionNameQaRelModel>();// 得到id和生成的排序值

		int a = 0;// 生成orderBy
		for (QuestionNameQaRelModel p : list) {
			QuestionNameQaRelModel check0 = new QuestionNameQaRelModel();
			a += 1;
			Double b = (double) a;
			check0.setId(p.getId());
			check0.setOrderBy(b);
			List.add(check0);
		}
		for (QuestionNameQaRelModel qq : List) {
			QuestionNameQaRelModel checkre = new QuestionNameQaRelModel();
			checkre.setId(qq.getId());
			checkre.setOrderBy(qq.getOrderBy());
			questionNameQaRelService.update(checkre);
		}

		out.print("1");
		out.flush();
		out.close();

	}
	//删除问卷
	@RequestMapping("/deleteQnRel.do")
	public void deleteQn(QuestionNameQaRelModel m, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CreateQuestionModel cm=new CreateQuestionModel();
		cm.setCreateQuestionCode(m.getCreateQuestionCode());
		List<CreateQuestionModel> cmlist = createQuestionService.selectAll(cm);
		if(FormatEmpty.isEmpty(cmlist)) {
			return;
		}
		Integer cmid = cmlist.get(0).getId();
		createQuestionService.delete(cmid);//删除问卷表问卷
		String midlist = m.getIdlist();
		if(FormatEmpty.isEmpty(midlist)) {
			return;
		}
		String[] midl = midlist.split(",");
		for(String id:midl) {
			questionNameQaRelService.delete(Integer.parseInt(id));//删除关系表的题目
		}
		List<CreateQuestionModel> tip=new ArrayList<CreateQuestionModel>();//返回用
		HtmlUtil.writerJson(response, tip);
		
		
		}
	//跳转问卷页
	@RequestMapping("/seeQnPage.shtml")
	public ModelAndView seeQnPage(QuestionNameQaRelModel qm,HttpServletRequest request, HttpServletResponse response) {
		if(FormatEmpty.isEmpty(qm.getCreateQuestionCode())) {
			return null;
		}
		ModelAndView m = new ModelAndView();
		m.addObject("createQuestionCode",qm.getCreateQuestionCode());
		m.addObject("userName",qm.getUserName());
		m.addObject("titleid",qm.getTitleid());
		m.setViewName("/page/question/seeQuestion");
		return m;

	}
}
