package com.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Login_Page {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	// Locators (Login)
	By usernameLoc = By
			.xpath("//input[@kwp='frmLogin_loginComponent_tbxUserName'] | //input[@placeholder='Enter Username']");
	By passwordLoc = By
			.xpath("//input[@kwp='frmLogin_loginComponent_tbxPassword'] | //input[@placeholder='Enter password']");
	By rememberMeLoc = By.xpath("//div[@role='checkbox' and contains(@kwp, 'rememberMe')]");
	By loginBtnLoc = By.xpath("//button[@kwp='frmLogin_loginComponent_btnLogin']");

	// Locator ("Yes" - Switch Language Pop-up)
	By languageYesBtnLoc = By.xpath("//button[@kwp='frmLogin_DiffLanguagePopup_btnNo']");

	public Login_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		this.js = (JavascriptExecutor) driver;
	}

	// Handle Language Pop-up (NO)
	By languageNoBtnLoc = By.xpath("//button[@kwp='frmLogin_DiffLanguagePopup_btnNo']");

	private void handleLanguagePopup() {
		try {
			// Wait 10 Seconds
			WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));

			// Try to find the button "No" & Click on it
			WebElement noBtn = shortWait.until(ExpectedConditions.presenceOfElementLocated(languageNoBtnLoc));

			// Use JS if the click fails
			js.executeScript("arguments[0].click();", noBtn);

			System.out.println("Language popup (No) handled successfully.");

			// Wait until the pop-up disappears
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Language popup did not appear within 10 seconds.");
		}
	}

	public void login(String user, String pass) {

		// 1. Login
		WebElement uName = wait.until(ExpectedConditions.elementToBeClickable(usernameLoc));
		uName.clear();
		uName.sendKeys(user);

		WebElement pWord = wait.until(ExpectedConditions.elementToBeClickable(passwordLoc));
		pWord.clear();
		pWord.sendKeys(pass);

		driver.findElement(rememberMeLoc).click();

		WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(loginBtnLoc));
		js.executeScript("arguments[0].removeAttribute('disabled');", btn);
		js.executeScript("arguments[0].click();", btn);

		// 2. Handle Language Pop-up
		handleLanguagePopup();
	}
}