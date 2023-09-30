package com.stepDefinetion;

import sof20b141.Geetdrive;
import sof20b141.Screanshot;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



//import org.openqa.selenium.support.ui.Select;

public class SD01 extends Geetdrive {	
	
	private WebDriver drive = getdriver();//assign webdriver to webdriver variable
	 
	Screanshot screanshot =new Screanshot(drive);//create instant to capture screenshot
	
	@Given("open browser and {string} load the url")
	public void homepageLoading(String url) throws InterruptedException {
		
        
		drive.manage().window().maximize();//maximize window for better view
		drive.get(url);
        Thread.sleep(1000);
        screanshot.capture("01-homepage");//capture screenshot of home page
		
	}

	@When("Clicks {string}")
	public void clicksSignIn(String Signinbtn) {
		
		drive.findElement(By.linkText(Signinbtn)).click();//sign in button clicking
		
	}

	@And("Enters username {string} Enters password {string} Clicks {string}")
	public void enterUsername(String username,String password,String Signin) {
		drive.findElement(By.id("email")).sendKeys(username);//username entered
		drive.findElement(By.id("pass")).sendKeys(password);//password entered 
		screanshot.capture("02-loging");//capture screenshot of login page
		drive.findElement(By.xpath("//button[contains(., '"+Signin+"')]")).click();//sign in button click
	}


	@Then("user lands {string}")
	public void homepage_loaded(String title) throws InterruptedException {
		Thread.sleep(3000);//wait 3s to load page title
		String curunttitle = (String)drive.getTitle();//get currant page title to currant-title and compare with expected page title
		if(curunttitle.equalsIgnoreCase(title)) {
			//home page loaded so code continue
		}else {
			System.err.println("Home page didn't load.");//Displaying err if title did't match with expected title
		}
		
	}

	@When("click {string}")
	public void clickshopnewyoga(String shopyogabtn) {

		drive.findElement(By.xpath("//span[contains(., '"+shopyogabtn+"')]")).click();//shop new yoga button click
	}


	@And("user select item {string} with size {string} and color {string} and cart {string}")
	public void selectItemWithSizeAndColor(String itemName, String size, String color ,String itemnumber) throws InterruptedException {
	  
		WebDriverWait wait = new WebDriverWait(drive, Duration.ofSeconds(25));
		WebElement item=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(.//a[@class='product-item-link']/text(), '"+itemName+"')]")));
	    ((JavascriptExecutor) drive).executeScript("arguments[0].scrollIntoView(true);", item);//scroll into item
	    
	    item.findElement(By.xpath(".//div[@aria-label='" + size + "']")).click();// Size of item
	    item.findElement(By.xpath(".//div[@aria-label='" + color + "']")).click(); // Color of item
	    item.findElement(By.xpath(".//button[@title='Add to Cart']")).click();// Add to cart item
	    Thread.sleep(5000);
	    screanshot.capture("04."+itemnumber+"_"+itemName+"_"+size+"_"+color+"_selected");//capture screenshot of oder 
	    
	}
	
	
	@And("go to {string} and place a oder")
	public void gotocart(String cart) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(drive, Duration.ofSeconds(60));
		WebElement cartbtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(.,'"+cart+"')]")));//cart button 
		Thread.sleep(2000);//waiting to loading appearance
		cartbtn.click();
		screanshot.capture("05-cart_btn_click");
		
		WebElement checkoutbtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("top-cart-btn-checkout")));//proceed to checkout btn
		Thread.sleep(2000);//waiting to loading
		checkoutbtn.click();
		screanshot.capture("06-click_chekout");
	}

	@And("fill shiping deatils")
	public void fillshipingdeatils() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(drive, Duration.ofSeconds(25));//wait object to wait until loading element to loading
		WebElement Nextbtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//button[@data-role='opc-continue']")));
		Thread.sleep(4000);
		Nextbtn.click();//next button clicked
		
	}
	
	@And("user clicks {string}")
	public void user_clicks(String btntxt) throws InterruptedException {
	    
	    WebDriverWait wait = new WebDriverWait(drive, Duration.ofSeconds(25));
		WebElement placeoderbtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//button[@title='"+ btntxt +"']")));
		Thread.sleep(4000);
		placeoderbtn.click();//placeoderbtn button clicked
	    
	}
	
	
	@And("Check {string} massage")
	public void check_massage(String message) throws InterruptedException {
		
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(drive, Duration.ofSeconds(25));
		WebElement responce = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//span[@class='base' and @data-ui-id='page-title-wrapper']")));
		Thread.sleep(5000);	//wait to element stable
		
		 if(responce.getText().equalsIgnoreCase(message)) {
	    	//Continue code
	    }else {
	    	System.err.println("Didn't Compleate purchase!");//output err oder didn't place properly
	    }
	    screanshot.capture("08-"+message);//capture screenshot of oder completion
	}
	
	@Then("quit browser")
	public void quit() throws InterruptedException {
		Thread.sleep(2000);
		drive.quit();//quit the driver
		
	}
	
}
