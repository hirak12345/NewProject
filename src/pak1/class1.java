package pak1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class class1 {
	 WebDriver driver;
	
	@Test
	 public void test1()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("http://orangehrm.uftselenium.com/symfony/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//input[@name='txtUsername']")).sendKeys("sreekanthsreekanth4");
		driver.findElement(By.xpath("//input[@name='txtPassword']")).sendKeys("sreekanth@123324");
		
		driver.findElement(By.xpath("//input[@name='Submit']")).submit();
		
		Assert.assertEquals(driver.getTitle(), "OrangeHRM");
		
		System.out.println("Title Verifyed");
	}
	
	@AfterTest
	public  void close()
	{
		driver.close();
	}

}
