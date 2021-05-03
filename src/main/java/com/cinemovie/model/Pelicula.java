package com.cinemovie.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "tb_pelicula")
@NamedQueries({
	@NamedQuery(name = "findAllP", query = "SELECT p FROM Pelicula p"),
	@NamedQuery(name = "findxName", query = "SELECT p FROM Pelicula p WHERE p.name LIKE :vname")
})
public class Pelicula {

	@Id
	@Column(name = "id_Peli")
	private int id;
	
	@Column(name = "name_Peli", nullable = false)
	private String name;
	
	@Column(name = "description_Peli", nullable = false)
	private String description;
	
	@Column(name = "director_Peli", nullable = false)
	private String director;
	
	@Column(name = "actors_Peli", nullable = false)
	private String actores;
	
	@Column(name = "idType_Peli", nullable = false)
	private int type;
	
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActores() {
		return actores;
	}
	public void setActores(String actores) {
		this.actores = actores;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
