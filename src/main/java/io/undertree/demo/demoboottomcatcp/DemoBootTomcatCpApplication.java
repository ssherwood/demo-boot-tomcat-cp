package io.undertree.demo.demoboottomcatcp;

import io.undertree.demo.demoboottomcatcp.domain.JobStatus;
import io.undertree.demo.demoboottomcatcp.domain.JobStatusService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

        @GetMapping("/job-status")
        public Iterable<JobStatus> findAll(Pageable page) {
            return jobStatusService.findAll(page);
        }

        @GetMapping("/job-status-custom")
        public Iterable<JobStatus> findAll() {
            return jobStatusService.findAllCustom();
        }

        @GetMapping("/job-status/{id}")
        public JobStatus getOne(@PathVariable UUID id) {
            return jobStatusService.findById(id);
        }

        @PostMapping("/job-status")
        @ResponseStatus(HttpStatus.CREATED)
        public JobStatus newOne(@RequestBody JobStatus jobStatus) {
            return jobStatusService.saveOne(jobStatus);
        }

        @PutMapping("/job-status/{id}/status/{status}")
        @ResponseStatus(HttpStatus.OK)
        public JobStatus updateStatus(@PathVariable UUID id, @PathVariable String status) {
            // transaction 1
            // uncomment one of these approaches...

            // using Spring Data JPA (w/ JPA transaction manager implied)
            jobStatusService.updateStatusSpringDataJPA(id, status);

            // using Hibernate EntityManger (w/ JPA transaction manager specified)
            // jobStatusService.updateStatusHibernateEntityManager(id, status);

            // using JDBCTemplate manually (no JPA transaction manager)
            //jobStatusService.updateStatusJDBC(id, status);

            // transaction 2
            return jobStatusService.findById(id);
        }
    }
}
