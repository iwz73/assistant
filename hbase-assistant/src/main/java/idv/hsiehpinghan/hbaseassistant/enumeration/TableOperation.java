package idv.hsiehpinghan.hbaseassistant.enumeration;

public enum TableOperation {
	/**
	 * Drop and create table.
	 */
	DROP_CREATE,
	/**
	 * If table not exists, create it. 
	 */
	ADD_NONEXISTS
}
