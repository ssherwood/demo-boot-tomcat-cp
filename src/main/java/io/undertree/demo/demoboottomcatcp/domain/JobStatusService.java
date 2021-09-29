package io.undertree.demo.demoboottomcatcp.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class JobStatusService {
    private final JobStatusRepository jobStatusRepository;

    public JobStatusService(JobStatusRepository jobStatusRepository) {
        this.jobStatusRepository = jobStatusRepository;
    }

    @Retryable(value = SQLException.class, maxAttempts = 5, backoff = @Backoff(delay = 100, multiplier = 2))
    public Iterable<JobStatus> findAll(Pageable pageable) {
        // the problem appears to be the additional count(*) done buy the pageable request
        return jobStatusRepository.findAll(pageable);
    }

    //@Retryable(value = SQLException.class, maxAttempts = 5, backoff = @Backoff(delay = 100, multiplier = 2))
    public Iterable<JobStatus> findAll() {
        return jobStatusRepository.findAllCustom();
    }

    public JobStatus save(JobStatus jobStatus) {
        return jobStatusRepository.save(jobStatus);
    }
}
