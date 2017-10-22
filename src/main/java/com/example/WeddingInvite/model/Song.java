package com.example.WeddingInvite.model;

import javax.persistence.*;

import lombok.Data;

import java.util.Date;

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
	
	@Column(name="submitted_by")
	private String submittedBy;
	
	@Column(name="submitted_by_date")
	private Date submittedByDate;
	

}
