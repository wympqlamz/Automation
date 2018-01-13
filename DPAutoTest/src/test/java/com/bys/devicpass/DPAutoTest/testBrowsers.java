package com.bys.devicpass.DPAutoTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.bys.devicepass.launch.Browsers;
import com.bys.devicepass.launch.BrowsersType;

public class testBrowsers {
	WebDriver driver1 = null;
	
	@Test(groups="test1")
	public void test1()	{
		Browsers b = new Browsers(BrowsersType.firefox);
		driver1 = b.driver;
		driver1.get("http://www.baidu.com");
System.out.println("test1 success");
		driver1.close();
	}
	
	@Test(groups="test2")
	public void test2()	{
		Browsers b = new Browsers(BrowsersType.chrome);
		driver1 = b.driver;
		driver1.get("http://www.baidu.com");
System.out.println("test2 success");
		driver1.quit();
	}
	
	@Test(groups="test3")
	public void test3()	{
		Browsers b = new Browsers(BrowsersType.chrome);
		driver1 = b.driver;
		driver1.get("http://www.baidu.com");
System.out.println("test3 success");
		driver1.close();
	}
}
