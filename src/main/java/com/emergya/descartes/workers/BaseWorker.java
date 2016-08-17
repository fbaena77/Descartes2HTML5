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
        setJob(job);
    }

    @Override
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
    private void setJob(JobConverter job) {
        this.job = job;
    }
}
