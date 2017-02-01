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
	Pipeline pipeline; // This goes to the GRIP pipeline that does all the work
	Mat image_process; // Input image
	ArrayList<MatOfPoint> final_contours; // Contours that GRIP gives at the end
	ArrayList<Rect> bbox = new ArrayList<Rect>();

	public VisionData(Mat image) { // Init
		image_process = image;
		pipeline = new Pipeline();
	}

	public void processImage() {
		pipeline.process(image_process);
		final_contours = pipeline.filterContoursOutput(); // Get GRIP output

	}

	public ArrayList<Rect> getBoundingBox() {
		for (int i = 0; i < final_contours.size(); i++) {
			bbox.add(Imgproc.boundingRect(final_contours.get(i)));
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
	
	public double getYaw() {
		getBoundingBox();
		int x = 0;
		for (Rect r : bbox) {
			x += r.x;
		}
		x /= bbox.size();
		return 0.0;
	}
	
	public double getDist() {
		getBoundingBox();
		double area = 0.0;
		for (Rect r : bbox) {
			area += r.area();
		}
		return 0.0;
	}
	
}
