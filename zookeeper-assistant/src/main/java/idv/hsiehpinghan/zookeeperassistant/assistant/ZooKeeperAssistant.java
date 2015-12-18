package idv.hsiehpinghan.zookeeperassistant.assistant;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ZooKeeperAssistant {
	@Autowired
	private Environment environment;

	public ZooKeeper generateZooKeeper() throws IOException,
			InterruptedException {
		String zooKeeperHost = environment.getRequiredProperty("zooKeeperHost");
		int sessionTimeOut = Integer.valueOf(environment
				.getRequiredProperty("sessionTimeOut"));
		ZooKeeperWatcher watcher = new ZooKeeperWatcher();
		ZooKeeper zooKeeper = new ZooKeeper(zooKeeperHost, sessionTimeOut,
				watcher);
		watcher.getCountDownLatch().await();
		return zooKeeper;
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
