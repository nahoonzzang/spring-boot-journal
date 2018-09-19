package apress.spring.repository;

import apress.spring.domain.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long>{ }
