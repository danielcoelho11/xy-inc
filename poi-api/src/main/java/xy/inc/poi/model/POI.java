package xy.inc.poi.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class POI implements Serializable {
	
	private static final long serialVersionUID = -5567737108969799200L;
	
	private String name;
	private int pointX;
	private int pointY;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPointX() {
		return pointX;
	}
	
	public void setPointX(int pointX) {
		this.pointX = pointX;
	}
	
	public int getPointY() {
		return pointY;
	}
	
	public void setPointY(int pointY) {
		this.pointY = pointY;
	}		
}
