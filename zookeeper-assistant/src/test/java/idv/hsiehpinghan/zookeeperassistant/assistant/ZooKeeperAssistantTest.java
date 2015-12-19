package idv.hsiehpinghan.zookeeperassistant.assistant;

import idv.hsiehpinghan.zookeeperassistant.suit.TestngSuitSetting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ZooKeeperAssistantTest {
	private ApplicationContext applicationContext;
	private String groupPath;

	@BeforeClass
	public void beforeClass() throws IOException {
		applicationContext = TestngSuitSetting.getApplicationContext();
	}

	@Test
	public void init() throws Exception {
		ZooKeeperAssistant zooKeeperAssistant = applicationContext
				.getBean(ZooKeeperAssistant.class);
		try {
			zooKeeperAssistant.init();
			Assert.assertNotNull(zooKeeperAssistant);
		} finally {
			zooKeeperAssistant.close();
		}
	}

	@Test(dependsOnMethods = { "init" })
	public void createGroup() throws Exception {
		ZooKeeperAssistant zooKeeperAssistant = applicationContext
				.getBean(ZooKeeperAssistant.class);
		try {
			zooKeeperAssistant.init();
			String path = "/group-";
			byte[] data = null;
			ArrayList<ACL> acls = Ids.OPEN_ACL_UNSAFE;
			CreateMode createMode = CreateMode.PERSISTENT_SEQUENTIAL;
			groupPath = zooKeeperAssistant.create(path, data, acls, createMode);
			Assert.assertNotNull(groupPath);
		} finally {
			zooKeeperAssistant.close();
		}
	}

	@Test(dependsOnMethods = { "createGroup" })
	public void addGroupMember() throws Exception {
		ZooKeeperAssistant zooKeeperAssistant = applicationContext
				.getBean(ZooKeeperAssistant.class);
		try {
			zooKeeperAssistant.init();
			String path = groupPath + "/member-";
			byte[] data = null;
			ArrayList<ACL> acls = Ids.OPEN_ACL_UNSAFE;
			CreateMode createMode = CreateMode.PERSISTENT_SEQUENTIAL;
			String memberPath = zooKeeperAssistant.create(path, data, acls,
					createMode);
			Assert.assertNotNull(memberPath);
		} finally {

		}
	}

	@Test(dependsOnMethods = { "addGroupMember" })
	public void listGroupMember() throws Exception {
		ZooKeeperAssistant zooKeeperAssistant = applicationContext
				.getBean(ZooKeeperAssistant.class);
		try {
			zooKeeperAssistant.init();
			List<String> members = zooKeeperAssistant.getChildren(groupPath,
					false);
			Assert.assertEquals(members.size(), 1);
		} finally {
			zooKeeperAssistant.close();
		}
	}

	@Test(dependsOnMethods = { "listGroupMember" })
	public void deleteGroup() throws Exception {
		ZooKeeperAssistant zooKeeperAssistant = applicationContext
				.getBean(ZooKeeperAssistant.class);
		try {
			zooKeeperAssistant.init();
			List<String> members = zooKeeperAssistant.getChildren(groupPath,
					false);
			for (String member : members) {
				zooKeeperAssistant.delete(groupPath + "/" + member, -1);
			}
			zooKeeperAssistant.delete(groupPath, -1);
			Stat stat = zooKeeperAssistant.exists(groupPath, false);
			Assert.assertNull(stat);
		} finally {
			zooKeeperAssistant.close();
		}
	}
}
