package idv.hsiehpinghan.neo4jassistant.node;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.v1.types.IsoDuration;
import org.neo4j.driver.v1.types.Point;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Node_0_0 {
	@Id
	private Long id;
	private String null_;
	private List<String> list;
	private Map<String, Integer> map;
	private Boolean boolean_;
	private Long long_;
	private Double double_;
	private String string;
	private byte[] bytes;
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

	public Node_0_0(Long id, String null_, List<String> list, Map<String, Integer> map, Boolean boolean_, Long long_,
			Double double_, String string, byte[] bytes, LocalDate localDate, OffsetTime offsetTime,
			LocalTime localTime, ZonedDateTime zonedDateTime, LocalDateTime localDateTime, IsoDuration isoDuration,
			Point point) {
		super();
		this.id = id;
		this.null_ = null_;
		this.list = list;
		this.map = map;
		this.boolean_ = boolean_;
		this.long_ = long_;
		this.double_ = double_;
		this.string = string;
		this.bytes = bytes;
		this.localDate = localDate;
		this.offsetTime = offsetTime;
		this.localTime = localTime;
		this.zonedDateTime = zonedDateTime;
		this.localDateTime = localDateTime;
		this.isoDuration = isoDuration;
		this.point = point;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNull_() {
		return null_;
	}

	public void setNull_(String null_) {
		this.null_ = null_;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

	public Boolean getBoolean_() {
		return boolean_;
	}

	public void setBoolean_(Boolean boolean_) {
		this.boolean_ = boolean_;
	}

	public Long getLong_() {
		return long_;
	}

	public void setLong_(Long long_) {
		this.long_ = long_;
	}

	public Double getDouble_() {
		return double_;
	}

	public void setDouble_(Double double_) {
		this.double_ = double_;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	public OffsetTime getOffsetTime() {
		return offsetTime;
	}

	public void setOffsetTime(OffsetTime offsetTime) {
		this.offsetTime = offsetTime;
	}

	public LocalTime getLocalTime() {
		return localTime;
	}

	public void setLocalTime(LocalTime localTime) {
		this.localTime = localTime;
	}

	public ZonedDateTime getZonedDateTime() {
		return zonedDateTime;
	}

	public void setZonedDateTime(ZonedDateTime zonedDateTime) {
		this.zonedDateTime = zonedDateTime;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public IsoDuration getIsoDuration() {
		return isoDuration;
	}

	public void setIsoDuration(IsoDuration isoDuration) {
		this.isoDuration = isoDuration;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
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
		return "Node_0_0 [id=" + id + ", null_=" + null_ + ", list=" + list + ", map=" + map + ", boolean_=" + boolean_
				+ ", long_=" + long_ + ", double_=" + double_ + ", string=" + string + ", bytes="
				+ Arrays.toString(bytes) + ", localDate=" + localDate + ", offsetTime=" + offsetTime + ", localTime="
				+ localTime + ", zonedDateTime=" + zonedDateTime + ", localDateTime=" + localDateTime + ", isoDuration="
				+ isoDuration + ", point=" + point + "]";
	}

}
