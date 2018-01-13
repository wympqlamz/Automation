/**
 * 版权 @Copyright: 2017 BeiJing beyondsoft Co. Ltd. <br/>
 * 项目名称：DP-Test<br/>
 * 文件名称：啊啊.java <br/>
 * 包名：com.by.automate.testCases.devicepass.user.workspaces.apps<br/>
 * 创建人：@author cjkl@beyondsoft.com
 * 创建时间：2017年5月16日/下午5:43:26<br/>
 * 修改人： cjkl@beyondsoft.com<br/>
 * 修改时间：2017年5月16日/下午5:43:26<br/>
 * 修改备注：<br/>
 */
package com.by.automate.testCases.devicepass.user.workspaces.apps;
import org.testng.annotations.Test;


public class Test_FC_app_CurrentUse_Basic extends Test_FC_app_core {
    @Test
    public void test01_selectDevice() {
        openAllDevices();
//        selectDevice();
        allDevice_selectDevice();
        allDevice_controlDevice();
    }

    @Test
    public void test02_check_no_records() {
        check_app_no_records();
    }

    @Test
    public void test030_uploadApp() {
        uploadApp();
    }

    @Test
    public void test040_editApp() {
        currentUse_editApp();
    }

    @Test
    public void test050_removeApp() {
        removeApp();
    }

    @Test
    public void test060_releaseAll() {
        releaseAll();
    }
}


