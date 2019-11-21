package com.atmecs.validation;

import java.util.Properties;
import org.openqa.selenium.By;
import com.atmecs.actions.ClickOnElementAction;
import com.atmecs.constants.ConstantsFilePaths;
import com.atmecs.testbase.TestBase;
import com.atmecs.utils.LocatorType;
import com.atmecs.utils.ReadLocatorsFile;

public class VerifyShirtItem extends TestBase {
	static String actualhomepage, actualshirtsize, actualname, actualshirtcolor, actualprice, actualtotal,
			actualtotalafterupdate, actualitemname, actualmensactualhomepage, actualhotsauce, actualmerchandise,
			actualclearance, actualnewtohotsauce, actualfaq;
	static Properties properties;
	static Properties properties1;
	static ClickOnElementAction click = new ClickOnElementAction();

	public static void validatingProduct() {
		properties = ReadLocatorsFile.loadProperty(ConstantsFilePaths.LOCATOR_FILE);
		properties1 = ReadLocatorsFile.loadProperty(ConstantsFilePaths.TESTDATA_FILE);

		ValidationResult.validateData(driver.findElement(By.xpath((properties.getProperty("loc-verify-itemname")))).getText(),properties1.getProperty("expecteditemname"));
		ValidationResult.validateData(driver.findElement(By.xpath((properties.getProperty("loc-verify-shirtsize")))).getText(),properties1.getProperty("expectedshirtsize"));
		ValidationResult.validateData(driver.findElement(By.xpath((properties.getProperty("loc-verify-name")))).getText(), properties1.getProperty("expectedname"));
		ValidationResult.validateData(driver.findElement(By.xpath((properties.getProperty("loc-verify-shirtcolor")))).getText(), properties1.getProperty("expectedshirtcolor"));
		ValidationResult.validateData(driver.findElement(By.xpath((properties.getProperty("loc-verify-price")))).getText(), properties1.getProperty("expectedprice"));
		ValidationResult.validateData(actualtotal, properties1.getProperty("expectedtotal"));
		log.info("Validated Product Details");
	}
	public static void validatingAfterUpdate() {
		ValidationResult.validateData(driver.findElement(By.xpath((properties.getProperty("loc-verify-totalafterupdate")))).getText(), properties1.getProperty("expectedtotalafterupdate"));
		log.info("Completed Selecting Shirt and validating all details");
	}
	public static void testingHomePageTabs() {
		ValidationResult.validateData( driver.findElement(By.xpath((properties.getProperty("loc-verify-homepage")))).getText(), properties1.getProperty("expectedhomepage1"));
		log.info("Validated Home Page");
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-hotsauces"));
		ValidationResult.validateData(driver.findElement(By.xpath((properties.getProperty("loc-click-hotsauces")))).getText(), properties1.getProperty("expectedhotsauce"));
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-merchandise"));
		ValidationResult.validateData( driver.findElement(By.xpath((properties.getProperty("loc-click-merchandise")))).getText(), properties1.getProperty("expectedmerchandise"));
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-clearance"));
		ValidationResult.validateData(driver.findElement(By.xpath((properties.getProperty("loc-verify-clearance")))).getText(), properties1.getProperty("expectedclearance"));
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-newtohotsauce"));
		ValidationResult.validateData(driver.findElement(By.xpath((properties.getProperty("loc-verify-newtohotsauce")))).getText(), properties1.getProperty("expectednewtohotsauce"));
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-faq"));
		ValidationResult.validateData(driver.findElement(By.xpath((properties.getProperty("loc-verify-faq")))).getText(), properties1.getProperty("expectedfaq"));
		log.info("Successfully Validated all tabs in a Home Page");
	}
}

