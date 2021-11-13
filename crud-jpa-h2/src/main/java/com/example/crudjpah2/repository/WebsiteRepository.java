package com.example.crudjpah2.repository;

import com.example.crudjpah2.model.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteRepository extends JpaRepository<Website,Long> {
}
