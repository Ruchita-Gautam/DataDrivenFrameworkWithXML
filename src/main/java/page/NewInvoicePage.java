package page;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NewInvoicePage {
	WebDriver driver;

	// Every Page must have a constructor to invite the driver
	public NewInvoicePage(WebDriver driver) {
		this.driver = driver;
	}

	// Element Library
	@FindBy(how = How.NAME, using = "desc[]")
	WebElement ItemInputField;
	@FindBy(how = How.NAME, using = "desc[]")
	WebElement qtyInputField;
	@FindBy(how = How.NAME, using = "desc[]")
	WebElement priceInputField;
	@FindBy(how = How.XPATH, using = "//select[@id='cid']/option[2]")
	WebElement SecondCustomer;
	@FindBy(how = How.ID, using = "save_n_close")
	WebElement SaveAndCloseButton;

	public void enterQty(String qty) {
		qtyInputField.sendKeys(qty);
	}

	public void enterPrice(String price) {
		priceInputField.sendKeys(price);
	}

	public void selectAcustomer() {
		SecondCustomer.click();
	}

	public void clickSaveAndClose() {
		SaveAndCloseButton.click();
	}

	public String enterARandomItemName(String itemName) {
		Random rnd = new Random();
		String expectedItemName = itemName + rnd.nextInt(999);
		ItemInputField.sendKeys(expectedItemName);
		return expectedItemName;
	}
}