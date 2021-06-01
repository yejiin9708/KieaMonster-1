package org.tain.config.mybatis;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MybatisConfig {

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		
		bean.setDataSource(dataSource);
		bean.setConfigLocation(resolver.getResource("classpath:mybatis/_mybatis_config.xml"));
		bean.setMapperLocations(resolver.getResources("classpath:mybatis/mappers/**/*Mapper.xml"));
		bean.setTypeAliasesPackage("org.tain.mybatis.models");
		bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);  // Camel 적용
		bean.getObject().getConfiguration().setCallSettersOnNulls(true);  // 쿼리결과 필드가 null인 경우, 누락이 되서 나오는데 누락이 안되게 하는 설정
		bean.getObject().getConfiguration().setJdbcTypeForNull(JdbcType.NULL);  // 쿼리에 보내는 파라메터가 null 인 경우, 오류 발생하는 것을 방지.
		bean.getObject().getConfiguration().setCacheEnabled(true);
		
		return bean.getObject();
	}
}
