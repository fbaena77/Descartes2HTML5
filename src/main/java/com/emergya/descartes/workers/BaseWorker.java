package com.emergya.descartes.workers;

import com.emergya.descartes.job.JobConverter;

/**
 * @author fbaena
 * 
 */
public class BaseWorker implements Runnable {

	private JobConverter job;

	/**
	 * @param job
	 */
	public BaseWorker(JobConverter job) {
		this.job = job;
	}

	public void run() {

	}

	/**
	 * @return JobConverter
	 */
	public JobConverter getJob() {
		return job;
	}

	/**
	 * @param job
	 */
	public void setJob(JobConverter job) {
		this.job = job;
	}

}
