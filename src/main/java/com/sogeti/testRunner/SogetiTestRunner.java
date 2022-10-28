package com.sogeti.testRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;
import com.sogeti.steps.ParentStep;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(

		

		features = {"src/test/resources/feature/SogetiUITest.feature","src/test/resources/feature/SogetiApiTest.feature"}, glue = { "com.sogeti.steps" }, plugin = {
				"com.cucumber.listener.ExtentCucumberFormatter:",
				"rerun:src/cucumber-reports/rerun.txt" }, strict = true, dryRun = false, monochrome = true, tags = {})	


public class SogetiTestRunner extends AbstractTestNGCucumberTests {
	static ExtentProperties extentProperties;
	public static Properties testProperties;

	@AfterClass
	public void writeExtentReport() throws IOException {

		Reporter.loadXMLConfig(new File("src/test/resources/extentReportConfig/extentConfig.xml"));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Machine", InetAddress.getLocalHost().getHostName());

		Properties properties = new Properties();
		properties.load(new FileInputStream(
				new File((System.getProperty("user.dir") + "/src/test/resources/propertiesFile/maven.properties"))));

		Reporter.setSystemInfo("Maven", properties.getProperty("maven.compiler.version"));
		Reporter.setSystemInfo("Java Version", properties.getProperty("java.version"));
		Reporter.setSystemInfo("Selenium Version", properties.getProperty("selenium.version"));
		Reporter.setSystemInfo("Cucumber Version", properties.getProperty("cucumber.version"));
	}

	@BeforeClass
	public void setup() {
		Properties properties = new Properties();

		try {
			properties.load(new FileInputStream(new File((System.getProperty("user.dir") + "/src/test/resources/propertiesFile/Environment.properties"))));
		} catch (IOException e) {
			System.out.println("Exception in file handling. " + e.getMessage());
			e.printStackTrace();
		}
		
		ParentStep.environment = properties.getProperty("Environment");
		ParentStep.executionId = properties.getProperty("ExecutionId");
		

		File reportDir = new File(System.getProperty("user.dir") + "/TestReport/" + ParentStep.environment + "_" + ParentStep.executionId);
		
		
		
		
		System.out.println("Execution Report Path : [" + reportDir.getPath() + "]");
		
		if (!reportDir.exists()) {
			reportDir.mkdir();
		}

		extentProperties = ExtentProperties.INSTANCE;
		extentProperties.setReportPath(reportDir + "\\" + ParentStep.environment + "_" + ParentStep.executionId + "_SELENIUM"
		 + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())
				+ ".htm");
		extentProperties.setProjectName("SOGETI");
	}

}