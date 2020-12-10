package com.atr.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.atr.models.Tutorial;

@RepositoryRestResource(path = "tutorials")
public interface TutorialRepository extends MongoRepository<Tutorial, String> {

	@RestResource(path= "find-title")
	List<Tutorial> findByTitleContaining(@Param("title")String title);

	List<Tutorial> findByPublished(boolean published);
	


}
