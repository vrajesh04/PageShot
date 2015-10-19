
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

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
		final String link = "http://localhost/sprint/comp_guide2/search1.php";
		final String link1 = "http://localhost/sprint/comp_guide2/search.php";
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
			
			//Screenshot diff
			BufferedImage img = ImageIO.read(screenShot);
			BufferedImage img1 = ImageIO.read(screenShot1);
			File difffile = new File("diff_file.png");
			


			System.out.print("("+img.getHeight()+","+img.getWidth()+");("+img1.getHeight()+","+img1.getWidth()+")");
			for(int i = 0; i < img.getWidth(); i++)
			{
			    for(int j = 0; j < img.getHeight(); j++)
			    {
			    	if(img.getRGB(i, j) != img1.getRGB(i, j))
			    	{
			    		System.out.println(i+","+j);
			    		img.setRGB(i, j, new java.awt.Color(255,0,0).getRGB());
			    	}
			    	//System.out.print(img.getRGB(i, j));
			    }
			}
			ImageIO.write(img, "png", difffile);
			
		} finally {
			driver.close();
		}
		PageShooter.LOGGER.debug("done");
	}
}