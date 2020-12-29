package com.atr.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tutorials")
public class Tutorial {

	@Id
	private String id;

	private String title;
	private String description;
	private List<String> tags;
	private Integer year;
	private boolean published;

	public Tutorial() {

	}

	public Tutorial(String title, String description, boolean published, Integer year, List<String> tags) {
		this.title = title;
		this.description = description;
		this.published = published;
		this.year = year;
		this.tags = tags;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean isPublished) {
		this.published = isPublished;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", title=" + title + ", description=" + description + ", year=" + year
				+ ", published=" + published + "]";
	}

}
