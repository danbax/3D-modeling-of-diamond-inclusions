package helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import org.json.*;


 
public class Settings
{
	private String settingsString;
	
	public Settings() {
		settingsString = "";
	}

	public String getSettingString() throws FileNotFoundException {
		 // pass the path to the file as a parameter 
		
		String settingsString = "";
	    File settingsFile = new File("settings.json"); 
	    Scanner sc = new Scanner(settingsFile); 
	  
	    while (sc.hasNextLine()) 
	    	settingsString += sc.nextLine(); 
	    
	    //settingsString = settingsString.substring(13);
		return  settingsString;
	}
	
	public void putSettingsString(String settings) throws IOException {
		File settingsFile = new File("settings.json");
		
		if (settingsFile.exists() && settingsFile.isFile())
			settingsFile.delete();
		
		FileWriter settingsWriter = new FileWriter(settingsFile, false); // true to append
		

		// false to overwrite.
		settingsWriter.write(settings);
		settingsWriter.close();
		
		
	}
	
	public JSONObject getSettingsJsonObject() throws JSONException, FileNotFoundException {
		 final JSONObject obj = new JSONObject(this.getSettingString());
		 return obj;
	}
	
	public void setFolderLocation(String folder) throws JSONException, IOException {
		JSONObject settings = this.getSettingsJsonObject();
		settings.remove("imagesFolder");
		settings.put("imagesFolder", folder);
		
		this.putSettingsString(settings.toString());
	}
	
	public String getFolderLocation() throws JSONException, FileNotFoundException {
		 //System.out.println(this.getSettingString());
		 //JSONObject JSONObject = getSettingsJsonObject();
		 //return JSONObject.getString("imagesFolder");
		 return this.getSettingString();
	}

}

