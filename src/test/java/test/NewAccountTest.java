package test;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import page.ListAccountPage;
import page.LoginPage;
import page.NewAccountPage;
import page.SideNavigation;
import util.BrowserFactory;

public class NewAccountTest {
	WebDriver driver;
	Random rnd = new Random();

//	String userName = "techfiosdemo@gmail.com";
//	String password = "abc123";
//	String accountName = "Kamal" + rnd.nextInt(999);
//	String description = "Lunch Money";
//	String amount = "20";

	@Test
	@Parameters({ "userName", "Password", "AccountName", "Description", "Amount" })
	public void addANewContact(String userName, String password, String accountName, String description, String amount)
			throws InterruptedException {
		driver = BrowserFactory.startBrowser();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login(userName, password);
		SideNavigation sideNavigation = PageFactory.initElements(driver, SideNavigation.class);
		sideNavigation.goToNewAccountPage();
		NewAccountPage newAccountPage = PageFactory.initElements(driver, NewAccountPage.class);

		newAccountPage.fillInTheNewAccountForm(accountName, description, amount);

		sideNavigation.goToListAccountPage();

		ListAccountPage listAccountPage = PageFactory.initElements(driver, ListAccountPage.class);

		List<String> accountList = listAccountPage.getListOf("Account");

		Assert.assertTrue(isDataExist(accountName, accountList), "Account name was not found!");

	}

	@AfterMethod
	public void close() {
		driver.close();
		driver.quit();
	}

	private boolean isDataExist(String expectedData, List<String> actualList) {
		for (int i = 0; i < actualList.size(); i++) {
			if (expectedData.equalsIgnoreCase(actualList.get(i))) {
				return true;
			}
		}
		return false;
	}
}
