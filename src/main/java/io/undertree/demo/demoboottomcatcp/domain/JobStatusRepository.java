package io.undertree.demo.demoboottomcatcp.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface JobStatusRepository extends PagingAndSortingRepository<JobStatus, UUID> {

    @Override
    //@QueryHints(@javax.persistence.QueryHint(name="org.hibernate.fetchSize", value="50"))
    Page<JobStatus> findAll(Pageable pageable);

    @Query(value = "select * from job_status order by job_end_ts limit 20", nativeQuery = true)
    Iterable<JobStatus> findAllCustom();
}
