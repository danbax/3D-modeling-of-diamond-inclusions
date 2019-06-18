package helper;


public class Reconstruction3DJNI {
	static {
		System.loadLibrary("Reconstruction3D");
	}
	
	//input parameters 
	public native boolean SetParameter(String sParam_name, String sValue);
	
	//input
	public native boolean InputVideoStart(int iFramesCount, int iWidth, int iHeight);//Checked
	public native boolean InputVideoFinish();//Checked
	public native boolean InputVideoSetFrame(int iFrameIndex, byte[] aFrameData);//Checked

	//output
	public native float[] OutputGetVoxelsData();//Checked
	public native int OutputGetVoxelsCount();//Checked
	public native int OutputGetVoxelsFields();//Checked
	
	//process
	//global stone procedures
	public native boolean Init();
	public native boolean Clear();
	public native boolean Process();
	
	//process
	//single slice processing - process single slice using provided slice index
	public native boolean ProcessSingleSlice(int iSliceIndex);
	//retrive data related to last slice processing
	public native boolean GetSingleSliceSingram();
	public native boolean GetSingleSliceProjectionDataPoint(int iX, int iY);
	public native boolean GetSingleSliceSProjectionImage();
	
	//process
	//get process-related data
	public native int GetProgress(); //Checked
	public native String GetLog();
	
	//error handling
	public native int GetLastErrorCode();//Checked
	public native String GetLastErrorString();//Checked

}
