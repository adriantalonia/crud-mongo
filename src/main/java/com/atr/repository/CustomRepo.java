package com.atr.repository;

import java.util.List;

import com.atr.models.Tutorial;

public interface CustomRepo {

	Tutorial saveTutorial(Tutorial tutorial);

	List<Tutorial> getAllTutorial();

	List<Tutorial> getAllTutorialPaginated(int page, int size);

	Tutorial findOneByTitle(String title);

	List<Tutorial> findByTitle(String title);

	List<Tutorial> findByYearAfter(Integer year);

	List<Tutorial> findByYearRange(int lower, int upper);

	List<Tutorial> findByTagTutorials(String tag);

	void updateMultipleTutorialYear();

	Tutorial updateOneTutorial(Tutorial tutorial);

	void deleteTutorial(Tutorial Tutorial);
}
