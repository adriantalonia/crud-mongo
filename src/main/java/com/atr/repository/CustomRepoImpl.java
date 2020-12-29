package com.atr.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.atr.models.Tutorial;

@Repository
public class CustomRepoImpl implements CustomRepo {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Tutorial saveTutorial(Tutorial tutorial) {
		return mongoTemplate.save(tutorial);
	}

	@Override
	public List<Tutorial> getAllTutorial() {
		return mongoTemplate.findAll(Tutorial.class);
	}

	@Override
	public List<Tutorial> getAllTutorialPaginated(int page, int size) {
		Query query = new Query();
		query.skip(page * size);
		query.limit(size);
		return mongoTemplate.find(query, Tutorial.class);
	}

	@Override
	public Tutorial findOneByTitle(String title) {
		Query query = new Query();
		query.addCriteria(Criteria.where("title").is(title));
		return mongoTemplate.findOne(query, Tutorial.class);
	}

	@Override
	public List<Tutorial> findByTitle(String title) {
		Query query = new Query();
		query.addCriteria(Criteria.where("title").is(title));
		return mongoTemplate.find(query, Tutorial.class);
	}

	@Override
	public List<Tutorial> findByYearAfter(Integer year) {
		Query query = new Query();
		query.addCriteria(Criteria.where("year").gt(year));
		return mongoTemplate.find(query, Tutorial.class);
	}

	@Override
	public List<Tutorial> findByYearRange(int lower, int upper) {
		Query query = new Query();
		query.addCriteria(Criteria.where("year").gt(lower).andOperator(Criteria.where("year").lt(upper)));
		return mongoTemplate.find(query, Tutorial.class);
	}

	@Override
	public List<Tutorial> findByTagTutorials(String tag) {
		Query query = new Query();
		query.addCriteria(Criteria.where("tags").in(tag));
		return mongoTemplate.find(query, Tutorial.class);
	}

	@Override
	public void updateMultipleTutorialYear() {
		Query query = new Query();
		Update update = new Update().inc("year", 1);
		mongoTemplate.findAndModify(query, update, Tutorial.class);
		
	}

	@Override
	public Tutorial updateOneTutorial(Tutorial tutorial) {
		mongoTemplate.save(tutorial);
		return tutorial;
	}

	@Override
	public void deleteTutorial(Tutorial tutorial) {
		mongoTemplate.remove(tutorial);

	}

}
