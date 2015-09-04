import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class main {
	
	public static void main(String args[]){
		
		//WebDriver driver = new HtmlUnitDriver();
		WebDriver driver = new FirefoxDriver();
		//WebDriver driver = new PhantomJSDriver();
		
		driver.get("https://www.isis.ufl.edu/cgi-bin/nirvana?MDASTRAN=RSI-FSCHED");
		//driver.get("https://login.ufl.edu/idp/Authn/UserPassword");
		//delay(5000);
		//System.out.println(driver.getTitle());
		
		//Wait until it gets redirected to login.
		while( !driver.getTitle().equals("UF Authentication - GatorLink Login » University of Florida") );
		
		WebElement username = driver.findElement(By.name("j_username"));
		username.sendKeys("pawel");
		
		WebElement password = driver.findElement(By.name("j_password"));
		password.sendKeys("INPUT REAL PASS HERE");
		
		WebElement submit_button = driver.findElement(By.name("login"));
		submit_button.submit();
		
		while( !driver.getTitle().equals("ISIS") );
		
		System.out.println(driver.getPageSource());
		//System.out.println(driver.getPageSource());
		
		driver.quit();
	}
	
	public static void delay(long delayms){
		long end = System.currentTimeMillis() + delayms;
        while (System.currentTimeMillis() < end);
	}
	
}