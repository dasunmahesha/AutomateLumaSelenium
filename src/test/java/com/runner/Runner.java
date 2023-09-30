package com.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class) 
@CucumberOptions(
		features="src/test/resources/Features",
		glue={"com.stepDefinetion"},
		monochrome=true,
		plugin = {
				"pretty",
				"html:target\\Reports\\html\\test01.html" 
				},
		dryRun = false
)
public class Runner {	
}	