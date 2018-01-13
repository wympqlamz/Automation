package com.bys.devicepass.launch;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browsers {
	
	public WebDriver driver = null;
	private FirefoxProfile firefoxprofile = null;
	private static DesiredCapabilities cap = null;
	private String projectPath = System.getProperty("user.dir");
	
	@SuppressWarnings("incomplete-switch")
	public Browsers(BrowsersType browsertype) {
		
		switch(browsertype) {
		
			case chrome:
				System.setProperty("webdriver.chrome.driver", projectPath + "\\tool\\chromedriver.exe");
//System.out.println(projectPath);
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				break;
				
			case firefox:
				firefoxprofile = new FirefoxProfile();
				cap = new DesiredCapabilities();
				
				try {
					File firebug = new File(projectPath + "\\tool\\firebug@software.joehewitt.com.xpi");
					File firepath = new File(projectPath + "\\tool\\FireXPath@pierre.tholence.com.xpi");
					firefoxprofile.addExtension(firebug);
					firefoxprofile.addExtension(firepath);
					firefoxprofile.setPreference("webdriver.accept.untrusted.certs", "true");
					firefoxprofile.setPreference("extensions.firebug.currentVersion", "2.0.19");
				} catch (NullPointerException e) {
					System.out.println("Can not find firebug/firepath directory");
				}
				cap.setCapability(FirefoxDriver.PROFILE, firefoxprofile);
				System.setProperty("webdriver.firefox.bin","D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
				driver = new FirefoxDriver(firefoxprofile);
				
		}
	}

}
