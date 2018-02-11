package com.utill;

import com.attendance.model.WorkModel;

public class JudgeCheck {
public static WorkModel judgeCheck(String checkName,String status) {
	WorkModel work=new WorkModel();
	if("oneCheck".equals(checkName)) {
		work.setOneCheck(status);
		work.setTwoCheck("0");
		work.setThreeCheck("0");
		work.setFourCheck("0");
		work.setFiveCheck("0");
		work.setSixCheck("0");
	}
	if("twoCheck".equals(checkName)) {
		work.setTwoCheck(status);
		work.setOneCheck("0");
		work.setThreeCheck("0");
		work.setFourCheck("0");
		work.setFiveCheck("0");
		work.setSixCheck("0");
	}
	if("threeCheck".equals(checkName)) {
		work.setThreeCheck(status);
		work.setOneCheck("0");
		work.setTwoCheck("0");
		work.setFourCheck("0");
		work.setFiveCheck("0");
		work.setSixCheck("0");
	}
	if("fourCheck".equals(checkName)) {
		work.setFourCheck(status);
		work.setOneCheck("0");
		work.setTwoCheck("0");
		work.setThreeCheck("0");
		work.setFiveCheck("0");
		work.setSixCheck("0");
	}
	if("fiveCheck".equals(checkName)) {
		work.setFiveCheck(status);
		work.setOneCheck("0");
		work.setTwoCheck("0");
		work.setThreeCheck("0");
		work.setFourCheck("0");
		work.setSixCheck("0");
	}
	if("sixCheck".equals(checkName)) {
		work.setSixCheck(status);
		work.setOneCheck("0");
		work.setTwoCheck("0");
		work.setThreeCheck("0");
		work.setFourCheck("0");
		work.setFiveCheck("0");
	}
	work.setType(1);
	return work;
	
}
public static WorkModel updateJudgeCheck(String checkName,String status) {
	WorkModel work=new WorkModel();
	if("oneCheck".equals(checkName)) {
		work.setOneCheck(status);
	}
	if("twoCheck".equals(checkName)) {
		work.setTwoCheck(status);
	}
	if("threeCheck".equals(checkName)) {
		work.setThreeCheck(status);
	}
	if("fourCheck".equals(checkName)) {
		work.setFourCheck(status);
	}
	if("fiveCheck".equals(checkName)) {
		work.setFiveCheck(status);
	}
	if("sixCheck".equals(checkName)) {
		work.setSixCheck(status);
	}
	work.setType(1);
	return work;
	
}
}
