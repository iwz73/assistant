package idv.hsiehpinghan.clouderamanagerapiassistant.model;

import java.util.List;

public class ClustersModel {
	private List<Item> items;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "ClustersModel [items=" + items + "]";
	}

	public static class Item {
		private String name; // cluster
		private String displayName; // Cluster 1
		private String clusterUrl; // http://localhost:7180/cmf/clusterRedirect/cluster
		private String hostsUrl; // http://localhost:7180/cmf/clusterRedirect/cluster/hosts
		private String version; // CDH5
		private String fullVersion; // 5.9.0
		private Boolean maintenanceMode; // false
		private List<Object> maintenanceOwners;
		private List<Object> services;
		private List<Object> parcels;
		private String entityStatus; // CONCERNING_HEALTH

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

		public String getClusterUrl() {
			return clusterUrl;
		}

		public void setClusterUrl(String clusterUrl) {
			this.clusterUrl = clusterUrl;
		}

		public String getHostsUrl() {
			return hostsUrl;
		}

		public void setHostsUrl(String hostsUrl) {
			this.hostsUrl = hostsUrl;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getFullVersion() {
			return fullVersion;
		}

		public void setFullVersion(String fullVersion) {
			this.fullVersion = fullVersion;
		}

		public Boolean getMaintenanceMode() {
			return maintenanceMode;
		}

		public void setMaintenanceMode(Boolean maintenanceMode) {
			this.maintenanceMode = maintenanceMode;
		}

		public List<Object> getMaintenanceOwners() {
			return maintenanceOwners;
		}

		public void setMaintenanceOwners(List<Object> maintenanceOwners) {
			this.maintenanceOwners = maintenanceOwners;
		}

		public List<Object> getServices() {
			return services;
		}

		public void setServices(List<Object> services) {
			this.services = services;
		}

		public List<Object> getParcels() {
			return parcels;
		}

		public void setParcels(List<Object> parcels) {
			this.parcels = parcels;
		}

		public String getEntityStatus() {
			return entityStatus;
		}

		public void setEntityStatus(String entityStatus) {
			this.entityStatus = entityStatus;
		}

		@Override
		public String toString() {
			return "Cluster [name=" + name + ", displayName=" + displayName + ", clusterUrl=" + clusterUrl
					+ ", hostsUrl=" + hostsUrl + ", version=" + version + ", fullVersion=" + fullVersion
					+ ", maintenanceMode=" + maintenanceMode + ", maintenanceOwners=" + maintenanceOwners
					+ ", services=" + services + ", parcels=" + parcels + ", entityStatus=" + entityStatus + "]";
		}

	}

}
