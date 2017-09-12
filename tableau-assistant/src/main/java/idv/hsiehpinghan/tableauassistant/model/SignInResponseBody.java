package idv.hsiehpinghan.tableauassistant.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tsResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class SignInResponseBody {
	@XmlElement
	private Credentials credentials;

	private SignInResponseBody() {
		super();
	}

	private SignInResponseBody(Credentials credentials) {
		super();
		this.credentials = credentials;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Credentials {
		@XmlAttribute
		private String token;
		@XmlElement
		private Site site;
		@XmlElement
		private User user;

		private Credentials() {
			super();
		}

		private Credentials(String token, Site site, User user) {
			super();
			this.token = token;
			this.site = site;
			this.user = user;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public Site getSite() {
			return site;
		}

		public void setSite(Site site) {
			this.site = site;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		public static class Site {
			@XmlAttribute
			private String id;
			@XmlAttribute
			private String contentUrl;

			private Site() {
				super();
			}

			private Site(String id, String contentUrl) {
				super();
				this.id = id;
				this.contentUrl = contentUrl;
			}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getContentUrl() {
				return contentUrl;
			}

			public void setContentUrl(String contentUrl) {
				this.contentUrl = contentUrl;
			}

		}

		@XmlAccessorType(XmlAccessType.FIELD)
		public static class User {
			@XmlAttribute
			private String id;

			private User() {
				super();
			}

			private User(String id) {
				super();
				this.id = id;
			}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

		}
	}
}
