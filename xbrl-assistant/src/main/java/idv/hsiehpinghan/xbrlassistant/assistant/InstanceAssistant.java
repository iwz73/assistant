package idv.hsiehpinghan.xbrlassistant.assistant;

import idv.hsiehpinghan.xbrlassistant.cache.InstanceCache;
import idv.hsiehpinghan.xbrlassistant.enumeration.XbrlTaxonomyVersion;
import idv.hsiehpinghan.xbrlassistant.xbrl.Instance;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jcx.xbrl.data.XbrlContext;
import jcx.xbrl.data.XbrlDocument;
import jcx.xbrl.data.XbrlElement;
import jcx.xbrl.data.XbrlPeriod;
import jcx.xbrl.taxonomy.XbrlTaxonomy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class InstanceAssistant {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private InstanceCache cache;
	@Autowired
	private TaxonomyAssistant taxonomyAssistant;
	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Get instance json format of PresentationIds.
	 * 
	 * @param instanceFile
	 * @param PresentationIds
	 * @return
	 * @throws Exception
	 */
	public ObjectNode getInstanceJson(File instanceFile,
			List<String> PresentationIds) throws Exception {
		XbrlDocument document = loadXbrlDocument(instanceFile);
		XbrlTaxonomyVersion version = taxonomyAssistant
				.getXbrlTaxonomyVersion(instanceFile);
		Set<String> eleIds = taxonomyAssistant.getPresentationElementIds(
				version, PresentationIds);
		ObjectNode rsltNode = objectMapper.createObjectNode();
		for (String eleId : eleIds) {
			XbrlElement[] eles = document.getAllItems(eleId);
			if (eles.length <= 0) {
				continue;
			}
			ArrayNode subRsltArrNode = objectMapper.createArrayNode();
			for (XbrlElement ele : eles) {
				ObjectNode subRsltNode = objectMapper.createObjectNode();
				XbrlContext context = ele.getContext();
				generatePeriodTypeContent(subRsltNode, context);
				subRsltNode.put(Instance.Attribute.UNIT, ele.getUnit());
				subRsltNode.put(Instance.Attribute.VALUE, ele.getValue());
				subRsltArrNode.add(subRsltNode);
			}
			rsltNode.set(eleId, subRsltArrNode);
		}
		return rsltNode;
	}

	public Map<String, Set<String>> getContexts(File instanceFile,
			String PresentationId) throws Exception {
		List<String> PresentationIds = new ArrayList<String>();
		PresentationIds.add(PresentationId);
		return getContexts(instanceFile, PresentationIds);
	}

	private Map<String, Set<String>> getContexts(File instanceFile,
			List<String> PresentationIds) throws Exception {
		XbrlDocument document = loadXbrlDocument(instanceFile);
		XbrlTaxonomyVersion version = taxonomyAssistant
				.getXbrlTaxonomyVersion(instanceFile);
		Set<String> eleIds = taxonomyAssistant.getPresentationElementIds(
				version, PresentationIds);
		Map<String, Set<String>> resultMap = new HashMap<String, Set<String>>(2);
		Set<String> instantSet = new HashSet<String>();
		Set<String> durationSet = new HashSet<String>();
		for (String eleId : eleIds) {
			XbrlElement[] eles = document.getAllItems(eleId);
			if (eles.length <= 0) {
				continue;
			}
			for (XbrlElement ele : eles) {
				XbrlContext context = ele.getContext();
				String periodType = context.getPeriodType();
				XbrlPeriod period = context.getPeriod();
				if (Instance.Attribute.INSTANT.equals(periodType)) {
					instantSet.add(period.getInstantDateString());
				} else if (Instance.Attribute.DURATION.equals(periodType)) {
					durationSet.add(period.getStartDateString() + "~" + period.getEndDateString());
				} else {
					throw new RuntimeException("PeriodType(" + periodType
							+ ") not implement !!!");
				}
			}
		}
		resultMap.put(Instance.Attribute.INSTANT, instantSet);
		resultMap.put(Instance.Attribute.DURATION, durationSet);
		return resultMap;
	}

	XbrlDocument loadXbrlDocument(File instanceFile) throws Exception {
		XbrlTaxonomy taxonomy = taxonomyAssistant.getTaxonomy(instanceFile);
		return cache.getXbrlDocument(instanceFile, taxonomy);
	}

	private void generatePeriodTypeContent(ObjectNode subRsltNode,
			XbrlContext context) {
		String periodType = context.getPeriodType();
		XbrlPeriod period = context.getPeriod();
		subRsltNode.put(Instance.Attribute.PERIOD_TYPE, periodType);
		if (Instance.Attribute.INSTANT.equals(periodType)) {
			subRsltNode.put(Instance.Attribute.INSTANT,
					period.getInstantDateString());
		} else if (Instance.Attribute.DURATION.equals(periodType)) {
			subRsltNode.put(Instance.Attribute.START_DATE,
					period.getStartDateString());
			subRsltNode.put(Instance.Attribute.END_DATE,
					period.getEndDateString());
		} else {
			throw new RuntimeException("PeriodType(" + periodType
					+ ") not implement !!!");
		}
	}
}
