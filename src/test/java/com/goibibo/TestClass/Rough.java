package com.goibibo.TestClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Rough {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.goibibo.com/");
		
		System.out.println(driver.getTitle());
		driver.findElement(By.cssSelector(".logSprite.icClose")).click();
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//label[@for='fromCity']")).click();
		driver.findElement(By.xpath("//input[@autocomplete=\"off\"]")).sendKeys("dehradun");
		driver.findElement(By.xpath("//div[@data-id=\"dweb_pip_id\"]/p")).click();
		driver.findElement(By.xpath("//ul[@role='listbox']/li[1]//p[1]/span/span")).click();
		driver.findElement(By.xpath("//label[@for='departure']")).click();
		System.out.println(driver.findElement(By.xpath("//div[@class='DayPicker-Months']/div[2]/div[1]/div")).getText());
		Thread.sleep(5000);
		driver.close();
		
	}
}
