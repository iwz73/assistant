package idv.hsiehpinghan.clouderamanagerapiassistant;

import com.cloudera.api.ApiRootResource;
import com.cloudera.api.ClouderaManagerClientBuilder;
import com.cloudera.api.v14.RootResourceV14;

public class Main {
	public static void main(String[] args) {
		String hostname = "127.0.0.1";
		int port = 7180;
		String username = "admin";
		String password = "admin";
		ApiRootResource root = new ClouderaManagerClientBuilder().withHost(hostname).withPort(port)
				.withUsernamePassword(username, password).build();
		RootResourceV14 v14 = root.getRootV14();
		System.err.println(v14.getClouderaManagerResource().getVersion().getVersion());
	}
}
