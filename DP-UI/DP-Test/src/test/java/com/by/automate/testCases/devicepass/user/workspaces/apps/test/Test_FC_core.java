/*Apps************************
 *  openApps();               读取DPUi_apps.xml文件，获取apps视图，并点击进入app页面。
 *  check_app_no_records();	     清空已上传app、ipa，并检查no record信息。
 *  uploadApp();			      上传制定app文件：ep2p.apk。
 *  uploadApp(String appname) 上传指定名称app文件。 须将文件放在data/App/android_apk/下。
 *  isUploadStatus();		      监视app文件上传进度，如果超过10分钟，则终止。
 *  app_editApp();			      在app界面中，编辑app信息。
 *  removeApp();			      清空所有已上传app。
 *  checkAppNoScript();		       检查appScript（自动化脚本）的no record信息。
 *  editAppScript();		       编辑appScript（自动化脚本）信息。
 *  closePrompt();			       关闭右上角弹出通知信息，如剩余时间、各种错误等。
 *  checkversion();			       读取当前版本信息。
 *  app_selectPhoneGroup();	       从app界面进入自动化测试（兼容性，calabash等），进入选择设备界面后，如果同时存在多个group，会选择有存在可有手机的group（第一个gorup没有可用手机，会选择第二个）。
 *  app_selectPhone();		       从app界面进入自动化测试（兼容性，calabash等），进入选择设备界面选定group后，选择随机数量可用手机。（必须先用app_selectPhoneGroup）
*/

/*Test Report******************
 *  checkReportstatus();		持续从界面最上方Test Status检查自动化脚本运行状态，直到完成测试，生成报告，如果超过30分钟，则终止。
 *  downloadReport();			持续检查Test report界面最新的报告，检查下载按钮，直到出现为止， 然后点击下载，如果超过30分钟，则终止。（只下载一个报告）
 *  deleteAllReport();			持续检查Test report界面最新的报告，检查删除按钮，直到出现为止， 然后点击删除，如果超过30分钟，则终止。（只删除一个报告）
*/

/*Screenshots******************

*/

/*current Use******************
 * 好多模块可以和apps界面的复用。 具体可参考我的用例，不再重复说明。
 * currentUse_editApp();		currentUse界面对app信息进行编辑

*/

/*All Devices******************
 * 	openAllDevices();			读取DPUi_apps.xml文件，获取apps视图，并点击进入AllDevices页面。
 *  allDevice_selectDevice();	随机选择选择2-10个状态为available的andriod设备。
 *  allDevice_controlDevice(); 	对已选中设备，进行control。（必须先用allDevice_selectDevice）
 *  allDevice_groupDevice();	对已选中设备，建立group。（必须先用allDevice_selectDevice）
 *  releaseAll();				释放所有已控手机。

*/


/*Groups***********************
 * openGroups();				读取DPUi_apps.xml文件，获取apps视图，并点击进入Groups页面。
 * AddNewGroup();				创建group。
 * editGroup();					编辑group信息。
 * GroupSelectDevices();		对group添加随机数量手机。
 * GroupUnselectDevices();		随机数量删除group已添加手机（至少剩余1台）。
 * deleteAllGroups();			删除所有group。

*/
package com.by.automate.testCases.devicepass.user.workspaces.apps.test;

import com.by.automate.fwk.DPWebApp;
import org.testng.annotations.Test;
import java.util.Random;


public class Test_FC_core {
	String uploadAppName;
	public DPWebApp dp = null;
	Random random = new Random();
	
	@Test(groups = "setUp")
	public void setUp() {
		dp = new DPWebApp();
		dp.openApp();
	}
}





