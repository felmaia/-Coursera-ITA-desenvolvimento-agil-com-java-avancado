package testes;


import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;
import org.testng.annotations.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class TestConversaoTemperaturas {
    
  private static WebDriver driver;
  private static String baseUrl;
  private boolean acceptNextAlert = true;
  private static StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass
  public static void setUp() throws Exception {
    System.setProperty("webdriver.gecko.driver","C:\\Java\\Tools\\GeckoDriver-v0.10.0\\geckodriver.exe");    
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testeConverteCelsiusParaFahrenheit() throws Exception {
	  driver.get(baseUrl + "/ConversorTemperaturaSemana1/");
	    new Select(driver.findElement(By.name("tipoConversao"))).selectByVisibleText("Celsious -> Fahrenheit");
	    driver.findElement(By.id("temp")).clear();
	    driver.findElement(By.id("temp")).sendKeys("100");
	    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	    assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "100.0 em Celsious equivale a 212.0 em Farenheit.");
  }
  
  @Test
  public void testeConverteFahrenheitParaCelsius() throws Exception {
	  driver.get(baseUrl + "/ConversorTemperaturaSemana1/");
      new Select(driver.findElement(By.name("tipoConversao"))).selectByVisibleText("Fahrenheit -> Celsious");
      driver.findElement(By.id("temp")).clear();
      driver.findElement(By.id("temp")).sendKeys("212");
      driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
      assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "212.0 em Farenheit equivale a 100.0 em Celsious.");
  }

  @AfterClass
  public static void tearDown() throws Exception {
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
