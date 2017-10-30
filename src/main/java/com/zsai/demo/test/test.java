package com.zsai.demo.test;

import java.util.HashMap;
import java.util.Map;

public class test {

	public static void test(final Map<String, Object> otherProperties) {
		if (otherProperties != null) {
			for (String property : otherProperties.keySet()) {
				System.out.println(property + " : " + otherProperties.get(property));
			}
		}
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();

		map.put("keyaa", 111111);

		test(null);
		test(map);
	}
}
