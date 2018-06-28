package idv.hsiehpinghan.clouderamanagerapiassistant.model;

import java.util.Date;
import java.util.List;

public class HostsModel {
	private List<Item> items;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "HostsModel [items=" + items + "]";
	}

	public static class Item {
		private String hostId; // 3de1029a-5586-404b-8749-f9033217b4df
		private String ipAddress; // 10.88.97.22
		private String hostname; // localhost
		private String rackId; // /default
		private String hostUrl; // http://localhost:7180/cmf/hostRedirect/3de1029a-5586-404b-8749-f9033217b4df
		private Date lastHeartbeat; // 2018-06-26T07:30:24.965Z
		private String healthSummary; // GOOD
		private List<HealthCheck> healthChecks;
		private List<RoleRef> roleRefs;
		private Boolean maintenanceMode; // false
		private List<Object> maintenanceOwners;
		private String commissionState; // COMMISSIONED
		private Integer numCores; // 32
		private Integer numPhysicalCores; // 16
		private Long totalPhysMemBytes; // 135084175360
		private String entityStatus; // GOOD_HEALTH
		private ClusterRef clusterRef;

		public String getHostId() {
			return hostId;
		}

		public void setHostId(String hostId) {
			this.hostId = hostId;
		}

		public String getIpAddress() {
			return ipAddress;
		}

		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}

		public String getHostname() {
			return hostname;
		}

		public void setHostname(String hostname) {
			this.hostname = hostname;
		}

		public String getRackId() {
			return rackId;
		}

		public void setRackId(String rackId) {
			this.rackId = rackId;
		}

		public String getHostUrl() {
			return hostUrl;
		}

		public void setHostUrl(String hostUrl) {
			this.hostUrl = hostUrl;
		}

		public Date getLastHeartbeat() {
			return lastHeartbeat;
		}

		public void setLastHeartbeat(Date lastHeartbeat) {
			this.lastHeartbeat = lastHeartbeat;
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

		public List<RoleRef> getRoleRefs() {
			return roleRefs;
		}

		public void setRoleRefs(List<RoleRef> roleRefs) {
			this.roleRefs = roleRefs;
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

		public Integer getNumCores() {
			return numCores;
		}

		public void setNumCores(Integer numCores) {
			this.numCores = numCores;
		}

		public Integer getNumPhysicalCores() {
			return numPhysicalCores;
		}

		public void setNumPhysicalCores(Integer numPhysicalCores) {
			this.numPhysicalCores = numPhysicalCores;
		}

		public Long getTotalPhysMemBytes() {
			return totalPhysMemBytes;
		}

		public void setTotalPhysMemBytes(Long totalPhysMemBytes) {
			this.totalPhysMemBytes = totalPhysMemBytes;
		}

		public String getEntityStatus() {
			return entityStatus;
		}

		public void setEntityStatus(String entityStatus) {
			this.entityStatus = entityStatus;
		}

		public ClusterRef getClusterRef() {
			return clusterRef;
		}

		public void setClusterRef(ClusterRef clusterRef) {
			this.clusterRef = clusterRef;
		}

		@Override
		public String toString() {
			return "HostInfoModel [hostId=" + hostId + ", ipAddress=" + ipAddress + ", hostname=" + hostname
					+ ", rackId=" + rackId + ", hostUrl=" + hostUrl + ", lastHeartbeat=" + lastHeartbeat
					+ ", healthSummary=" + healthSummary + ", healthChecks=" + healthChecks + ", roleRefs=" + roleRefs
					+ ", maintenanceMode=" + maintenanceMode + ", maintenanceOwners=" + maintenanceOwners
					+ ", commissionState=" + commissionState + ", numCores=" + numCores + ", numPhysicalCores="
					+ numPhysicalCores + ", totalPhysMemBytes=" + totalPhysMemBytes + ", entityStatus=" + entityStatus
					+ ", clusterRef=" + clusterRef + "]";
		}

		public static class HealthCheck {
			private String name; // HOST_AGENT_LOG_DIRECTORY_FREE_SPACE
			private String summary; // GOOD
			private String explanation; // The Cloudera Manager Agent's log directory (/var/log/cloudera-scm-agent) is
										// on a filesystem with more than 2.0 GiB of its space free.
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

			public String getExplanation() {
				return explanation;
			}

			public void setExplanation(String explanation) {
				this.explanation = explanation;
			}

			public Boolean getSuppressed() {
				return suppressed;
			}

			public void setSuppressed(Boolean suppressed) {
				this.suppressed = suppressed;
			}

			@Override
			public String toString() {
				return "HealthCheck [name=" + name + ", summary=" + summary + ", explanation=" + explanation
						+ ", suppressed=" + suppressed + "]";
			}

		}

		public static class RoleRef {
			private String clusterName; // cluster
			private String serviceName; // kafka
			private String roleName; // kafka-GATEWAY-daf9761199123d6b541091181be594dc

			public String getClusterName() {
				return clusterName;
			}

			public void setClusterName(String clusterName) {
				this.clusterName = clusterName;
			}

			public String getServiceName() {
				return serviceName;
			}

			public void setServiceName(String serviceName) {
				this.serviceName = serviceName;
			}

			public String getRoleName() {
				return roleName;
			}

			public void setRoleName(String roleName) {
				this.roleName = roleName;
			}

			@Override
			public String toString() {
				return "RoleRef [clusterName=" + clusterName + ", serviceName=" + serviceName + ", roleName=" + roleName
						+ "]";
			}

		}

		public static class ClusterRef {
			private String clusterName; // cluster

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

	}
}
