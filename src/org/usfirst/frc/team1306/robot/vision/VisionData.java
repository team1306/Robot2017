package org.usfirst.frc.team1306.robot.vision;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

import edu.wpi.first.wpilibj.vision.VisionPipeline;

import org.opencv.core.*;
import org.opencv.core.Core.*;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.*;
import org.opencv.objdetect.*;
import org.usfirst.frc.team1306.robot.Constants;


public class VisionData extends Pipeline {
	Pipeline pipeline; // This goes to the GRIP pipeline that does all the work
	Mat image_process; // Input image
<<<<<<< HEAD
	ArrayList<Rect> bbox;
=======
	ArrayList<MatOfPoint> final_contours; // Contours that GRIP gives at the end
	ArrayList<Rect> bbox = new ArrayList<Rect>();

>>>>>>> origin/master
	public VisionData(Mat image) { // Init
		image_process = image;
		pipeline = new Pipeline();

	}

	public ArrayList<MatOfPoint> processImage() {
		ArrayList<MatOfPoint> final_contours; // Contours that GRIP gives at the end
		pipeline.process(image_process);
		final_contours = pipeline.filterContoursOutput(); // Get GRIP output
		return final_contours;
	}

<<<<<<< HEAD
	public ArrayList<Rect> getBoundingBox(ArrayList<MatOfPoint> contours) {
		ArrayList<Rect> bbox= new ArrayList<Rect>(); //Bbox stuff

		for (int i = 0; i < contours.size(); i++) {
			bbox.add(Imgproc.boundingRect(contours.get(i)));
=======
	public ArrayList<Rect> getBoundingBox() {
		for (int i = 0; i < final_contours.size(); i++) {
			bbox.add(Imgproc.boundingRect(final_contours.get(i)));
>>>>>>> origin/master
		}
		return bbox;
	}
	
	public double getPitch() {
		getBoundingBox();
		int y = 0;
		for (Rect r : bbox) {
			y += r.y;
		}
		y /= bbox.size();
		return 0.0;
	}
	
<<<<<<< HEAD
	public double[] getYaw(ArrayList<Rect> bounding_box) {
		
		double yaw_array[] = new double[bounding_box.size()]; 
		
		//This takes the width of the image in pixels and divides it by the horizontal angle,
	   //giving the number of degrees per pixel
		double deg_per_pixel= Constants.AXIS_RES_WIDTH / Constants.AXIS_HORIZ_ANGLE;
		double center_col = (Constants.AXIS_RES_WIDTH - 1) / 2; //Center column of image, -1 is to account for 
		//0 indexing
		
		for (int i=0; i< bounding_box.size(); i++) {
			Rect contour_info= bounding_box.get(i);
			int contour_x=contour_info.x; //column of center of contour (tape) 
			yaw_array[i]=(contour_x - center_col) * deg_per_pixel;
			//(contour_x - center_col) is the horizontal distance between 
			//the center of the image and the center of the target in pixels, so 
			//you multiply that by the degrees/pixel conversion factor to get degrees
		}
		return yaw_array;	
		
		//Unless the image given fits the Axis camera constants used, the values are probably going to be off
		}
	
=======
	public double getYaw() {
		getBoundingBox();
		int x = 0;
		for (Rect r : bbox) {
			x += r.x;
		}
		x /= bbox.size();
		return 0.0;
	}
>>>>>>> origin/master
	
	public double getDist() {
		getBoundingBox();
		double area = 0.0;
		for (Rect r : bbox) {
			area += r.area();
		}
		return 0.0;
	}
	
	public static void main(String args[]) {
	Mat image=Imgcodecs.imread("/Users/Amit_Rajesh/Downloads/2017VisionExample/Vision Images/LED Boiler/1ftH11ftD2Angle0Brightness.jpg");
	VisionData data=new VisionData(image);
	ArrayList<MatOfPoint> final_contours;
	ArrayList<Rect> bounding_box;
	final_contours=data.processImage();
	bounding_box=data.getBoundingBox(final_contours);
	double yaw[] = data.getYaw(bounding_box);
	
	for (int x=0; x<yaw.length; x++) {
	    System.out.println(yaw[x]);
	}
	
}
	
}
