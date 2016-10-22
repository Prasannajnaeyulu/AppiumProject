package com.seneca.appiumassessment.flipkart.pageobject;

public enum HomeButtonOptions {
   LIFESTYLE("Lifestyle "),
   TELEVISIONS("Televisions"),
   APPLIANCES("Appliances"),
   HOME("Home"),
   STYLETECH("StyleTech"),
   OFFER_ZONE("Offer Zone"),
   NOTIFICATIONS("Notifications"),
   MY_REWARDS("My Rewards"),
   MY_CART("My Cart"),
   MY_WISHLIST("My Wishlist"),
   MY_ORDERS("My Orders"),
   MY_ACCOUNT("My Account"),
   GIFT_CARD("Gift Card"),
   STORES("Stores"),
   MY_CHATS("My Chats"),
   HELP_CENTRE("Help Centre"),
   LEGAL("Legal");
	
   String option;
   private HomeButtonOptions(String option){
	   this.option = option;
   }
   
   public String getOptionValue(){
	   return option;
   }
   
}
