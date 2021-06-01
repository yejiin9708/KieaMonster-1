package org.tain.mybatis.models;

import java.util.HashMap;

import org.springframework.jdbc.support.JdbcUtils;

@SuppressWarnings("rawtypes")
public class LowerMap extends HashMap {

	private static final long serialVersionUID = 0L;
	
	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return super.put(JdbcUtils.convertUnderscoreNameToPropertyName((String) key), value);
	}
}
