package com.seneca.appiumassessment.flipkart.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import io.appium.java_client.android.AndroidElement;

public class FlipkartHomepage extends AbstractPageObject{
	
	public void searchforProduct(String nameorcategory){
		AndroidElement element = waitForElementVisibility(By.id("search_widget_textbox"));
		tapOn(element);
		AndroidElement searchbox = driver.findElement(By.id("search_autoCompleteTextView"));
		searchbox.sendKeys(nameorcategory);
		AndroidElement contextviewelement = driver.findElementsById("incorrect_login_text").get(0);
		contextviewelement.click();
	}
	

	@Override
	public void navigateToPage() {

	}

	@Override
	public void assertInPage() {
		// TODO Auto-generated method stub
		
	}
}
