package PosMalaysia;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Seleniumscript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("WebDriver.chrome.driver", "C:\\Selenium Webdriver\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		// Maximized the browser window
		driver.manage().window().maximize();
		
		// Step 1, go to the website
		driver.get("https://pos.com.my/send/ratecalculator");
		driver.findElement(By.xpath("//div/*[@id=\"notificationText\"]/span/fa-icon/*[@data-icon=\"xmark\"]/*")).click();
		
		// Scrolls the element into view
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement element = driver.findElement(By.xpath("//*[contains(text(), 'Get your domestic or international parcel quotation in under 30 seconds using our free price check tool.')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);

		// Step 2, Enter 35600 at 'From' section
		int from = 35600;
		driver.findElement(By.xpath("//input[@placeholder='Postcode']")).sendKeys(String.valueOf(from));
				
		// Step 3, Enter India at 'To' section
		WebElement input_to = driver.findElement(By.xpath("//*[@id='mat-input-0']"));
		input_to.clear(); input_to.sendKeys("India");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By india = By.xpath("//*[contains(text(), 'India - IN')]");
		wait.until(ExpectedConditions.presenceOfElementLocated(india)).click();
				
		// Step 4, Enter 1 at 'Weight' section
		int weight = 1;
		driver.findElement(By.xpath("//input[@formcontrolname='itemWeight']")).sendKeys(String.valueOf(weight));
		
		// Step 5, Verify user can see multiple options
		driver.findElement(By.xpath("//*[contains(text(), 'Calculate')]")).click();
		
		By quote = By.xpath("//*[contains(text(), 'Your Quote')]");
		wait.until(ExpectedConditions.presenceOfElementLocated(quote));
		WebElement booking = driver.findElement(By.xpath("//*[contains(text(), 'Select a quote to start booking your shipment.')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", booking);
		
		List<WebElement> counter = driver.findElements(By.xpath("//*[contains(text(), 'Book Now')]"));
		int count = counter.size();
		System.out.println("Total Shipment Options from 35600 Malaysia to India: " + count);
		
		driver.quit();

	}

}
