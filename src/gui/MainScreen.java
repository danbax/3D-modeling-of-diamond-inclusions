package gui;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import org.json.JSONException;

import entities.Point2D;
import entities.Point3D;
import entities.PointsCloud2D;
import entities.PointsCloud3D;
import helper.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import helper.ImagesColored;
import helper.ImagesLoader;
import helper.ReconstructionJNI;

public class MainScreen extends GUIcontroller  implements Initializable  {
	@FXML private Text AppName = new Text();
	@FXML private Text folderText = new Text();
	@FXML private Text NumberOfImages = new Text();
	
	@FXML private TextField scale = new TextField();
	@FXML private TextField imageWidth = new TextField();
	@FXML private TextField imageHeight = new TextField();
	@FXML private TextField imageCount = new TextField();
	@FXML private TextField angleStep = new TextField();
	@FXML private TextField rotationCenter = new TextField();

	@FXML private Button selectImagesFolder;
	@FXML private ProgressBar progressBar;
	
	ReconstructionJNI reconstructionJNI = new ReconstructionJNI();
	Stage thisStage;
	Settings settings;
	String folderPath;
	String shortFolderPath;
	
	PointsCloud3D pointsArray = new PointsCloud3D();
	
		/*********************************************
		 *  Triggered by clicking on "select images folder"
		 *********************************************/
		public void selectImagesFolder(ActionEvent event) throws Exception {
			
			DirectoryChooser directoryChooser = new DirectoryChooser();
			File selectedDirectory = directoryChooser.showDialog(thisStage);

			if(selectedDirectory == null){
			     //No Directory selected
			}else{
					folderPath = selectedDirectory.getAbsolutePath();
			     	
			     	// save images folder location to settings.json
					settings = new Settings();
					settings.setFolderLocation(folderPath);
					
					// Adjust name length for text field
					if(folderPath.length() >23)
						shortFolderPath = folderPath.substring(0,23)+"...";
			     	folderText.setText(shortFolderPath);
			     	
			     	laodImagesFromFolder(folderPath);
			}
			
		
		}
		
		/*********************************************
		 *  Triggered by clicking on "Create 3D model"
		 *********************************************/
		
