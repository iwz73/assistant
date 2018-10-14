package idv.hsiehpinghan.mesosassistant.scheduler;

import java.util.List;

import org.apache.mesos.Protos.ExecutorID;
import org.apache.mesos.Protos.FrameworkID;
import org.apache.mesos.Protos.MasterInfo;
import org.apache.mesos.Protos.Offer;
import org.apache.mesos.Protos.OfferID;
import org.apache.mesos.Protos.SlaveID;
import org.apache.mesos.Protos.TaskStatus;
import org.apache.mesos.Scheduler;
import org.apache.mesos.SchedulerDriver;

public class MyScheduler implements Scheduler {

	@Override
	public void registered(SchedulerDriver schedulerDriver, FrameworkID frameworkId, MasterInfo masterInfo) {
		System.out.println(String.format("registered(%s)", frameworkId.getValue()));
	}

	@Override
	public void reregistered(SchedulerDriver schedulerDriver, MasterInfo masterInfo) {
		System.out.println("Scheduler reregistered");
		
	}

	@Override
	public void offerRescinded(SchedulerDriver schedulerDriver, OfferID offerID) {
		System.out.println("offerRescinded");
	}

	
	@Override
	public void disconnected(SchedulerDriver schedulerDriver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(SchedulerDriver schedulerDriver, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executorLost(SchedulerDriver schedulerDriver, ExecutorID executorID, SlaveID slaveID, int status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void frameworkMessage(SchedulerDriver schedulerDriver, ExecutorID executorID, SlaveID slaveID, byte[] status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resourceOffers(SchedulerDriver schedulerDriver, List<Offer> offers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void slaveLost(SchedulerDriver schedulerDriver, SlaveID slaveID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void statusUpdate(SchedulerDriver schedulerDriver, TaskStatus taskStatus) {
		// TODO Auto-generated method stub
		
	}

}
