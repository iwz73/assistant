package idv.hsiehpinghan.solrassistant7.document;

import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class DefaultDocument {
	@Field
	private String id;
	@Field
	private String sku;
	@Field
	private String name;
	@Field
	private String manu;
	@Field
	private List<String> cat;
	@Field
	private List<String> features;
	@Field
	private String includes;
	@Field
	private Float weight;
	@Field
	private Float price;
	@Field
	private Integer popularity;
	@Field
	private Boolean inStock;
	@Field
	private String store;
	@Field
	private List<String> title;
	@Field
	private String subject;
	@Field
	private String description;
	@Field
	private String comments;
	@Field
	private String author;
	@Field
	private String keywords;
	@Field
	private String category;
	@Field
	private String resourcename;
	@Field
	private String url;
	@Field
	private List<String> content_type;
	@Field
	private Date last_modified;
	@Field
	private List<String> links;
	@Field
	private List<String> content;
	@Field
	private List<String> text;
	@Field
	private List<String> text_rev;
	@Field
	private String manu_exact;
	@Field
	private String payloads;
	// @Field
	// private String lang_detect_language_identifier_update_processor_factory;
	// @Field
	// private String text_mapping_char_filter_factory;
	// @Field
	// private String text_pattern_replace_char_filter_factory;
	// @Field
	// private String text_html_strip_char_filter_factory;
	// @Field
	// private String text_whitespace_tokenizer_factory;
	// @Field
	// private String text_standard_tokenizer_factory;
	// @Field
	// private String text_stop_filter_factory;
	// @Field
	// private String text_lower_case_filter_factory;
	// @Field
	// private String text_word_delimiter_filter_factory;
	// @Field
	// private String text_ascii_folding_filter_factory;
	// @Field
	// private String text_kstem_filter_factory;
	// @Field
	// private String text_synonym_filter_factory;
	// @Field
	// private String text_upper_case_token_filter_factory;
	// @Field
	// private String text_jieba_tokenizer_factory;
	// @Field
	// private String text_hanlp_tokenizer_factory;
	// @Field
	// private String text_ansj_tokenizer_factory;
	@Field
	private Integer dynamicfield_i;
	@Field
	private List<Integer> dynamicfield_is;
	@Field
	private String dynamicfield_s;
	@Field
	private List<String> dynamicfield_ss;
	@Field
	private Long dynamicfield_l;
	@Field
	private List<Long> dynamicfield_ls;
	@Field
	private String dynamicfield_t;
	@Field
	private List<String> dynamicfield_txt;
	@Field
	private List<String> dynamicfield_en;
	@Field
	private Boolean dynamicfield_b;
	@Field
	private List<Boolean> dynamicfield_bs;
	@Field
	private Float dynamicfield_f;
	@Field
	private List<Float> dynamicfield_fs;
	@Field
	private Double dynamicfield_d;
	@Field
	private List<Double> dynamicfield_ds;
	// @Field
	// private Double dynamicfield_coordinate;
	@Field
	private Date dynamicfield_dt;
	@Field
	private List<Date> dynamicfield_dts;
	@Field
	private String dynamicfield_p;
	// @Field
	// private Integer dynamicfield_ti;
	// @Field
	// private Long dynamicfield_tl;
	// @Field
	// private Float dynamicfield_tf;
	// @Field
	// private Double dynamicfield_td;
	// @Field
	// private Date dynamicfield_tdt;
	@Field
	private Object ignored_dynamicfield;
	@Field
	private List<String> attr_dynamicfield;

	// public DefaultDocument() {
	// super();
	// }

	public DefaultDocument(String id, String sku, String name, String manu, List<String> cat, List<String> features,
			String includes, Float weight, Float price, Integer popularity, Boolean inStock, String store,
			List<String> title, String subject, String description, String comments, String author, String keywords,
			String category, String resourcename, String url, List<String> content_type, Date last_modified,
			List<String> links, List<String> content, List<String> text, List<String> text_rev, String manu_exact,
			String payloads, Integer dynamicfield_i, List<Integer> dynamicfield_is, String dynamicfield_s,
			List<String> dynamicfield_ss, Long dynamicfield_l, List<Long> dynamicfield_ls, String dynamicfield_t,
			List<String> dynamicfield_txt, List<String> dynamicfield_en, Boolean dynamicfield_b,
			List<Boolean> dynamicfield_bs, Float dynamicfield_f, List<Float> dynamicfield_fs, Double dynamicfield_d,
			List<Double> dynamicfield_ds, Date dynamicfield_dt, List<Date> dynamicfield_dts, String dynamicfield_p,
			Object ignored_dynamicfield, List<String> attr_dynamicfield) {
		super();
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.manu = manu;
		this.cat = cat;
		this.features = features;
		this.includes = includes;
		this.weight = weight;
		this.price = price;
		this.popularity = popularity;
		this.inStock = inStock;
		this.store = store;
		this.title = title;
		this.subject = subject;
		this.description = description;
		this.comments = comments;
		this.author = author;
		this.keywords = keywords;
		this.category = category;
		this.resourcename = resourcename;
		this.url = url;
		this.content_type = content_type;
		this.last_modified = last_modified;
		this.links = links;
		this.content = content;
		this.text = text;
		this.text_rev = text_rev;
		this.manu_exact = manu_exact;
		this.payloads = payloads;
		this.dynamicfield_i = dynamicfield_i;
		this.dynamicfield_is = dynamicfield_is;
		this.dynamicfield_s = dynamicfield_s;
		this.dynamicfield_ss = dynamicfield_ss;
		this.dynamicfield_l = dynamicfield_l;
		this.dynamicfield_ls = dynamicfield_ls;
		this.dynamicfield_t = dynamicfield_t;
		this.dynamicfield_txt = dynamicfield_txt;
		this.dynamicfield_en = dynamicfield_en;
		this.dynamicfield_b = dynamicfield_b;
		this.dynamicfield_bs = dynamicfield_bs;
		this.dynamicfield_f = dynamicfield_f;
		this.dynamicfield_fs = dynamicfield_fs;
		this.dynamicfield_d = dynamicfield_d;
		this.dynamicfield_ds = dynamicfield_ds;
		this.dynamicfield_dt = dynamicfield_dt;
		this.dynamicfield_dts = dynamicfield_dts;
		this.dynamicfield_p = dynamicfield_p;
		this.ignored_dynamicfield = ignored_dynamicfield;
		this.attr_dynamicfield = attr_dynamicfield;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
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

	public String getIncludes() {
		return includes;
	}

	public void setIncludes(String includes) {
		this.includes = includes;
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

	public List<String> getTitle() {
		return title;
	}

	public void setTitle(List<String> title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getResourcename() {
		return resourcename;
	}

	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getContent_type() {
		return content_type;
	}

	public void setContent_type(List<String> content_type) {
		this.content_type = content_type;
	}

	public Date getLast_modified() {
		return last_modified;
	}

	public void setLast_modified(Date last_modified) {
		this.last_modified = last_modified;
	}

	public List<String> getLinks() {
		return links;
	}

	public void setLinks(List<String> links) {
		this.links = links;
	}

	public List<String> getContent() {
		return content;
	}

	public void setContent(List<String> content) {
		this.content = content;
	}

	public List<String> getText() {
		return text;
	}

	public void setText(List<String> text) {
		this.text = text;
	}

	public List<String> getText_rev() {
		return text_rev;
	}

	public void setText_rev(List<String> text_rev) {
		this.text_rev = text_rev;
	}

	public String getManu_exact() {
		return manu_exact;
	}

	public void setManu_exact(String manu_exact) {
		this.manu_exact = manu_exact;
	}

	public String getPayloads() {
		return payloads;
	}

	public void setPayloads(String payloads) {
		this.payloads = payloads;
	}

	public Integer getDynamicfield_i() {
		return dynamicfield_i;
	}

	public void setDynamicfield_i(Integer dynamicfield_i) {
		this.dynamicfield_i = dynamicfield_i;
	}

	public List<Integer> getDynamicfield_is() {
		return dynamicfield_is;
	}

	public void setDynamicfield_is(List<Integer> dynamicfield_is) {
		this.dynamicfield_is = dynamicfield_is;
	}

	public String getDynamicfield_s() {
		return dynamicfield_s;
	}

	public void setDynamicfield_s(String dynamicfield_s) {
		this.dynamicfield_s = dynamicfield_s;
	}

	public List<String> getDynamicfield_ss() {
		return dynamicfield_ss;
	}

	public void setDynamicfield_ss(List<String> dynamicfield_ss) {
		this.dynamicfield_ss = dynamicfield_ss;
	}

	public Long getDynamicfield_l() {
		return dynamicfield_l;
	}

	public void setDynamicfield_l(Long dynamicfield_l) {
		this.dynamicfield_l = dynamicfield_l;
	}

	public List<Long> getDynamicfield_ls() {
		return dynamicfield_ls;
	}

	public void setDynamicfield_ls(List<Long> dynamicfield_ls) {
		this.dynamicfield_ls = dynamicfield_ls;
	}

	public String getDynamicfield_t() {
		return dynamicfield_t;
	}

	public void setDynamicfield_t(String dynamicfield_t) {
		this.dynamicfield_t = dynamicfield_t;
	}

	public List<String> getDynamicfield_txt() {
		return dynamicfield_txt;
	}

	public void setDynamicfield_txt(List<String> dynamicfield_txt) {
		this.dynamicfield_txt = dynamicfield_txt;
	}

	public List<String> getDynamicfield_en() {
		return dynamicfield_en;
	}

	public void setDynamicfield_en(List<String> dynamicfield_en) {
		this.dynamicfield_en = dynamicfield_en;
	}

	public Boolean getDynamicfield_b() {
		return dynamicfield_b;
	}

	public void setDynamicfield_b(Boolean dynamicfield_b) {
		this.dynamicfield_b = dynamicfield_b;
	}

	public List<Boolean> getDynamicfield_bs() {
		return dynamicfield_bs;
	}

	public void setDynamicfield_bs(List<Boolean> dynamicfield_bs) {
		this.dynamicfield_bs = dynamicfield_bs;
	}

	public Float getDynamicfield_f() {
		return dynamicfield_f;
	}

	public void setDynamicfield_f(Float dynamicfield_f) {
		this.dynamicfield_f = dynamicfield_f;
	}

	public List<Float> getDynamicfield_fs() {
		return dynamicfield_fs;
	}

	public void setDynamicfield_fs(List<Float> dynamicfield_fs) {
		this.dynamicfield_fs = dynamicfield_fs;
	}

	public Double getDynamicfield_d() {
		return dynamicfield_d;
	}

	public void setDynamicfield_d(Double dynamicfield_d) {
		this.dynamicfield_d = dynamicfield_d;
	}

	public List<Double> getDynamicfield_ds() {
		return dynamicfield_ds;
	}

	public void setDynamicfield_ds(List<Double> dynamicfield_ds) {
		this.dynamicfield_ds = dynamicfield_ds;
	}

	public Date getDynamicfield_dt() {
		return dynamicfield_dt;
	}

	public void setDynamicfield_dt(Date dynamicfield_dt) {
		this.dynamicfield_dt = dynamicfield_dt;
	}

	public List<Date> getDynamicfield_dts() {
		return dynamicfield_dts;
	}

	public void setDynamicfield_dts(List<Date> dynamicfield_dts) {
		this.dynamicfield_dts = dynamicfield_dts;
	}

	public String getDynamicfield_p() {
		return dynamicfield_p;
	}

	public void setDynamicfield_p(String dynamicfield_p) {
		this.dynamicfield_p = dynamicfield_p;
	}

	public Object getIgnored_dynamicfield() {
		return ignored_dynamicfield;
	}

	public void setIgnored_dynamicfield(Object ignored_dynamicfield) {
		this.ignored_dynamicfield = ignored_dynamicfield;
	}

	public List<String> getAttr_dynamicfield() {
		return attr_dynamicfield;
	}

	public void setAttr_dynamicfield(List<String> attr_dynamicfield) {
		this.attr_dynamicfield = attr_dynamicfield;
	}

	@Override
	public String toString() {
		return "DefaultDocument [id=" + id + ", sku=" + sku + ", name=" + name + ", manu=" + manu + ", cat=" + cat
				+ ", features=" + features + ", includes=" + includes + ", weight=" + weight + ", price=" + price
				+ ", popularity=" + popularity + ", inStock=" + inStock + ", store=" + store + ", title=" + title
				+ ", subject=" + subject + ", description=" + description + ", comments=" + comments + ", author="
				+ author + ", keywords=" + keywords + ", category=" + category + ", resourcename=" + resourcename
				+ ", url=" + url + ", content_type=" + content_type + ", last_modified=" + last_modified + ", links="
				+ links + ", content=" + content + ", text=" + text + ", text_rev=" + text_rev + ", manu_exact="
				+ manu_exact + ", payloads=" + payloads + ", dynamicfield_i=" + dynamicfield_i + ", dynamicfield_is="
				+ dynamicfield_is + ", dynamicfield_s=" + dynamicfield_s + ", dynamicfield_ss=" + dynamicfield_ss
				+ ", dynamicfield_l=" + dynamicfield_l + ", dynamicfield_ls=" + dynamicfield_ls + ", dynamicfield_t="
				+ dynamicfield_t + ", dynamicfield_txt=" + dynamicfield_txt + ", dynamicfield_en=" + dynamicfield_en
				+ ", dynamicfield_b=" + dynamicfield_b + ", dynamicfield_bs=" + dynamicfield_bs + ", dynamicfield_f="
				+ dynamicfield_f + ", dynamicfield_fs=" + dynamicfield_fs + ", dynamicfield_d=" + dynamicfield_d
				+ ", dynamicfield_ds=" + dynamicfield_ds + ", dynamicfield_dt=" + dynamicfield_dt
				+ ", dynamicfield_dts=" + dynamicfield_dts + ", dynamicfield_p=" + dynamicfield_p
				+ ", ignored_dynamicfield=" + ignored_dynamicfield + ", attr_dynamicfield=" + attr_dynamicfield + "]";
	}

}
