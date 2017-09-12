package idv.hsiehpinghan.tableauassistant.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tsRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class SignInRequestBody {
	@XmlElement
	private Credentials credentials;

	/**
	 * Signs you in as a user on the specified site on Tableau Server. This call
	 * returns an authentication token that you use in subsequent calls to the
	 * server.
	 * 
	 * @param name
	 *            The name of the user. The name and password in the
	 *            <credentials> element can represent any user in the specified
	 *            site. If the user is not an administrator, the user might have
	 *            limited permissions to perform subsequent operations.
	 * @param password
	 *            The password.
	 * @param contentUrl
	 *            The URL of the site to sign in to.
	 * @param id
	 *            (Optional) For impersonation, the ID (not name) of a user to
	 *            sign in as.
	 * @return
	 */
	public static SignInRequestBody build(String name, String password, String contentUrl, String id) {
		Credentials.Site site = new Credentials.Site(contentUrl);
		Credentials credentials = null;
		if (id == null) {
			credentials = new Credentials(name, password, site);
		} else {
			Credentials.User user = new Credentials.User(id);
			credentials = new Credentials(name, password, site, user);
		}
		return new SignInRequestBody(credentials);
	}

	private SignInRequestBody() {
		super();
	}

	private SignInRequestBody(Credentials credentials) {
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
		private String name;
		@XmlAttribute
		private String password;
		@XmlElement
		private Site site;
		@XmlElement
		private User user;

		private Credentials() {
			super();
		}

		private Credentials(String name, String password, Site site) {
			super();
			this.name = name;
			this.password = password;
			this.site = site;
		}

		private Credentials(String name, String password, Site site, User user) {
			super();
			this.name = name;
			this.password = password;
			this.site = site;
			this.user = user;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
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
			private String contentUrl;

			private Site() {
				super();
			}

			private Site(String contentUrl) {
				super();
				this.contentUrl = contentUrl;
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
