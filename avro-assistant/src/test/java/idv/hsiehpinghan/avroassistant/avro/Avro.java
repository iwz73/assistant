/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package idv.hsiehpinghan.avroassistant.avro;  
@SuppressWarnings("all")
/** Avro doc. */
@org.apache.avro.specific.AvroGenerated
public class Avro extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Avro\",\"namespace\":\"idv.hsiehpinghan.avroassistant.avro\",\"doc\":\"Avro doc.\",\"fields\":[{\"name\":\"_boolean\",\"type\":\"boolean\",\"doc\":\"boolean doc\",\"default\":true,\"order\":\"ignore\"},{\"name\":\"_int\",\"type\":\"int\",\"doc\":\"int doc\",\"default\":1,\"order\":\"ignore\"},{\"name\":\"_long\",\"type\":\"long\",\"doc\":\"long doc\",\"default\":2,\"order\":\"ignore\"},{\"name\":\"_float\",\"type\":\"float\",\"doc\":\"float doc\",\"default\":1.1,\"order\":\"ignore\"},{\"name\":\"_double\",\"type\":\"double\",\"doc\":\"double doc\",\"default\":2.2,\"order\":\"ignore\"},{\"name\":\"_bytes\",\"type\":\"bytes\",\"doc\":\"bytes doc\",\"default\":\"A\",\"order\":\"ignore\"},{\"name\":\"_string\",\"type\":\"string\",\"doc\":\"string doc\",\"default\":\"string\",\"order\":\"ignore\"},{\"name\":\"_record\",\"type\":{\"type\":\"record\",\"name\":\"NestedRecord\",\"fields\":[{\"name\":\"_boolean\",\"type\":\"boolean\",\"doc\":\"boolean doc\",\"default\":true,\"order\":\"ignore\"},{\"name\":\"_int\",\"type\":\"int\",\"doc\":\"int doc\",\"default\":1,\"order\":\"ignore\"}]},\"doc\":\"record doc\",\"default\":{},\"order\":\"ignore\"},{\"name\":\"_enum\",\"type\":{\"type\":\"enum\",\"name\":\"Enumeration\",\"namespace\":\"idv.hsiehpinghan.avroassistant.enumeration\",\"doc\":\"Enumeration doc\",\"symbols\":[\"ENUM_0\",\"ENUM_1\",\"ENUM_2\"]},\"doc\":\"enum doc\",\"default\":\"ENUM_1\",\"order\":\"ignore\"},{\"name\":\"_array\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"ArrayItem\",\"fields\":[{\"name\":\"id\",\"type\":\"long\"},{\"name\":\"name\",\"type\":\"string\"}]}},\"doc\":\"array doc\",\"default\":[],\"order\":\"ignore\"},{\"name\":\"_map\",\"type\":{\"type\":\"map\",\"values\":{\"type\":\"record\",\"name\":\"MapValue\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"age\",\"type\":\"int\"}]}},\"doc\":\"map doc\",\"default\":{},\"order\":\"ignore\"},{\"name\":\"_unions\",\"type\":[\"string\",\"null\"],\"doc\":\"unions doc\",\"default\":\"\",\"order\":\"ignore\"},{\"name\":\"_fixed\",\"type\":{\"type\":\"fixed\",\"name\":\"Fixed\",\"size\":16},\"doc\":\"fixed doc\",\"default\":\"\\u0000\",\"order\":\"ignore\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  /** boolean doc */
   private boolean _boolean;
  /** int doc */
   private int _int;
  /** long doc */
   private long _long;
  /** float doc */
   private float _float;
  /** double doc */
   private double _double;
  /** bytes doc */
   private java.nio.ByteBuffer _bytes;
  /** string doc */
   private java.lang.CharSequence _string;
  /** record doc */
   private idv.hsiehpinghan.avroassistant.avro.NestedRecord _record;
  /** enum doc */
   private idv.hsiehpinghan.avroassistant.enumeration.Enumeration _enum;
  /** array doc */
   private java.util.List<idv.hsiehpinghan.avroassistant.avro.ArrayItem> _array;
  /** map doc */
   private java.util.Map<java.lang.CharSequence,idv.hsiehpinghan.avroassistant.avro.MapValue> _map;
  /** unions doc */
   private java.lang.CharSequence _unions;
  /** fixed doc */
   private idv.hsiehpinghan.avroassistant.avro.Fixed _fixed;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public Avro() {}

  /**
   * All-args constructor.
   */
  public Avro(java.lang.Boolean _boolean, java.lang.Integer _int, java.lang.Long _long, java.lang.Float _float, java.lang.Double _double, java.nio.ByteBuffer _bytes, java.lang.CharSequence _string, idv.hsiehpinghan.avroassistant.avro.NestedRecord _record, idv.hsiehpinghan.avroassistant.enumeration.Enumeration _enum, java.util.List<idv.hsiehpinghan.avroassistant.avro.ArrayItem> _array, java.util.Map<java.lang.CharSequence,idv.hsiehpinghan.avroassistant.avro.MapValue> _map, java.lang.CharSequence _unions, idv.hsiehpinghan.avroassistant.avro.Fixed _fixed) {
    this._boolean = _boolean;
    this._int = _int;
    this._long = _long;
    this._float = _float;
    this._double = _double;
    this._bytes = _bytes;
    this._string = _string;
    this._record = _record;
    this._enum = _enum;
    this._array = _array;
    this._map = _map;
    this._unions = _unions;
    this._fixed = _fixed;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return _boolean;
    case 1: return _int;
    case 2: return _long;
    case 3: return _float;
    case 4: return _double;
    case 5: return _bytes;
    case 6: return _string;
    case 7: return _record;
    case 8: return _enum;
    case 9: return _array;
    case 10: return _map;
    case 11: return _unions;
    case 12: return _fixed;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: _boolean = (java.lang.Boolean)value$; break;
    case 1: _int = (java.lang.Integer)value$; break;
    case 2: _long = (java.lang.Long)value$; break;
    case 3: _float = (java.lang.Float)value$; break;
    case 4: _double = (java.lang.Double)value$; break;
    case 5: _bytes = (java.nio.ByteBuffer)value$; break;
    case 6: _string = (java.lang.CharSequence)value$; break;
    case 7: _record = (idv.hsiehpinghan.avroassistant.avro.NestedRecord)value$; break;
    case 8: _enum = (idv.hsiehpinghan.avroassistant.enumeration.Enumeration)value$; break;
    case 9: _array = (java.util.List<idv.hsiehpinghan.avroassistant.avro.ArrayItem>)value$; break;
    case 10: _map = (java.util.Map<java.lang.CharSequence,idv.hsiehpinghan.avroassistant.avro.MapValue>)value$; break;
    case 11: _unions = (java.lang.CharSequence)value$; break;
    case 12: _fixed = (idv.hsiehpinghan.avroassistant.avro.Fixed)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the '_boolean' field.
   * boolean doc   */
  public java.lang.Boolean getBoolean$1() {
    return _boolean;
  }

  /**
   * Sets the value of the '_boolean' field.
   * boolean doc   * @param value the value to set.
   */
  public void setBoolean$1(java.lang.Boolean value) {
    this._boolean = value;
  }

  /**
   * Gets the value of the '_int' field.
   * int doc   */
  public java.lang.Integer getInt$1() {
    return _int;
  }

  /**
   * Sets the value of the '_int' field.
   * int doc   * @param value the value to set.
   */
  public void setInt$1(java.lang.Integer value) {
    this._int = value;
  }

  /**
   * Gets the value of the '_long' field.
   * long doc   */
  public java.lang.Long getLong$1() {
    return _long;
  }

  /**
   * Sets the value of the '_long' field.
   * long doc   * @param value the value to set.
   */
  public void setLong$1(java.lang.Long value) {
    this._long = value;
  }

  /**
   * Gets the value of the '_float' field.
   * float doc   */
  public java.lang.Float getFloat$1() {
    return _float;
  }

  /**
   * Sets the value of the '_float' field.
   * float doc   * @param value the value to set.
   */
  public void setFloat$1(java.lang.Float value) {
    this._float = value;
  }

  /**
   * Gets the value of the '_double' field.
   * double doc   */
  public java.lang.Double getDouble$1() {
    return _double;
  }

  /**
   * Sets the value of the '_double' field.
   * double doc   * @param value the value to set.
   */
  public void setDouble$1(java.lang.Double value) {
    this._double = value;
  }

  /**
   * Gets the value of the '_bytes' field.
   * bytes doc   */
  public java.nio.ByteBuffer getBytes$1() {
    return _bytes;
  }

  /**
   * Sets the value of the '_bytes' field.
   * bytes doc   * @param value the value to set.
   */
  public void setBytes$1(java.nio.ByteBuffer value) {
    this._bytes = value;
  }

  /**
   * Gets the value of the '_string' field.
   * string doc   */
  public java.lang.CharSequence getString$1() {
    return _string;
  }

  /**
   * Sets the value of the '_string' field.
   * string doc   * @param value the value to set.
   */
  public void setString$1(java.lang.CharSequence value) {
    this._string = value;
  }

  /**
   * Gets the value of the '_record' field.
   * record doc   */
  public idv.hsiehpinghan.avroassistant.avro.NestedRecord getRecord$1() {
    return _record;
  }

  /**
   * Sets the value of the '_record' field.
   * record doc   * @param value the value to set.
   */
  public void setRecord$1(idv.hsiehpinghan.avroassistant.avro.NestedRecord value) {
    this._record = value;
  }

  /**
   * Gets the value of the '_enum' field.
   * enum doc   */
  public idv.hsiehpinghan.avroassistant.enumeration.Enumeration getEnum$1() {
    return _enum;
  }

  /**
   * Sets the value of the '_enum' field.
   * enum doc   * @param value the value to set.
   */
  public void setEnum$1(idv.hsiehpinghan.avroassistant.enumeration.Enumeration value) {
    this._enum = value;
  }

  /**
   * Gets the value of the '_array' field.
   * array doc   */
  public java.util.List<idv.hsiehpinghan.avroassistant.avro.ArrayItem> getArray$1() {
    return _array;
  }

  /**
   * Sets the value of the '_array' field.
   * array doc   * @param value the value to set.
   */
  public void setArray$1(java.util.List<idv.hsiehpinghan.avroassistant.avro.ArrayItem> value) {
    this._array = value;
  }

  /**
   * Gets the value of the '_map' field.
   * map doc   */
  public java.util.Map<java.lang.CharSequence,idv.hsiehpinghan.avroassistant.avro.MapValue> getMap$1() {
    return _map;
  }

  /**
   * Sets the value of the '_map' field.
   * map doc   * @param value the value to set.
   */
  public void setMap$1(java.util.Map<java.lang.CharSequence,idv.hsiehpinghan.avroassistant.avro.MapValue> value) {
    this._map = value;
  }

  /**
   * Gets the value of the '_unions' field.
   * unions doc   */
  public java.lang.CharSequence getUnions$1() {
    return _unions;
  }

  /**
   * Sets the value of the '_unions' field.
   * unions doc   * @param value the value to set.
   */
  public void setUnions$1(java.lang.CharSequence value) {
    this._unions = value;
  }

  /**
   * Gets the value of the '_fixed' field.
   * fixed doc   */
  public idv.hsiehpinghan.avroassistant.avro.Fixed getFixed$1() {
    return _fixed;
  }

  /**
   * Sets the value of the '_fixed' field.
   * fixed doc   * @param value the value to set.
   */
  public void setFixed$1(idv.hsiehpinghan.avroassistant.avro.Fixed value) {
    this._fixed = value;
  }

  /** Creates a new Avro RecordBuilder */
  public static idv.hsiehpinghan.avroassistant.avro.Avro.Builder newBuilder() {
    return new idv.hsiehpinghan.avroassistant.avro.Avro.Builder();
  }
  
  /** Creates a new Avro RecordBuilder by copying an existing Builder */
  public static idv.hsiehpinghan.avroassistant.avro.Avro.Builder newBuilder(idv.hsiehpinghan.avroassistant.avro.Avro.Builder other) {
    return new idv.hsiehpinghan.avroassistant.avro.Avro.Builder(other);
  }
  
  /** Creates a new Avro RecordBuilder by copying an existing Avro instance */
  public static idv.hsiehpinghan.avroassistant.avro.Avro.Builder newBuilder(idv.hsiehpinghan.avroassistant.avro.Avro other) {
    return new idv.hsiehpinghan.avroassistant.avro.Avro.Builder(other);
  }
  
  /**
   * RecordBuilder for Avro instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Avro>
    implements org.apache.avro.data.RecordBuilder<Avro> {

    private boolean _boolean;
    private int _int;
    private long _long;
    private float _float;
    private double _double;
    private java.nio.ByteBuffer _bytes;
    private java.lang.CharSequence _string;
    private idv.hsiehpinghan.avroassistant.avro.NestedRecord _record;
    private idv.hsiehpinghan.avroassistant.enumeration.Enumeration _enum;
    private java.util.List<idv.hsiehpinghan.avroassistant.avro.ArrayItem> _array;
    private java.util.Map<java.lang.CharSequence,idv.hsiehpinghan.avroassistant.avro.MapValue> _map;
    private java.lang.CharSequence _unions;
    private idv.hsiehpinghan.avroassistant.avro.Fixed _fixed;

    /** Creates a new Builder */
    private Builder() {
      super(idv.hsiehpinghan.avroassistant.avro.Avro.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(idv.hsiehpinghan.avroassistant.avro.Avro.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other._boolean)) {
        this._boolean = data().deepCopy(fields()[0].schema(), other._boolean);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other._int)) {
        this._int = data().deepCopy(fields()[1].schema(), other._int);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other._long)) {
        this._long = data().deepCopy(fields()[2].schema(), other._long);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other._float)) {
        this._float = data().deepCopy(fields()[3].schema(), other._float);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other._double)) {
        this._double = data().deepCopy(fields()[4].schema(), other._double);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other._bytes)) {
        this._bytes = data().deepCopy(fields()[5].schema(), other._bytes);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other._string)) {
        this._string = data().deepCopy(fields()[6].schema(), other._string);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other._record)) {
        this._record = data().deepCopy(fields()[7].schema(), other._record);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other._enum)) {
        this._enum = data().deepCopy(fields()[8].schema(), other._enum);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other._array)) {
        this._array = data().deepCopy(fields()[9].schema(), other._array);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other._map)) {
        this._map = data().deepCopy(fields()[10].schema(), other._map);
        fieldSetFlags()[10] = true;
      }
      if (isValidValue(fields()[11], other._unions)) {
        this._unions = data().deepCopy(fields()[11].schema(), other._unions);
        fieldSetFlags()[11] = true;
      }
      if (isValidValue(fields()[12], other._fixed)) {
        this._fixed = data().deepCopy(fields()[12].schema(), other._fixed);
        fieldSetFlags()[12] = true;
      }
    }
    
    /** Creates a Builder by copying an existing Avro instance */
    private Builder(idv.hsiehpinghan.avroassistant.avro.Avro other) {
            super(idv.hsiehpinghan.avroassistant.avro.Avro.SCHEMA$);
      if (isValidValue(fields()[0], other._boolean)) {
        this._boolean = data().deepCopy(fields()[0].schema(), other._boolean);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other._int)) {
        this._int = data().deepCopy(fields()[1].schema(), other._int);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other._long)) {
        this._long = data().deepCopy(fields()[2].schema(), other._long);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other._float)) {
        this._float = data().deepCopy(fields()[3].schema(), other._float);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other._double)) {
        this._double = data().deepCopy(fields()[4].schema(), other._double);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other._bytes)) {
        this._bytes = data().deepCopy(fields()[5].schema(), other._bytes);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other._string)) {
        this._string = data().deepCopy(fields()[6].schema(), other._string);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other._record)) {
        this._record = data().deepCopy(fields()[7].schema(), other._record);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other._enum)) {
        this._enum = data().deepCopy(fields()[8].schema(), other._enum);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other._array)) {
        this._array = data().deepCopy(fields()[9].schema(), other._array);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other._map)) {
        this._map = data().deepCopy(fields()[10].schema(), other._map);
        fieldSetFlags()[10] = true;
      }
      if (isValidValue(fields()[11], other._unions)) {
        this._unions = data().deepCopy(fields()[11].schema(), other._unions);
        fieldSetFlags()[11] = true;
      }
      if (isValidValue(fields()[12], other._fixed)) {
        this._fixed = data().deepCopy(fields()[12].schema(), other._fixed);
        fieldSetFlags()[12] = true;
      }
    }

    /** Gets the value of the '_boolean' field */
    public java.lang.Boolean getBoolean$1() {
      return _boolean;
    }
    
    /** Sets the value of the '_boolean' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder setBoolean$1(boolean value) {
      validate(fields()[0], value);
      this._boolean = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the '_boolean' field has been set */
    public boolean hasBoolean$1() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the '_boolean' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder clearBoolean$1() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the '_int' field */
    public java.lang.Integer getInt$1() {
      return _int;
    }
    
    /** Sets the value of the '_int' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder setInt$1(int value) {
      validate(fields()[1], value);
      this._int = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the '_int' field has been set */
    public boolean hasInt$1() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the '_int' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder clearInt$1() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the '_long' field */
    public java.lang.Long getLong$1() {
      return _long;
    }
    
    /** Sets the value of the '_long' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder setLong$1(long value) {
      validate(fields()[2], value);
      this._long = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the '_long' field has been set */
    public boolean hasLong$1() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the '_long' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder clearLong$1() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the '_float' field */
    public java.lang.Float getFloat$1() {
      return _float;
    }
    
    /** Sets the value of the '_float' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder setFloat$1(float value) {
      validate(fields()[3], value);
      this._float = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the '_float' field has been set */
    public boolean hasFloat$1() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the '_float' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder clearFloat$1() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the '_double' field */
    public java.lang.Double getDouble$1() {
      return _double;
    }
    
    /** Sets the value of the '_double' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder setDouble$1(double value) {
      validate(fields()[4], value);
      this._double = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the '_double' field has been set */
    public boolean hasDouble$1() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the '_double' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder clearDouble$1() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the '_bytes' field */
    public java.nio.ByteBuffer getBytes$1() {
      return _bytes;
    }
    
    /** Sets the value of the '_bytes' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder setBytes$1(java.nio.ByteBuffer value) {
      validate(fields()[5], value);
      this._bytes = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the '_bytes' field has been set */
    public boolean hasBytes$1() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the '_bytes' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder clearBytes$1() {
      _bytes = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /** Gets the value of the '_string' field */
    public java.lang.CharSequence getString$1() {
      return _string;
    }
    
    /** Sets the value of the '_string' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder setString$1(java.lang.CharSequence value) {
      validate(fields()[6], value);
      this._string = value;
      fieldSetFlags()[6] = true;
      return this; 
    }
    
    /** Checks whether the '_string' field has been set */
    public boolean hasString$1() {
      return fieldSetFlags()[6];
    }
    
    /** Clears the value of the '_string' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder clearString$1() {
      _string = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /** Gets the value of the '_record' field */
    public idv.hsiehpinghan.avroassistant.avro.NestedRecord getRecord$1() {
      return _record;
    }
    
    /** Sets the value of the '_record' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder setRecord$1(idv.hsiehpinghan.avroassistant.avro.NestedRecord value) {
      validate(fields()[7], value);
      this._record = value;
      fieldSetFlags()[7] = true;
      return this; 
    }
    
    /** Checks whether the '_record' field has been set */
    public boolean hasRecord$1() {
      return fieldSetFlags()[7];
    }
    
    /** Clears the value of the '_record' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder clearRecord$1() {
      _record = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /** Gets the value of the '_enum' field */
    public idv.hsiehpinghan.avroassistant.enumeration.Enumeration getEnum$1() {
      return _enum;
    }
    
    /** Sets the value of the '_enum' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder setEnum$1(idv.hsiehpinghan.avroassistant.enumeration.Enumeration value) {
      validate(fields()[8], value);
      this._enum = value;
      fieldSetFlags()[8] = true;
      return this; 
    }
    
    /** Checks whether the '_enum' field has been set */
    public boolean hasEnum$1() {
      return fieldSetFlags()[8];
    }
    
    /** Clears the value of the '_enum' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder clearEnum$1() {
      _enum = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /** Gets the value of the '_array' field */
    public java.util.List<idv.hsiehpinghan.avroassistant.avro.ArrayItem> getArray$1() {
      return _array;
    }
    
    /** Sets the value of the '_array' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder setArray$1(java.util.List<idv.hsiehpinghan.avroassistant.avro.ArrayItem> value) {
      validate(fields()[9], value);
      this._array = value;
      fieldSetFlags()[9] = true;
      return this; 
    }
    
    /** Checks whether the '_array' field has been set */
    public boolean hasArray$1() {
      return fieldSetFlags()[9];
    }
    
    /** Clears the value of the '_array' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder clearArray$1() {
      _array = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    /** Gets the value of the '_map' field */
    public java.util.Map<java.lang.CharSequence,idv.hsiehpinghan.avroassistant.avro.MapValue> getMap$1() {
      return _map;
    }
    
    /** Sets the value of the '_map' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder setMap$1(java.util.Map<java.lang.CharSequence,idv.hsiehpinghan.avroassistant.avro.MapValue> value) {
      validate(fields()[10], value);
      this._map = value;
      fieldSetFlags()[10] = true;
      return this; 
    }
    
    /** Checks whether the '_map' field has been set */
    public boolean hasMap$1() {
      return fieldSetFlags()[10];
    }
    
    /** Clears the value of the '_map' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder clearMap$1() {
      _map = null;
      fieldSetFlags()[10] = false;
      return this;
    }

    /** Gets the value of the '_unions' field */
    public java.lang.CharSequence getUnions$1() {
      return _unions;
    }
    
    /** Sets the value of the '_unions' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder setUnions$1(java.lang.CharSequence value) {
      validate(fields()[11], value);
      this._unions = value;
      fieldSetFlags()[11] = true;
      return this; 
    }
    
    /** Checks whether the '_unions' field has been set */
    public boolean hasUnions$1() {
      return fieldSetFlags()[11];
    }
    
    /** Clears the value of the '_unions' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder clearUnions$1() {
      _unions = null;
      fieldSetFlags()[11] = false;
      return this;
    }

    /** Gets the value of the '_fixed' field */
    public idv.hsiehpinghan.avroassistant.avro.Fixed getFixed$1() {
      return _fixed;
    }
    
    /** Sets the value of the '_fixed' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder setFixed$1(idv.hsiehpinghan.avroassistant.avro.Fixed value) {
      validate(fields()[12], value);
      this._fixed = value;
      fieldSetFlags()[12] = true;
      return this; 
    }
    
    /** Checks whether the '_fixed' field has been set */
    public boolean hasFixed$1() {
      return fieldSetFlags()[12];
    }
    
    /** Clears the value of the '_fixed' field */
    public idv.hsiehpinghan.avroassistant.avro.Avro.Builder clearFixed$1() {
      _fixed = null;
      fieldSetFlags()[12] = false;
      return this;
    }

    @Override
    public Avro build() {
      try {
        Avro record = new Avro();
        record._boolean = fieldSetFlags()[0] ? this._boolean : (java.lang.Boolean) defaultValue(fields()[0]);
        record._int = fieldSetFlags()[1] ? this._int : (java.lang.Integer) defaultValue(fields()[1]);
        record._long = fieldSetFlags()[2] ? this._long : (java.lang.Long) defaultValue(fields()[2]);
        record._float = fieldSetFlags()[3] ? this._float : (java.lang.Float) defaultValue(fields()[3]);
        record._double = fieldSetFlags()[4] ? this._double : (java.lang.Double) defaultValue(fields()[4]);
        record._bytes = fieldSetFlags()[5] ? this._bytes : (java.nio.ByteBuffer) defaultValue(fields()[5]);
        record._string = fieldSetFlags()[6] ? this._string : (java.lang.CharSequence) defaultValue(fields()[6]);
        record._record = fieldSetFlags()[7] ? this._record : (idv.hsiehpinghan.avroassistant.avro.NestedRecord) defaultValue(fields()[7]);
        record._enum = fieldSetFlags()[8] ? this._enum : (idv.hsiehpinghan.avroassistant.enumeration.Enumeration) defaultValue(fields()[8]);
        record._array = fieldSetFlags()[9] ? this._array : (java.util.List<idv.hsiehpinghan.avroassistant.avro.ArrayItem>) defaultValue(fields()[9]);
        record._map = fieldSetFlags()[10] ? this._map : (java.util.Map<java.lang.CharSequence,idv.hsiehpinghan.avroassistant.avro.MapValue>) defaultValue(fields()[10]);
        record._unions = fieldSetFlags()[11] ? this._unions : (java.lang.CharSequence) defaultValue(fields()[11]);
        record._fixed = fieldSetFlags()[12] ? this._fixed : (idv.hsiehpinghan.avroassistant.avro.Fixed) defaultValue(fields()[12]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}