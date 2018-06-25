package operation;

import java.io.File;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class UIOperation {
	WebDriver driver;
	public UIOperation(WebDriver driver) {
		//Add a CONSTRACTOR(this):
		//this.GlobalDriver= LocalDriver
		//this.WebDriver= WebDriver
		this.driver = driver ;
	}
	public void KeyWordPerform(Properties p,String keyWord, String objectName,
			String objectType, String Data) throws Exception {
		switch (keyWord.toUpperCase()) {
		case "GOTOURL":
			driver.get(p.getProperty(Data));
			break;
		case "SENDKEYS":
			driver.findElement(this.getObject(p, objectName, objectType)).sendKeys(Data);
			break;
		case "CLICK":
			driver.findElement(this.getObject(p, objectName, objectType)).click();
			break;
		case "SELECT":
			new Select(driver.findElement(this.getObject(p, objectName, objectType))).selectByVisibleText(Data);
			break;
		case "GETTITLE":
			String title = driver.getTitle();
			System.out.println("Title is: "+title);
			break;
		case "GETTEXT":
			String text =driver.findElement(this.getObject(p, objectName, objectType)).getText();
			System.out.println("Text is: "+ text);
			break;
		case "GETTYPEDTEXT":
			String typedtext =driver.findElement(this.getObject(p, objectName, objectType)).getAttribute("value");
			System.out.println("Text is: "+typedtext);
			break;
		
		case "RIGHTCLICK":
			WebElement ele1= driver.findElement(this.getObject(p, objectName, objectType));
			Actions act = new Actions (driver);
			act.contextClick(ele1).build().perform();
		case "SCREENSHOT":
			File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile,new File ("C:\\Oyshi\\Shot.jpg"));
			break;
		case "GETPOINTOFELEMENT":
			Point pnt = driver.findElement(this.getObject(p, objectName, objectType)).getLocation();
			System.out.println("Point is: "+pnt);
			break;
		case "FILEUPLOAD":
			driver.findElement(this.getObject(p, objectName, objectType)).sendKeys("PathOfFile");			
			break;
		case"FILEDOWNLOAD":
			FirefoxProfile fp = new FirefoxProfile();
			fp.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			driver = new FirefoxDriver(fp);
			driver.findElement(this.getObject(p, objectName, objectType));
			System.out.println("FileDownloaded successfully");
			break;
		case "SCROLLDOWN":
			JavascriptExecutor jsc1 = (JavascriptExecutor)driver;
			jsc1.executeScript("window.scrollBy(0,800)");
			break;
		case "SCROLLUP":
			JavascriptExecutor jsc2 = (JavascriptExecutor)driver;
			jsc2.executeScript("window.scrollBy(0,-800)");
			break;
		case "IFRAMEHANDLING":
			driver.switchTo().frame("");
			break;
		case "RETURNTOMAINFRAME":
			driver.switchTo().defaultContent();
			break;
		case "SUBMANUE":
			
			break;
		case "LINKCOUNTING":
			
			break;
		case "DOUBLECLICK":
			
			break;
		case "DRAGANDDROP":
			WebElement src = driver.findElement(this.getObject(p, objectName, objectType));
			WebElement dst = driver.findElement(this.getObject(p, objectName, objectType));
			Actions act1 =new Actions (driver);
			act1.dragAndDrop(src, dst).build().perform();
			System.out.println("Drag and drop Performed Successfully");
			break;
		case "CHILDWINDOWHANDLING":
			Set<String>ChildWindow = driver.getWindowHandles();
			for(String child:ChildWindow) {
				driver.switchTo().window(child);
			}
		break;
		case "MAINWINDOWHANLING":
			String mainWindow = driver.getWindowHandle();
			driver.switchTo().window(mainWindow);
			break;
		case "ALERTHANDLING":
			
			break;
		case "IMAGEVERIFY":
			
			break;
		case "GOTOBACKPAGE":
			driver.navigate().back();
			break;
		case "GOTOFORWARDPAGE":
			driver.navigate().forward();
			break;
		case "PAGEREFRESH":
			driver.navigate().refresh();
		 	break;
		case "CLOSE":	
			driver.close();
			break;
		case "QUIT":
			driver.quit();
			break;
		case "THREAD":
			Thread.sleep(3000);
			break;			
		}
	}
	public By getObject(Properties p,String objectName,String objectType) throws Exception {
		
		if(objectType.equalsIgnoreCase("ID")) {
			return By.id(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("NAME")) {
			return By.name(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("CLASSNAME")) {
			return By.className(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("XPATH")) {
			return By.xpath(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("TAGNAME")) {
			return By.tagName(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("LINKTEXT")) {
			return By.linkText(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("PERTIALLINKTEXT")) {
			return By.partialLinkText(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("CSSSELECTOR")) {
			return By.cssSelector(p.getProperty(objectName));
		}
		else {
			throw new Exception("Wrong Object Type");
		}

	}
}
