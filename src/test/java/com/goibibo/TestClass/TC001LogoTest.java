package com.goibibo.TestClass;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.goibibo.PageObjectClass.HomePage;



public class TC001LogoTest extends BaseTest {

	@Test(groups = {"smoke","sanity"})
	public void isLogoPresent() {

		try {

			logger.info("******TC001LogoTest is started******");
			HomePage hp = new HomePage(driver);
			boolean status = hp.checkLogo();
			Assert.assertEquals(true, status);
			logger.info("******TC001LogoTest is ended******");
		}
		catch (Exception e)// if try block will not able to run completely then catch will run
		{
			logger.error("TC001LogoTest is failed :" + e.getMessage());
			logger.debug("Debugs logs");
			Assert.fail();
		}
	}
}
