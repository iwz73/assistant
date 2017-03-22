package idv.hsiehpinghan.hibernatesearchormassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.AnalyzerDefs;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

@Entity
@Indexed
@AnalyzerDefs({
		 @AnalyzerDef(name = "standardAnalyzer", 
				 tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), 
				 filters = {
						 @TokenFilterDef(factory = LowerCaseFilterFactory.class),
						 @TokenFilterDef(
								 factory = StopFilterFactory.class, 
								 params = {
										 @Parameter(name = "words", value = "stopwords_en.txt"),
										 @Parameter(name = "ignoreCase", value = "true") 
								 }
						 ) 
				 }
		 ) 
})
//		@AnalyzerDef(name = "ansjAnalyzer", tokenizer = @TokenizerDef(factory = AnsjTokenizerFactory.class, params = {
//				@Parameter(name = "type", value = "index_ansj") })) })
@Analyzer(definition = "standardAnalyzer")
public class AnalyzerEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@DocumentId
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	@Field
	private String englishString;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEnglishString() {
		return englishString;
	}

	public void setEnglishString(String englishString) {
		this.englishString = englishString;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnalyzerEntity other = (AnalyzerEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
