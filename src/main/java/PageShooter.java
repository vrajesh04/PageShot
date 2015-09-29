
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageShooter {

	public static final Logger LOGGER = LoggerFactory.getLogger(PageShooter.class);

	public static void main(final String[] args) throws Exception {
		System.out.print(args);
		final String link = "http://www.vmware.com/resources/compatibility/search.php";
		final String link1 = "http://www.vmware.com/resources/compatibility/search.php?deviceCategory=san";
		final File screenShot = new File("screenshot.png").getAbsoluteFile();
		final File screenShot1 = new File("screenshot1.png").getAbsoluteFile();

		PageShooter.LOGGER.debug("Creating Firefox Driver");
		final WebDriver driver = new FirefoxDriver();
		try {
			PageShooter.LOGGER.debug("Opening page: {}", link);
			driver.get(link);

			PageShooter.LOGGER.debug("Wait a bit for the page to render");
			TimeUnit.SECONDS.sleep(5);

			PageShooter.LOGGER.debug("Taking Screenshot");
			final File outputFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(outputFile, screenShot);
			PageShooter.LOGGER.debug("Screenshot saved: {}", screenShot);
			System.out.println(screenShot);
			
			PageShooter.LOGGER.debug("Opening page: {}", link1);
			driver.get(link1);

			PageShooter.LOGGER.debug("Wait a bit for the page to render");
			TimeUnit.SECONDS.sleep(5);

			PageShooter.LOGGER.debug("Taking Screenshot");
			final File outputFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(outputFile1, screenShot1);
			PageShooter.LOGGER.debug("Screenshot saved: {}", screenShot1);
			System.out.println(screenShot1);
			
		} finally {
			driver.close();
		}
		PageShooter.LOGGER.debug("done");
	}
}