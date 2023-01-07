package com.example.product.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.product.WeightDistancePriceCalc;
import com.example.product.WeightDistancePriceCalc.DistAndWeigh;

@Component
public class EComUtils {

	@Autowired
	WeightDistancePriceCalc weightDistancePriceCalc = new WeightDistancePriceCalc();

	public EComUtils() {

	}

	public float getDistWeightPrice(float a_distance, float a_totalWeight) {

		Optional<DistAndWeigh> keySet = weightDistancePriceCalc.getDistWeightMap().keySet().stream()
				.filter(a -> ((a_distance > a.dist.from) && (a_distance < a.dist.to)))
				.filter(a -> ((a_totalWeight > a.weight.min) && (a_totalWeight < a.weight.max))).findAny();

		float price = weightDistancePriceCalc.getDistWeightMap().get(keySet.get());
		return price;
	}

	public float getDiscountPrice(float a_price, float a_discount) {

		float finalPrice = a_price - (a_price * a_discount / 100);
		return finalPrice;
	}

}
