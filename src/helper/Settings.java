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
    String basicData = "{\"numberOfImages\":0,\"imagesFolder\":\"C:/\",\"imageWidth\":\"1024\",\"imageHeight\":\"1280\""
    		+ ",\"scale\":\"0.013046\",\"rotationCenter\":\"511.996\",\"ImageCount\":\"300\",\"angleStep\":\"1.2\"}";
	
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
		
		// update scale in settings
		public void setScale(String scale) throws JSONException, IOException {
			JSONObject settings = this.getSettingsJsonObject();
			settings.remove("scale");
			settings.put("scale", scale);
			
			this.putSettingsString(settings.toString());
		}
		
		// retrieve scale from settings
		public String getScale() throws JSONException, IOException {
			 JSONObject JSONObject = getSettingsJsonObject();
			 return JSONObject.getString("scale");
		}
		
		// update rotationCenter in settings
		public void setRotationCenter(String rotationCenter) throws JSONException, IOException {
			JSONObject settings = this.getSettingsJsonObject();
			settings.remove("rotationCenter");
			settings.put("rotationCenter", rotationCenter);
			
			this.putSettingsString(settings.toString());
		}
		
		// retrieve rotationCenter from settings
		public String getRotationCenter() throws JSONException, IOException {
			 JSONObject JSONObject = getSettingsJsonObject();
			 return JSONObject.getString("rotationCenter");
		}
		
		// update ImageCount in settings
		public void setImageCount(String ImageCount) throws JSONException, IOException {
			JSONObject settings = this.getSettingsJsonObject();
			settings.remove("ImageCount");
			settings.put("ImageCount", ImageCount);
			
			this.putSettingsString(settings.toString());
		}
		
		// retrieve ImageCount from settings
		public String getImageCount() throws JSONException, IOException {
			 JSONObject JSONObject = getSettingsJsonObject();
			 return JSONObject.getString("ImageCount");
		}
		
		// update angleStep in settings
		public void setAngleStep(String angleStep) throws JSONException, IOException {
			JSONObject settings = this.getSettingsJsonObject();
			settings.remove("angleStep");
			settings.put("angleStep", angleStep);
			
			this.putSettingsString(settings.toString());
		}
		
		// retrieve angleStep from settings
		public String getAngleStep() throws JSONException, IOException {
			 JSONObject JSONObject = getSettingsJsonObject();
			 return JSONObject.getString("angleStep");
		}
		
		// update imageWidth in settings
		public void setImageWidth(String imageWidth) throws JSONException, IOException {
			JSONObject settings = this.getSettingsJsonObject();
			settings.remove("imageWidth");
			settings.put("imageWidth", imageWidth);
			
			this.putSettingsString(settings.toString());
		}
		
		// retrieve imageWidth from settings
		public String getImageWidth() throws JSONException, IOException {
			 JSONObject JSONObject = getSettingsJsonObject();
			 return JSONObject.getString("imageWidth");
		}
		
		// update ImageHeight in settings
		public void setImageHeight(String imageHeight) throws JSONException, IOException {
			JSONObject settings = this.getSettingsJsonObject();
			settings.remove("imageHeight");
			settings.put("imageHeight", imageHeight);
			
			this.putSettingsString(settings.toString());
		}
		
		// retrieve imageHeight from settings
		public String getImageHeight() throws JSONException, IOException {
			 JSONObject JSONObject = getSettingsJsonObject();
			 return JSONObject.getString("imageHeight");
		}

}

