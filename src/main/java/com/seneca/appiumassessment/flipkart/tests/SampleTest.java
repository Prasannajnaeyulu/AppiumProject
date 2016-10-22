package com.seneca.appiumassessment.flipkart.tests;

import org.testng.annotations.Test;

import junit.framework.Assert;

public class SampleTest {
	@Test
	public void test1(){
		System.out.println("Inside Test1");
		Assert.assertEquals(true, false);
	}
	
	@Test
	public void test2(){
		System.out.println("Inside Test2");
	}

	@Test
	public void test3(){
		System.out.println("Inside Test3");
	}

}
