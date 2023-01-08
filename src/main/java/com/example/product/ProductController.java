package com.example.product;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.product.utils.DBUtilities;
import com.example.product.utils.EComUtils;
import com.example.product.utils.ListAsDB;

import model.PostalCode;
import model.PostalResponse;
import model.ProductDetailResponse;
import model.ProductId;

@RestController
public class ProductController {

	int postalCode = 0;

	DBUtilities dbutil = new ListAsDB();

	@Value("${productIdUrl}")
	String productDetailUrl;

	@Value("${postalUrl}")
	String getPostalUrl;

	@Autowired
	EComUtils eComUtils;

	@GetMapping("/cart/items")
	public List<String> getProduct() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ProductDetailResponse> response = null;
		List<String> cartItems = new ArrayList<>();

		for (int i = 0; i < dbutil.getTotalRows(); i++) {
			response = restTemplate.getForEntity(productDetailUrl + dbutil.getCartItems(i),
					ProductDetailResponse.class);
			cartItems.add(response.getBody().response.toString());
		}

		return cartItems;

	}

	@PostMapping("/cart/item")
	public ResponseEntity<String> addProduct(@RequestBody ProductId id) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ProductDetailResponse> response = restTemplate.getForEntity(productDetailUrl + id.getId(),
				ProductDetailResponse.class);

		if (response.getBody().getStatus() != HttpStatus.OK.value()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getBody().getMessage());
		} else {
			dbutil.addToCard(id.getId());

			return ResponseEntity.status(HttpStatus.OK)
					.body("Product Added in cart successfully and size is " + dbutil.getTotalRows());

		}

	}

	@GetMapping("/cart/checkout-value")
	public Object cartCheckoutValue(@RequestBody PostalCode postcode) {
		postalCode = postcode.getPostal_code();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ProductDetailResponse> response = null;
		ResponseEntity<PostalResponse> postalResponse = null;
		float finalPrice = 0;
		float totalWeight = 0;
		float distance = 0;

		for (int i = 0; i < dbutil.getTotalRows(); i++) {
			response = restTemplate.getForEntity(productDetailUrl + dbutil.getCartItems(i),
					ProductDetailResponse.class);

			// failure case for postal response to be added
			postalResponse = restTemplate.getForEntity(getPostalUrl + postalCode, PostalResponse.class);

			if (postalResponse.getBody().getStatus() != 200) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Issue in Postal Service: " + postalResponse.getBody().getMessage());
			}

			distance = postalResponse.getBody().getDistance_in_kilometers();

			totalWeight += totalWeight + response.getBody().getResponse().getWeight_in_grams() / 1000;
			finalPrice += eComUtils.getDiscountPrice(response.getBody().response.getPrice(),
					response.getBody().response.getDiscount_percentage());

		}

		if (finalPrice > 0)
		{
			
			finalPrice += eComUtils.getDistWeightPrice(distance, totalWeight);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.OK).body("Your Cart is empty ");
		}

		return ResponseEntity.status(HttpStatus.OK).body("Your Cart Item final price is: " + finalPrice);

	}

	@DeleteMapping("/cart")
	public void deleteCartItems() {
		dbutil.deleteCartItems();
		postalCode = 0;

	}

}
