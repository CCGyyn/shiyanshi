package com.zeng.ezsh.wy.entity;

public class StuRegister {
	private Integer stuId;

	private String telephone;// 学生电话

	private Integer pId;// 小区

	private String test;// 自我描述

	private String subject;// 所教导学科

	private String stuName;// 学生姓名

	private String stuPhoto;// 学生照片

	private String stuClass;// 学生教导的年级

	private String sex;// 性别

	private String question;// 注册时设置问题，忘记密码时找回方式

	private String answer;// 忘记密码时，回答问题，以找回密码
	private String createTime;// 注册时间

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone == null ? null : telephone.trim();
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test == null ? null : test.trim();
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject == null ? null : subject.trim();
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName == null ? null : stuName.trim();
	}

	public String getStuPhoto() {
		return stuPhoto;
	}

	public void setStuPhoto(String stuPhoto) {
		this.stuPhoto = stuPhoto == null ? null : stuPhoto.trim();
	}

	public String getStuClass() {
		return stuClass;
	}

	public void setStuClass(String stuClass) {
		this.stuClass = stuClass == null ? null : stuClass.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question == null ? null : question.trim();
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer == null ? null : answer.trim();
	}
}