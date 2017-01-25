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
	public VisionData(Mat image) { //Init 
		image_process= image;
		pipeline=new Pipeline();
	}
	public void ProcessImage() {
		pipeline.process(image_process);
		final_contours=pipeline.filterContoursOutput(); //Get GRIP output
		
	}
	public ArrayList<Rect> GetBoundingBox() {
		ArrayList<Rect> bbox = new ArrayList<Rect>();
		for (int x=0; x<final_contours.size(); x++) {
		bbox.add(Imgproc.boundingRect(final_contours.get(x)));
	}
	return bbox;

	}
}
	
	

