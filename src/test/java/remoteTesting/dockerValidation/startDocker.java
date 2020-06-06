package remoteTesting.dockerValidation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class startDocker {

	public void startFile() throws IOException, InterruptedException {

		boolean flag = false;
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c start dockerUp.bat");

		String f = "output.txt";

		Calendar cal = Calendar.getInstance();// 2:44 15th second
		System.out.println("value:"+Calendar.SECOND);
		cal.add(Calendar.SECOND, 45);// 2:44 45seconds
		long stopnow = cal.getTimeInMillis();
		System.out.println("stopnow:"+stopnow);
		Thread.sleep(3000);

		while (System.currentTimeMillis() < stopnow) {
			//System.out.println("while1");
			if (flag) {
				System.out.println("flag status1:"+flag);
				break;
			}

			BufferedReader reader = new BufferedReader(new FileReader(f));
			String currentLine = reader.readLine();
			while (currentLine != null && !flag)

			{
				//System.out.println("while2");
				//"Registered a node"
				//registered to the hub and ready to use
				if (currentLine.contains("Registered a node")) {
					System.out.println("found my text");
					flag = true;// 14th seconds
					break;
				}

				currentLine = reader.readLine();
			}
			reader.close();

		}

		System.out.println("while3");
		Assert.assertTrue(flag);
		System.out.println("while4");
		runtime.exec("cmd /c start scale.bat");
		System.out.println("while5");
		Thread.sleep(5000);

	}

}
