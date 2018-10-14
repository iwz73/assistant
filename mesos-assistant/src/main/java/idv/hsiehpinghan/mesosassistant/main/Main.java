package idv.hsiehpinghan.mesosassistant.main;

import org.apache.mesos.MesosSchedulerDriver;
import org.apache.mesos.Protos;

import idv.hsiehpinghan.mesosassistant.scheduler.MyScheduler;

public class Main {
	public static void main(String args[]) {
		Protos.FrameworkInfo.Builder frameworkInfoBuilder = Protos.FrameworkInfo.newBuilder();
		frameworkInfoBuilder.setName("MY-FRAMEWORK");
		frameworkInfoBuilder.setUser("");

		Protos.FrameworkInfo frameworkInfo = frameworkInfoBuilder.build();

		// args[0] is mesos master ip address
		MesosSchedulerDriver schedulerDriver = new MesosSchedulerDriver(new MyScheduler(), frameworkInfo, "127.0.0.1:5050");

		schedulerDriver.run();
	}

}
