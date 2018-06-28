package idv.hsiehpinghan.clouderamanagerapiassistant.model;

import java.util.List;

public class RolesModel {
	private List<Item> items;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "RolesModel [items=" + items + "]";
	}

	public static class Item {
		private String name; // mgmt-EVENTSERVER-daf9761199123d6b541091181be594dc
		private String type; // EVENTSERVER
		private ServiceRef serviceRef;
		private String roleUrl; // http://localhost:7180/cmf/roleRedirect/mgmt-EVENTSERVER-daf9761199123d6b541091181be594dc
		private String roleState; // STARTED
		private String healthSummary; // GOOD
		private HostRef hostRef;
		private List<HealthCheck> healthChecks;
		private String configStalenessStatus; // FRESH
		private Boolean maintenanceMode; // false
		private List<Object> maintenanceOwners;
		private String commissionState; // COMMISSIONED
		private RoleConfigGroupRef roleConfigGroupRef;
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

		public ServiceRef getServiceRef() {
			return serviceRef;
		}

		public void setServiceRef(ServiceRef serviceRef) {
			this.serviceRef = serviceRef;
		}

		public String getRoleUrl() {
			return roleUrl;
		}

		public void setRoleUrl(String roleUrl) {
			this.roleUrl = roleUrl;
		}

		public String getRoleState() {
			return roleState;
		}

		public void setRoleState(String roleState) {
			this.roleState = roleState;
		}

		public String getHealthSummary() {
			return healthSummary;
		}

		public void setHealthSummary(String healthSummary) {
			this.healthSummary = healthSummary;
		}

		public HostRef getHostRef() {
			return hostRef;
		}

		public void setHostRef(HostRef hostRef) {
			this.hostRef = hostRef;
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

		public String getCommissionState() {
			return commissionState;
		}

		public void setCommissionState(String commissionState) {
			this.commissionState = commissionState;
		}

		public RoleConfigGroupRef getRoleConfigGroupRef() {
			return roleConfigGroupRef;
		}

		public void setRoleConfigGroupRef(RoleConfigGroupRef roleConfigGroupRef) {
			this.roleConfigGroupRef = roleConfigGroupRef;
		}

		public String getEntityStatus() {
			return entityStatus;
		}

		public void setEntityStatus(String entityStatus) {
			this.entityStatus = entityStatus;
		}

		@Override
		public String toString() {
			return "Item [name=" + name + ", type=" + type + ", serviceRef=" + serviceRef + ", roleUrl=" + roleUrl
					+ ", roleState=" + roleState + ", healthSummary=" + healthSummary + ", hostRef=" + hostRef
					+ ", healthChecks=" + healthChecks + ", configStalenessStatus=" + configStalenessStatus
					+ ", maintenanceMode=" + maintenanceMode + ", maintenanceOwners=" + maintenanceOwners
					+ ", commissionState=" + commissionState + ", roleConfigGroupRef=" + roleConfigGroupRef
					+ ", entityStatus=" + entityStatus + "]";
		}

		public static class ServiceRef {
			private String serviceName; // mgmt

			public String getServiceName() {
				return serviceName;
			}

			public void setServiceName(String serviceName) {
				this.serviceName = serviceName;
			}

			@Override
			public String toString() {
				return "ServiceRef [serviceName=" + serviceName + "]";
			}

		}

		public static class HostRef {
			private String hostId; // 3de1029a-5586-404b-8749-f9033217b4df

			public String getHostId() {
				return hostId;
			}

			public void setHostId(String hostId) {
				this.hostId = hostId;
			}

			@Override
			public String toString() {
				return "HostRef [hostId=" + hostId + "]";
			}

		}

		public static class HealthCheck {
			private String name; // EVENT_SERVER_EVENT_STORE_SIZE
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

		public static class RoleConfigGroupRef {
			private String roleConfigGroupName; // mgmt-EVENTSERVER-BASE

			public String getRoleConfigGroupName() {
				return roleConfigGroupName;
			}

			public void setRoleConfigGroupName(String roleConfigGroupName) {
				this.roleConfigGroupName = roleConfigGroupName;
			}

			@Override
			public String toString() {
				return "RoleConfigGroupRef [roleConfigGroupName=" + roleConfigGroupName + "]";
			}

		}

	}
}
