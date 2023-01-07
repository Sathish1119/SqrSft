package com.example.product;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class WeightDistancePriceCalc {

	final HashMap<DistAndWeigh, Integer> hmDistWeight = new HashMap<>();

	public WeightDistancePriceCalc() {

		Distance dist_0_5 = new Distance(0, 5);
		Distance dist_5_20 = new Distance(5, 20);
		Distance dist_20_50 = new Distance(20, 50);
		Distance dist_50_500 = new Distance(50, 500);
		Distance dist_500_800 = new Distance(500, 800);
		Distance dist_800_max = new Distance(800, Integer.MAX_VALUE);

		Weight weigh_0_2 = new Weight(0f, 2f);
		Weight weigh_2_5 = new Weight(2.01f, 5f);
		Weight weigh_5_20 = new Weight(5.01f, 20f);
		Weight weigh_20_max = new Weight(20.01f, Float.MAX_VALUE);

		hmDistWeight.put(new DistAndWeigh(dist_0_5, weigh_0_2), 12);
		hmDistWeight.put(new DistAndWeigh(dist_5_20, weigh_0_2), 15);
		hmDistWeight.put(new DistAndWeigh(dist_20_50, weigh_0_2), 20);
		hmDistWeight.put(new DistAndWeigh(dist_50_500, weigh_0_2), 50);
		hmDistWeight.put(new DistAndWeigh(dist_500_800, weigh_0_2), 100);
		hmDistWeight.put(new DistAndWeigh(dist_800_max, weigh_0_2), 220);

		hmDistWeight.put(new DistAndWeigh(dist_0_5, weigh_2_5), 14);
		hmDistWeight.put(new DistAndWeigh(dist_5_20, weigh_2_5), 18);
		hmDistWeight.put(new DistAndWeigh(dist_20_50, weigh_2_5), 24);
		hmDistWeight.put(new DistAndWeigh(dist_50_500, weigh_2_5), 55);
		hmDistWeight.put(new DistAndWeigh(dist_500_800, weigh_2_5), 110);
		hmDistWeight.put(new DistAndWeigh(dist_800_max, weigh_2_5), 250);

		hmDistWeight.put(new DistAndWeigh(dist_0_5, weigh_5_20), 16);
		hmDistWeight.put(new DistAndWeigh(dist_5_20, weigh_5_20), 25);
		hmDistWeight.put(new DistAndWeigh(dist_20_50, weigh_5_20), 30);
		hmDistWeight.put(new DistAndWeigh(dist_50_500, weigh_5_20), 80);
		hmDistWeight.put(new DistAndWeigh(dist_500_800, weigh_5_20), 130);
		hmDistWeight.put(new DistAndWeigh(dist_800_max, weigh_5_20), 270);

		hmDistWeight.put(new DistAndWeigh(dist_0_5, weigh_20_max), 21);
		hmDistWeight.put(new DistAndWeigh(dist_5_20, weigh_20_max), 35);
		hmDistWeight.put(new DistAndWeigh(dist_20_50, weigh_20_max), 50);
		hmDistWeight.put(new DistAndWeigh(dist_50_500, weigh_20_max), 90);
		hmDistWeight.put(new DistAndWeigh(dist_500_800, weigh_20_max), 150);
		hmDistWeight.put(new DistAndWeigh(dist_800_max, weigh_20_max), 300);

	}

	public HashMap<DistAndWeigh, Integer> getDistWeightMap() {
		return hmDistWeight;
	}

	public class Distance {
		public int from;
		public int to;

		public Distance(int a_from, int a_to) {
			this.from = a_from;
			this.to = a_to;
		}

	}

	public class Weight {

		public float min;
		public float max;

		public Weight(float a_min, float a_max) {
			this.min = a_min;
			this.max = a_max;
		}

	}

	public class DistAndWeigh {
		public Distance dist;
		public Weight weight;

		public DistAndWeigh(Distance a_dist, Weight a_weigh) {
			dist = a_dist;
			weight = a_weigh;
		}

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			return ((weight.min == ((DistAndWeigh) obj).weight.min) && (dist.from == ((DistAndWeigh) obj).dist.from));

		}

	}

}
