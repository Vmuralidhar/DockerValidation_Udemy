package remoteTesting.dockerValidation;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class chromeStandAloneTest {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		DesiredCapabilities cap=DesiredCapabilities.chrome();
		//Docker is configured with 
		//docker is configured to use the default machine with IP 192.168.99.100
		URL url = new URL("http://192.168.99.100:4444/wd/hub");
		//URL u=new URL("http://localhost:4444/wd/hub");
		
		RemoteWebDriver driver=new RemoteWebDriver(url,cap);
		driver.get("http://google.com");
		System.out.println(driver.getTitle());
		
	}

}
