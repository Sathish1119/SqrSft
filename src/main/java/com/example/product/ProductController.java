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

import com.example.product.utils.EComUtils;

import model.PostalCode;
import model.PostalResponse;
import model.ProductDetailResponse;
import model.ProductId;

@RestController
public class ProductController {

	List<Integer> lst = new ArrayList<>();
	int postalCode = 0;

	@Value("${productIdUrl}")
	String productDetailUrl;

	@Autowired
	EComUtils eComUtils;

	@GetMapping("/cart/items")
	public List<Object> getProduct() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ProductDetailResponse> response = null;
		List<Object> cartItems = new ArrayList<>();

		for (int i = 0; i < lst.size(); i++) {
			response = restTemplate.getForEntity(productDetailUrl + lst.get(i), ProductDetailResponse.class);
			cartItems.add(response.getBody().response.toString());
		}

		return cartItems;

	}

	@PostMapping("/cart/item")
	public ResponseEntity<String> addProduct(@RequestBody ProductId id) {
		System.out.println(id.getId());

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ProductDetailResponse> response = restTemplate.getForEntity(productDetailUrl + id.getId(),
				ProductDetailResponse.class);

		if (response.getBody().getStatus() != HttpStatus.OK.value()) {
			System.out.println(response.getBody());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getBody().toString());
		} else {
			lst.add(id.getId());

			return ResponseEntity.status(HttpStatus.OK)
					.body("Product Added in cart successfully and size is" + lst.size());

		}

	}

	@GetMapping("/cart/checkout-value")
	public Float cartCheckoutValue() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ProductDetailResponse> response = null;
		ResponseEntity<PostalResponse> postalResponse = null;
		float finalPrice = 0;
		float totalWeight = 0;
		float distance = 0;

		for (int i = 0; i < lst.size(); i++) {
			response = restTemplate.getForEntity(productDetailUrl + lst.get(i), ProductDetailResponse.class);
			postalResponse = restTemplate.getForEntity(
					"http://15.206.157.204:8080/warehouse/distance?postal_code="+postalCode, PostalResponse.class);

			distance = postalResponse.getBody().getDistance_in_kilometers();

			totalWeight += totalWeight + response.getBody().getResponse().getWeight_in_grams() / 1000;
			finalPrice += eComUtils.getDiscountPrice(response.getBody().response.getPrice(),
					response.getBody().response.getDiscount_percentage());

		}

		if (finalPrice > 0)
			finalPrice += eComUtils.getDistWeightPrice(distance, totalWeight);

		return finalPrice;

	}

	@DeleteMapping("/cart")
	public void deleteCartItems() {
		lst = new ArrayList<>();
		postalCode = 0;

	}
	
	@PostMapping("/postal")
	public void PostalCode(@RequestBody PostalCode postcode)
	{
		postalCode = postcode.getId();
	}

}
