package testes;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.concurrent.ThreadLocalRandom;
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

public class TestGameficationSemana4 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String usuarioLogin  = null;

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	System.setProperty("webdriver.gecko.driver","C:\\Java\\Tools\\GeckoDriver-v0.10.0\\geckodriver.exe");
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    usuarioLogin = "doria"+ThreadLocalRandom.current().nextInt(1, 100 + 1);
  }

  @Test
  public void testNovoCadastro() throws Exception {
    driver.get(baseUrl + "/GamificationSemana4/novo-usuario.jsp");
    driver.findElement(By.id("nome")).clear();
    driver.findElement(By.id("nome")).sendKeys("Rafael Doria");
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("rafael@email.com");
    driver.findElement(By.id("login")).clear();
    driver.findElement(By.id("login")).sendKeys(usuarioLogin);
    driver.findElement(By.id("senha")).clear();
    driver.findElement(By.id("senha")).sendKeys("123");
    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
    Thread.sleep(2000);
    assertEquals(driver.findElement(By.cssSelector("h3")).getText(), "Login");
    assertEquals(driver.findElement(By.cssSelector("div.alert.alert-success")).getText(), "Successo! Cadastro realizado.");
  }
  
  @Test(dependsOnMethods="testNovoCadastro")
  public void testLogin() throws Exception {
	driver.get(baseUrl + "/GamificationSemana4/");
    driver.findElement(By.name("login")).clear();
    driver.findElement(By.name("login")).sendKeys(usuarioLogin);
    driver.findElement(By.name("senha")).clear();
    driver.findElement(By.name("senha")).sendKeys("123");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    Thread.sleep(2000);
    assertEquals(driver.findElement(By.cssSelector("h3")).getText(), "Tópicos");
  }
  
  @Test(dependsOnMethods="testLogin")
  public void testNovoTopico() throws Exception {
    driver.get(baseUrl + "/GamificationSemana4/topicoController?acao=listar");
    driver.findElement(By.linkText("Novo Tópico")).click();
    driver.findElement(By.id("titulo")).clear();
    driver.findElement(By.id("titulo")).sendKeys("Topico 1");
    driver.findElement(By.id("conteudo")).clear();
    driver.findElement(By.id("conteudo")).sendKeys("texto topico 1");
    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
    Thread.sleep(2000);
    assertEquals(driver.findElement(By.cssSelector("h3")).getText(), "Tópicos");
    assertEquals(driver.findElement(By.cssSelector("div.alert.alert-success")).getText(), "Successo! Novo tópico criado.");
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
