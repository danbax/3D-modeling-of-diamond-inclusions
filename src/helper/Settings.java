package helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.*;


 
public class Settings
{
	private String settingsString;
	private String settingsFolderLocation = "C:\\diamondsModelingData";
	private String settingsFileName = "settings.json";
    String basicData = "{\"numberOfImages\":0,\"imagesFolder\":\"C:/\"}";
	
	public Settings() throws IOException {
		settingsString = "";
		File myDir = new File(settingsFolderLocation); 
		myDir.mkdir(); 
		File myFile = new File(settingsFolderLocation + "\\" + settingsFileName); 
		myFile.createNewFile(); 
	}

	// get settings.json contents
	public String getSettingString() throws IOException {
		 // pass the path to the file as a parameter 
		
	    File settingsFile = new File(settingsFolderLocation + "\\" + settingsFileName); 
	
	    Scanner sc = new Scanner(settingsFile); 
	  
	    while (sc.hasNextLine()) 
	    	settingsString += sc.nextLine(); 
	    
	    // initialize file content if empty
	    if(settingsString.isEmpty() || settingsString == null || settingsString == "") {
	    	this.putSettingsString(basicData);
	    	settingsString = basicData;
	    }
	    
	    sc.close();
	    
	    //settingsString = settingsString.substring(13);
		return  settingsString;
	}
	
	// overwrite settings.json content
	public void putSettingsString(String settings) throws IOException {
		File settingsFile = new File(settingsFolderLocation + "\\" + settingsFileName); 
		
		if (settingsFile.exists() && settingsFile.isFile())
			settingsFile.delete();
		
		FileWriter settingsWriter = new FileWriter(settingsFile, false); // true to append
		

		// false to overwrite.
		settingsWriter.write(settings);
		settingsWriter.close();
		
		
	}
	
	// get settings.json content as object
	public JSONObject getSettingsJsonObject() throws JSONException, IOException {
		 final JSONObject obj = new JSONObject(this.getSettingString());
		 return obj;
	}
	
	// update folderLocation in settings
	public void setFolderLocation(String folder) throws JSONException, IOException {
		JSONObject settings = this.getSettingsJsonObject();
		settings.remove("imagesFolder");
		settings.put("imagesFolder", folder);
		
		this.putSettingsString(settings.toString());
	}
	
	// retrieve folder Location from settings
	public String getFolderLocation() throws JSONException, IOException {
		 JSONObject JSONObject = getSettingsJsonObject();
		 return JSONObject.getString("imagesFolder");
	}

}

