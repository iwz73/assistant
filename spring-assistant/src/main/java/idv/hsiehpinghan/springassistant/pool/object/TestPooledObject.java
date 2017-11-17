package idv.hsiehpinghan.springassistant.pool.object;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TestPooledObject implements DisposableBean {
	private int activateCount = 0;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getActivateCount() {
		return activateCount;
	}

	public void addActivateCount() {
		++this.activateCount;
	}

	@Override
	public void destroy() throws Exception {
		System.err.println(String.format("%s destroy.", this.toString()));
	}

	@Override
	public String toString() {
		return "TestPooledObject [activateCount=" + activateCount + ", name=" + name + "]";
	}

}
