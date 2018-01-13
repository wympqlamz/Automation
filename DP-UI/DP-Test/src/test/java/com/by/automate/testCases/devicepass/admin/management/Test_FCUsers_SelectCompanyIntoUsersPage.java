package com.by.automate.testCases.devicepass.admin.management;

import com.by.automate.fwk.DPWebApp;
import com.by.automate.utils.CommonTools;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dp on 2017/6/28.
 */
public class Test_FCUsers_SelectCompanyIntoUsersPage {

private DPWebApp dp = null;
@BeforeClass
    public void setUp(){
    dp = new DPWebApp();
    dp.openApp("management","users");
    dp.DP_Login();

}

@Test(priority = 1)
 public void Test010_selectCompany(){

    dp.verifyManagementElement();
    dp.waitByTimeOut(1000);

    //进入users界面
    dp.clickOn("users");
    dp.verifyIsShown("selectCompanyTitle");
    dp.verifyIsShown("selectCompanyTextbox");
    dp.clickOn("selectCompanyTextbox");
    dp.waitByTimeOut(500);
    //dp.verifyIsShown("firstBySelcetCompany");


    List<WebElement> allCompanyName = dp.getElements("allCompanyByMenu");  //将所有的companyName生成一个集合
    List<String> values = new ArrayList<String>();  //将除去第一个的所有的集合
    for (int i = 1; i < allCompanyName.size(); i++) {


        String value = allCompanyName.get(i).getText(); //获得每一个元素的文本
        values.add(value); // 将value 的值添加到values的集合中去
    }
    int random = CommonTools.getRandom(values.size()-1); // 从values集合中随机生成一个数字并打印出来
    dp.log(values.get(random));
    String selectCompanyName = values.get(random); // 取出该随机数的文本值
    dp.setValueTo("selectCompanyTextbox", values.get(random)); // 定位符给予Select来做点击的操作
    dp.waitByTimeOut(1000);
    dp.uiMapUpdateView("users:usersPage");//跳转至user里面

    dp.verifyIsShown("usersInCompanyTitle");
    dp.verifyIsShown("usersInCompanyName");
    String selectedCompanyName = dp.getValueOf("usersInCompanyName"); // 在取出当前的company Name的名字
    dp.assertEqual(selectedCompanyName, selectCompanyName); // 两次取出来的文本值进行对比，是否一样的
    dp.verifyIsShown("selectCompanyDropdownBtn");
    dp.clickOn("selectCompanyDropdownBtn");
    dp.waitByTimeOut(500);
    List<WebElement> allCompanyNameOfBtn = dp.getElements("allCompanyOfBtn");
    int randomSum = CommonTools.getRandom(allCompanyNameOfBtn.size());
    dp.log(randomSum);  // 打印随机数
    dp.waitByTimeOut(500);
    //  stale element reference: element is not attached to the page document(元素过期)
    //allCompanyNameOfBtn = dp.getElements("allCompanyOfBtn");
    allCompanyNameOfBtn.get(randomSum).click(); // 点击随机数
    dp.waitByTimeOut(500);

}





}
