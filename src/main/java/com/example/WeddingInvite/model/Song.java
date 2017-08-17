package com.example.WeddingInvite.model;

import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Entity
@Table(name="SONG")
@Data
public class Song {
	
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String title;

	@Column
	private String artist;
	
	@Column
	private int popularity;

	@Transient
	private List<String> numOfHearts;

}
