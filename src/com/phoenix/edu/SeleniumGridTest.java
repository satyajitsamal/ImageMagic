package com.phoenix.edu;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SeleniumGridTest {

	@BeforeSuite(alwaysRun = true)
	public void setup(ITestContext context) {

		System.out.println("Before Suite");
		// context.getCurrentXmlTest().getSuite().setThreadCount(5);

	}

	/**
	 * Synchronized list of web drivers that stores the browser driver instance
	 * for each thread instance
	 */
	private static List<WebDriver> m_listOfWebDrivers = Collections.synchronizedList(new ArrayList<WebDriver>());
	private static ThreadLocal<WebDriver> m_driverForThread = new ThreadLocal<WebDriver>() {

		@Override
		protected WebDriver initialValue() {
			WebDriver driver = null;
			try {
				driver = loadDriver();
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("Initializing Webdriver");
			m_listOfWebDrivers.add(driver);
			return driver;
		}
	};

	public WebDriver getDriver() {
		return m_driverForThread.get();
	}

	public static RemoteWebDriver loadDriver() throws Exception {

		return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.firefox());
	}

	@DataProvider(name = "test1", parallel = true)
	public static Object[][] urls() {
		return new Object[][] { { "https://google.com" }, { "https://facebook.com" }, { "https://freecharge.com" },
				{ "https://gmail.com" }, { "https://www.irctc.co.in" } };
	}

	@Test(dataProvider = "test1")
	public void test1(String urls) throws Exception {

		getDriver().get(urls);
		Thread.sleep(10000);

	}

	// @Test(dataProvider = "test1")
	public void test2(String urls) throws Exception {

		getDriver().get(urls);
		Thread.sleep(10000);

	}

	// @Test
	public void test3() throws Exception {

		getDriver().get("https://google.com");
		Thread.sleep(10000);

	}

	// @Test
	public void test4() throws Exception {

		getDriver().get("https://google.com");
		Thread.sleep(10000);

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestContext context, Method methodName) throws Exception {

		System.out.println("AfterMethod:: Output dir:: " + context.getOutputDirectory());
		System.out.println(getIPOfRemoteNode(getDriver()) + " :: " + context.getOutputDirectory());

		takeScreenShot(getDriver(), context.getOutputDirectory(), methodName.getName());
	}

	@AfterSuite(alwaysRun = true)
	public void shutdown(ITestContext context) {

		System.out.println("@AfterSuite Shutdown WebDrivers");

		if (!m_listOfWebDrivers.isEmpty()) {

			System.out.println("@AfterSuite Drivers to quit:" + m_listOfWebDrivers.size());

			for (WebDriver driver : m_listOfWebDrivers) {

				if (driver != null) {

					try {

						System.out.println("@AfterSuite Quit the driver:" + driver);
						driver.quit();
						System.out.println("@AfterSuite WebDriver Quit Success");

					} catch (Exception e) {
						System.out.println("@AfterSuite Exception Caught while quitting driver: " + e.getMessage());
						e.printStackTrace();
					}

				}

			}
		}

	}

	public static String getIPOfRemoteNode(WebDriver driver) {
		String hostFound = null;
		try {

			RemoteWebDriver remoteDriver = (RemoteWebDriver) driver;
			if (remoteDriver.getCommandExecutor() instanceof HttpCommandExecutor) {

				HttpCommandExecutor ce = (HttpCommandExecutor) remoteDriver.getCommandExecutor();
				String hostName = ce.getAddressOfRemoteServer().getHost();
				int port = ce.getAddressOfRemoteServer().getPort();
				HttpHost host = new HttpHost(hostName, port);

				HttpClient httpclient = HttpClientBuilder.create().build();
				URL sessionURL = new URL("http://" + hostName + ":" + port + "/grid/api/testsession?session="
						+ remoteDriver.getSessionId());
				BasicHttpEntityEnclosingRequest r = new BasicHttpEntityEnclosingRequest("POST",
						sessionURL.toExternalForm());
				HttpResponse response = httpclient.execute(host, r);
				JSONObject object = extractObject(response);
				URL myURL = new URL(object.getString("proxyId"));
				if ((myURL.getHost() != null) && (myURL.getPort() != -1)) {
					hostFound = myURL.getHost();
				}
			}

		} catch (Exception e) {
			System.out.println("Error while getting remote node IP address:" + e);
		}
		return hostFound;
	}

	/**
	 * This method is used for parsing the http response and returns JSON object
	 * of that response.
	 * 
	 * @param resp
	 * @return json_object
	 * @throws IOException
	 * @throws JSONException
	 */
	private static JSONObject extractObject(HttpResponse resp) throws IOException, JSONException {
		InputStream contents = resp.getEntity().getContent();
		StringWriter writer = new StringWriter();
		IOUtils.copy(contents, writer, "UTF8");
		JSONObject objToReturn = new JSONObject(writer.toString());
		return objToReturn;
	}

	public static void takeScreenShot(WebDriver driver, String outputDir, String methodName) throws Exception {

		try {
			String fileNameWithPath = null;
			String fileName = null;

			if (driver != null) {

				// Augment the driver only if the driver instance
				// is of type RemoteWebDriver(When tests are running in Grid)
				// if (driver.getClass() == RemoteWebDriver.class) {
				// driver = new Augmenter().augment(driver);
				// }

				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				fileName = methodName + getDateAndTime() + ".png";
				fileNameWithPath = outputDir + "/" + fileName;

				File scrShot = new File(fileNameWithPath);

				if (null != scrFile && scrShot.exists()) {
					scrShot.delete();
				}

				FileUtils.moveFile(scrFile, scrShot);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getDateAndTime() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd (hh- mm- ss)");
		String time = dateFormat.format(now);
		return time;
	}
}
