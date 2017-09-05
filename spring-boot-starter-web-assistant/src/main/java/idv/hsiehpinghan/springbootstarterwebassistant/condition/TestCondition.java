package idv.hsiehpinghan.springbootstarterwebassistant.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class TestCondition implements Condition {
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		try {
			context.getClassLoader().loadClass("org.springframework.jdbc.core.JdbcTemplate");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
