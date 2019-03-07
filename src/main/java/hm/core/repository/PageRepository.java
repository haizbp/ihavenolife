package hm.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hm.core.entity.PageEntity;

public interface PageRepository extends JpaRepository<PageEntity, Long> {

	PageEntity findByPath(String path);
	
}
