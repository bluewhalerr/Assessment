package com.OnshipTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public static Actions action;

	public static WebDriver launchBrowser(String browser) throws Throwable {

		try {
			if (browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("incognito");
				options.addArguments("--ignore-certificate-errors");
				options.addArguments("chrome.switches", "--disable-extensions");
				options.addArguments("--disable-notifications");
				options.addArguments("enable-automation");
				options.addArguments("--start-maximized");
				driver = new ChromeDriver(options);

			} else if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();

			} else if (browser.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("invalid browser");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return driver;

	}

	public static WebDriver getUrl(String url) {
		driver.get(url);
		return driver;
	}
	
	public static String titleOfWebpage() {
		return driver.getTitle();
	}
	
	public static String currentUrl() {
		return driver.getCurrentUrl();
	}
	
	public static void inputClear(WebElement ele) {
		ele.clear();
	}
	
	public static void userInput(WebElement element, String value) {
		element.sendKeys(value);
	}
	
	public static void eclick(WebElement element) {
		element.click();

	}
	
	public static void printValue(WebElement element) {
		String text = element.getText();
		System.out.println(text);
	}
	
	public static void alertPopUp() {
		driver.switchTo().alert().accept();
	}
	
	public static void forwardNavigate() {
		driver.navigate().forward();
	}
	
	public static void backNavigate() {
		driver.navigate().back();
	}
	
	public static void refreshNavigate() {
		driver.navigate().refresh();
	}
	
	public static void pageUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-500)");
	}

	public static void pageDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
	}	
	
	public static void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public static void waituntilElementVisibility(int timeDuration, WebElement element) {
		try {
			WebDriverWait wb = new WebDriverWait(driver, Duration.ofSeconds(timeDuration));
			wb.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("NoSuchElementException");
			throw new RuntimeException();
		}
	}
	
	public static void waituntilElementClickable(int timeDuration, WebElement element) {
		try {
			WebDriverWait wb = new WebDriverWait(driver, Duration.ofSeconds(timeDuration));
			wb.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Element unable to click");
			throw new RuntimeException();
		}
	}
	
	public static void waitInvisibleElement(int timeDuration, WebElement element) {
		try {
			WebDriverWait wb = new WebDriverWait(driver, Duration.ofSeconds(timeDuration));
			wb.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Element disappears");
			throw new RuntimeException();
		}
	}
	
	public static void mouseAction(WebElement element, String options) throws Throwable {
		Actions a = new Actions(driver);
		try {
			if (options.equalsIgnoreCase("click")) {
				waituntilElementVisibility(30, element);
				a.click(element).perform();
			} else if (options.equalsIgnoreCase("context click")) {
				waituntilElementVisibility(30, element);
				a.contextClick(element).perform();
			} else if (options.equalsIgnoreCase("double click")) {
				waituntilElementVisibility(30, element);
				a.doubleClick(element).perform();
			} else if (options.equalsIgnoreCase("move to element")) {
				waituntilElementVisibility(30, element);
				a.moveToElement(element).perform();
				a.click().perform();
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("invalid mouse action");
		}
	}
	
	public static void dragAndDrop(WebElement from, WebElement to) {
		action = new Actions(driver);
		action.dragAndDrop(from, to).build().perform();
	}
	
	public static void WebElementDisplay(WebElement element) {
		boolean display = element.isDisplayed();
		if (display) {
			System.out.println("Button is display");
		} else {
			System.out.println("Button not display");
		}
	}
	
	public static void webElementEnabled(WebElement element) {
		boolean enabled = element.isEnabled();
		if (enabled) {
			System.out.println("Button is enabled");
		} else {
			System.out.println("Button not enabled");
		}
	}
	
	public static void dropDown(WebElement element, String Option, String value) {
		Select sc = new Select(element);
		try {
			if (Option.equalsIgnoreCase("byIndex")) {
				int parseInt = Integer.parseInt(value);	
				sc.selectByIndex(parseInt);
			} else if (Option.equalsIgnoreCase("byValue")) {
				sc.selectByValue(value);
			} else if (Option.equalsIgnoreCase("byVisibleText")) {
				sc.selectByVisibleText(value);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("invalid select");
		}
		WebElement selectedOption = sc.getFirstSelectedOption();
		String selectedText = selectedOption.getText();
		System.out.println("Selected Option: " + selectedText);
	}
	
	public static void dropDownOption(WebElement ele) {
		Select dropOptions = new Select(ele);

		List<WebElement> options = dropOptions.getOptions();

		for (WebElement option : options) {
			System.out.println(option.getText());
		}

	}
	
	public void takeScreenshot(String stepName) throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
		Date currentDate = new Date();
		String timeStamp = dateFormat.format(currentDate);
		String fileName = stepName + "_" + timeStamp;
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File("./Screenshots/"+ fileName + ".png");
		FileUtils.copyFile(source, destination);
	}
	
	public static WebElement framesWindows(int index, String nameOrId, WebElement ele) {
		if (nameOrId != null && !nameOrId.isEmpty()) {
			driver.switchTo().frame(nameOrId);
		} else if (index >= 0) {
			driver.switchTo().frame(index);
		} else if (ele != null)
			driver.switchTo().frame(ele);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		return null;
	}
	
	public static void iterationWidowHandling(String text) {
		String homeTab = driver.getWindowHandle();
		Set<String> windowHandels = driver.getWindowHandles();
		Iterator<String> childTab = windowHandels.iterator();
		while (childTab.hasNext()) {
			String selenium = childTab.next();
			String title = text;
			if (driver.switchTo().window(selenium).getTitle().contains(title)) {
				break;
			}
		}
	}
}
