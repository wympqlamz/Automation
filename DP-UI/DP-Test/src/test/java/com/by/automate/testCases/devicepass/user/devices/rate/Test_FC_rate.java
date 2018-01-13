/**
 * @author Jay-Z
 * @version V0.1
 * @date 2017/11/24 10:17 
 *
 */
package com.by.automate.testCases.devicepass.user.devices.rate;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.by.automate.fwk.DPWebApp;

public class Test_FC_rate {
	
	private DPWebApp dp = null;
	
	@Parameters({"url","username","password"}) 
	@BeforeMethod(alwaysRun=true) 
	public void setUp(String url, String username, String password) {
		dp = new DPWebApp();
		dp.openApp("rate", "rate", "rate:rate");
		dp.openUrl(url);
		
	}
	
	@Test(groups="Group4")
	public void A1_test() {
		
	}
}