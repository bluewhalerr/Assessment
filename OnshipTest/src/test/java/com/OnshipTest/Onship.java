package com.OnshipTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Onship {
	public static WebDriver driver;
	public static WebDriverWait wait;

	public static void main(String[] args) throws IOException, AWTException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://onship.app/onship");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions actions = new Actions(driver);
		
//--------SCENARIO-1--------------------
		// LoginPage
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		userLogin("Sush#1203");
		// HomePage-to overcome Thread.sleep
		// waitInvisibleElement(25,
		// driver.findElement(By.xpath("//div[@class='circularBarComponent']//child::div//child::div/div[contains(text(),'Loading')]")));
		Thread.sleep(40000);
		wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		profileMenu();

		// Profile page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// profilepic
		WebElement camIcon = driver.findElement(By.xpath("//i[@class='fa fa-camera']"));
		camIcon.click();

		Robot r = new Robot();
		   String filePath = "C:\\Users\\User\\Downloads\\ship.png";
           StringSelection str = new StringSelection(filePath);
           Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
           r.keyPress(KeyEvent.VK_CONTROL);
           r.keyPress(KeyEvent.VK_V);
      	r.keyRelease(KeyEvent.VK_CONTROL);
           r.keyRelease(KeyEvent.VK_V);		
		r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
        
      //Runtime.getRuntime().exec("src\\test\\resources\\AutoIT\\ProfilePic.exe");
		// Profile details
		// nationality
//				WebElement profileUpdateText = driver.findElement(By.xpath("//div[@class=\"d-flex align-items-center justify-content-between\"]"));
//				wait.until(ExpectedConditions.visibilityOf(profileUpdateText));

		//profileMenu();
		
		Thread.sleep(2000);
		
		WebElement nationality = driver.findElement(By.xpath("(//div[@id='nationality'])[@role='button']"));
		nationality.click();

		WebElement container = driver.findElement(By.xpath("//ul[@aria-labelledby='nationality']"));
		WebElement option = driver.findElement(By.xpath("//li[@data-value='Indian']"));
		js.executeScript("arguments[0].scrollTop = arguments[1].offsetTop;", container, option);
		option.click();

		// city
		WebElement cityInput = driver.findElement(By.xpath(
				"//div[@class='regional-details']/child::form/child::div/following-sibling::div/following-sibling::div/following::div/child::div/following::div/input[@id='cityBill']"));
		WebElement city = wait.until(ExpectedConditions.elementToBeClickable(cityInput));
		cityInput.click();
		cityInput.clear();
		cityInput.sendKeys("chennai");
		
		// save
		WebElement saveProfile = driver.findElement(By.xpath("//button[@type='button'][contains(text(),'Save')]"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		saveProfile.click();

		// add the profile
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		profileMenu();
		Thread.sleep(2000);
		WebElement addPosition = driver.findElement(By.xpath("//button[contains(text(),'Edit position or role')]"));
		actions.moveToElement(addPosition).click().build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-body']")));

		WebElement yesRadioBtn = driver
				.findElement(By.xpath("(//input[@name='radio-buttons-group'])[@value='Yes'][1]"));
		yesRadioBtn.click();

		WebElement dropdownType = driver.findElement(By.xpath("//div[@id='select-role']"));
		actions.moveToElement(dropdownType).click().build().perform();
		
		Thread.sleep(3000);
		
		WebElement optionType = driver.findElement(By.xpath("//li[@data-value='Engine']"));
		actions.moveToElement(optionType).click().build().perform();
		
		Thread.sleep(3000);
		
		WebElement dropdownRank = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='select-rank']")));
		actions.moveToElement(dropdownRank).click().build().perform();
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		WebElement optionRank = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-value='Chief Engineer']")));
		actions.moveToElement(optionRank).click().build().perform();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement saveRole = driver.findElement(By.xpath("(//button[@type='button'])[text()='Save'][2]"));
		saveRole.click();
		
		Thread.sleep(2000);
		
		WebElement p = driver.findElement(By.xpath("//div[contains(@class,'dropdown dropstart')]//child::button"));
		
		actions.moveToElement(p).click().build().perform();
		wait.until(ExpectedConditions.visibilityOf(p));
		WebElement logOut = driver.findElement(By.xpath("//div[@role='menu']/child::a/following::a"));
		actions.moveToElement(logOut).click().build().perform();

		// change password
		//profileMenu();
		
		userLogin("Sush#1203");
		
		Thread.sleep(30000);
		
		WebElement p1 = driver.findElement(By.xpath("//div[contains(@class,'dropdown dropstart')]//child::button"));
		actions.moveToElement(p1).click().build().perform();
		
		WebElement mp = driver.findElement(By.linkText("My Profile"));
		actions.moveToElement(mp).click().build().perform();

		WebElement changePassword = driver.findElement(By.linkText("Change Password"));
		actions.moveToElement(changePassword).click().build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-content']")));

		WebElement currentPassword = driver.findElement(By.id("inputCP"));
		currentPassword.sendKeys("Sush#1203");

		WebElement newPassword = driver.findElement(By.id("inputPassword"));
		newPassword.sendKeys("Sush$1203");

		WebElement confirmPassword = driver.findElement(By.id("inputConfirmPassword"));
		confirmPassword.sendKeys("Sush$1203");
		
		
		WebElement submitBtn = driver.findElement(By.xpath("//button[text()='Submit']"));
		wait.until(ExpectedConditions.visibilityOf(submitBtn));
		submitBtn.click();
		
		userLogin("Sush$1203");

//--------SCENARIO-2&3--------------------
		//chat bot
		Thread.sleep(20000);
		WebElement emmaCatalogue = driver.findElement(By.xpath("(//div[@class=\"catalog-card\"])[1]"));
		
		emmaCatalogue.click();
		
		WebElement emmaInput = driver.findElement(By.id("div-input-editable"));
		emmaInput.sendKeys("Can you provide tips for improving productivity at sea?");
		
		WebElement sendIcon = driver.findElement(By.xpath("//span[@class='pointer']"));
		sendIcon.click();
		
		java.util.List<WebElement> elements = driver.findElements(By.xpath("//span[@class='chat-username mr-2 onship_bold_font']/parent::div/parent::div/child::div/child::div"));

		int size = elements.size();

		WebElement element = elements.get(size-1);

		System.out.println(element.getText());
		
		driver.quit();

		
	}

	public static void profileMenu() {
		WebElement profileMenu = driver
				.findElement(By.xpath("//div[contains(@class,'dropdown dropstart')]//child::button"));
		profileMenu.click();

		WebElement myProfile = driver.findElement(By.linkText("My Profile"));
		myProfile.click();

	}
	
	public static void profileWait() {
		WebElement profileUpdateText = driver.findElement(By.xpath("//div[@class=\"d-flex align-items-center justify-content-between\"]"));
		wait.until(ExpectedConditions.visibilityOf(profileUpdateText));
	}
	
	public static void userLogin(String passwordKey) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		WebElement email = driver.findElement(By.xpath("//input[@type='email']"));
		email.sendKeys("sushmaanth.r@gmail.com");

		WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
		password.sendKeys(passwordKey);//current password "Sush$1203"

		WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		loginBtn.click();
		
	}

	

}
