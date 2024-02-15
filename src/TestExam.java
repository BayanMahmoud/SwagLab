import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class TestExam {
	WebDriver driver = new ChromeDriver();
	String myWebsite = "https://magento.softwaretestingboard.com/";
	String PageLogOut = "https://magento.softwaretestingboard.com/customer/account/logout/";
	String PageSighnIn = "https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/";

	String[] firstNames = { "Ahmad", "Ayla", "Maya", "Rana", "Bana" };
	String[] lastNames = { "Samer", "Ali", "Eyad", "Abderahman", "Omar" };

	Random rand = new Random();
	int firstNameIndex = rand.nextInt(firstNames.length);
	int lastNameIndex = rand.nextInt(firstNames.length);
	int emailID = rand.nextInt(999);
	String firstName = firstNames[firstNameIndex];
	String lastName = lastNames[lastNameIndex];

	String email = firstName + lastName + emailID + "@gmail.com";
	String UserPassword = "ASDasd123!";

	@BeforeTest
	public void setUp() {

		driver.manage().window().maximize();
		driver.get(myWebsite);
	}

	@Test(priority = 1)
	public void SignIn() throws InterruptedException {

		WebElement creatAnaccount = driver.findElement(By.linkText("Create an Account"));
		creatAnaccount.click();

		WebElement firstNameInput = driver.findElement(By.id("firstname"));
		WebElement lastNameInput = driver.findElement(By.id("lastname"));
		WebElement emailInput = driver.findElement(By.id("email_address"));
		WebElement passInput = driver.findElement(By.id("password"));
		WebElement passConfimeInput = driver.findElement(By.id("password-confirmation"));
		WebElement creatAcountButton = driver.findElement(By.cssSelector("button[title='Create an Account'] span"));

		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		emailInput.sendKeys(email);
		passInput.sendKeys(UserPassword);
		passConfimeInput.sendKeys(UserPassword);

		creatAcountButton.click();

		Thread.sleep(3000);
		WebElement succesMsg = driver.findElement(By.className("message-success"));

		Assert.assertEquals(succesMsg.getText().contains("Thank you for registering"), true,
				"This is Cheak Create acount its done");
		Assert.assertEquals(succesMsg.getText(), "Thank you for registering with Main Website Store.");
	}

	@Test(priority = 2, enabled = false)
	public void logOut() throws InterruptedException {

		driver.get(PageLogOut);
		Thread.sleep(2000);

		// WebElement MsgHeader = driver.findElement(By.xpath("//div[@class='panel
		// header']"));

		// Assert.assertEquals(MsgHeader.getText().contains("Click “Write for us” link
		// in the footer to submit a guest post "),true, "This is check logout");

		WebElement logOutMsg = driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']"));

		String ActualMsg = logOutMsg.getText();

		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(ActualMsg, "You are signed out");
		softassert.assertEquals(ActualMsg.contains("signed out"), true);
		softassert.assertAll();

		Thread.sleep(3000);
	}

	@Test(priority = 3, enabled = false)
	public void LogInPage() throws InterruptedException {

		driver.get(PageSighnIn);
		WebElement Email = driver.findElement(By.id("email"));
		WebElement Password = driver.findElement(By.id("pass"));
		WebElement SignIn = driver.findElement(By.id("send2"));

		Email.sendKeys(email);
		Password.sendKeys(UserPassword);

		SignIn.click();
		Thread.sleep(2000);

		WebElement WelcomeMsg = driver.findElement(By.xpath("//div[@class='panel header']"));

		Assert.assertEquals(WelcomeMsg.getText().contains("Welcome"), true, "This is check of Welome Msg");

	}

	@Test(priority = 2)
	public void addFirstItem() throws InterruptedException {

		// Navigate to the website
		driver.get("https://magento.softwaretestingboard.com/men/tops-men/jackets-men.html");
		Thread.sleep(2000);
		WebElement TheITemsContainer = driver.findElement(By.cssSelector(".products.list.items.product-items"));
		List<WebElement> allITems = TheITemsContainer.findElements(By.tagName("li"));
		allITems.get(0).click();

		WebElement TheSizeContainer = driver.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));
		List<WebElement> SizeITem = TheSizeContainer.findElements(By.tagName("div"));

		WebElement TheColorContainer = driver.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
		List<WebElement> ColorITem = TheColorContainer.findElements(By.tagName("div"));

		WebElement AddToCartButton = driver.findElement(By.cssSelector("#product-addtocart-button"));

		Random rand = new Random();

		int SizeIndex = rand.nextInt(SizeITem.size());
		int ColorIndex = rand.nextInt(ColorITem.size());

		SizeITem.get(SizeIndex).click();

		String expectedSize = SizeITem.get(SizeIndex).getText();
		Thread.sleep(1000);
		String actualSize = driver.findElement(By.className("swatch-attribute-selected-option")).getText();
		System.out.println(actualSize);

		System.out.println(expectedSize);
		Thread.sleep(2000);
		ColorITem.get(ColorIndex).click();

		Thread.sleep(2000);

		String expectedColor = ColorITem.get(ColorIndex).getAttribute("option-label");
		Thread.sleep(2000);
		String actualColor = driver.findElement(By.cssSelector("div[class='swatch-attribute color'] span[class='swatch-attribute-selected-option']")).getText();

		System.out.println(actualColor);
		System.out.println(expectedColor);

		Thread.sleep(1000);

		Assert.assertEquals(actualSize, expectedSize);
		Assert.assertEquals(actualColor, expectedColor);
		Thread.sleep(2000);
		AddToCartButton.click();
	
		
		WebElement AlertAddToCart =driver.findElement(By.className("messages"));
		Thread.sleep(5000);
		Assert.assertEquals(AlertAddToCart.getText().contains("You added"),true,"Cheak of add is done");
		
		
	}
	

	@AfterTest
	public void postTesting() {
	}
}
