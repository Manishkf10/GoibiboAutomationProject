package com.goibibo.TestClass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import freemarker.template.SimpleDate;

public class BaseTest {

	public  WebDriver driver;
	protected static final Logger logger = LogManager.getLogger(BaseTest.class);;
	public Properties prop;
	public String localHostUrl = "http://localhost:4444/wd/hub";

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "executionEnvironment", "browser" })
	void setUp(@Optional("loacl") String executionEnvironment, @Optional("chrome") String browser) throws IOException {

		// configures Logger of log4j
		// logger = LogManager.getLogger(this.getClass());
		// configures properties file
		FileReader file = new FileReader(
				System.getProperty("user.dir") + "\\src\\main\\resorce\\commonData.properties");
		prop = new Properties();
		prop.load(file);

		// selecton browsers

		if (executionEnvironment.toLowerCase().equals("remote")) {
			switch (browser.toLowerCase()) {
			case "chrome":
				driver=new RemoteWebDriver(new URL(localHostUrl), new ChromeOptions());
				break;
			case "edge":
				System.setProperty("webdriver.edge.driver", "D:\\manish work space\\eclipse setup\\DemoExcercise\\src\\test\\resources\\msedgedriver.exe");
				driver=new EdgeDriver();break;
			case "firefox":
				driver=new RemoteWebDriver(new URL(localHostUrl), new FirefoxOptions());
				break;
			default:
				System.out.println("brower is not correct");return;
			}

		} else {
			switch (browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("browser is not define");
				return;
			}
		}

		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@AfterMethod(alwaysRun = true)
	void tearDown() {
	
		driver.quit();
		
	}

	public String getScreenshots(String testName) throws IOException {
		String timeStamp = new SimpleDateFormat("dd_MM_hh_mm").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + "\\screenShots\\" + timeStamp + testName + ".png";// it
																												// should
																												// be in
																												// System.getProperty
																												// becauseit
																												// will
																												// not
																												// change
																												// when
																												// we
																												// return
																												// it as
																												// String
		System.out.println("file path at base class " + filePath);
		File fileLocation = new File(filePath);
		FileUtils.copyFile(sourceFile, fileLocation);

		return filePath;

	}
}