		public void create3dModel(ActionEvent event) throws Exception {
			// show error messeage if there are no images
			if(ImagesLoader.imagesArray.size()==0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
				alert.setTitle("Error");
				alert.setHeaderText("Images not found");
				alert.setContentText("You should select images folder!");

				alert.showAndWait();
			}else {
				

				progressBar.setVisible(true);

//**********************************************Testing AREA********************************************************//
				//byte[] bytes = { (byte) 48, (byte) 65, (byte)6f, (byte)20, (byte)77, (byte)6f, (byte)72 , (byte)64};
				byte[] bytes = { 0x3f,0x3d,0x01,0x21,1,1,1};
				
			/*	
				ReconstructionJNI rJNI = new ReconstructionJNI();
				rJNI.InputVideoFinish();
				rJNI.InputVideoSetFrame(1, bytes);
				rJNI.OutputGetVoxelsFields();
				*/
				Runnable r = new Runnable() {
			         public void run() {

			        	 /* send parameters */
			        	 try {
							Settings settings = new Settings();
							reconstructionJNI.SetParameter_float("scale", Float.parseFloat(settings.getScale()));
							reconstructionJNI.SetParameter_float("scale", Float.parseFloat(settings.getAngleStep()));
							reconstructionJNI.SetParameter_int("scale", Integer.parseInt(settings.getImageCount()));
							reconstructionJNI.SetParameter_int("scale", Integer.parseInt(settings.getImageHeight()));
							reconstructionJNI.SetParameter_int("scale", Integer.parseInt(settings.getImageWidth()));
							reconstructionJNI.SetParameter_float("scale", Float.parseFloat(settings.getRotationCenter()));
							
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        	 
			        	 
			        	 /* send byte array to c++ dll */
			        	 ArrayList<BufferedImage> imagesArray = ImagesLoader.imagesArray;
			 			
							for(int i=0;i<imagesArray.size();i++){
						 		BufferedImage image = imagesArray.get(i);
						 		byte[] bytes = ImagesLoader.BufferedImageToByteArray(image);
						 		reconstructionJNI.InputVideoSetFrame(i, bytes);
						 		
						 		Timer timer = new Timer();
						 		int begin = 0;
						 		int timeInterval = 1000;
						 		timer.schedule(new TimerTask() {
						 	    int counter = 0;
						 		   @Override
						 		   public void run() {

							 		  int progress = reconstructionJNI.GetProcessProgress();
						 			  progressBar.setProgress(progress);
						 			   
						 		       //call the method
						 		       counter++;
						 		       if (counter >= 50){
						 		         timer.cancel();
						 		       }
						 		      if(progress >= 100) {
						 		    	  
						 		    	  /**********************************
						 		    	   * HERE we should get voxels, create 3D points cloud -> make 2D points cloud
						 		    	   * //ImagesColored images = new ImagesColored(pointsArray);
						 		    	   *
						 		    	   */
						 		    	  
						 		    	  GUIcontroller guic = new GUIcontroller();
						 		    	  try {
											guic.loadFxml("Results.fxml");
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
						 		      }
						 		   }
						 		}, begin, timeInterval);

						 		
							}
			         }
			     };

			     new Thread(r).start();			
				
				
				
			}
		}
		
		public void start(Stage primaryStage) throws Exception {
			
			/*
			 * start frame
			 */

			
			Parent root = FXMLLoader.load(getClass().getResource("/main/resources/MainScreen.fxml"));
					
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/main/resources/AppStyle.css").toExternalForm());
			//gui controller
			
			GUIcontroller.setCurrentScene(scene);
			
			thisStage = primaryStage;
			
			primaryStage.setTitle("Diamonds Modeling");
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			

			primaryStage.show();
			

		}
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {	

			progressBar.setVisible(false);
			
			Settings settings = null;
			String folderLocation = null;
			try {
				settings = new Settings();
				folderLocation = settings.getFolderLocation();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(folderLocation != null) {
				folderText.setText(folderLocation);
				laodImagesFromFolder(folderLocation);
			}
			
			setParametersInTextField();
			
			/************************************************
			 * SET settings listeners
			 ************************************************/
			scale.textProperty().addListener((observable, oldValue, newValue) -> {
				
				try {
					Settings settingsUpdate = new Settings();
					settingsUpdate.setScale(newValue);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			});
			/************************************************
			 * SET settings listeners - images count
			 ************************************************/
			imageCount.textProperty().addListener((observable, oldValue, newValue) -> {
				
				try {
					Settings settingsUpdate = new Settings();
					settingsUpdate.setScale(newValue);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			});
			/************************************************
			 * SET settings angel step
			 ************************************************/
			angleStep.textProperty().addListener((observable, oldValue, newValue) -> {
	
				try {
					Settings settingsUpdate = new Settings();
					settingsUpdate.setScale(newValue);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			});
			/************************************************
			 * SET settings rotation center
			 ************************************************/
			rotationCenter.textProperty().addListener((observable, oldValue, newValue) -> {
				
				try {
					Settings settingsUpdate = new Settings();
					settingsUpdate.setScale(newValue);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			});
			/************************************************
			 * SET settings image width
			 ************************************************/
			imageWidth.textProperty().addListener((observable, oldValue, newValue) -> {
				
				try {
					Settings settingsUpdate = new Settings();
					settingsUpdate.setScale(newValue);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			});
			/************************************************
			 * SET settings image height
			 ************************************************/
			imageHeight.textProperty().addListener((observable, oldValue, newValue) -> {
				
				try {
					Settings settingsUpdate = new Settings();
					settingsUpdate.setScale(newValue);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			});
			
			/*
			 Point3D point = null;
			 PointsCloud3D pointsCloud = new PointsCloud3D();
			 PointsCloud2D pointsCloud2d = new PointsCloud2D();
			 String csvFile = "D:\\\\Stone 1\\\\Voxels.csv";
			 String line = "";
	         String cvsSplitBy = ",";
	         

	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

	            while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] pointString = line.split(cvsSplitBy);
	                point = new Point3D(Double.parseDouble(pointString[0]),Double.parseDouble(pointString[1]),Double.parseDouble(pointString[2]),Double.parseDouble(pointString[3])); 
	                pointsCloud.addPoint(point);

	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        //pointsCloud.print();
	        this.pointsArray = pointsCloud;
	        */
	    }
			
		
		
		private void laodImagesFromFolder(String imagesFolderLocation) {
			// Loading images from selected folder to static arrayList varaiable
			ImagesLoader imgLoad = new ImagesLoader();
		 	ArrayList<BufferedImage> imagesArray = imgLoad.getImages(imagesFolderLocation);
		 	if(imagesArray.size()==0) {
		 		NumberOfImages.setFill(Color.RED);
			 	NumberOfImages.setText("Images not found in folder");
		 	}else {
			 	NumberOfImages.setFill(Color.GREEN);
			 	NumberOfImages.setText(imagesArray.size()+ " images found");
		 	}
		}
		
		private void setParametersInTextField() {
			Settings settings = null;
			try {
				settings = new Settings();
				scale.setText(settings.getScale());
				imageWidth.setText(settings.getImageWidth());
				imageHeight.setText(settings.getImageHeight());
				imageCount.setText(settings.getImageCount());
				angleStep.setText(settings.getAngleStep());
				rotationCenter.setText(settings.getRotationCenter());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		

	
}
