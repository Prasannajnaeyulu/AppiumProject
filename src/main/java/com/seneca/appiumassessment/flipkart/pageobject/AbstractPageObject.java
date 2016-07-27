package com.seneca.appiumassessment.flipkart.pageobject;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public abstract class AbstractPageObject {
	private static AppiumDriver<AndroidElement> driver;
	private static Logger logger = Logger.getLogger(AbstractPageObject.class);
	public static boolean isAppOpen = false;

	/*static{
		envprops = new Properties();
		loadprops("environment.properties");
	}

	public static void loadprops(String filename){
		try {
			envprops.load(new FileReader("environment.properties"));
		} catch (FileNotFoundException e) {
			logger.error("environment.properties was not found hence failed to load");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 */

	public AbstractPageObject(){
		try {
			createDriver();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createDriver() throws MalformedURLException{
		if(!isAppOpen){
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "../../../apps/ContactManager");
			File app = new File(appDir, "ContactManager.apk");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("deviceName","Android Emulator");
			capabilities.setCapability("platformVersion", "4.4");
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("appPackage", "com.example.android.contactmanager");
			capabilities.setCapability("appActivity", ".ContactManager");
			driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			isAppOpen = true;
		}
		else
			logger.info("App is already opened");
	}

	public WebElement waitForElement(By bylocator){
		return waitForElement(bylocator, 30);
	}

	public WebElement waitForElementVisibility(By bylocator){
		return waitForElementVisibility(bylocator, 30);
	}

	public WebElement waitForElement(By bylocator, int timeunitinseconds){
		WebElement we=null;
		WebDriverWait wait = new WebDriverWait(driver,timeunitinseconds);

		try{
			we = wait.until(ExpectedConditions.presenceOfElementLocated(bylocator));
		}
		catch(NoSuchElementException e){
			logger.error("Element not found in the given time"+timeunitinseconds);
		}
		return we;
	}

	public WebElement waitForElementVisibility(By bylocator, int timeunitinseconds){
		WebElement we=null;
		WebDriverWait wait = new WebDriverWait(driver,timeunitinseconds);

		try{
			we = wait.until(ExpectedConditions.visibilityOfElementLocated(bylocator));
		}
		catch(Exception e){
			logger.error("Element not visible in the given time"+timeunitinseconds);
		}
		return we;
	}
	public WebElement waitForElementToBeClickable(By bylocator, int timeunitinseconds){
		WebElement we=null;
		WebDriverWait wait = new WebDriverWait(driver,timeunitinseconds);

		try{
			we = wait.until(ExpectedConditions.elementToBeClickable(bylocator));
		}
		catch(Exception e){
			logger.error("Element not clickable in the given time"+timeunitinseconds);
		}
		return we;
	}
	public WebElement waitForElementToBeClickable(By bylocator){
		return waitForElementToBeClickable(bylocator, 20);	
	}

	public void clickLink(String linktext){
		WebElement we = waitForElementVisibility(By.linkText(linktext));
		if(we!=null)
			we.click();
	}
	public void typeEditBox(String editboxidentifier, String texttotype){
		String xpath = "//input[@id='"+editboxidentifier+"' or @name='"+editboxidentifier+"']";
		WebElement editbox = waitForElementVisibility(By.xpath(xpath));
		if(editbox!=null)
		{
			editbox.clear();
			editbox.sendKeys(texttotype);
		}
		else
		{
			logger.error("Failed to find edit box with the xpath: "+xpath);
			return;
		}
	}

	public void clickButton(String buttonidentifier){
		String xpath = "//button[@id='"+buttonidentifier+"' or text()='"+buttonidentifier+"']";
		WebElement button = waitForElementVisibility(By.xpath(xpath));
		if(button!=null)
			button.click();
		else
		{
			logger.error("Failed to find button with the xpath: "+xpath);
			return;
		}
	}

	public void selectByNameorID(String identifier, String valuetoselect){
		String xpath = "//select[@name='"+identifier+"']";
		WebElement selectelement = waitForElementVisibility(By.xpath(xpath));
		if(selectelement!=null)
		{
			Select select = new Select(selectelement);
			select.selectByVisibleText(valuetoselect);
		}
	}

	public boolean isTextPresent(String texttofind){
		int count=0;
		while(!driver.getPageSource().contains(texttofind) && count++<3){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(count>=3){
			return false;
		}

		return true;
	}

	public static void quit() {
		// TODO Auto-generated method stub
		logger.info("inside quite method...");
		driver.quit();
		isAppOpen = false;
	}

	public static void takescreenshot(String filename) throws IOException{
		String outputfile = System.getProperty("user.dir")+"/Screenshots/"+filename;
		logger.info("Taking the screenshot into a file: "+outputfile);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(outputfile));
	}

	public abstract void navigateToPage();
	public abstract void assertInPage();
}
