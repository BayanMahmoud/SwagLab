package SwagLab;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class TestCases {

	WebDriver driver = new ChromeDriver();

	String MyWebsite = "https://www.saucedemo.com/";
	String usernames = "standard_user";
	String password = "secret_sauce";

	@BeforeTest
	public void Setup() {
		driver.manage().window().maximize();
		driver.get(MyWebsite);
	}

	@Test(priority = 1)
	public void loginTest() {
		WebElement userNameInput = driver.findElement(By.xpath("//input[@id='user-name']"));
		WebElement passwordInput = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement loginInput = driver.findElement(By.cssSelector("#login-button"));

		userNameInput.sendKeys(usernames);
		passwordInput.sendKeys(password);
		loginInput.click();
		System.out.println("mr7aba");
	}

	@Test(enabled = false)
	public void PrintfistItem() {

		WebElement fistItem = driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Backpack']"));
		String firstItemText = fistItem.getText();
		System.out.println(firstItemText);
	}

	@Test(enabled = false)
	public void PrintAllItem() {
		List<WebElement> allitems = driver.findElements(By.className("inventory_item_name"));
		for (int i = 0; i < allitems.size(); i++) {
			String allitemsText = allitems.get(i).getText();
			System.out.println(allitemsText);

		}

	}

	@Test(enabled = false)
	public void allAdd() {

		List<WebElement> allAdd = driver.findElements(By.className("btn"));

		for (int i = 0; i < allAdd.size(); i++) {
			allAdd.get(i).click();
		}

	}

	@Test(enabled = false)
	public void addAllItems() throws InterruptedException {

		List<WebElement> addAll = driver.findElements(By.className("btn"));

		for (WebElement Button : addAll) { // هاي الفور مختصره بتحكيلي شفت اليست الي اسمها ادد اول
			// العناصر الي فيها عباره عن بوتن
			// اعمل كليك علبوتن كلها

			Button.click();
		}
		Thread.sleep(2000);

		List<WebElement> removeAll = driver.findElements(By.className("btn"));
		for (int i = 0; i < removeAll.size(); i++) {
			removeAll.get(i).click();

		}
	}

	@Test(enabled = false)
	public void additionsSomeItem() throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> addSomeItem = driver.findElements(By.className("btn"));
		for (int i = 0; i < addSomeItem.size(); i++) {
			// لاضافه العناصر الي اندكس الها عدد زوجي
			if (i % 2 == 0) {
				addSomeItem.get(i).click();
			}
		}

	}

	@Test(enabled = false)
	public void additionsItem() throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> Item = driver.findElements(By.className("btn"));

		for (int i = 0; i < Item.size(); i++) {
			// لاضافه العناصر الي الها الاندكس عدد فردي
			if (i % 2 == 1) {
				Item.get(i).click();
			}
		}

	}

	@Test(enabled = false)
	public void addNewItem() {

		List<WebElement> addSomeItem = driver.findElements(By.className("btn"));
		List<WebElement> textItem = driver.findElements(By.className("inventory_item_name"));
		List<WebElement> priceItem = driver.findElements(By.className("inventory_item_price"));

		for (int i = 0; i < addSomeItem.size(); i++) {

			String Nameitem = textItem.get(i).getText();
			String price = priceItem.get(i).getText();

			if (Nameitem.contains("Bike") || Nameitem.contains("Onesie")) {

				addSomeItem.get(i).click();
				System.out.println("the price befor tax :" + " " + Nameitem + price);

			}

		}
	}

	@Test(priority = 3)
	public void myNewItemPrice() {

		List<WebElement> addSomeItem = driver.findElements(By.className("btn"));
		List<WebElement> textItem = driver.findElements(By.className("inventory_item_name"));
		List<WebElement> priceItem = driver.findElements(By.className("inventory_item_price"));

		for (int i = 0; i < addSomeItem.size(); i++) {

			String Nameitem = textItem.get(i).getText();
			String price = priceItem.get(i).getText();

			double priceD = Double.parseDouble(price.replace("$", ""));
			double tax = .10;
			double newPrice = Math.round((priceD * tax )+priceD);

			if (Nameitem.contains("Bike") || Nameitem.contains("Onesie")) {

				addSomeItem.get(i).click();
				System.out.println("the price befor tax :" + " " + Nameitem + price);

				System.out.println("the price AFter tax :" + " " + Nameitem + newPrice);

			}
		}
	}
	
	
	@Test(priority = 4)
	public void shoppingCart() throws InterruptedException{
		
		WebElement Basket = driver.findElement(By.className("shopping_cart_badge"));
		 Basket.click();
		 Thread.sleep(2000);
		 WebElement cheack =driver.findElement(By.id("checkout"));
		   cheack.click();
		   
	}
	
	@Test(priority=5)
	public void cheackoutYourInformation() throws InterruptedException {
		
		WebElement firstNameInput = driver.findElement(By.id("first-name"));
		WebElement lastNameInput  =driver.findElement(By.id("last-name"));
		WebElement zipInput  =driver.findElement(By.id("postal-code"));
		
		WebElement continueButton  =driver.findElement(By.id("continue"));
		 Thread.sleep(1000);
		firstNameInput.sendKeys("Bayan");
		lastNameInput.sendKeys("Omar");
		 zipInput.sendKeys("wertyui34567");
		 Thread.sleep(2000);
		 continueButton.click();
		 
	}
	@Test
	 public void checkoutOverview() {
		WebElement totalValue = driver.findElement(By.className("summary_info_label summary_total_label"));
		
	String text  = totalValue.getText();
	//double value = Double.parseDouble(text.replace("Total: $" ," "));

    Assert.assertEquals( text.contains("$19.9"),true,"SDFGHJ");
		
	}

	@AfterTest
	public void postTesting() {

	}

}
