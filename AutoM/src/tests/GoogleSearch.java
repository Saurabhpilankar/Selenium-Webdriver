package tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GoogleSearch {
	
	public static WebDriver driver;
	
	@Parameters({"browser"})
	  @BeforeTest
		  public void beforeTest(String browser) {
		
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\raviraj\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\raviraj\\Downloads\\geckodriver024\\geckodriver.exe");
			driver= new FirefoxDriver();
		}
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  
		  }	
  @Test
  public void f() throws InterruptedException {
	  //Open google.co.in
		driver.get("https://www.google.co.in");
		
		//Enter world cup into search field
		driver.findElement(By.name("q")).sendKeys("world cup");
		
		//wait for suggestions
		Thread.sleep(1000);
		
		//Get all the suggestions
		List<WebElement> Suggestions =driver.findElements(By.xpath("//ul[@class='erkvQe']/li/div/div/div/span/b[contains(text(),' 2019')]"));
		
		//print no.of suggestions shown
		int suggestioncount =Suggestions.size();
		System.out.println("No. of Suggestions" +suggestioncount);
		
		//Print suggestions
		for(int i=0;i<suggestioncount;i++) {
			System.out.println("Suggestion: World Cup "+Suggestions.get(i).getText());	
		}
		
		//click on the suggestion
		Suggestions.get(2).click();

		//click on the cricbuzz link
		driver.findElement(By.partialLinkText("Cricbuzz")).click();
		
		System.out.println("Cricbuzz link opened");
		
		
	}
  
  @AfterTest
  public void afterTest() {
	  driver.quit();
		System.out.println("Closing the browser");
  }
  }

