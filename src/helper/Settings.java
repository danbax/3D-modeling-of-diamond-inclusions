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
    String settingsNoFilter = "{"
    		+ "\"dPixelsSize\":\"0.013\","
    		+ "\"dActualAngleRange\":\"180\","
    		+ "\"iActualFramesCount\":\"150\","
    		+ "\"dCOR\":\"512\","
    		+ "\"dVoxelsFOVFactor\":\"1\","
    		+ "\"dWidthStretchFactor\":\"1\","
    		+ "\"stSignalSpecificParams.bIsInvertedImage\":\"true\","
    		+ "\"stSignalSpecificParams.bIsFlipYImage\":\"false\","
    		+ "\"iRampKernelSize\":\"-1\","
    		+ "\"bIsTraceImageData\":\"true\","
    		+ "\"iVideoDownscaleFactor\":\"2\","
    		+ "\"dNormalizeInputVideoHistogramMaxValue\":\"255\","
    		+ "\"stVoxelsIntensityStretching_Params.iType\":\"0\","
    		+ "\"stVoxelsIntensityStretching_Params.dAdaptiveThreshold\":\"0.8\","
    		+ "\"stVoxelsIntensityStretching_Params.dAdaptivePercentage\":\"1\","
    		+ "\"stVoxelsIntensityStretching_Params.dHighPercentage\":\"5\","
    		+ "\"stVoxelsIntensityStretching_Params.dLowPercentage\":\"5\","
    		+ "\"stStoneManualROI_Params.bIsEnabled\":\"true\","
    		+ "\"stStoneManualROI_Params.iHeightMin\":\"200\","
    		+ "\"stStoneManualROI_Params.iHeightMax\":\"400\","
    		+ "\"stStoneManualROI_Params.iRadiusMin\":\"0\","
    		+ "\"stStoneManualROI_Params.iRadiusMax\":\"150\","
    		+ "\"dDetectionThresholdLow\":\"210\","
    		+ "\"dDetectionThresholdHigh\":\"255\","
    		+ "\"dDetectionSignalsMinCountPercentage\":\"5\","
    		+ "\"iCurrentOppositeFunc\":\"0\","
    		+ "\"numberOfImages\":0,"
    		+ "\"imagesFolder\":\"C:/\","
    		+ "\"imageWidth\":\"1024\","
    		+ "\"imageHeight\":\"1280\""
    		+ ",\"scale\":\"0.013046\","
    		+ "\"rotationCenter\":\"511.996\","
    		+ "\"ImageCount\":\"300\","
    		+ "\"angleStep\":\"1.2\""
    		+ "}";
	
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
	    	this.putSettingsString(settingsNoFilter);
	    	settingsString = settingsNoFilter;
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

		/*************************************************
		 * 
		 * 
		 * @param XXXXX
		 * @throws JSONException
		 * @throws IOException
		 */
		// update dPixelsSize in settings
		public void setdPixelsSize(String dPixelsSize) throws JSONException, IOException {
			JSONObject settings = this.getSettingsJsonObject();
			settings.remove("dPixelsSize");
			settings.put("dPixelsSize", dPixelsSize);
			
			this.putSettingsString(settings.toString());
		}
		
		// retrieve dPixelsSize from settings
		public String getdPixelsSize() throws JSONException, IOException {
			 JSONObject JSONObject = getSettingsJsonObject();
			 return JSONObject.getString("dPixelsSize");
		}
		
		// update dActualAngleRange in settings
				public void setdActualAngleRange(String dActualAngleRange) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("dActualAngleRange");
					settings.put("dActualAngleRange", dActualAngleRange);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve dActualAngleRange from settings
				public String getdActualAngleRange() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("dActualAngleRange");
				}
				
				// update iActualFramesCount in settings
				public void setiActualFramesCount(String iActualFramesCount) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("iActualFramesCount");
					settings.put("iActualFramesCount", iActualFramesCount);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve iActualFramesCount from settings
				public String getiActualFramesCount() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("iActualFramesCount");
				}
				
				// update dCOR in settings
				public void setdCOR(String dCOR) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("dCOR");
					settings.put("dCOR", dCOR);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve dCOR from settings
				public String getdCOR() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("dCOR");
				}
				
				// update dVoxelsFOVFactor in settings
				public void setdVoxelsFOVFactor(String dVoxelsFOVFactor) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("dVoxelsFOVFactor");
					settings.put("dVoxelsFOVFactor", dVoxelsFOVFactor);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve dVoxelsFOVFactor from settings
				public String getdVoxelsFOVFactor() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("dVoxelsFOVFactor");
				}
				
				// update dWidthStretchFactor in settings
				public void setdWidthStretchFactor(String dWidthStretchFactor) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("dWidthStretchFactor");
					settings.put("dWidthStretchFactor", dWidthStretchFactor);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve dWidthStretchFactor from settings
				public String getdWidthStretchFactor() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("dWidthStretchFactor");
				}
				
				// update stSignalSpecificParams in settings
				public void setstSignalSpecificParamsbIsInvertedImage(String stSignalSpecificParamsbIsInvertedImage) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("stSignalSpecificParams.bIsInvertedImage");
					settings.put("stSignalSpecificParams.bIsInvertedImage", stSignalSpecificParamsbIsInvertedImage);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve stSignalSpecificParams from settings
				public String getstSignalSpecificParamsbIsInvertedImage() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("stSignalSpecificParams.bIsInvertedImage");
				}
				
				// update stSignalSpecificParams in settings
				public void setstSignalSpecificParamsbIsFlipYImage(String stSignalSpecificParamsbIsFlipYImage) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("stSignalSpecificParams.bIsFlipYImage");
					settings.put("stSignalSpecificParams.bIsFlipYImage", stSignalSpecificParamsbIsFlipYImage);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve stSignalSpecificParams from settings
				public String getstSignalSpecificParamsbIsFlipYImage() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("stSignalSpecificParams.bIsFlipYImage");
				}
				
				// update iRampKernelSize in settings
				public void setiRampKernelSize(String iRampKernelSize) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("iRampKernelSize");
					settings.put("iRampKernelSize", iRampKernelSize);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve iRampKernelSize from settings
				public String getiRampKernelSize() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("iRampKernelSize");
				}
				
				// update bIsTraceImageData in settings
				public void setbIsTraceImageData(String bIsTraceImageData) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("bIsTraceImageData");
					settings.put("bIsTraceImageData", bIsTraceImageData);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve bIsTraceImageData from settings
				public String getbIsTraceImageData() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("bIsTraceImageData");
				}
				
				// update iVideoDownscaleFactor in settings
				public void setiVideoDownscaleFactor(String iVideoDownscaleFactor) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("iVideoDownscaleFactor");
					settings.put("iVideoDownscaleFactor", iVideoDownscaleFactor);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve iVideoDownscaleFactor from settings
				public String getiVideoDownscaleFactor() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("iVideoDownscaleFactor");
				}
				
				
				// update XXXXX in settings
				public void setdNormalizeInputVideoHistogramMaxValue(String dNormalizeInputVideoHistogramMaxValue) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("dNormalizeInputVideoHistogramMaxValue");
					settings.put("dNormalizeInputVideoHistogramMaxValue", dNormalizeInputVideoHistogramMaxValue);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve XXXXX from settings
				public String getdNormalizeInputVideoHistogramMaxValue() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("dNormalizeInputVideoHistogramMaxValue");
				}
				// update XXXXX in settings
				public void setstVoxelsIntensityStretching_ParamsiType(String stVoxelsIntensityStretching_ParamsiType) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("stVoxelsIntensityStretching_Params.iType");
					settings.put("stVoxelsIntensityStretching_Params.iType", stVoxelsIntensityStretching_ParamsiType);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve XXXXX from settings
				public String getstVoxelsIntensityStretching_ParamsiType() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("stVoxelsIntensityStretching_Params.iType");
				}
				// update XXXXX in settings
				public void setstVoxelsIntensityStretching_ParamsdAdaptiveThreshold(String stVoxelsIntensityStretching_ParamsdAdaptiveThreshold) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("stVoxelsIntensityStretching_Params.dAdaptiveThreshold");
					settings.put("stVoxelsIntensityStretching_Params.dAdaptiveThreshold", stVoxelsIntensityStretching_ParamsdAdaptiveThreshold);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve XXXXX from settings
				public String getstVoxelsIntensityStretching_ParamsdAdaptiveThreshold() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("stVoxelsIntensityStretching_Params.dAdaptiveThreshold");
				}
				// update XXXXX in settings
				public void setstVoxelsIntensityStretching_ParamsdAdaptivePercentage(String stVoxelsIntensityStretching_ParamsdAdaptivePercentage) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("stVoxelsIntensityStretching_Params.dAdaptivePercentage");
					settings.put("stVoxelsIntensityStretching_Params.dAdaptivePercentage", stVoxelsIntensityStretching_ParamsdAdaptivePercentage);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve XXXXX from settings
				public String getstVoxelsIntensityStretching_ParamsdAdaptivePercentage() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("stVoxelsIntensityStretching_Params.dAdaptivePercentage");
				}
				// update XXXXX in settings
				public void setstVoxelsIntensityStretching_ParamsdHighPercentage(String stVoxelsIntensityStretching_ParamsdHighPercentage) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("stVoxelsIntensityStretching_Params.dHighPercentage");
					settings.put("stVoxelsIntensityStretching_Params.dHighPercentage", stVoxelsIntensityStretching_ParamsdHighPercentage);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve XXXXX from settings
				public String getstVoxelsIntensityStretching_ParamsdHighPercentage() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("stVoxelsIntensityStretching_Params.dHighPercentage");
				}
				// update XXXXX in settings
				public void setstVoxelsIntensityStretching_ParamsdLowPercentage(String stVoxelsIntensityStretching_ParamsdLowPercentage) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("stVoxelsIntensityStretching_Params.dLowPercentage");
					settings.put("stVoxelsIntensityStretching_Params.dLowPercentage", stVoxelsIntensityStretching_ParamsdLowPercentage);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve XXXXX from settings
				public String getstVoxelsIntensityStretching_ParamsdLowPercentage() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("stVoxelsIntensityStretching_Params.dLowPercentage");
				}
				// update XXXXX in settings
				public void setbIsEnabled(String bIsEnabled) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("stStoneManualROI_Params.bIsEnabled");
					settings.put("stStoneManualROI_Params.bIsEnabled", bIsEnabled);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve XXXXX from settings
				public String getbIsEnabled() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("stStoneManualROI_Params.bIsEnabled");
				}
				// update XXXXX in settings
				public void setiHeightMin(String iHeightMin) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("stStoneManualROI_Params.iHeightMin");
					settings.put("stStoneManualROI_Params.iHeightMin", iHeightMin);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve XXXXX from settings
				public String getiHeightMin() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("stStoneManualROI_Params.iHeightMin");
				}
				// update XXXXX in settings
				public void setiHeightMax(String iHeightMax) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("stStoneManualROI_Params.iHeightMax");
					settings.put("stStoneManualROI_Params.iHeightMax", iHeightMax);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve XXXXX from settings
				public String getiHeightMax() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("stStoneManualROI_Params.iHeightMax");
				}
				// update XXXXX in settings
				public void setiRadiusMin(String iRadiusMin) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("stStoneManualROI_Params.iRadiusMin");
					settings.put("stStoneManualROI_Params.iRadiusMin", iRadiusMin);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve XXXXX from settings
				public String getiRadiusMin() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("stStoneManualROI_Params.iRadiusMin");
				}
				// update XXXXX in settings
				public void setiRadiusMax(String iRadiusMax) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("stStoneManualROI_Params.iRadiusMax");
					settings.put("stStoneManualROI_Params.iRadiusMax", iRadiusMax);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve XXXXX from settings
				public String getiRadiusMax() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("stStoneManualROI_Params.iRadiusMax");
				}
				// update XXXXX in settings
				public void setdDetectionThresholdLow(String dDetectionThresholdLow) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("dDetectionThresholdLow");
					settings.put("dDetectionThresholdLow", dDetectionThresholdLow);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve XXXXX from settings
				public String getdDetectionThresholdLow() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("dDetectionThresholdLow");
				}	
				// update XXXXX in settings
				public void setdDetectionThresholdHigh(String dDetectionThresholdHigh) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("dDetectionThresholdHigh");
					settings.put("dDetectionThresholdHigh", dDetectionThresholdHigh);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve XXXXX from settings
				public String getdDetectionThresholdHigh() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("dDetectionThresholdHigh");
				}	
				// update XXXXX in settings
				public void setdDetectionSignalsMinCountPercentage(String dDetectionSignalsMinCountPercentage) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("dDetectionSignalsMinCountPercentage");
					settings.put("dDetectionSignalsMinCountPercentage", dDetectionSignalsMinCountPercentage);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve XXXXX from settings
				public String getdDetectionSignalsMinCountPercentage() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("dDetectionSignalsMinCountPercentage");
				}	
				
				// update XXXXX in settings
				public void setiCurrentOppositeFunc(String iCurrentOppositeFunc) throws JSONException, IOException {
					JSONObject settings = this.getSettingsJsonObject();
					settings.remove("iCurrentOppositeFunc");
					settings.put("iCurrentOppositeFunc", iCurrentOppositeFunc);
					
					this.putSettingsString(settings.toString());
				}
				
				// retrieve XXXXX from settings
				public String getiCurrentOppositeFunc() throws JSONException, IOException {
					 JSONObject JSONObject = getSettingsJsonObject();
					 return JSONObject.getString("iCurrentOppositeFunc");
				}

}

