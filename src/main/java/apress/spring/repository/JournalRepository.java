package apress.spring.repository;

import apress.spring.domain.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long> { // @Entity를 붙인 클래스와 Serializable 객체인 ID를 받음
}
