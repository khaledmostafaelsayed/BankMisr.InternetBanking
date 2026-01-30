package com.tests;

import com.base.BaseTest;
import com.pages.Login_Page;
import com.pages.Local_Transfer_Page;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Local_Transfer_TestSuite extends BaseTest {

	@DataProvider(name = "transferData")
	public Object[][] getTransferData() {
		return new Object[][] { { "khaled@1", "Kony@1357", "1674", "6801", "100", "111111" },
				{ "khaled@1", "Kony@1357", "1674", "6801", "500", "111111" } };
	}

	@Test(dataProvider = "transferData")
	public void verifyTransferBug(String user, String pass, String fromAcc, String toAcc, String amt, String otp)
			throws InterruptedException {
		Login_Page login = new Login_Page(driver);
		Local_Transfer_Page transferPage = new Local_Transfer_Page(driver);

		// 1. Login
		login.login(user, pass);
		// 2. Navigate To Local Transfer Page
		transferPage.navigateToLocalTransfer();
		// 3. Enter Transfer Data
		transferPage.performTransfer(fromAcc, toAcc, amt);
		// 4. Click on Button "Continue"
		transferPage.clickContinue();
		// 5. Click on Button "Continue"
		transferPage.clickConfirm();

		// 6. Enter OTP & Confirm
		transferPage.enterOtp(otp);

		// 7. Confirm OTP
		transferPage.confirmOTP();

	}
}