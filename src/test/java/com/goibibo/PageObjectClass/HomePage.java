package com.goibibo.PageObjectClass;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);

	}

//Locators
	@FindBy(css = ".sc-1f95z5i-14.eFJvvO.header-sprite.logo.gi-logo")//correct (.sc-1f95z5i-14.eFJvvO.header-sprite.logo.gi-logo)
	WebElement logo;
	@FindBy(xpath = "//div[@data-id=\"dweb-modal\"]")
	WebElement loginBanner;
	@FindBy(css = ".logSprite.icClose")
	WebElement buttonCrossBanner;
	@FindBy(xpath = "//label[@for=\"fromCity\"]")
	WebElement fromCity;
	@FindBy(xpath = "//label[@for='toCity']")
	WebElement toCity;
	@FindBy(xpath = "//input[@autocomplete=\"off\"]")
	WebElement searchBox;
	@FindBy(xpath = "//label[@for='departure']")
	WebElement calender;
	@FindBy(xpath = "//a[text()='Search']")
	WebElement buttonSearch;
	@FindBy(xpath = "//ul[@role='listbox']/li[1]")
	WebElement resultFromCity;
	@FindBy(xpath = "//div[@data-id='dweb_pip_id']/p")
	WebElement smallBanner;
	@FindBy(xpath = "//div[@class='DayPicker-Months']/div[2]/div[1]/div")
	WebElement month;
	@FindBy(xpath = "//div[@class=\"DayPicker-Months\"]/div[2]//div[@aria-selected='false']")
	List<WebElement> date;
	@FindBy(xpath = "//span[@aria-label=\"Next Month\"]")
	WebElement btnNxtMonth;

//Actions
	public boolean checkLogo() {
		return logo.isDisplayed();
	}

	public void disableLoginBanner() {

		buttonCrossBanner.click();

	}

	public void seachFromCity(String fromcityName) throws InterruptedException {
		fromCity.click();
		searchBox.sendKeys(fromcityName);
		Thread.sleep(1000);
		resultFromCity.click();
	}

	public void searchToCity(String toCityName) throws InterruptedException {
		toCity.click();
		searchBox.sendKeys(toCityName);
		Thread.sleep(1000);
		resultFromCity.click();
	}

	public void click_buttonSearch() {
		buttonSearch.click();
	}

	public void click_date(String mnth, String date) {
		calender.click();
		while (true) {
			if (month.getText().toLowerCase().contains(mnth.toLowerCase())) {
				break;
			}
			btnNxtMonth.click();
		}
		List<WebElement> dates = this.date;
		for (WebElement dte : dates) {
			if (dte.getText().toLowerCase().contains(date)) {
				dte.click();
				break;
			}
		}

	}

	public void crossSmallBanner() {
		try {
		smallBanner.click();
		}catch(Exception e) {
			e.getMessage();
		}

	}
}
