package model;

public class ProductDetail {

	String category;
	String description;
	float discount_percentage;
	int id;
	String image;
	float price;
	Rating rating;
	String title;
	float weight_in_grams;

	@Override
	public String toString() {
		return "ProductDetail [category=" + category + ", description=" + description + ", discount_percentage="
				+ discount_percentage + ", id=" + id + ", image=" + image + ", price=" + price + ", rating=" + rating
				+ ", title=" + title + ", weight_in_grams=" + weight_in_grams + "]";
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getDiscount_percentage() {
		return discount_percentage;
	}

	public void setDiscount_percentage(float discount_percentage) {
		this.discount_percentage = discount_percentage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getWeight_in_grams() {
		return weight_in_grams;
	}

	public void setWeight_in_grams(float weight_in_grams) {
		this.weight_in_grams = weight_in_grams;
	}

}
