package com.seneca.appiumassessment.flipkart.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.seneca.appiumassessment.flipkart.pageobject.FlipkartHomepage;
import com.seneca.appiumassessment.flipkart.pageobject.HomeButtonOptions;
import com.seneca.appiumassessment.flipkart.pageobject.SearchProductsPage;

public class FlipkartHomePageTests extends BaseTest{
	
	@DataProvider(name="HomeButtonOptionsProvider")
	public Object[][] getHomeButtonOptions(){
		
		HomeButtonOptions[] options = new HomeButtonOptions[]{HomeButtonOptions.GIFT_CARD, HomeButtonOptions.HELP_CENTRE,HomeButtonOptions.APPLIANCES, HomeButtonOptions.LIFESTYLE,
				 HomeButtonOptions.HOME, HomeButtonOptions.LEGAL,
				HomeButtonOptions.MY_ACCOUNT};
		
		Object[][] obj = new Object[options.length][1];
		for(int i=0;i<options.length;i++){
			obj[i][0] = options[i];
		}
		return obj;
	}
	
	@Test
	public void searchProduct(){
		FlipkartHomepage homepage = new FlipkartHomepage();
		homepage.searchforProduct("mobile");
		SearchProductsPage page = new SearchProductsPage();
		page.assertInPage();
	}
	
	@Test(dataProvider="HomeButtonOptionsProvider")
	public void validateHomeButtonOptions(HomeButtonOptions option){
		FlipkartHomepage homepage = new FlipkartHomepage();
		homepage.navigateToFlipKartHomeButton();
		homepage.navigateToHomeButtonOption(option);
	}
	
	
}
