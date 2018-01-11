package com.satyaki.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Movies")
public class MovieDetailsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue
	private int id;
	@Column
	private String name;
	@Column
	private double rating;
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getRating() {
		return rating;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
}
