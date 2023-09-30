package sof20b141;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Screanshot {
	
	private WebDriver driver;
	public Screanshot(WebDriver driver) {
		this.driver=driver;
	}

	public void capture(String name) {
        try{
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String destinationPath = "Screanshots/"+name + ".png";
            FileHandler.copy(screenshotFile, new File(destinationPath));
            
        } catch (Exception e){
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
	}
}