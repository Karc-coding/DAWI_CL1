package com.cinemovie.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "tb_type")
@NamedQuery(name = "findAllTP", query = "SELECT p FROM TipoPelicula p")
public class TipoPelicula {
	
	public TipoPelicula() {
	}

	public TipoPelicula(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	@Id
	@Column(name = "id_type")
	private int id;
	
	@Column(name = "name_type")
	private String name;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
