/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package idv.hsiehpinghan.goraassistant.entity;  
@SuppressWarnings("all")
public class MapValue extends org.apache.gora.persistency.impl.PersistentBase implements org.apache.avro.specific.SpecificRecord, org.apache.gora.persistency.Persistent {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"MapValue\",\"namespace\":\"idv.hsiehpinghan.goraassistant.entity\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"age\",\"type\":\"int\"}]}");

  /** Enum containing all data bean's fields. */
  public static enum Field {
    NAME(0, "name"),
    AGE(1, "age"),
    ;
    /**
     * Field's index.
     */
    private int index;

    /**
     * Field's name.
     */
    private String name;

    /**
     * Field's constructor
     * @param index field's index.
     * @param name field's name.
     */
    Field(int index, String name) {this.index=index;this.name=name;}

    /**
     * Gets field's index.
     * @return int field's index.
     */
    public int getIndex() {return index;}

    /**
     * Gets field's name.
     * @return String field's name.
     */
    public String getName() {return name;}

    /**
     * Gets field's attributes to string.
     * @return String field's attributes to string.
     */
    public String toString() {return name;}
  };

  public static final String[] _ALL_FIELDS = {
  "name",
  "age",
  };

  /**
   * Gets the total field count.
   * @return int field count
   */
  public int getFieldsCount() {
    return MapValue._ALL_FIELDS.length;
  }

  private java.lang.CharSequence name;
  private int age;
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return name;
    case 1: return age;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value) {
    switch (field$) {
    case 0: name = (java.lang.CharSequence)(value); break;
    case 1: age = (java.lang.Integer)(value); break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'name' field.
   */
  public java.lang.CharSequence getName() {
    return name;
  }

  /**
   * Sets the value of the 'name' field.
   * @param value the value to set.
   */
  public void setName(java.lang.CharSequence value) {
    this.name = value;
    setDirty(0);
  }
  
  /**
   * Checks the dirty status of the 'name' field. A field is dirty if it represents a change that has not yet been written to the database.
   * @param value the value to set.
   */
  public boolean isNameDirty() {
    return isDirty(0);
  }

  /**
   * Gets the value of the 'age' field.
   */
  public java.lang.Integer getAge() {
    return age;
  }

  /**
   * Sets the value of the 'age' field.
   * @param value the value to set.
   */
  public void setAge(java.lang.Integer value) {
    this.age = value;
    setDirty(1);
  }
  
  /**
   * Checks the dirty status of the 'age' field. A field is dirty if it represents a change that has not yet been written to the database.
   * @param value the value to set.
   */
  public boolean isAgeDirty() {
    return isDirty(1);
  }

  /** Creates a new MapValue RecordBuilder */
  public static idv.hsiehpinghan.goraassistant.entity.MapValue.Builder newBuilder() {
    return new idv.hsiehpinghan.goraassistant.entity.MapValue.Builder();
  }
  
  /** Creates a new MapValue RecordBuilder by copying an existing Builder */
  public static idv.hsiehpinghan.goraassistant.entity.MapValue.Builder newBuilder(idv.hsiehpinghan.goraassistant.entity.MapValue.Builder other) {
    return new idv.hsiehpinghan.goraassistant.entity.MapValue.Builder(other);
  }
  
  /** Creates a new MapValue RecordBuilder by copying an existing MapValue instance */
  public static idv.hsiehpinghan.goraassistant.entity.MapValue.Builder newBuilder(idv.hsiehpinghan.goraassistant.entity.MapValue other) {
    return new idv.hsiehpinghan.goraassistant.entity.MapValue.Builder(other);
  }
  
  private static java.nio.ByteBuffer deepCopyToReadOnlyBuffer(
      java.nio.ByteBuffer input) {
    java.nio.ByteBuffer copy = java.nio.ByteBuffer.allocate(input.capacity());
    int position = input.position();
    input.reset();
    int mark = input.position();
    int limit = input.limit();
    input.rewind();
    input.limit(input.capacity());
    copy.put(input);
    input.rewind();
    copy.rewind();
    input.position(mark);
    input.mark();
    copy.position(mark);
    copy.mark();
    input.position(position);
    copy.position(position);
    input.limit(limit);
    copy.limit(limit);
    return copy.asReadOnlyBuffer();
  }
  
  /**
   * RecordBuilder for MapValue instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<MapValue>
    implements org.apache.avro.data.RecordBuilder<MapValue> {

    private java.lang.CharSequence name;
    private int age;

    /** Creates a new Builder */
    private Builder() {
      super(idv.hsiehpinghan.goraassistant.entity.MapValue.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(idv.hsiehpinghan.goraassistant.entity.MapValue.Builder other) {
      super(other);
    }
    
    /** Creates a Builder by copying an existing MapValue instance */
    private Builder(idv.hsiehpinghan.goraassistant.entity.MapValue other) {
            super(idv.hsiehpinghan.goraassistant.entity.MapValue.SCHEMA$);
      if (isValidValue(fields()[0], other.name)) {
        this.name = (java.lang.CharSequence) data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.age)) {
        this.age = (java.lang.Integer) data().deepCopy(fields()[1].schema(), other.age);
        fieldSetFlags()[1] = true;
      }
    }

    /** Gets the value of the 'name' field */
    public java.lang.CharSequence getName() {
      return name;
    }
    
    /** Sets the value of the 'name' field */
    public idv.hsiehpinghan.goraassistant.entity.MapValue.Builder setName(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.name = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'name' field has been set */
    public boolean hasName() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'name' field */
    public idv.hsiehpinghan.goraassistant.entity.MapValue.Builder clearName() {
      name = null;
      fieldSetFlags()[0] = false;
      return this;
    }
    
    /** Gets the value of the 'age' field */
    public java.lang.Integer getAge() {
      return age;
    }
    
    /** Sets the value of the 'age' field */
    public idv.hsiehpinghan.goraassistant.entity.MapValue.Builder setAge(int value) {
      validate(fields()[1], value);
      this.age = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'age' field has been set */
    public boolean hasAge() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'age' field */
    public idv.hsiehpinghan.goraassistant.entity.MapValue.Builder clearAge() {
      fieldSetFlags()[1] = false;
      return this;
    }
    
    @Override
    public MapValue build() {
      try {
        MapValue record = new MapValue();
        record.name = fieldSetFlags()[0] ? this.name : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.age = fieldSetFlags()[1] ? this.age : (java.lang.Integer) defaultValue(fields()[1]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
  
  public MapValue.Tombstone getTombstone(){
  	return TOMBSTONE;
  }

  public MapValue newInstance(){
    return newBuilder().build();
  }

  private static final Tombstone TOMBSTONE = new Tombstone();
  
  public static final class Tombstone extends MapValue implements org.apache.gora.persistency.Tombstone {
  
      private Tombstone() { }
  
	  		  /**
	   * Gets the value of the 'name' field.
		   */
	  public java.lang.CharSequence getName() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'name' field.
		   * @param value the value to set.
	   */
	  public void setName(java.lang.CharSequence value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'name' field. A field is dirty if it represents a change that has not yet been written to the database.
		   * @param value the value to set.
	   */
	  public boolean isNameDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'age' field.
		   */
	  public java.lang.Integer getAge() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'age' field.
		   * @param value the value to set.
	   */
	  public void setAge(java.lang.Integer value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'age' field. A field is dirty if it represents a change that has not yet been written to the database.
		   * @param value the value to set.
	   */
	  public boolean isAgeDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
		  
  }
  
}

