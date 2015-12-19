package idv.hsiehpinghan.zookeeperassistant.assistant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ZooKeeperAssistant {
	private ZooKeeper zooKeeper;
	@Autowired
	private Environment environment;

	public void init() throws IOException, InterruptedException {
		String zooKeeperHost = environment.getRequiredProperty("zooKeeperHost");
		int sessionTimeOut = Integer.valueOf(environment
				.getRequiredProperty("sessionTimeOut"));
		ZooKeeperWatcher watcher = new ZooKeeperWatcher();
		zooKeeper = new ZooKeeper(zooKeeperHost, sessionTimeOut, watcher);
		watcher.getCountDownLatch().await();
	}

	public String create(String path, byte[] data, ArrayList<ACL> acls,
			CreateMode createMode) throws KeeperException, InterruptedException {
		return zooKeeper.create(path, data, acls, createMode);
	}

	public List<String> getChildren(String path, boolean watch)
			throws KeeperException, InterruptedException {
		return zooKeeper.getChildren(path, false);
	}

	public void delete(String path, int version) throws InterruptedException,
			KeeperException {
		zooKeeper.delete(path, version);
	}

	public Stat exists(String path, boolean watch) throws KeeperException,
			InterruptedException {
		return zooKeeper.exists(path, watch);
	}

	public void close() throws InterruptedException {
		if (zooKeeper != null) {
			zooKeeper.close();
		}
	}

	private class ZooKeeperWatcher implements Watcher {
		private CountDownLatch countDownLatch = new CountDownLatch(1);

		@Override
		public void process(WatchedEvent watchedEvent) {
			if (watchedEvent.getState() == KeeperState.SyncConnected) {
				countDownLatch.countDown();
			}
		}

		public CountDownLatch getCountDownLatch() {
			return countDownLatch;
		}
	}
}
