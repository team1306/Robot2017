package org.usfirst.frc.team1306.robot.vision;

import java.util.ArrayList;
import java.util.Comparator;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.*;
import org.usfirst.frc.team1306.robot.Constants;


public class VisionData extends Pipeline {
	Pipeline pipeline; // This goes to the GRIP pipeline that does all the work
	Mat image_process; // Input image

	public VisionData(Mat image) { // Init
		image_process = image;
		pipeline = new Pipeline();
		Math.atan(0);
	}

	public ArrayList<MatOfPoint> processImage() {
		ArrayList<MatOfPoint> final_contours; // Contours that GRIP gives at the end
		pipeline.process(image_process);
		final_contours = pipeline.filterContoursOutput(); // Get GRIP output
		return final_contours;
	}

	public ArrayList<Rect> getBoundingBox(ArrayList<MatOfPoint> contours) {
		ArrayList<Rect> bbox= new ArrayList<Rect>(); //Bbox stuff

		for (int i = 0; i < contours.size(); i++) {
			bbox.add(Imgproc.boundingRect(contours.get(i)));
		}
		return bbox;
	}

	public double getYaw(Rect upper_tape) {
		
		double yaw;
		//This takes the width of the image in pixels and divides it by the horizontal angle,
	   //giving the number of degrees per pixel
		double deg_per_pixel= Constants.LOGITECH_RES_WIDTH / Constants.LOGITECH_HORIZ_ANGLE;
		double center_col = (Constants.LOGITECH_RES_WIDTH - 1) / 2; //Center column of image, -1 is to account for 
		//0 indexing
		
			int contour_x= upper_tape.x;//column of center of contour (tape) 
			yaw=(contour_x - center_col) * deg_per_pixel;
			//(contour_x - center_col) is the horizontal distance between 
			//the center of the image and the center of the target in pixels, so 
			//you multiply that by the degrees/pixel conversion factor to get degrees
		
		return yaw;	
		
		//Unless the image given fits the Axis camera constants used, the values are probably going to be off
		}
	public double getDistance(Rect upper_tape) {
		double apparent_width=upper_tape.width;
		double horiz_distance= (Constants.UPPER_TAPE_WIDTH * Constants.LOGITECH_FOCAL_LENGTH) / apparent_width;
		return horiz_distance;

	}
	public double getAngle(double horiz_dist) {
		return (Math.toDegrees(Math.atan(Constants.TOWER_HEIGHT/horiz_dist)));
	}
	

	
	public static void main(String args[]) {
		Mat image=Imgcodecs.imread("/Users/Amit_Rajesh/Downloads/2017VisionExample/Vision Images/LED Boiler/1ftH11ftD2Angle0Brightness.jpg");
		VisionData data=new VisionData(image);
		ArrayList<MatOfPoint> final_contours;
		ArrayList<Rect> bounding_box;
		double yaw, dist, angle;
		final_contours=data.processImage();
		bounding_box=data.getBoundingBox(final_contours);
		// Sorting (We want the top tape, not the bottom one
		java.util.Collections.sort(bounding_box, new Comparator<Rect>() {
		        @Override
		        public int compare(Rect bbox1, Rect bbox2)
		        {

		            return  Double.compare(bbox1.y,bbox2.y);
		        }
		    });
		yaw=data.getYaw(bounding_box.get(0));
		dist=data.getDistance(bounding_box.get(0));
		angle=data.getAngle(dist);
		System.out.println(yaw);
		System.out.println(dist);
		System.out.println(angle);
	
}
	
}
