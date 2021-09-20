package io.undertree.demo.demoboottomcatcp.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobStatusRepository extends JpaRepository<JobStatus, UUID> {
}
