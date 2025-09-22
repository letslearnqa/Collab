package testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IciciPru {
  WebDriver driver;	
  
  
  @BeforeClass
  @Parameters("browser")
  public void setup(String browser)
  {
	  if(browser.equalsIgnoreCase("chrome"))
	  {
		  WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();  
		  
	  }
	  else if(browser.equalsIgnoreCase("edge"))
	  {
		  WebDriverManager.edgedriver().setup();
		  driver = new EdgeDriver();
	  }
	  
	  else if(browser.equalsIgnoreCase("firefox"))
	  {
		  WebDriverManager.firefoxdriver().setup();
		  driver = new FirefoxDriver();
	  }
	  
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
	  driver.get("https://www.iciciprulife.com/");
	  
  }
  
	
  @Test(priority =1)
  public void validateHomePageTitle() {
	  
	  String actualPageTitle = driver.getTitle();
	  String expectedPageTitle = "Life Insurance - ICICI Prudential Life Insurance India 2025";
	  Assert.assertEquals(actualPageTitle, expectedPageTitle);
	  	    
  }
  
  @Test(priority =2)
  public void validaeTermInsuranceMenu() {
	  
	driver.findElement(By.xpath("//a//span[text()='Term Insurance']")).click();
	List<WebElement> termmenulist =  driver.findElements(By.xpath("//a//span[text()='Term Insurance']//following::ul[1]//li//a"));
	for(WebElement termoption : termmenulist)
	{
		String suboptiontext = termoption.getText();
		System.out.println(suboptiontext);
		Assert.assertTrue(suboptiontext.contains("TERM INSURANCE"));
	}
	
	
  }
  
  @Test(priority =3)
  public void validaeNRIPlansMenu() {
	  
	  driver.findElement(By.xpath("//a//span[text()='NRI Plans']")).click();
	  List<WebElement> NRImenulist =  driver.findElements(By.xpath("//a//span[text()='NRI Plans']//following::ul[1]//li//a"));
	  for(WebElement nrioption : NRImenulist)
		{
			String suboptiontext = nrioption.getText();
			if(suboptiontext.equals("CONTACT US"))
			{
				continue;
			}
			System.out.println(suboptiontext);
			Assert.assertTrue(suboptiontext.contains("NRI"));
		}
  
  
  }
  
  @Test(priority =4)
  public void validaeLogin() {
	  
	  driver.findElement(By.id("login-toggle")).click();
	  driver.findElement(By.linkText("Individual")).click();
	  String expectedURL = "https://customer.iciciprulife.com/csr/cmmn-home.htm?execution=e1s1";
	  String actualURL = driver.getCurrentUrl();
	  Assert.assertEquals(actualURL, expectedURL);
	  
  }
  
  @AfterClass
  public void teardown()
  {
	  driver.quit();
	  //Note from QA 1 in day shift
  }
  
    
}
