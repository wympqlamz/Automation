package com.by.automate.testCases.devicepass.uiChange;

import com.by.automate.fwk.DPWebApp;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by dp on 2017/6/6.
 */
public class Test_FCApps_UiChange {

    private DPWebApp dp = null;

    @BeforeClass
    public void setUp() {
        dp = new DPWebApp();
        dp.openApp("uiChanges","apps");
        dp.DP_Login();
    }

    @Test
    public void Test010_appsUiChange() {

        dp.verifyIsShown("apps");
        dp.clickOn("apps");
        dp.verifyUiChanges("uploadBtn");
        dp.verifyUiChanges("removeBtn");
        dp.verifyUiChanges("searchBar");
        dp.verifyUiChanges("searchIcon");
        dp.verifyUiChanges("selectAllCheckbox");
        dp.verifyUiChanges("firstScriptCount");
        dp.verifyUiChanges("firstRunBtn");
        dp.verifyUiChanges("firstEditBtn");
        dp.verifyUiChanges("firstRemoveBtn");
        dp.verifyUiChanges("loadAppList");
        dp.verifyUiChanges("pageSizeTitle");
        dp.verifyUiChanges("changePageSizeBar");
        dp.verifyUiChanges("showTotalCount");

        dp.log(dp.s);
        dp.log(dp.f);
        dp.wirteUiChanges();

    }

    @AfterClass
    public void tearDown() {

        dp.close();
    }

}
