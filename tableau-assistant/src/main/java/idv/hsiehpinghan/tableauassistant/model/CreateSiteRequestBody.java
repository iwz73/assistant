package idv.hsiehpinghan.tableauassistant.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tsRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateSiteRequestBody {
	
	
//	<tsRequest>
//	  <site name="site-name"
//	    contentUrl="content-url"
//	    adminMode="admin-mode"
//	    userQuota="num-users"
//	    storageQuota="limit-in-megabytes"
//	    disableSubscriptions="disable-subscriptions" />
//	</tsRequest>
	
	
	@XmlElement
	private Site site;


	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Site {
		private String name;
//		@XmlAttribute
//		private String name;
//		@XmlAttribute
//		private String name;
//		@XmlAttribute
//		private String name;
//		@XmlAttribute
//		private String name;
//		@XmlAttribute
//		private String name;



	}
}
