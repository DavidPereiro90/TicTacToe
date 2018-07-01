package es.codeurjc.ais.tictactoe;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author David
 */
public class WebAppTest {
    
    private static ChromeDriver  navegador1, navegador2; 
    private static WebDriverWait espera1, espera2;
    
    public WebAppTest() {
        
    }
    
    	@BeforeClass
	public static void setUpClass() {
            ChromeDriverManager.getInstance().setup();
            WebApp.start();
	}
        
        @Before
        public void setUp(){
            navegador1 = new ChromeDriver();
            navegador2 = new ChromeDriver();
            espera1 = new WebDriverWait(navegador1,  15);
            espera2 = new WebDriverWait(navegador2,  15);
        } 
        
        private void login(String nombre, ChromeDriver jug, WebDriverWait esp){
            jug.get("http://localhost:8082");
            WebElement input = esp.until(ExpectedConditions.presenceOfElementLocated(By.id("nickname")));
            input.sendKeys(nombre);
            WebElement play = esp.until(ExpectedConditions.
                    elementToBeClickable(By.id("startBtn")));
            play.click();
            List<WebElement> celdas = esp.until(ExpectedConditions.
                    visibilityOfAllElementsLocatedBy(By.className("gameCell")));
            assertThat(celdas).isNotEmpty();
        }
        
        public void alertaValida(WebDriverWait esp, String msm){
            Alert alerta = esp.until(ExpectedConditions.alertIsPresent());
            assertThat(alerta.getText()).isEqualTo(msm);
        }
       
        
        
        @Test
        public void testJugador1Gana() {
            login("Pepe",navegador1, espera1);
            login("Juan", navegador2, espera2);
            
            navegador1.findElement(By.id("cell-4")).click();
            espera1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-4"), "X"));
            
            navegador2.findElement(By.id("cell-2")).click();
            espera2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-2"), "O"));
            
            navegador1.findElement(By.id("cell-7")).click();
            espera1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-7"), "X"));
            
            navegador2.findElement(By.id("cell-1")).click();
            espera2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-1"), "O"));
            
            navegador1.findElement(By.id("cell-8")).click();
            espera1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-8"), "X"));
            
            navegador2.findElement(By.id("cell-6")).click();
            espera2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-6"), "O"));
            
            navegador1.findElement(By.id("cell-0")).click();
           
            alertaValida(espera1,"Pepe wins! Juan looses.");
            alertaValida(espera2,"Pepe wins! Juan looses.");
        }
        
        @Test
        public void testJugador2Gana() {
            login("Pepe",navegador1, espera1);
            login("Juan", navegador2, espera2);
            
            navegador1.findElement(By.id("cell-4")).click();
            espera1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-4"), "X"));
            
            navegador2.findElement(By.id("cell-1")).click();
            espera2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-1"), "O"));
            
            navegador1.findElement(By.id("cell-7")).click();
            espera1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-7"), "X"));
            
            navegador2.findElement(By.id("cell-2")).click();
            espera2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-2"), "O"));
            
            navegador1.findElement(By.id("cell-5")).click();
            espera1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-5"), "X"));
            
            navegador2.findElement(By.id("cell-0")).click();
            
            
            alertaValida(espera1,"Juan wins! Pepe looses.");
            alertaValida(espera2,"Juan wins! Pepe looses.");
        }
        
        @Test
        public void testEmpate() {
            login("Pepe", navegador1,  espera1);
            login("Juan", navegador2,  espera2);
            
            navegador1.findElement(By.id("cell-5")).click();
            espera1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-5"), "X"));
            
            navegador2.findElement(By.id("cell-1")).click();
            espera2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-1"), "O"));
            
            navegador1.findElement(By.id("cell-2")).click();
            espera1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-2"), "X"));
            
            navegador2.findElement(By.id("cell-8")).click();
            espera2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-8"), "O"));
            
            navegador1.findElement(By.id("cell-4")).click();
            espera1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-4"), "X"));
            
            navegador2.findElement(By.id("cell-3")).click();
            espera2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-3"), "O"));
            
            navegador1.findElement(By.id("cell-7")).click();
            espera1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-7"), "X"));
            
            navegador2.findElement(By.id("cell-6")).click();
            espera2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-6"), "O"));
            
            navegador1.findElement(By.id("cell-0")).click();
            
           
            alertaValida(espera1,"Draw!");
            alertaValida(espera2,"Draw!");
        }

       
        @After
        public void tearDown() {
            navegador1.close();
            navegador2.close();
	}

	@AfterClass
	public static void tearDownClass() {
            WebApp.stop();
	}
    
}
