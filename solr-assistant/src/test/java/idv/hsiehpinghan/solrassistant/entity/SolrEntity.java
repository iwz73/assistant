package idv.hsiehpinghan.solrassistant.entity;

import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.repository.Score;

public class SolrEntity {
	@Id
	private String id;

	@Score
	private Float score;

	@Field
	private String name;

	@Field
	private String manu;

	@Field
	private String manu_id_s;

	@Field
	private List<String> cat;

	@Field
	private List<String> features;

	@Field
	private Float weight;

	@Field
	private Float price;

	@Field
	private String price_c;

	@Field
	private Integer popularity;

	@Field
	private Boolean inStock;

	@Field
	private String store;

	@Field
	private Date manufacturedate_dt;

	@Field
	private Long _version_;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManu() {
		return manu;
	}

	public void setManu(String manu) {
		this.manu = manu;
	}

	public String getManu_id_s() {
		return manu_id_s;
	}

	public void setManu_id_s(String manu_id_s) {
		this.manu_id_s = manu_id_s;
	}

	public List<String> getCat() {
		return cat;
	}

	public void setCat(List<String> cat) {
		this.cat = cat;
	}

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getPrice_c() {
		return price_c;
	}

	public void setPrice_c(String price_c) {
		this.price_c = price_c;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

	public Boolean getInStock() {
		return inStock;
	}

	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public Date getManufacturedate_dt() {
		return manufacturedate_dt;
	}

	public void setManufacturedate_dt(Date manufacturedate_dt) {
		this.manufacturedate_dt = manufacturedate_dt;
	}

	public Long get_version_() {
		return _version_;
	}

	public void set_version_(Long _version_) {
		this._version_ = _version_;
	}

	@Override
	public String toString() {
		return "SolrEntity [id=" + id + ", score=" + score + ", name=" + name
				+ ", manu=" + manu + ", manu_id_s=" + manu_id_s + ", cat="
				+ cat + ", features=" + features + ", weight=" + weight
				+ ", price=" + price + ", price_c=" + price_c + ", popularity="
				+ popularity + ", inStock=" + inStock + ", store=" + store
				+ ", manufacturedate_dt=" + manufacturedate_dt + ", _version_="
				+ _version_ + "]";
	}

}
