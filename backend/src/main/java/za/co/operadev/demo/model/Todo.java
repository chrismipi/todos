package za.co.operadev.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todo {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private String title;
	private Boolean complete;

	public Todo() {
	}

	public Todo(String title, Boolean complete) {
		this.title = title;
		this.complete = complete;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
}
