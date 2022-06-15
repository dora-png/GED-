package com.microservice.ged.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.Trace;

@Repository
public interface TraceRepository extends JpaRepository<Trace, Long> {
	Page<Trace> findByUsername(String username, Pageable pageable);
}
