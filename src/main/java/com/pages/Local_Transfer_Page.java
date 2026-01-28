package com.pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Local_Transfer_Page {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	// 1. Locators
	private By fundTransferLink = By.xpath("//div[@kwp='frmDashboard_quickLinksNew_links1']");
	private By localTransferBtn = By.xpath("//button[@aria-label='Make transfer to international account']");
	private By fromField = By
			.xpath("//div[contains(@id, 'flxFromAccount')] | //input[contains(@placeholder, 'Search')]");
	private By toField = By.xpath(
			"//input[@kwp='frmUTFDomesticTransfer_UnifiedTransfer_tbxToAccount'] | //input[contains(@placeholder, 'Beneficiary')]");
	private By amountField = By.xpath("//input[@kwp='frmUTFDomesticTransfer_UnifiedTransfer_tbxAmount']");
	private By purposeDropdown = By
			.xpath("//div[@kwp='frmUTFDomesticTransfer_UnifiedTransfer_flxPurposeCodeDropdown']");
	private By continueBtn = By.xpath("//button[@aria-label='Continue to Confirmation Screen']");

	// Constructor
	public Local_Transfer_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		this.js = (JavascriptExecutor) driver;
	}

	// 2. Actions (Methods)
	public void navigateToLocalTransfer() {
		System.out.println("Waiting for Fund Transfer link...");
		WebElement fundBtn = wait.until(ExpectedConditions.elementToBeClickable(fundTransferLink));
		js.executeScript("arguments[0].click();", fundBtn);

		WebElement localBtn = wait.until(ExpectedConditions.elementToBeClickable(localTransferBtn));
		js.executeScript("arguments[0].click();", localBtn);
	}

	public void performTransfer(String fromAcc, String toAcc, String amount) throws InterruptedException {
		// From Account
		WebElement fromInput = wait.until(ExpectedConditions.elementToBeClickable(fromField));
		fromInput.click();
		new Actions(driver).sendKeys(fromAcc).pause(Duration.ofMillis(1000)).sendKeys(Keys.ENTER)
				.pause(Duration.ofSeconds(2)).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).perform();

		// To Account
		WebElement toInput = wait.until(ExpectedConditions.elementToBeClickable(toField));
		toInput.click();
		new Actions(driver).sendKeys(toAcc).pause(Duration.ofMillis(1000)).sendKeys(Keys.ENTER)
				.pause(Duration.ofSeconds(2)).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).perform();

		// Amount
		WebElement amtInput = wait.until(ExpectedConditions.elementToBeClickable(amountField));
		amtInput.click();
		amtInput.clear();
		amtInput.sendKeys(amount);

		// Purpose
		WebElement dropdown = driver.findElement(purposeDropdown);
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
		Thread.sleep(1000);
		dropdown.click();
		Thread.sleep(1500);
		new Actions(driver).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).pause(Duration.ofMillis(500))
				.sendKeys(Keys.ENTER).perform();
	}

	public void clickContinue() {
		WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(continueBtn));
		js.executeScript("arguments[0].scrollIntoView(true);", btn);
		js.executeScript("arguments[0].click();", btn);
	}
}