package com.qa.project2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Music {
	
	@Id //This is the Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //This AutoIncrements
	private Integer id;
	
	@Column(nullable = false) //Marks column as NOT NULL
	private String song;
	
	@Column(nullable = false)
	private String album;
	
	@Column(nullable = false)
	private String artist;
	
	@Column(nullable = false)
	private Integer releaseDate;
	
	@Column(nullable = false)
	private String artistCountry;
	
	//Constructors
	public Music() {
		super();
	}
	
	public Music(Integer id, String song, String album, String artist, Integer releaseDate, String artistCountry) {
		super();
		this.id = id;
		this.song = song;
		this.album = album;
		this.artist = artist;
		this.releaseDate = releaseDate;
		this.artistCountry = artistCountry;
	}
	
	//Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Integer getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Integer releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getArtistCountry() {
		return artistCountry;
	}

	public void setArtistCountry(String artistCountry) {
		this.artistCountry = artistCountry;
	}
	
	//toString Method
	@Override
	public String toString() {
		return "Music [id=" + id + ", song=" + song + ", album=" + album + ", artist=" + artist + ", releaseDate="
				+ releaseDate + ", artistCountry=" + artistCountry + "]";
	}
	
	
	
	
	
}
