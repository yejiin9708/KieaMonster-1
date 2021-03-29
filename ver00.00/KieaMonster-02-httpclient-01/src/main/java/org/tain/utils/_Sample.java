package org.tain.utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class _Sample {

	public static Map<?,?> getMap() {
		Map<String,Object> map = new HashMap<>();
		map.put("name", "Kiea 강석");
		map.put("date", LocalDateTime.now());
		map.put("message", "Hello, world!!!");
		map.put("age", 25);
		map.put("money", 123456789012345L);
		map.put("isAlive", true);
		return map;
	}
}
