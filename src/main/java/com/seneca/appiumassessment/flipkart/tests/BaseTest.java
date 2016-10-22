package com.seneca.appiumassessment.flipkart.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import com.seneca.appiumassessment.flipkart.pageobject.AbstractPageObject;

public class BaseTest {
    
  @AfterMethod
  public void teardown(){
	  AbstractPageObject.quit();
  }
  
}
