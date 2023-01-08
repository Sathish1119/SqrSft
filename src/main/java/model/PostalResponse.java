package model;

public class PostalResponse {

	float distance_in_kilometers;
	int status;
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public float getDistance_in_kilometers() {
		return distance_in_kilometers;
	}

	public void setDistance_in_kilometers(float distance_in_kilometers) {
		this.distance_in_kilometers = distance_in_kilometers;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public PostalResponse() {

	}

}
