package model;

public class ProductDetailResponse {

	public ProductDetail response;
	public int status;

	public ProductDetail getResponse() {
		return response;
	}

	public void setResponse(ProductDetail response) {
		this.response = response;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProductDetailResponse [response=" + response + ", status=" + status + "]";
	}

}
