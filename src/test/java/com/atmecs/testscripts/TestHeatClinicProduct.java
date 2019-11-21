package com.atmecs.testscripts;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.atmecs.actions.ClickOnElementAction;
import com.atmecs.actions.SendKeysAction;
import com.atmecs.constants.ConstantsFilePaths;
import com.atmecs.reports.ExtentReport;
import com.atmecs.testbase.TestBase;
import com.atmecs.utils.LocatorType;
import com.atmecs.utils.ReadExcelFile;
import com.atmecs.utils.ReadLocatorsFile;
import com.atmecs.validation.VerifyShirtItem;

public class TestHeatClinicProduct extends TestBase {
	/*
	 * In this Class,Selecting one Product Validating on that product details
	 */
	Properties properties, properties1;
	ClickOnElementAction click = new ClickOnElementAction();
	SendKeysAction sendkeys = new SendKeysAction();
	ReadExcelFile readexcel = new ReadExcelFile();
	WebElement element;
	String personalizedName, productQuantity, ProjectData;
	String browserUrl;

	@BeforeClass
	public void launchingUrl() throws IOException {
		properties = ReadLocatorsFile.loadProperty(ConstantsFilePaths.CONFIG_FILE);
		browserUrl = properties.getProperty("url1");
		driver.get(browserUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@DataProvider
	public Object[][] inputValues() {
		/*
		 * Data provider is using to read the data from excel file
		 */
		Object data[][] = readexcel.readExcel("inputs-HeatClinic", ConstantsFilePaths.TESTDATA_FILE1);
		return data;
	}

	@Test(dataProvider = "inputValues")
	public void selectingProduct(String name, String quantity) {
		// locators are reading through LOCATOR_FILE
		properties = ReadLocatorsFile.loadProperty(ConstantsFilePaths.LOCATOR_FILE);
		VerifyShirtItem.testingHomePageTabs();
		element = driver.findElement(By.xpath(properties.getProperty("loc-click-mens")));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-mens1"));
		log.info("Clicked Mens Products");
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-shirt"));
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-redcolor"));
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-size"));
		log.info("Selected Shirt,Color and size");
		sendkeys.sendKeys(driver, LocatorType.XPATH, properties.getProperty("loc-sendkey-name"), name);
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-buynow"));
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-cart"));
		log.info("Product added into cart");
		VerifyShirtItem.validatingProduct();
		driver.findElement(By.xpath(properties.getProperty("loc-sendkey-quantity"))).clear();
		sendkeys.sendKeys(driver, LocatorType.XPATH, properties.getProperty("loc-sendkey-quantity"), quantity);
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-update"));
		log.info("Successfully updated the quantity");
		VerifyShirtItem.validatingAfterUpdate();
		ExtentReport.reportLog("selectingProduct", "failed");
	}
}
