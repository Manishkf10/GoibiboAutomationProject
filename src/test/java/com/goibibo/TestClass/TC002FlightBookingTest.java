package com.goibibo.TestClass;

import org.testng.annotations.Test;

import com.goibibo.PageObjectClass.HomePage;
import com.goibibo.Utilities.ExcelReader;

public class TC002FlightBookingTest extends BaseTest{

	@Test(dataProvider = "FlightBookingDetails",dataProviderClass = ExcelReader.class,groups = "sanity")
	void verifyFlightBookingSearch(String from,String to,String month,String date) throws InterruptedException {
		
		HomePage hp=new HomePage(driver);
		hp.disableLoginBanner();
		hp.crossSmallBanner();
		hp.seachFromCity(from);
		hp.searchToCity(to);
		hp.click_date(month,date);
		hp.click_buttonSearch();
		
		
		
	}
}
