package com.seneca.appiumassessment.flipkart.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SearchProductsPage extends AbstractPageObject {

	@Override
	public void navigateToPage() {
		// TODO Auto-generated method stub

	}

	@Override
	public void assertInPage() {
		WebElement sortbutton = waitForElementToBeClickable(By.xpath("//android.widget.TextView[@text='Sort']"));
		Assert.assertNotNull(sortbutton, "Unable to navigate to Search Page");
	}

}
