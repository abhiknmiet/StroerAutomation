package com.stroeer.testAutomation.bussinesslogic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import com.stroeer.steps.ParentStep;
import com.stroeer.util.BrowserFactory;


/**
 * This class defines all the elements for a specific page. Every Class which
 * contains page objects should extend "STROEERUtility" class This gives access to
 * the webdriver object and utility methods
 */
/**
 * @author Abhishek Kumar
 *
 */
public class StroeerUtility extends BrowserFactory {

	
	
	WebElement webelement;
	

	public void scrollToElement(WebElement element) {
		if (element.isDisplayed()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		} else {
			System.out.println("Element is not displayed");
		}
	}

	/**
	 * Mathod for taking screenshot
	 * 
	 * @param None
	 * 
	 */

	public void takeScreenShot() {

		

		try {
			// This takes a screenshot from the driver at save it to the specified location
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		
			File destinationPath = new File(System.getProperty("user.dir") + "/TestReport/" + ParentStep.environment + "_" + ParentStep.executionId + "\\" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) + ".png");
			// Copy taken screenshot from source location to destination location
			Files.copy(sourcePath, destinationPath);

			// This attach the specified screenshot to the test
			Reporter.addScreenCaptureFromPath(destinationPath.toString());

		} catch (IOException e) {
		}
	}


	/**
	 * Mathod for sending text into textBox
	 * 
	 * @param string
	 * @param inputtext
	 * @throws InterruptedException
	 */
	public void inputTextByXpath(String xPath, String text) {
		performActionOnTextbox(By.xpath(xPath), text);
	}

	/**
	 * @throws InterruptedException
	 * 
	 */
	public void inputText(String elementId, String text) {
		performActionOnTextbox(By.id(elementId), text);
	}

	

	/*
	 * Mathod for tab out
	 * 
	 */
	public void tabPress(String xpath) {

		WebElement webElementToClick = getWebElement(By.xpath(xpath));
		webElementToClick.sendKeys(Keys.TAB);
	}


	/*
	 * Method for waiting for web element
	 */
	public void waitForElementByCSS(String css) {

		WebElement webElement = driver.findElement(By.cssSelector(css));
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}



	/**
	 * Method for getting page title
	 * 
	 * @return
	 */
	public String getPageTitle() {
		if (driver != null) {
			return driver.getTitle();
		}
		return "NO PAGE OPEN YET";
	}

	/*
	 * Method for moving mouse to Element
	 */

	public void moveToElement(String xpath) {
		if (!mouseHoverOnControl(By.xpath(xpath))) {
			System.out.println("Error in moving to element with xpath: " + xpath);
			Assert.assertTrue(false, "Error in moving to element with xpath: " + xpath);
			takeScreenShot();
		}
	}

	

	public void doubleClickWebElement(String pre_xpath) {
		
		doubleClickOnControl(By.xpath(pre_xpath));
	}

	/*
	 * Method for getting all list by xpath
	 */

	public List<WebElement> getWebElementsByXpath(String xpath) {
		try {

			
			List<WebElement> webElement = getListofWebElements(By.xpath(xpath));
			return webElement;
		} catch (Exception nsee) {
			return null;
		}
	}

	public void mouseClickByLocator(String Xpath) {
	
		clickOnControl(By.xpath(Xpath));
		System.out.println("clicked");
	}


	


	/**
	 * Method for getting the Text using Xpath
	 * 
	 * @throws InterruptedException
	 */

	public String getTextByXpath(String xPath) {
		
		WebElement webElement = getWebElement(By.xpath(xPath));
		return webElement.getText();

	}

	/**
	 * Using javascript executor to execute the DOM command
	 * 
	 * @throws InterruptedException
	 */

	public void javaScriptExec(String exec_script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(exec_script);
	}



	public void clickElementByLinkText(String text) {

		clickOnControl(By.linkText(text));
	}

	
	

	public List<WebElement> getListofWebElements(By byControlIdentifier) {
		List<WebElement> webElementList = null;
		if (waitForJSandJQueryToLoad()) {
			try {
				webElementList = wait.ignoring(StaleElementReferenceException.class)
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byControlIdentifier));
			} catch (ElementNotInteractableException e) {
				System.out.println("[" + new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date())
						+ "] : ElementNotVisibleException at getListofWebElements() method for control: "
						+ byControlIdentifier + " occured. Actual exception message: " + e.getMessage());
			} catch (Exception e) {
				System.out.println("[" + new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date())
						+ "] : Exception at getListofWebElements() method for control: " + byControlIdentifier
						+ " occured. Actual exception message: " + e.getMessage());
			}
		}
		return webElementList;

	}

	public WebElement getWebElement(By byControlIdentifier) {
		WebElement webElement = null;
		if (waitForJSandJQueryToLoad()) {
			webElement = wait.ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(byControlIdentifier));

		}
		return webElement;
	}

	private boolean PerformControlAction(TestAutoControlInput STROEERControlitem) {
		// Do not print value of Password control
		if (STROEERControlitem.controlIdentifier.toString().contains("assword")) {
			System.out.println("[" + new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date())
					+ "] : Action to start for Control Type : " + STROEERControlitem.controlType + " - Identifier : "
					+ STROEERControlitem.controlIdentifier);
		} else {
			System.out.println("[" + new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date())
					+ "] : Action to start for Control Type : " + STROEERControlitem.controlType + " - Identifier : "
					+ STROEERControlitem.controlIdentifier + " - Value : " + STROEERControlitem.valueToUse);
		}

		WebElement webElement = getWebElement(STROEERControlitem.controlIdentifier);

		try {
			if (webElement != null) {
				switch (STROEERControlitem.controlType) {
				case Textbox:
					fillInputValue(webElement, STROEERControlitem.valueToUse);
					break;
				case Dropdown:
					selectDropDownItem(webElement, STROEERControlitem.valueToUse, STROEERControlitem.dropdownSelectBy);
					break;
				case SetFocus:
					setFocus(webElement);
					break;
				case ClickElement:
					clickElement(webElement);
					break;
				case Click:
					click(webElement);
					break;
				case DoubleClickElement:
					doubleClickElement(webElement);
					break;
				case MoveElement:
					moveElement(webElement);
					break;
				case RadioButton:
					clickElement(webElement);
					break;
				case Checkbox:
					clickElement(webElement);
					break;
				default:
					System.out.println("Control Type is incorrect.");
					break;
				}

				System.out.println("[" + new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date())
						+ "] : Action completed for - Control Type : " + STROEERControlitem.controlType
						+ " - Identifier : " + STROEERControlitem.controlIdentifier);
				return true;
			} else {
				takeScreenShot();
				System.out.println("[" + new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date())
						+ "] : Could not find Webelement, so Action not completed for - Control Type : "
						+ STROEERControlitem.controlType + " - Identifier : " + STROEERControlitem.controlIdentifier);
				Reporter.addStepLog("[" + new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date())
						+ "] : Element Control Type : " + STROEERControlitem.controlType + " - Identifier : "
						+ STROEERControlitem.controlIdentifier + " not found");
				
				return false;
			}
		} catch (Exception ex) {
			takeScreenShot();
			System.out.println("[" + new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date())
					+ "] : Exception while performing operation on control - Control Type : "
					+ STROEERControlitem.controlType + " - Identifier : " + STROEERControlitem.controlIdentifier
					+ ". Actual excpetion message: " + ex.getMessage());
			
			return false;
		}

	}

	private void fillInputValue(WebElement webElement, String strValue) {
		wait.until(ExpectedConditions.elementToBeClickable(webElement));

		boolean flag = true;
		while (flag) {
			webElement.clear();
			webElement.sendKeys(strValue);
			sleepWithComment(1, "Waiting to verify textbox value");
			if (webElement.getAttribute("value").equalsIgnoreCase(strValue)) {
				flag = false;
			}
		}
	}

	private void selectDropDownItem(WebElement webElement, String strValue, enumDropdownSelectBy DropdownItemlocator) {
		if (webElement.isEnabled()) {
			Select itemList = new Select(webElement);

			switch (DropdownItemlocator) {
			case Value:
				itemList.selectByValue(strValue);
				break;
			case Index:
				itemList.selectByIndex(Integer.parseInt(strValue));
				break;
			case VisibleText:
				boolean flag = true;
				while (flag) {
					System.out.println(
							"Selected option in dropdown:: " + itemList.getFirstSelectedOption().getText().trim());
					itemList.selectByVisibleText(strValue);
					sleepWithComment(1, "Waiting for 1 second till Drop down option gets selected.");
					if (itemList.getFirstSelectedOption().getText().trim().equalsIgnoreCase(strValue.trim())) {
						flag = false;
						System.out.println(
								"Selected option in dropdown:: " + itemList.getFirstSelectedOption().getText());
					}
				}
				
				break;
			case NA:
				break;
			}
		}

	}

	private void setFocus(WebElement webElement) {
		webElement.sendKeys(Keys.TAB);
	}

	private void clickElement(WebElement webElement) {
		Actions builder = new Actions(driver);
		builder.moveToElement(webElement).click().build().perform();
	}

	private void click(WebElement webElement) {
		if (webElement != null) {
			wait.until(ExpectedConditions.elementToBeClickable(webElement));
			webElement.click();
			System.out.println("[" + new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date())
					+ "] : Action completed for - Control Type : ClickElement - Identifier : " + webElement);
		}

	}

	private void doubleClickElement(WebElement webElement) {
		Actions builder = new Actions(driver);
		builder.moveToElement(webElement).doubleClick().build().perform();
	}

	private void moveElement(WebElement webElement) {
		Actions builder = new Actions(driver);
		builder.moveToElement(webElement).build().perform();
	}

	public boolean performActionOnTextbox(By byControlIdentifier, String strValuetoUse) {
		return PerformControlAction(new TestAutoControlInput(enumControlType.Textbox, byControlIdentifier, strValuetoUse,
				enumDropdownSelectBy.NA, false));
	}

	public boolean setFocusOnControl(By byControlIdentifier) {
		return PerformControlAction(new TestAutoControlInput(enumControlType.SetFocus, byControlIdentifier, "",
				enumDropdownSelectBy.NA, false));
	}

	public boolean performActionOnDropDown(By byControlIdentifier, String strValuetoUse,
			enumDropdownSelectBy enDropdownSelectBy) {

		return PerformControlAction(new TestAutoControlInput(enumControlType.Dropdown, byControlIdentifier, strValuetoUse,
				enDropdownSelectBy, false));
	}

	public boolean performActionOnCheckBox(By byControlIdentifier, boolean bChecked) {
		return PerformControlAction(new TestAutoControlInput(enumControlType.Checkbox, byControlIdentifier, "",
				enumDropdownSelectBy.NA, bChecked));
	}

	public boolean performActionOnRadioButton(By byControlIdentifier, boolean bChecked) {
		return PerformControlAction(new TestAutoControlInput(enumControlType.RadioButton, byControlIdentifier, "",
				enumDropdownSelectBy.NA, bChecked));
	}

	public boolean clickOnControl(By byControlIdentifier) {

		return PerformControlAction(new TestAutoControlInput(enumControlType.ClickElement, byControlIdentifier, "",
				enumDropdownSelectBy.NA, false));
	}

	public boolean performClickOnElement(By byControlIdentifier) {

		return PerformControlAction(
				new TestAutoControlInput(enumControlType.Click, byControlIdentifier, "", enumDropdownSelectBy.NA, false));
	}

	public boolean doubleClickOnControl(By byControlIdentifier) {
		return PerformControlAction(new TestAutoControlInput(enumControlType.DoubleClickElement, byControlIdentifier, "",
				enumDropdownSelectBy.NA, false));
	}

	public boolean mouseHoverOnControl(By byControlIdentifier) {
		return PerformControlAction(new TestAutoControlInput(enumControlType.MoveElement, byControlIdentifier, "",
				enumDropdownSelectBy.NA, false));
	}

	public void switchToFramebyIndex(int index) {
		try {
			driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
		} catch (Exception e) {
			System.out.println("Frame with index :: " + index + " not found.");

		}
	}

	

	// Method to wait for JQuery and Javascript to load completely
	public boolean waitForJSandJQueryToLoad() {

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) getDriver()).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					// no jQuery present
					return true;
				}
			}
		};

		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

	
	public WebElement find(By Element) {
		return getWebElement(Element);
	}

	
	public void click(By Element) {
		performClickOnElement(Element);

	}

	
	public void waitForRequiredWebElementCondition(ExpectedCondition<WebElement> condition) {
		waitForRequiredWebElementCondition(condition, 30, 1000);
		
	}

	public void waitForRequiredWebElementCondition(ExpectedCondition<WebElement> condition, int timeout,
			int pollingInterval) {
		System.out.println("Waiting for " + condition + " for " + timeout + " seconds");
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(pollingInterval)).ignoring(NoSuchElementException.class);
		wait.until(condition);
		System.out.println("Wait completed for " + condition);
	}

	
	public void waitForRequiredWebDriverCondition(ExpectedCondition<WebDriver> condition) {
		System.out.println("Waiting for " + condition + " for 30 seconds");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		
		wait.until(condition);
	}

	
	public void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitForRequiredWebElementCondition(ExpectedConditions.visibilityOfElementLocated(locator));
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
			attempts++;
		}
	}

	

	
	public static void sleep(Integer seconds) {
		Integer miliseconds = seconds * 1000;
		long secondsLong = (long) miliseconds;
		System.out.println("Performing wait action for " + seconds + " seconds");
		try {
			Thread.sleep(secondsLong);
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted.");
			e.printStackTrace();
		}

	}


	public static void sleepWithComment(Integer seconds, String comment) {
		Integer miliseconds = seconds * 1000;
		long secondsLong = (long) miliseconds;
		System.out.println("Performing wait action for " + seconds + " seconds. " + comment);
		try {
			Thread.sleep(secondsLong);
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted.");
			e.printStackTrace();
		}

	}

	
	
	
	public void clickUsingJavaScriptExecutor(By locator) {
		webelement = find(locator);
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click();", webelement);
	}

	
	public void selectValueFromDropDown(By Element, String visibleText) {
		System.out.println("[" + new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date())
				+ "] : Action to start for Control Type : Dropdown - Identifier : " + Element);
		List<WebElement> ddList = driver.findElements(Element);
		try {
			String value = null;
			for (int i = 0; i < ddList.size(); i++) {
				value = ddList.get(i).getText();
				if (value.equalsIgnoreCase(visibleText)) {
					ddList.get(i).click();
					System.out.println("[" + new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date())
							+ "] : Action completed for - Control Type : Dropdown - Identifier : " + Element);
					break;
				} else if (!(value.equalsIgnoreCase(visibleText)) && i == ddList.size() - 1) {
					takeScreenShot();
					System.out.println("[" + new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date())
							+ "] : Action not completed for - Control Type : Dropdown - Identifier : " + Element);
					Reporter.addStepLog("[" + new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date())
							+ "] : Element Control Type : Dropdown - Identifier : " + Element + " not found");
					Assert.assertNotNull(Element);
				}
			}
		} catch (Exception e) {
			takeScreenShot();
			System.out.println("[" + new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date())
					+ "] : Exception while performing operation on control - Control Type : Dropdown - Identifier : "
					+ Element + ". Actual excpetion message: " + e.getMessage());
		}

	}

	

	/**
	 * This method gets visible web element by specifying frequency of polling
	 * 
	 * @param xpath
	 * @param delay
	 * @return
	 */
	public WebElement getVisibileWebElementByXpathWithExplicitWait(String xpath, int delay, int frequencyInMs) {
		@SuppressWarnings("deprecation")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(delay))
				.pollingEvery(Duration.ofSeconds(frequencyInMs))
				.ignoring(WebDriverException.class);

		try {
			WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			return webElement;
		} catch (NoSuchElementException | TimeoutException nsee) {
			return null;
		}
	}
	
	/**
	 * Using javascript executor to execute the DOM command and return in String
	 * 
	 * @throws InterruptedException
	 */

	public String javaScript_GetText(String exec_script) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		String displayString = (String) js.executeScript(exec_script);
		
		return displayString;

	}

}
