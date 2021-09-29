package io.undertree.demo.demoboottomcatcp;

import io.undertree.demo.demoboottomcatcp.domain.JobStatus;
import io.undertree.demo.demoboottomcatcp.domain.JobStatusService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@EnableRetry
public class DemoBootTomcatCpApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBootTomcatCpApplication.class, args);
    }

    @RestController
    @RequestMapping("/api")
    static class JobStatusAPI {
        private final JobStatusService jobStatusService;

        JobStatusAPI(JobStatusService jobStatusService) {
            this.jobStatusService = jobStatusService;
        }

//        @GetMapping("/job-status")
//        public Page<JobStatus> findAll(Pageable page) {
//            return jobStatusService.findAll(page);
//        }

        @GetMapping("/job-status")
        public Iterable<JobStatus> findAll() {
            return jobStatusService.findAll();
        }

        @PostMapping("/job-status")
        public JobStatus newOne(@RequestBody JobStatus jobStatus) {
            return jobStatusService.save(jobStatus);
        }
    }
}
