package idv.hsiehpinghan.kafkaassistant2.vo;

import java.util.List;

public class JsonVo {
	private String customer;
	private List<String> products;
	private Float bill;

	public JsonVo(String customer, List<String> products, Float bill) {
		super();
		this.customer = customer;
		this.products = products;
		this.bill = bill;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public List<String> getProducts() {
		return products;
	}

	public void setProducts(List<String> products) {
		this.products = products;
	}

	public Float getBill() {
		return bill;
	}

	public void setBill(Float bill) {
		this.bill = bill;
	}

}