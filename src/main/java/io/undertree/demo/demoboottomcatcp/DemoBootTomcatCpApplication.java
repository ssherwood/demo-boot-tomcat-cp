package io.undertree.demo.demoboottomcatcp;

import io.undertree.demo.demoboottomcatcp.domain.JobStatus;
import io.undertree.demo.demoboottomcatcp.domain.JobStatusRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
public class DemoBootTomcatCpApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBootTomcatCpApplication.class, args);
    }

    @RestController
    @RequestMapping("/api")
    static class JobStatusAPI {
        private final JobStatusRepository jobStatusRepository;

        JobStatusAPI(JobStatusRepository jobStatusRepository) {
            this.jobStatusRepository = jobStatusRepository;
        }

        @GetMapping("/job-status")
        public List<JobStatus> findAll() {
            return jobStatusRepository.findAll();
        }

        @PostMapping("/job-status")
        public JobStatus newOne(@RequestBody JobStatus jobStatus) {
            return jobStatusRepository.save(jobStatus);
        }
    }
}
