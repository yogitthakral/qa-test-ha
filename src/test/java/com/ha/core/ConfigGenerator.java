package com.ha.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;



public class ConfigGenerator extends ConfigDetails {
	
	
	public static void main(String[] args) {
		//get the arguments from maven command line
		initializeConfigVariables(args[0],args[1],args[2]);
		//write the arguments into config file
		writeConfig();

	}
	
	public static void initializeConfigVariables(String environment,
			String drivertype, String urlstring) {


		if (!environment.equalsIgnoreCase("${environment}")){
			env = environment.trim();
			System.out.println(environment);
		}	
		
		if (!drivertype.equalsIgnoreCase("${drivertype}")){
			Driver_Type = drivertype.trim();
			System.out.println(drivertype);
		}	
		
		
			urlstr = urlstring;
		
		


		System.out.println("Environment is  = " + env);
		System.out.println("driver =" + Driver_Type);
		System.out.println("urlstr = " + urlstr);

	}
	public static  void writeConfig(){
		try {
			String directory = System.getProperty("user.dir");
			File configfile = new File(directory+"/Config.properties");
			FileWriter fileWritter = new FileWriter(configfile, true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.newLine();
			bufferWritter.append("urlstr=" + urlstr);
			bufferWritter.newLine();
			bufferWritter.append("Environment=" + env);
			bufferWritter.newLine();
			bufferWritter.append("Driver_Type=" + Driver_Type);
			bufferWritter.newLine();
			bufferWritter.close();
			fileWritter.close();


		} catch (Exception e) {
			System.out.println("Failed to Generate Config.Properties!!");
			e.printStackTrace();
		}
	}

}
