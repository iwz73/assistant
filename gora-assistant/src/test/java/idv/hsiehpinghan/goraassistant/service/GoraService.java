package idv.hsiehpinghan.goraassistant.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.gora.query.Result;
import org.apache.gora.store.DataStore;
import org.apache.gora.store.DataStoreFactory;
import org.apache.gora.util.GoraException;
import org.apache.hadoop.conf.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.hsiehpinghan.goraassistant.entity.ArrayItem;
import idv.hsiehpinghan.goraassistant.entity.Gora;
import idv.hsiehpinghan.goraassistant.entity.NestedRecord;
import idv.hsiehpinghan.goraassistant.repository.GoraRepository;
import idv.hsiehpinghan.goraassistant.vo.ArrayItemVo;
import idv.hsiehpinghan.goraassistant.vo.GoraVo;
import idv.hsiehpinghan.goraassistant.vo.NestedRecordVo;

@Service
public class GoraService {
	@Autowired
	private Configuration configuration;
	@Autowired
	private GoraRepository repository;

	public void put(String key, Gora entity) throws GoraException {
		DataStore<String, Gora> dataStore = null;
		try {
			dataStore = getDataStore();
			repository.put(dataStore, key, entity);
		} finally {
			if (dataStore != null) {
				dataStore.flush();
				dataStore.close();
			}
		}
	}

	public GoraVo get(String key) throws GoraException {
		DataStore<String, Gora> dataStore = null;
		try {
			dataStore = getDataStore();
			Gora result = repository.get(dataStore, key);
			if (result == null) {
				return null;
			}
			return convertGoraToGoraVo(result);
		} finally {
			if (dataStore != null) {
				dataStore.flush();
				dataStore.close();
			}
		}
	}

	public Map<String, GoraVo> query(String key) throws Exception {
		DataStore<String, Gora> dataStore = null;
		Result<String, Gora> result = null;
		try {
			dataStore = getDataStore();
			result = repository.query(dataStore, key);
			return convertResultToMap(result);
		} finally {
			if (result != null) {
				result.close();
			}
			if (dataStore != null) {
				dataStore.flush();
				dataStore.close();
			}
		}
	}

	public Map<String, GoraVo> query(String startKey, long limit) throws Exception {
		DataStore<String, Gora> dataStore = null;
		Result<String, Gora> result = null;
		try {
			dataStore = getDataStore();
			result = repository.query(dataStore, startKey, limit);
			return convertResultToMap(result);
		} finally {
			if (result != null) {
				result.close();
			}
			if (dataStore != null) {
				dataStore.flush();
				dataStore.close();
			}
		}
	}

	public Map<String, GoraVo> query(String key, String... fields) throws Exception {
		DataStore<String, Gora> dataStore = null;
		Result<String, Gora> result = null;
		try {
			dataStore = getDataStore();
			result = repository.query(dataStore, key, fields);
			return convertResultToMap(result);
		} finally {
			if (result != null) {
				result.close();
			}
			if (dataStore != null) {
				dataStore.flush();
				dataStore.close();
			}
		}
	}

	public boolean delete(String key) throws GoraException {
		DataStore<String, Gora> dataStore = null;
		try {
			dataStore = getDataStore();
			return repository.delete(dataStore, key);
		} finally {
			if (dataStore != null) {
				dataStore.flush();
				dataStore.close();
			}
		}
	}

	public boolean exist(String key) throws GoraException, IOException, Exception {
		DataStore<String, Gora> dataStore = null;
		try {
			dataStore = getDataStore();
			return repository.exist(dataStore, key);
		} finally {
			if (dataStore != null) {
				dataStore.flush();
				dataStore.close();
			}
		}
	}

	private Map<String, GoraVo> convertResultToMap(Result<String, Gora> result) throws Exception {
		Map<String, GoraVo> map = new HashMap<>();
		while (result.next()) {
			String key = result.getKey();
			GoraVo value = convertGoraToGoraVo(result.get());
			map.put(key, value);
		}
		return map;
	}

	private GoraVo convertGoraToGoraVo(Gora gora) {
		GoraVo vo = new GoraVo();
		vo.setBytes$1(gora.getBytes$1());
		vo.setBoolean$1(gora.getBoolean$1());
		vo.setInt$1(gora.getInt$1());
		vo.setLong$1(gora.getLong$1());
		vo.setFloat$1(gora.getFloat$1());
		vo.setDouble$1(gora.getDouble$1());
		vo.setString$1(gora.getString$1());
		NestedRecordVo recordVo = null;
		if (gora.getRecord$1() != null) {
			recordVo = convertNestedRecordToNestedRecordVo(gora.getRecord$1());
		}
		vo.setRecord$1(recordVo);
		vo.setEnum$1(gora.getEnum$1());
		List<ArrayItemVo> arrayItemVos = null;
		if (gora.getArray$1() != null) {
			arrayItemVos = convertArrayItemsToArrayItemVos(gora.getArray$1());
		}
		vo.setArray$1(arrayItemVos);
		vo.setMap$1(gora.getMap$1());
		return vo;
	}

	private NestedRecordVo convertNestedRecordToNestedRecordVo(NestedRecord nestedRecord) {
		NestedRecordVo vo = new NestedRecordVo();
		vo.setBoolean$1(nestedRecord.getBoolean$1());
		vo.setInt$1(nestedRecord.getInt$1());
		return vo;
	}

	private List<ArrayItemVo> convertArrayItemsToArrayItemVos(List<ArrayItem> items) {
		List<ArrayItemVo> vos = new ArrayList<ArrayItemVo>(items.size());
		for (ArrayItem item : items) {
			vos.add(convertArrayItemToArrayItemVo(item));
		}
		return vos;
	}

	private ArrayItemVo convertArrayItemToArrayItemVo(ArrayItem item) {
		ArrayItemVo vo = new ArrayItemVo();
		vo.setId(item.getId());
		vo.setName(item.getName());
		return vo;
	}

	private DataStore<String, Gora> getDataStore() throws GoraException {
		return DataStoreFactory.getDataStore(String.class, Gora.class, configuration);
	}
}
