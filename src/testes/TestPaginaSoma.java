package testes;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestPaginaSoma {
  
	 private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @BeforeClass(alwaysRun = true)
	  public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Java\\Tools\\GeckoDriver-v0.10.0\\geckodriver.exe");
	    driver = new FirefoxDriver();
	    baseUrl = "http://localhost:8080";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testESoma() throws Exception {
	    driver.get(baseUrl + "/SomarParcelas/");
	    driver.findElement(By.name("p1")).clear();
	    driver.findElement(By.name("p1")).sendKeys("15");
	    driver.findElement(By.name("p2")).clear();
	    driver.findElement(By.name("p2")).sendKeys("11");
	    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	  //http://stackoverflow.com/questions/18377384/how-to-wait-a-page-before-going-to-another-page-in-selenium-webdriver-using-java
	    Thread.sleep(2000);
	    assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "O resultado foi 26");
	  }
	  
	  @Test
	  public void testESoma2() throws Exception {
	    driver.get(baseUrl + "/SomarParcelas/");
	    driver.findElement(By.name("p1")).clear();
	    driver.findElement(By.name("p1")).sendKeys("10");
	    driver.findElement(By.name("p2")).clear();
	    driver.findElement(By.name("p2")).sendKeys("10");
	    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	    //http://stackoverflow.com/questions/18377384/how-to-wait-a-page-before-going-to-another-page-in-selenium-webdriver-using-java
	    Thread.sleep(2000);
	    assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "O resultado foi 20");
	  }

	  @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
}
