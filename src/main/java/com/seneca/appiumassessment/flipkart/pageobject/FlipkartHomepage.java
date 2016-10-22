package com.seneca.appiumassessment.flipkart.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.log4testng.Logger;

import io.appium.java_client.android.AndroidElement;

public class FlipkartHomepage extends AbstractPageObject{
	
	Logger logger = Logger.getLogger(FlipkartHomepage.class);
	
	public void searchforProduct(String nameorcategory){
		AndroidElement element = waitForElementVisibility(By.id("search_widget_textbox"));
		tapOn(element);
		AndroidElement searchbox = driver.findElement(By.id("search_autoCompleteTextView"));
		searchbox.sendKeys(nameorcategory);
		AndroidElement contextviewelement = driver.findElementsById("incorrect_login_text").get(0);
		contextviewelement.click();
		logger.info("Successfully Searched the Product");
	}
	
	public void navigateToFlipKartHomeButton(){
		AndroidElement homebutton = waitForElementVisibility(By.xpath("//android.widget.ImageButton[@content-desc='Open Drawer']"));
		homebutton.click();
	}
	
	public void navigateToHomeButtonOption(HomeButtonOptions option){
		scrollToView(option.getOptionValue());
		AndroidElement homebuttonoption = waitForElementVisibility(By.xpath("//android.widget.TextView[@text='"+option.getOptionValue()+"']"));
		homebuttonoption.click();
	}
	

	@Override
	public void navigateToPage() {

	}

	@Override
	public void assertInPage() {
		// TODO Auto-generated method stub
		
	}
}
