package DriverFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunLibrary.Erp_Stock;
import Utilities.ExcelFileUtil1;

public class SupplierTest {
	WebDriver driver;
	Erp_Stock erp = new Erp_Stock();

	@BeforeTest
	public void adminlogin() throws Throwable {
		// Open_browser.open_browser();
		System.setProperty("webdriver.chrome.driver", "./CommonJars/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://webapp.qedge.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		erp.login(driver, "admin", "master");
	}

	@Test
	public void method() throws Throwable {

		ExcelFileUtil1 xls = new ExcelFileUtil1();
		String sheetname = "Suppliers";
		int lastrecord = xls.rowCount(sheetname);
		for (int i = 1; i <= lastrecord; i++) {
			String sname = xls.getData(sheetname, i, 0);
			String address = xls.getData(sheetname, i, 1);
			String city = xls.getData(sheetname, i, 2);
			String country = xls.getData(sheetname, i, 3);
			String contact_person = xls.getData(sheetname, i, 4);
			String phone_number = xls.getData(sheetname, i, 5);
			String email = xls.getData(sheetname, i, 6);
			String mobile = xls.getData(sheetname, i, 7);
			String notes = xls.getData(sheetname, i, 8);
			System.out.println(phone_number + "::" + mobile);
			String status = erp.suppliersadd(driver, sname, address, city, country, contact_person, phone_number, email,
					mobile, notes);
			System.out.println("sname::" + sname);
			xls.setData(sheetname, i, 9, status);
		}

	}

	public void logout() {
		driver.close();
	}
}
