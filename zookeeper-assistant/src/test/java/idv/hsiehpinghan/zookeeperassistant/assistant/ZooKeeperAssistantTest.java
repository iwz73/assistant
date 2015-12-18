package idv.hsiehpinghan.zookeeperassistant.assistant;

import idv.hsiehpinghan.zookeeperassistant.suit.TestngSuitSetting;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ZooKeeperAssistantTest {
	private ZooKeeperAssistant zooKeeperAssistant;
	private String groupPath;

	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		zooKeeperAssistant = applicationContext
				.getBean(ZooKeeperAssistant.class);
	}

	@Test
	public void generateZooKeeper() throws Exception {
		ZooKeeper zooKeeper = null;
		try {
			zooKeeper = zooKeeperAssistant.generateZooKeeper();
			Assert.assertNotNull(zooKeeper);
		} finally {
			if (zooKeeper != null) {
				zooKeeper.close();
			}
		}
	}

	@Test(dependsOnMethods = { "generateZooKeeper" })
	public void createGroup() throws Exception {
		ZooKeeper zooKeeper = null;
		try {
			zooKeeper = zooKeeperAssistant.generateZooKeeper();
			String path = "/group" + System.nanoTime();
			groupPath = zooKeeper.create(path, null, Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT);
			Assert.assertNotNull(groupPath);
		} finally {
			if (zooKeeper != null) {
				zooKeeper.close();
			}
		}
	}

	@Test(dependsOnMethods = { "createGroup" })
	public void addGroupMember() throws Exception {
		ZooKeeper zooKeeper = null;
		try {
			zooKeeper = zooKeeperAssistant.generateZooKeeper();
			String path = groupPath + "/member" + System.nanoTime();
			String memberPath = zooKeeper.create(path, null,
					Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			Assert.assertNotNull(memberPath);
		} finally {
			if (zooKeeper != null) {
				zooKeeper.close();
			}
		}
	}

	@Test(dependsOnMethods = { "addGroupMember" })
	public void listGroupMember() throws Exception {
		ZooKeeper zooKeeper = null;
		try {
			zooKeeper = zooKeeperAssistant.generateZooKeeper();
			List<String> members = zooKeeper.getChildren(groupPath, false);
			Assert.assertEquals(members.size(), 1);
		} finally {
			if (zooKeeper != null) {
				zooKeeper.close();
			}
		}
	}

	@Test(dependsOnMethods = { "listGroupMember" })
	public void deleteGroup() throws Exception {
		ZooKeeper zooKeeper = null;
		try {
			zooKeeper = zooKeeperAssistant.generateZooKeeper();
			List<String> members = zooKeeper.getChildren(groupPath, false);
			for (String member : members) {
				zooKeeper.delete(groupPath + "/" + member, -1);
			}
			zooKeeper.delete(groupPath, -1);
			Stat stat = zooKeeper.exists(groupPath, false);
			Assert.assertNull(stat);
		} finally {
			if (zooKeeper != null) {
				zooKeeper.close();
			}
		}
	}
}
