/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package idv.hsiehpinghan.goraassistant.entity;  
@SuppressWarnings("all")
public class ArrayItem extends org.apache.gora.persistency.impl.PersistentBase implements org.apache.avro.specific.SpecificRecord, org.apache.gora.persistency.Persistent {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"ArrayItem\",\"namespace\":\"idv.hsiehpinghan.goraassistant.entity\",\"fields\":[{\"name\":\"id\",\"type\":\"long\",\"doc\":\"ArrayItem id doc\",\"default\":0,\"order\":\"ignore\"},{\"name\":\"name\",\"type\":[\"null\",\"string\"],\"doc\":\"ArrayItem name doc\",\"default\":null,\"order\":\"ignore\"}]}");

  /** Enum containing all data bean's fields. */
  public static enum Field {
    ID(0, "id"),
    NAME(1, "name"),
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
  "id",
  "name",
  };

  /**
   * Gets the total field count.
   * @return int field count
   */
  public int getFieldsCount() {
    return ArrayItem._ALL_FIELDS.length;
  }

  /** ArrayItem id doc */
  private long id;
  /** ArrayItem name doc */
  private java.lang.CharSequence name;
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return name;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value) {
    switch (field$) {
    case 0: id = (java.lang.Long)(value); break;
    case 1: name = (java.lang.CharSequence)(value); break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * ArrayItem id doc   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * ArrayItem id doc   * @param value the value to set.
   */
  public void setId(java.lang.Long value) {
    this.id = value;
    setDirty(0);
  }
  
  /**
   * Checks the dirty status of the 'id' field. A field is dirty if it represents a change that has not yet been written to the database.
   * ArrayItem id doc   * @param value the value to set.
   */
  public boolean isIdDirty() {
    return isDirty(0);
  }

  /**
   * Gets the value of the 'name' field.
   * ArrayItem name doc   */
  public java.lang.CharSequence getName() {
    return name;
  }

  /**
   * Sets the value of the 'name' field.
   * ArrayItem name doc   * @param value the value to set.
   */
  public void setName(java.lang.CharSequence value) {
    this.name = value;
    setDirty(1);
  }
  
  /**
   * Checks the dirty status of the 'name' field. A field is dirty if it represents a change that has not yet been written to the database.
   * ArrayItem name doc   * @param value the value to set.
   */
  public boolean isNameDirty() {
    return isDirty(1);
  }

  /** Creates a new ArrayItem RecordBuilder */
  public static idv.hsiehpinghan.goraassistant.entity.ArrayItem.Builder newBuilder() {
    return new idv.hsiehpinghan.goraassistant.entity.ArrayItem.Builder();
  }
  
  /** Creates a new ArrayItem RecordBuilder by copying an existing Builder */
  public static idv.hsiehpinghan.goraassistant.entity.ArrayItem.Builder newBuilder(idv.hsiehpinghan.goraassistant.entity.ArrayItem.Builder other) {
    return new idv.hsiehpinghan.goraassistant.entity.ArrayItem.Builder(other);
  }
  
  /** Creates a new ArrayItem RecordBuilder by copying an existing ArrayItem instance */
  public static idv.hsiehpinghan.goraassistant.entity.ArrayItem.Builder newBuilder(idv.hsiehpinghan.goraassistant.entity.ArrayItem other) {
    return new idv.hsiehpinghan.goraassistant.entity.ArrayItem.Builder(other);
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
   * RecordBuilder for ArrayItem instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<ArrayItem>
    implements org.apache.avro.data.RecordBuilder<ArrayItem> {

    private long id;
    private java.lang.CharSequence name;

    /** Creates a new Builder */
    private Builder() {
      super(idv.hsiehpinghan.goraassistant.entity.ArrayItem.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(idv.hsiehpinghan.goraassistant.entity.ArrayItem.Builder other) {
      super(other);
    }
    
    /** Creates a Builder by copying an existing ArrayItem instance */
    private Builder(idv.hsiehpinghan.goraassistant.entity.ArrayItem other) {
            super(idv.hsiehpinghan.goraassistant.entity.ArrayItem.SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = (java.lang.Long) data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.name)) {
        this.name = (java.lang.CharSequence) data().deepCopy(fields()[1].schema(), other.name);
        fieldSetFlags()[1] = true;
      }
    }

    /** Gets the value of the 'id' field */
    public java.lang.Long getId() {
      return id;
    }
    
    /** Sets the value of the 'id' field */
    public idv.hsiehpinghan.goraassistant.entity.ArrayItem.Builder setId(long value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'id' field has been set */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'id' field */
    public idv.hsiehpinghan.goraassistant.entity.ArrayItem.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }
    
    /** Gets the value of the 'name' field */
    public java.lang.CharSequence getName() {
      return name;
    }
    
    /** Sets the value of the 'name' field */
    public idv.hsiehpinghan.goraassistant.entity.ArrayItem.Builder setName(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.name = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'name' field has been set */
    public boolean hasName() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'name' field */
    public idv.hsiehpinghan.goraassistant.entity.ArrayItem.Builder clearName() {
      name = null;
      fieldSetFlags()[1] = false;
      return this;
    }
    
    @Override
    public ArrayItem build() {
      try {
        ArrayItem record = new ArrayItem();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Long) defaultValue(fields()[0]);
        record.name = fieldSetFlags()[1] ? this.name : (java.lang.CharSequence) defaultValue(fields()[1]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
  
  public ArrayItem.Tombstone getTombstone(){
  	return TOMBSTONE;
  }

  public ArrayItem newInstance(){
    return newBuilder().build();
  }

  private static final Tombstone TOMBSTONE = new Tombstone();
  
  public static final class Tombstone extends ArrayItem implements org.apache.gora.persistency.Tombstone {
  
      private Tombstone() { }
  
	  		  /**
	   * Gets the value of the 'id' field.
	   * ArrayItem id doc	   */
	  public java.lang.Long getId() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'id' field.
	   * ArrayItem id doc	   * @param value the value to set.
	   */
	  public void setId(java.lang.Long value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'id' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * ArrayItem id doc	   * @param value the value to set.
	   */
	  public boolean isIdDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'name' field.
	   * ArrayItem name doc	   */
	  public java.lang.CharSequence getName() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'name' field.
	   * ArrayItem name doc	   * @param value the value to set.
	   */
	  public void setName(java.lang.CharSequence value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'name' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * ArrayItem name doc	   * @param value the value to set.
	   */
	  public boolean isNameDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
		  
  }
  
}

