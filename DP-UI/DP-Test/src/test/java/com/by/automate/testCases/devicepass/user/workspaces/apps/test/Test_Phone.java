package com.by.automate.testCases.devicepass.user.workspaces.apps.test;

import com.by.automate.fwk.DPWebApp;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.Calendar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

@Test(dependsOnGroups = { "setUp" }, alwaysRun = true)
public class Test_Phone extends Test_FC_meng {

	@Test(groups = { "NoDelete", "Login", "1" })
	public void test00010_Login() {
		dp.DP_Login("menglei@beyondsoft.com", "1qaz2wsx");
	}

	@Test(groups = { "NoDelete", "Login", "1" })
	public void test00020_ini() throws IOException {

		// 打开文件********************************************************************************************************************
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(
					"D:\\git\\DP-UI\\DP-Test\\src\\test\\java\\com\\by\\automate\\testCases\\devicepass\\user\\workspaces\\apps\\test\\jiningphone.ini"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("IO Exception:" + e);
		}

		// 一次读入一行（非空），直到读入null为文件结束,得到section和值
		String str = null;
		HashMap sections = new HashMap();
		Properties current = null;
		Properties pp = null;
		while ((str = reader.readLine()) != null) {
			if (str.startsWith("[") && str.endsWith("]")) {
				String newSection = str.substring(1, str.length() - 1).trim();
				// System.err.println("newSection: " + newSection);
				current = new Properties();
				sections.put(newSection, current);
			} else if (str.matches(".*=.*")) {
				// if (current != null) {
				int i = str.indexOf('=');
				String name = str.substring(0, i).trim();
				String value = str.substring(i + 1).trim();
				current.setProperty(name, value);

				// System.err.println("name: " + name);
				// System.err.println("value: " + value);

			}
			// try {
			// Thread.sleep(100);
			// } catch (Exception e) {
			// e.printStackTrace();
		}
		// System.err.println("current: " + current);
		System.err.println("sections: " + sections);

		// 读取网页默认视图********************************************************************************************************************
		dp.waitByTimeOut(15000);
		new WebDriverWait(dp.driver, 30).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@book-device,'bookDevice')]")));
		List<WebElement> PhoneName = dp.driver.findElements(By.xpath("//div[contains(@book-device,'bookDevice')]"));

		int p = Integer.valueOf(PhoneName.size());
		for (int i = 0; i < p; i++) {
			String android = PhoneName.get(i).getAttribute("title");
			System.err.println("手机 型号是:" + android);

			String android_height_width = PhoneName.get(i)
					.findElement(By.xpath(".//div[@class='height-width overflow-space']")).getAttribute("title");
			String android_size = PhoneName.get(i).findElement(By.xpath(".//div[@class='size overflow-space']"))
					.getAttribute("title");
			String android_cpu_cores = PhoneName.get(i)
					.findElement(By.xpath(".//div[@class='cpu-cores overflow-space']")).getAttribute("title");
			String android_cpu_frequency = PhoneName.get(i)
					.findElement(By.xpath(".//div[@class='cpu-frequency overflow-space']")).getAttribute("title");
			String android_ram = PhoneName.get(i).findElement(By.xpath(".//div[@class='ram overflow-space']"))
					.getAttribute("title");

			// 文本里的值
			pp = (Properties) sections.get(android);
			String height_width = pp.getProperty("height_width").replaceAll("NULL", "-");
			String size = pp.getProperty("size").replaceAll("NULL", "-");
			String cpu_cores = pp.getProperty("cpu_cores").replaceAll("NULL", "-");
			String cpu_frequency = pp.getProperty("cpu_frequency").replaceAll("NULL", "-");
			String ram = pp.getProperty("ram").replaceAll("NULL", "-");
//			System.err.println("android_height_width: " + android_height_width);
//			System.err.println("height_width: " + height_width);
//			System.err.println("Website data: " + android_size);
//			System.err.println("Database  data: " + size);
//			System.err.println("android_cpu_cores: " + android_cpu_cores);
//			System.err.println("cpu_cores: " + cpu_cores);
//			System.err.println("android_cpu_frequency: " + android_cpu_frequency);
//			System.err.println("cpu_frequency: " + cpu_frequency);
//			System.err.println("android_ram: " + android_ram);
//			System.err.println("ram: " + ram + "\n");

			if( !android_height_width.contains(height_width) ){
					System.out.println(android
					+ " height_width is not matching, Website data: " + android_height_width + "Database  data: "
					+ height_width);}
			if( !android_size.contains(size)){ 
				System.out.println(android + " size is not matching, Website data: " + android_size
					+ "Database  data: " + size);}
			if(! android_cpu_cores.contains(cpu_cores) ){
				System.out.println(android + " cpu_cores is not matching, Website data: "
					+ android_cpu_cores + "Database  data: " + cpu_cores);}
			if(! android_cpu_frequency.contains(cpu_frequency) ){
				System.out.println(android
					+ " cpu_frequency is not matching, Website data: " + android_cpu_frequency + "Database  data: "
					+ android_cpu_frequency);}
			if(! android_ram.contains(ram) ){
				System.out.println(android + " ram is not matching, Website data: " + android_ram
					+ "Database  data: " + ram);}
			dp.waitByTimeOut(1000);
			
			
			
//			assert android_height_width.contains(height_width) : android
//			+ " height_widthis not matching, Website data: " + android_height_width + "Database  data: "
//			+ height_width;
//			assert android_size.contains(size) : android + " size is not matching, Website data: " + android_size
//					+ "Database  data: " + size;
//			assert android_cpu_cores.contains(cpu_cores) : android + " cpu_coresis not matching, Website data: "
//					+ android_cpu_cores + "Database  data: " + cpu_cores;
//			assert android_cpu_frequency.contains(cpu_frequency) : android
//					+ " cpu_frequencyis not matching, Website data: " + android_cpu_frequency + "Database  data: "
//					+ android_cpu_frequency;
//			assert android_ram.contains(ram) : android + " ramis not matching, Website data: " + android_ram
//					+ "Database  data: " + ram;
		}

		// 切换视图********************************************************************************************************************
		dp.waitByTimeOut(3000);
		dp.driver.findElement(By.xpath("//a[contains(@uib-tooltip,'List View')]")).click();
		dp.waitByTimeOut(3000);

		List<WebElement> PhoneName1 = dp.driver
				.findElements(By.xpath("//span[contains(@ng-click,'openDeviceInfo(device)')]"));
		int ppp = Integer.valueOf(PhoneName1.size());
		for (int i = 0; i < ppp; i++) {
			String android1 = PhoneName1.get(i).getAttribute("uib-tooltip");
			System.err.println("切换视图 手机 型号是:" + android1);

			// 点开查找页面
			PhoneName1.get(i).click();

			dp.waitByTimeOut(4000);
			// WebElement height_width
			// =PhoneName.get(i).findElement(By.xpath("./*/div[class='height-width
			// overflow-space']"));

			String android_height = dp.driver
					.findElement(By
							.xpath("/html/body/div[1]/div/div/form/div[2]/div/div[3]/table/tbody/tr[2]/td[2]/span/span[1]"))
					.getText();
			String android_width = dp.driver
					.findElement(By
							.xpath("/html/body/div[1]/div/div/form/div[2]/div/div[3]/table/tbody/tr[2]/td[2]/span/span[3]"))
					.getText();
			String android_height_width = android_height + " * " + android_width;
			String android_size = dp.driver
					.findElement(
							By.xpath("/html/body/div[1]/div/div/form/div[2]/div/div[3]/table/tbody/tr[2]/td[4]/span"))
					.getText();
			String android_Released = dp.driver
					.findElement(
							By.xpath("/html/body/div[1]/div/div/form/div[2]/div/div[3]/table/tbody/tr[3]/td[4]/span"))
					.getText();
			String android_cpu_cores = dp.driver
					.findElement(
							By.xpath("/html/body/div[1]/div/div/form/div[2]/div/div[3]/table/tbody/tr[4]/td[2]/span"))
					.getText();
			String android_cpu_frequency = dp.driver
					.findElement(
							By.xpath("/html/body/div[1]/div/div/form/div[2]/div/div[3]/table/tbody/tr[5]/td[2]/span"))
					.getText();
			String android_ram = dp.driver
					.findElement(
							By.xpath("/html/body/div[1]/div/div/form/div[2]/div/div[3]/table/tbody/tr[5]/td[4]/span"))
					.getText();

			// 文本里的值
			pp = (Properties) sections.get(android1);
			String height_width = pp.getProperty("height_width").replaceAll("NULL", "-");
			String size = pp.getProperty("size").replaceAll("NULL", "-");
			String Released = pp.getProperty("released_time").replaceAll("NULL", "-");
			String cpu_cores = pp.getProperty("cpu_cores").replaceAll("NULL", "-");
			String cpu_frequency = pp.getProperty("cpu_frequency").replaceAll("NULL", "-");
			String ram = pp.getProperty("ram").replaceAll("NULL", "-");
//			System.err.println("Website  android_height_width: " + android_height_width);
//			System.err.println("Database height_width: " + height_width);
//			System.err.println("Website data: " + android_size);
//			System.err.println("Database  data: " + size);
//			System.err.println("Website  android_cpu_cores: " + android_Released);
//			System.err.println("Database  data: " + Released);
//			System.err.println("Website  android_cpu_cores: " + android_cpu_cores);
//			System.err.println("Database cpu_cores: " + cpu_cores);
//			System.err.println("Website android_cpu_frequency: " + android_cpu_frequency);
//			System.err.println("Database cpu_frequency: " + cpu_frequency);
//			System.err.println("Website android_ram: " + android_ram);
//			System.err.println("Database ram: " + ram + "\n");

			
			if( !android_height_width.contains(height_width) ){
				System.out.println(android1
				+ " height_width is not matching, Website data: " + android_height_width + "Database  data: "
					+ height_width);}
			if( !android_size.contains(size)){ 
				System.out.println(android1 + " size is not matching, Website data: " + android_size
					+ "Database  data: " + size);}
			if(! android_Released.contains(Released) ){
				System.out.println(android1 + " android_Released is not matching, Website data: "
			+ android_Released + "Database  data: " + Released);}
			if(! android_cpu_cores.contains(cpu_cores) ){
				System.out.println(android1 + " cpu_cores is not matching, Website data: "
					+ android_cpu_cores + "Database  data: " + cpu_cores);}
			if(! android_cpu_frequency.contains(cpu_frequency) ){
				System.out.println(android1
					+ " cpu_frequency is not matching, Website data: " + android_cpu_frequency + "Database  data: "
					+ android_cpu_frequency);}
			if(! android_ram.contains(ram) ){
				System.out.println(android1 + " ram is not matching, Website data: " + android_ram
					+ "Database  data: " + ram);}
		
		
//			assert android_height_width.contains(height_width) : android1
//					+ " height_widthis not matching, Website data: " + android_height_width + "Database  data: "
//					+ height_width;
//			assert android_size.contains(size) : android1 + " size is not matching, Website data: " + android_size
//					+ "Database  data: " + size;
//			assert android_Released.contains(Released) : android1 + " height_widthis not matching, Website data: "
//					+ android_Released + "Database  data: " + Released;
//			assert android_cpu_cores.contains(cpu_cores) : android1 + " cpu_coresis not matching, Website data: "
//					+ android_cpu_cores + "Database  data: " + cpu_cores;
//			assert android_cpu_frequency.contains(cpu_frequency) : android1
//					+ " cpu_frequencyis not matching, Website data: " + android_cpu_frequency + "Database  data: "
//					+ android_cpu_frequency;
//			assert android_ram.contains(ram) : android1 + " ramis not matching, Website data: " + android_ram
//					+ "Database  data: " + ram;

			// 关闭界面
			dp.driver.findElement(By.xpath("//button[contains(@ng-click,'cancel()')]")).click();
			dp.waitByTimeOut(2000);
		}
	}

	// @Test(groups = {"NoDelete","1"})
	// public void test99999_tearDown() {
	// dp.close();
	// }

}
