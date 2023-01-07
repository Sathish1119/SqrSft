package model;

public class Rating {
	int count;
	float rate;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "Rating [count=" + count + ", rate=" + rate + "]";
	}
}
