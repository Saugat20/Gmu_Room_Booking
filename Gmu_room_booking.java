package selenium_project;


import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Gmu_room_booking {
	
	public static void main(String[] args) throws InterruptedException {
		
		//Booking information Name and Email
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter your full name.");
		String name = scan.nextLine();
		System.out.println("Please enter your email.");
		String email = scan.nextLine();
		String[] splited = name.split(" ");		
		String fname = splited[0];
		String lname = splited[splited.length -1];
		
		//What type of room.
		System.out.println("How many people room do you want to book.");
		int room = scan.nextInt();
		scan.close();
		

		
	
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\dnesh\\eclipse-workspace\\selenium_project\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//Picking which room...
		int num_rooms_cap = 0;
		if(room == 2) {
			driver.navigate().to("https://gmu.libcal.com/spaces?lid=1205&gid=2116");
			num_rooms_cap = 14;
		}
		else if(room == 4) {
			driver.navigate().to("https://gmu.libcal.com/spaces?lid=1205&gid=2117");
			num_rooms_cap = 16;
		}
		else if(room == 6) {
			driver.navigate().to("https://gmu.libcal.com/spaces?lid=1205&gid=2118");
			num_rooms_cap = 9;
		}
		else if(room == 8) {
			driver.navigate().to("https://gmu.libcal.com/spaces?lid=1205&gid=2119");
			num_rooms_cap = 5;
		}
			
		Thread.sleep(2000);
		//starting time select
		//checks if any room is available for the given time.
		//If a room is available for given time books the room for longest time possible.(GMU allows rooms to be booked for maximum of 2.5 hours)
		int num_rooms = 1;
		while(num_rooms <= num_rooms_cap) {
			//checks all the room
			driver.findElement(By.xpath("/html/body/div[2]/main/div/div/div/div[3]/div[1]/div[2]/div/table/tbody/tr/td[1]/div/div/div/div[1]/div/table/tbody/tr[" + num_rooms + "]/td/div/div/a[2]/span")).click();
			Thread.sleep(1000);
			
			//checks if the room is available
			String title = driver.findElement(By.xpath("/html/body/div[2]/main/div/div/div/div[2]/div[1]/div[2]/div/table/tbody/tr/td[3]/div/div/div/div[1]/div/table/tbody/tr/td/div/div/a[1]")).getAttribute("title");
			Thread.sleep(1000);
			String[] title_split = title.split(" ");
			if(!title_split[8].equals("Available")) {
				//if room is not available goes back to check other rooms.
				driver.navigate().back();
				Thread.sleep(1000);
				num_rooms++;
			}
			else {
				driver.findElement(By.xpath("/html/body/div[2]/main/div/div/div/div[2]/div[1]/div[2]/div/table/tbody/tr/td[3]/div/div/div/div[1]/div/table/tbody/tr/td/div/div/a[1]")).click();
				break;
				
			}
		}
		
		Thread.sleep(2000);
		//drop down menu to select time
		//selects the room for maximum amount of time
		driver.findElement(By.id("bookingend_1")).click();
		
		Select times = new Select(driver.findElement(By.id("bookingend_1")));
		int max_index = 7;
		while(max_index >= 0) {
			try {
				times.selectByIndex(max_index);
				break;
			}
			catch(Exception e) {max_index--;}
		}
		
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
