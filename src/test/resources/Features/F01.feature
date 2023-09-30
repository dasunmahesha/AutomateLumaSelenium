#Author: dasunmahesha@gmail.com

Feature: Buy two different items in “YOGA Styles” of LUMA and add in cart and proceed with the payment.

  Scenario: User logging to LUMA
    Given open browser and "https://magento.softwaretestingboard.com/" load the url
    When Clicks "Sign In"
    And Enters username "maheshadasun@gmail.com" Enters password "Dasun123" Clicks "Sign In"
    Then user lands "Home Page"
    When click "Shop New Yoga"

    And user select item "Echo Fit Compression Short" with size "29" and color "Purple" and cart "1"
    And user select item "Elisa EverCool™ Tee" with size "M" and color "Red" and cart "2"
    
		And go to "My Cart" and place a oder
		And fill shiping deatils
		And user clicks "Place Order"
		And Check "Thank you for your purchase!" massage
		Then quit browser