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

public class VisionData extends Pipeline {
	Pipeline pipeline; //This goes to the GRIP pipeline that does all the work
	Mat image_process; //Input image
	ArrayList<MatOfPoint> final_contours; //Contours that GRIP gives at the end
	ArrayList<MatOfPoint2f> contours_modified= new ArrayList<MatOfPoint2f>(); //For minarearect
	public VisionData(Mat image) { //Init 
		image_process= image;
		pipeline=new Pipeline();
	}
	public void ProcessImage() {
		pipeline.process(image_process);
		final_contours=pipeline.filterContoursOutput(); //Get GRIP output
		
	}
	public ArrayList<RotatedRect> GetBoundingBox() {
		for(MatOfPoint point : final_contours) {
		     MatOfPoint2f newPoint = new MatOfPoint2f(point.toArray());
		     contours_modified.add(newPoint); //convert MatOfPoint to MatOfPoint2f
		 }
		ArrayList<RotatedRect> bbox = new ArrayList<RotatedRect>();
		for (int x=0; x<final_contours.size(); x++) {
		bbox.add(Imgproc.minAreaRect(contours_modified.get(x)));
		//Returns center of rotated rect, dimensions, and angle of rotation

	}
		return bbox;
	}



	public static void main(String args[]) {
		Mat image=Imgcodecs.imread("/Users/Amit_Rajesh/Downloads/2017VisionExample/Vision Images/LED Boiler/1ftH11ftD2Angle0Brightness.jpg");
		VisionData image_data=new VisionData(image);
		ArrayList<RotatedRect> rotated_bbox;
     	image_data.ProcessImage();
     	rotated_bbox=image_data.GetBoundingBox();
		for (int x=0; x<rotated_bbox.size(); x++) {
		    System.out.println(rotated_bbox.get(x));
		}
	}
}

	
	

