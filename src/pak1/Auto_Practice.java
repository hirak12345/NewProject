package pak1;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Auto_Practice {
	WebDriver driver;
	
	@Test (enabled=false)
	public void dragAndDrop()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("http://demo.guru99.com/test/drag_drop.html");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		Actions actions=new Actions(driver);
		actions.clickAndHold(driver.findElement(By.xpath("//div[@id='products']//li[5]")))
		.moveToElement(driver.findElement(By.xpath("(//li[@class='placeholder'])[1]")))
		.release()
		.build()
		.perform();
	}
	
	@Test(enabled=false)
	public void mouseMover()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://www.snapdeal.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		Actions actions =new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//span[text()='Mobile & Tablets']"))).build().perform();
	}
	@Test (enabled=false)
	public void dropAndDown()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		Select oSelect=new Select(driver.findElement(By.xpath("//select[@id='select-demo']")));
		oSelect.selectByVisibleText("Tuesday");
	}
	
	@Test (enabled=false)
	public void AlertMessage()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("window.scrollTo(0,500)");
		
		driver.findElement(By.xpath("(//button[@class='btn btn-default btn-lg'])[1]")).click();
		
		Alert a=driver.switchTo().alert();
		a.accept();
	}

	@Test(enabled=false)
	public void getAllDropdownValue()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		Select oSelect=new Select(driver.findElement(By.xpath("//select[@class='form-control']")));
	    List<WebElement> allLinks=oSelect.getOptions();
	    
	    for (WebElement e:allLinks)
	    {
	    	System.out.println(e.getText());
	    	
	    	if (e.getText().equals("Wednesday"))
	    	{
	    		e.click();
	    	}
	    }
	}
	
	@Test(enabled=false)
	public void GetWindowhandle() throws InterruptedException
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://www.telugumatrimony.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		driver.findElement(By.xpath("(//a[@class='paddr5'])[1]//img")).click();
		
		Set<String> allWindow=driver.getWindowHandles();
		Object[] option=allWindow.toArray();
		
		driver.switchTo().window(option[1].toString());
		
		driver.findElement(By.xpath("(//a[text()='Forgotten account?'])[1]")).click();
		Thread.sleep(3000);
		
		driver.switchTo().window(option[0].toString());
		
		driver.findElement(By.xpath("//a[@id='open_loginpp']")).click();
	}
	
	@Test(enabled=false)
	public void GoogleLogic() throws InterruptedException
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://google.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.name("q")).sendKeys("job");
		
		Thread.sleep(3000);
		
		String Alltxt=driver.findElement(By.xpath("//div[@class='aajZCb']")).getText();
		System.out.println(Alltxt);
		
		String[] s=Alltxt.split("\n");
		
		for (int i=0 ;i<s.length;i++)
		{
			if (s[i].equals("job alert"))
			{
				driver.findElement(By.name("q")).clear();
				driver.findElement(By.name("q")).sendKeys(s[i]);
				driver.findElement(By.name("btnK")).click();
				break;
			}
		}
	}
	
	@Test
	public void brokenLinks() 
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://www.telugumatrimony.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	    List<WebElement> link=driver.findElements(By.tagName("a"));
	    link.add(driver.findElement(By.tagName("img")));
	    
	    System.out.println("Total Links-->"+link.size());
	    
	    ArrayList<WebElement> activeLinks=new ArrayList<WebElement>();
	    
	    for (WebElement e :link)
	    {
	    	if(e.getAttribute("href") !=null && (!e.getAttribute("href").contains("javascript")))
	    	{
	    		activeLinks.add(e);
	    	}
	    }
		
	    System.out.println("Total Active Links-->"+activeLinks.size());
		
		for (WebElement e1 :activeLinks)
		{
			try {
			URL u=new URL(e1.getAttribute("href"));
			HttpURLConnection connection =(HttpURLConnection)u.openConnection();
			
			connection.connect();
			connection.setConnectTimeout(5000);
			connection.disconnect();
			
			System.out.println(e1.getAttribute("href")+"--->"+connection.getResponseMessage());
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			
			
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
