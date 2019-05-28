package helper;


public class ReconstructionJNI {
	static {
		System.loadLibrary("Reconstruction");
	}
	
	//parameters
	public native boolean SetParameter_int(String sParam_name, int iValue);//Checked
	public native boolean SetParameter_float(String sParam_name, float fValue);//Checked
	
	//input
	public native boolean InputVideoStart(int iFramesCount, int iWidth, int iHeight);//Checked
	public native boolean InputVideoFinish();//Checked
	public native boolean InputVideoSetFrame(int iFrameIndex, byte[] aFrameData);//Checked

	//output
	public native boolean OutputGetVoxelsData(float[] aVoxelsData);//Checked
	public native int OutputGetVoxelsCount();//Checked
	public native int OutputGetVoxelsFields();//Checked

	public native boolean OutputGetSliceData(int iSliceIndex, float[] aSliceData);//Checked
	public native int OutputGetSliceSize();//Checked

	//Process
	public native boolean Process();//Checked
	public native int GetProcessProgress();//Checked

	//error handling
	public native int GetLastErrorCode();//Checked
	public native String GetLastErrorString();//Checked

}
