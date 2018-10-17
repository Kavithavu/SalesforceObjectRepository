package com.testing.repo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ObjectMap {
	Properties prop;
	
	public ObjectMap(String strPath)
	{
		prop=new Properties();
		
		 try {
	            FileInputStream fis = new FileInputStream(strPath);
	            prop.load(fis);
	            fis.close();
	        }catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
	}

	public By getLocator(String strElement) throws Exception {
        
        // retrieve the specified object from the object list
        String locator = prop.getProperty(strElement);
         
        // extract the locator type and value from the object
        String locatorType = locator.split(":")[0];
        String locatorValue = locator.split(":")[1];
         
        // for testing and debugging purposes
        System.out.println("Retrieving object of type '" + locatorType + "' and value '" + locatorValue + "' from the object map");
         
        // return a instance of the By class based on the type of the locator
        // this By can be used by the browser object in the actual test
        if(locatorType.toLowerCase().equals("id"))
            return By.id(locatorValue);
        else if(locatorType.toLowerCase().equals("name"))
            return By.name(locatorValue);
        else if((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
            return By.className(locatorValue);
        else if((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
            return By.className(locatorValue);
        else if((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
            return By.linkText(locatorValue);
        else if(locatorType.toLowerCase().equals("partiallinktext"))
            return By.partialLinkText(locatorValue);
        else if((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
            return By.cssSelector(locatorValue);
        else if(locatorType.toLowerCase().equals("xpath"))
            return By.xpath(locatorValue);
        else
            throw new Exception("Unknown locator type '" + locatorType + "'");
    }
	public static void main(String[] args) {

		ObjectMap objmap=new ObjectMap("C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SalesforceObjectRepositoryAutomation\\src\\test\\resources\\objectmap.properties");
		WebDriver driver = new FirefoxDriver();
        driver.get("https://login.salesforce.com");
        
        try {
            
            // Retrieve search text box from object map and type search query
            WebElement username = driver.findElement(objmap.getLocator("salesforce.loginpage.username"));
            username.sendKeys("kavithavuppin@gmail.com");
             
            //Retrieve search text box from object map and type search query
            WebElement password = driver.findElement(objmap.getLocator("salesforcce.loginpage.password"));
            password.clear();
//            password.sendKeys(" ");
            
            // Retrieve search button from object map and click it
            WebElement login = driver.findElement(objmap.getLocator("salesforce.loginpage.click"));
            login.click();
            
           
           
             
            // Retrieve number of search results using results object from object map
//            element = driver.findElement(objMap.getLocator("bing.resultspage.results"));
//            System.out.println("Search result string: " + element.getText());
//             
//            // Verify page title
//            Assert.assertEquals(driver.getTitle(), "Alfa Romeo - Bing");
             
        } catch (Exception e) {
            System.out.println("Error during test execution:\n" + e.toString());
        }
	}

}
