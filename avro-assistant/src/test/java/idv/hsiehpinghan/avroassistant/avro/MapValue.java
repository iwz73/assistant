/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package idv.hsiehpinghan.avroassistant.avro;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class MapValue extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"MapValue\",\"namespace\":\"idv.hsiehpinghan.avroassistant.avro\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"age\",\"type\":\"int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
   private java.lang.CharSequence name;
   private int age;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public MapValue() {}

  /**
   * All-args constructor.
   */
  public MapValue(java.lang.CharSequence name, java.lang.Integer age) {
    this.name = name;
    this.age = age;
  }

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
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: name = (java.lang.CharSequence)value$; break;
    case 1: age = (java.lang.Integer)value$; break;
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
  }

  /** Creates a new MapValue RecordBuilder */
  public static idv.hsiehpinghan.avroassistant.avro.MapValue.Builder newBuilder() {
    return new idv.hsiehpinghan.avroassistant.avro.MapValue.Builder();
  }
  
  /** Creates a new MapValue RecordBuilder by copying an existing Builder */
  public static idv.hsiehpinghan.avroassistant.avro.MapValue.Builder newBuilder(idv.hsiehpinghan.avroassistant.avro.MapValue.Builder other) {
    return new idv.hsiehpinghan.avroassistant.avro.MapValue.Builder(other);
  }
  
  /** Creates a new MapValue RecordBuilder by copying an existing MapValue instance */
  public static idv.hsiehpinghan.avroassistant.avro.MapValue.Builder newBuilder(idv.hsiehpinghan.avroassistant.avro.MapValue other) {
    return new idv.hsiehpinghan.avroassistant.avro.MapValue.Builder(other);
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
      super(idv.hsiehpinghan.avroassistant.avro.MapValue.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(idv.hsiehpinghan.avroassistant.avro.MapValue.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.age)) {
        this.age = data().deepCopy(fields()[1].schema(), other.age);
        fieldSetFlags()[1] = true;
      }
    }
    
    /** Creates a Builder by copying an existing MapValue instance */
    private Builder(idv.hsiehpinghan.avroassistant.avro.MapValue other) {
            super(idv.hsiehpinghan.avroassistant.avro.MapValue.SCHEMA$);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.age)) {
        this.age = data().deepCopy(fields()[1].schema(), other.age);
        fieldSetFlags()[1] = true;
      }
    }

    /** Gets the value of the 'name' field */
    public java.lang.CharSequence getName() {
      return name;
    }
    
    /** Sets the value of the 'name' field */
    public idv.hsiehpinghan.avroassistant.avro.MapValue.Builder setName(java.lang.CharSequence value) {
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
    public idv.hsiehpinghan.avroassistant.avro.MapValue.Builder clearName() {
      name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'age' field */
    public java.lang.Integer getAge() {
      return age;
    }
    
    /** Sets the value of the 'age' field */
    public idv.hsiehpinghan.avroassistant.avro.MapValue.Builder setAge(int value) {
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
    public idv.hsiehpinghan.avroassistant.avro.MapValue.Builder clearAge() {
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
}
