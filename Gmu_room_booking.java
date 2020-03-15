package selenium_project;


import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Gmu_room_booking {
	
	public static void main(String[] args) throws InterruptedException {
		
		//****************Uncomment at the end******************
		//Booking information Name and Email
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter your full name.");
		String name = scan.nextLine();
		System.out.println("Please enter your email.");
		String email = scan.nextLine();
		String[] splited = name.split(" ");		
		String fname = splited[0];
		String lname = splited[1];
		
//		System.out.println("How many people room do you want to boook.");
//		int room = scan.nextInt();
		

		
	
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\dnesh\\eclipse-workspace\\selenium_project\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		
		
		//Website
		driver.navigate().to("https://gmu.libcal.com/spaces?lid=1205&gid=2117");
		
		//clicking the room 
		driver.findElement(By.xpath("/html/body/div[2]/main/div/div/div/div[3]/div[1]/div[2]/div/table/tbody/tr/td[1]/div/div/div/div[1]/div/table/tbody/tr[1]/td/div/div/a[2]/span")).click();
		Thread.sleep(2000);
		
		//starting time select
		WebElement cop = driver.findElement(By.xpath("/html/body/div[2]/main/div/div/div/div[2]/div[1]/div[2]/div/table/tbody/tr/td[3]/div/div/div/div[1]/div/table/tbody/tr/td/div/div/a[5]"));
		
		cop.click();
		Thread.sleep(2000);
		
		
		

		
		//drop down menu to select time
		driver.findElement(By.id("bookingend_1")).click();
		
		new Select(driver.findElement(By.id("bookingend_1"))).selectByVisibleText("7:30pm Sunday, March 15, 2020");
		
		
		
		
		
		
		
		Thread.sleep(2000);
		//submit button
		driver.findElement(By.id("submit_times")).click();
				
		Thread.sleep(2000);
				
		//accepting terms
		driver.findElement(By.id("terms_accept")).click();
				
		//Booking information Name and Email
		driver.findElement(By.id("fname")).sendKeys(fname);
		driver.findElement(By.id("lname")).sendKeys(lname);
		driver.findElement(By.id("email")).sendKeys(email);
		
		//conforming the room booking.
		driver.findElement(By.id("btn-form-submit")).click();
		
		

		
		
		
		
	}

}
