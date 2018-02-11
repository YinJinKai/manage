package com.utill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.system.model.DataModel;

import st.tool.FormatEmpty;

public class LoadData {
public static void Load(List<DataModel> data){
	if(FormatEmpty.isEmpty(data)) {
		return;
	}
	Map<String, String> map=new HashMap<String, String>(); 
	for(DataModel p: data) {
		 Integer type = p.getType();
		if(type==1) {
			String code = p.getCode();
			if("1".equals(code)) {
				map.put("chuqin", p.getStateName());
				continue;
			}
			if("0".equals(code)) {
				map.put("queqin", p.getStateName());
				continue;
			}
			if("2".equals(code)) {
				map.put("qingjia", p.getStateName());
				continue;
			}
		}
		if(type==2) {
			String code = p.getCode();
			if("1".equals(code)) {
				map.put("teacherconfirm", p.getStateName());
				continue;
			}
			if("t1".equals(code)) {
				map.put("teachernopass", p.getStateName());
				continue;
			}
			if("t2".equals(code)) {
				map.put("adminconfirm", p.getStateName());
				continue;
			}
			if("a1".equals(code)) {
				map.put("adminnopass", p.getStateName());
				continue;
			}
			if("a2".equals(code)) {
				map.put("adminpass", p.getStateName());
				continue;
			}
		}
		if(type==3) {
			String code = p.getCode();
			map.put("workscore", code);
		}
		if(type==4) {
			String code = p.getCode();
			if("1".equals(code)){
				map.put("objective", p.getStateName());
				continue;
			}
			if("2".equals(code)){
				map.put("subjective", p.getStateName());
				continue;
			}
		}
	}
   Constant.attend_Y=map.get("chuqin");
   Constant.attend_N=map.get("queqin");
   Constant.qingjia=map.get("qingjia");
   Constant.teacherconfirm=map.get("teacherconfirm");
   Constant.teachernopass=map.get("teachernopass");
   Constant.adminconfirm=map.get("adminconfirm");
   Constant.adminnopass=map.get("adminnopass");
   Constant.adminpass=map.get("adminpass");
   Constant.workscore=map.get("workscore");
   Constant.objective=map.get("objective");
   Constant.subjective=map.get("subjective");
}
}
