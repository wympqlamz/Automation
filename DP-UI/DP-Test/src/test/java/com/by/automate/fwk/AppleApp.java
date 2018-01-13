package com.by.automate.fwk;

import com.by.automate.base.WebApp;

public class AppleApp extends WebApp {

	public String email;
	public String password;
	

	public AppleApp() {

		this.email = getSutFullFileName("apple.userName");
		this.password = getSutFullFileName("apple.password");

	}

	@Override
	protected String getAppName() {

		return "Apple";
	}

	/**
	 * 封装方法,login, 提供给所有的测试脚本.调用该方法,直接可以登录到Devicepass网站.
	 * 
	 * @param email
	 *            用户名
	 * @param password
	 *            密码
	 */
	public void apple_Login() {

		verifyIsShown("account");
		verifyIsShown("logo");
		clickOn("account");
		verifyIsShown("userName");
		verifyIsShown("password");
		verifyIsShown("loginButton");
		setValueTo("userName", email);
		waitByTimeOut(1000);
		setValueTo("password", password);

		clickOn("loginButton");
		waitByTimeOut(2000);
	}

}