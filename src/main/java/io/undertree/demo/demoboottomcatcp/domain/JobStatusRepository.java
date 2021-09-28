package io.undertree.demo.demoboottomcatcp.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface JobStatusRepository extends PagingAndSortingRepository<JobStatus, UUID> {
    @Override
    @Transactional(readOnly = true)
    Page<JobStatus> findAll(Pageable pageable);
}
