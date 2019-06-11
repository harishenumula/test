package CommonFunLibrary;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import DriverFactory.Open_browser;

public class Erp_Stock { // Admin //master
	public void login(WebDriver driver, String username, String password) throws Throwable {
		driver.findElement(By.xpath("//input[@id='username']")).clear();
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='password']")).clear();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id='btnsubmit']")).click();
		Thread.sleep(5000);
		if (driver.findElement(By.linkText("Logout")).isDisplayed()) {
			System.out.println("login successful");
		} else {
			System.out.println("Login Failed");
		}
	}

	public String suppliersadd(WebDriver driver, String sname, String address, String city, String country,
			String contact_person, String phone_number, String email, String mobile, String notes) throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Suppliers")).click();
		driver.findElement(By
				.xpath("//div[@class='panel-heading ewGridUpperPanel']//a[@class='btn btn-default ewAddEdit ewAdd btn-sm']"))
				.click();
		Thread.sleep(5000);
		// String expectedSupplierNumber =
		// driver.findElement(By.xpath("//input[@id='x_Supplier_Number']"))
		// .getAttribute("value");

		driver.findElement(By.xpath("//input[@id='x_Supplier_Name']")).sendKeys(sname);
		driver.findElement(By.xpath("//textarea[@id='x_Address']")).sendKeys(address);
		driver.findElement(By.xpath("//input[@id='x_City']")).sendKeys(city);

		driver.findElement(By.xpath("//input[@id='x_Country']")).sendKeys(country);
		driver.findElement(By.xpath("//input[@id='x_Contact_Person']")).sendKeys(contact_person);
		driver.findElement(By.xpath("//input[@id='x_Phone_Number']")).sendKeys(phone_number);

		driver.findElement(By.xpath("//input[@id='x__Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='x_Mobile_Number']")).sendKeys(mobile);
		driver.findElement(By.xpath("//textarea[@id='x_Notes']")).sendKeys(notes);

		driver.findElement(By.xpath("//button[@id='btnAction']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		// driver.switchTo().alert().accept();
		driver.findElement(By.xpath("//button[text()='OK!']")).click();
		Thread.sleep(5000);
		// driver.findElement(By.xpath("//button[text()='ok'])[6]")).click();
		// alert.accept();
		// driver.switchTo().alert();
		driver.findElement(By.xpath("//button[@class='ajs-button btn btn-primary']")).click();
		Thread.sleep(5000);
		// driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
		// Thread.sleep(3000);
		/*
		 * if (!driver.findElement(By.id("btnsubmit")).isDisplayed()) {
		 * driver.findElement(By.xpath(
		 * "//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click(); }
		 * Thread.sleep(5000); driver.findElement(By.id("psearch")).clear();
		 * Thread.sleep(3000);
		 * driver.findElement(By.id("psearch")).sendKeys(expectedSupplierNumber)
		 * ; Thread.sleep(5000); driver.findElement(By.id("btnsubmit")).click();
		 * 
		 * String actualSuppliarnumber = driver.findElement(By.xpath(
		 * "//*[@id='el1_a_suppliers_Supplier_Number']/span")) .getText();
		 */

		String status = "";
		try {

			if (driver.getCurrentUrl().contains("a_supplierslist")) {
				System.out.println("supplier creation successfull");
				status = "Pass";
			} else {
				System.out.println("supplier creation failed");
				status = "Fail";
			}

		} catch (Exception e) {
			status = "Fail";
		}

		return status;

	}
}
