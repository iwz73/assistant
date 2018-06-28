package idv.hsiehpinghan.clouderamanagerapiassistant.model;

import java.util.List;

public class ClusterServicesModel {
	private List<Item> items;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "ClusterServicesModel [items=" + items + "]";
	}

	public static class Item {
		private String name; // zookeeper
		private String type; // ZOOKEEPER
		private ClusterRef clusterRef;
		private String serviceUrl; // http://localhost:7180/cmf/serviceRedirect/zookeeper
		private String roleInstancesUrl; // http://localhost:7180/cmf/serviceRedirect/zookeeper/instances
		private String serviceState; // STARTED
		private String healthSummary; // GOOD
		private List<HealthCheck> healthChecks;
		private String configStalenessStatus; // FRESH
		private String clientConfigStalenessStatus; // FRESH
		private Boolean maintenanceMode; // false
		private List<Object> maintenanceOwners;
		private String displayName; // ZooKeeper
		private String entityStatus; // GOOD_HEALTH

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public ClusterRef getClusterRef() {
			return clusterRef;
		}

		public void setClusterRef(ClusterRef clusterRef) {
			this.clusterRef = clusterRef;
		}

		public String getServiceUrl() {
			return serviceUrl;
		}

		public void setServiceUrl(String serviceUrl) {
			this.serviceUrl = serviceUrl;
		}

		public String getRoleInstancesUrl() {
			return roleInstancesUrl;
		}

		public void setRoleInstancesUrl(String roleInstancesUrl) {
			this.roleInstancesUrl = roleInstancesUrl;
		}

		public String getServiceState() {
			return serviceState;
		}

		public void setServiceState(String serviceState) {
			this.serviceState = serviceState;
		}

		public String getHealthSummary() {
			return healthSummary;
		}

		public void setHealthSummary(String healthSummary) {
			this.healthSummary = healthSummary;
		}

		public List<HealthCheck> getHealthChecks() {
			return healthChecks;
		}

		public void setHealthChecks(List<HealthCheck> healthChecks) {
			this.healthChecks = healthChecks;
		}

		public String getConfigStalenessStatus() {
			return configStalenessStatus;
		}

		public void setConfigStalenessStatus(String configStalenessStatus) {
			this.configStalenessStatus = configStalenessStatus;
		}

		public String getClientConfigStalenessStatus() {
			return clientConfigStalenessStatus;
		}

		public void setClientConfigStalenessStatus(String clientConfigStalenessStatus) {
			this.clientConfigStalenessStatus = clientConfigStalenessStatus;
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

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

		public String getEntityStatus() {
			return entityStatus;
		}

		public void setEntityStatus(String entityStatus) {
			this.entityStatus = entityStatus;
		}

		@Override
		public String toString() {
			return "Item [name=" + name + ", type=" + type + ", clusterRef=" + clusterRef + ", serviceUrl=" + serviceUrl
					+ ", roleInstancesUrl=" + roleInstancesUrl + ", serviceState=" + serviceState + ", healthSummary="
					+ healthSummary + ", healthChecks=" + healthChecks + ", configStalenessStatus="
					+ configStalenessStatus + ", clientConfigStalenessStatus=" + clientConfigStalenessStatus
					+ ", maintenanceMode=" + maintenanceMode + ", maintenanceOwners=" + maintenanceOwners
					+ ", displayName=" + displayName + ", entityStatus=" + entityStatus + "]";
		}

		public static class ClusterRef {
			private String clusterName; // cluster;

			public String getClusterName() {
				return clusterName;
			}

			public void setClusterName(String clusterName) {
				this.clusterName = clusterName;
			}

			@Override
			public String toString() {
				return "ClusterRef [clusterName=" + clusterName + "]";
			}

		}

		public static class HealthCheck {
			private String name; // ZOOKEEPER_CANARY_HEALTH
			private String summary; // GOOD
			private Boolean suppressed; // false

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getSummary() {
				return summary;
			}

			public void setSummary(String summary) {
				this.summary = summary;
			}

			public Boolean getSuppressed() {
				return suppressed;
			}

			public void setSuppressed(Boolean suppressed) {
				this.suppressed = suppressed;
			}

			@Override
			public String toString() {
				return "HealthCheck [name=" + name + ", summary=" + summary + ", suppressed=" + suppressed + "]";
			}

		}

	}

}
