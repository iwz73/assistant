package idv.hsiehpinghan.neo4jassistant.node;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.neo4j.driver.v1.types.IsoDuration;
import org.neo4j.driver.v1.types.Point;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

@NodeEntity
public class Node_0_0 {
	
	https://www.javadoc.io/doc/org.neo4j/neo4j-ogm-core/3.1.4
		https://neo4j.com/docs/ogm-manual/current/reference/
		
	@Id
	@GeneratedValue
	private Long id;
	private String null_;
	private List<String> list;
	// private Map<String, Integer> map;
	private Boolean boolean_;
	private Long long_;
	private Double double_;
	private String string;
	private byte[] bytes;
	private Date date;
	@DateLong
	private Date dateLong;
	private Instant instant;
	@DateLong
	private Instant instantLong;
	private LocalDate localDate;
	private OffsetTime offsetTime;
	private LocalTime localTime;
	private ZonedDateTime zonedDateTime;
	private LocalDateTime localDateTime;
	private IsoDuration isoDuration;
	private Point point;

	public Node_0_0() {
		super();
	}

	

	public Node_0_0(String null_, List<String> list, Boolean boolean_, Long long_, Double double_, String string,
			byte[] bytes, Date dateLong, Date date, LocalDate localDate, OffsetTime offsetTime, LocalTime localTime,
			ZonedDateTime zonedDateTime, LocalDateTime localDateTime, IsoDuration isoDuration, Point point) {
		super();
		this.null_ = null_;
		this.list = list;
		this.boolean_ = boolean_;
		this.long_ = long_;
		this.double_ = double_;
		this.string = string;
		this.bytes = bytes;
		this.dateLong = dateLong;
		this.date = date;
		this.localDate = localDate;
		this.offsetTime = offsetTime;
		this.localTime = localTime;
		this.zonedDateTime = zonedDateTime;
		this.localDateTime = localDateTime;
		this.isoDuration = isoDuration;
		this.point = point;
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
		Node_0_0 other = (Node_0_0) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node_0_0 [id=" + id + ", null_=" + null_ + ", list=" + list + ", boolean_=" + boolean_ + ", long_="
				+ long_ + ", double_=" + double_ + ", string=" + string + ", bytes=" + Arrays.toString(bytes)
				+ ", localDate=" + localDate + ", offsetTime=" + offsetTime + ", localTime=" + localTime
				+ ", zonedDateTime=" + zonedDateTime + ", localDateTime=" + localDateTime + ", isoDuration="
				+ isoDuration + ", point=" + point + "]";
	}

	
}
