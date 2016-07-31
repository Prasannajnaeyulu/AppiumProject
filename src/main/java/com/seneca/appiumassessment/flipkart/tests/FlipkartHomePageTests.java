package com.seneca.appiumassessment.flipkart.tests;

import org.testng.annotations.Test;

import com.seneca.appiumassessment.flipkart.pageobject.FlipkartHomepage;
import com.seneca.appiumassessment.flipkart.pageobject.SearchProductsPage;

public class FlipkartHomePageTests {
	@Test
	public void searchProduct(){
		FlipkartHomepage homepage = new FlipkartHomepage();
		homepage.searchforProduct("mobile");
		SearchProductsPage page = new SearchProductsPage();
		page.assertInPage();
	}
}
