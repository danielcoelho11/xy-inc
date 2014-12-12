package xy.inc.poi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "POI")
@JsonIgnoreProperties(ignoreUnknown = true)
public class POI implements Serializable {

	private static final long serialVersionUID = -5567737108969799200L;

	//field names
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String POINT_X = "pointX";
	public static final String POINT_Y = "pointY";
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "POINT_X")
	private int pointX;

	@Column(name = "POINT_Y")
	private int pointY;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
