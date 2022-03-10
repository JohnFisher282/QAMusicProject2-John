package com.qa.project2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.project2.domain.Music;

@Repository
public interface MusicRepo extends JpaRepository<Music, Integer> {
	//Spring Auto-Gens basic CRUD functions
	List<Music> findBySongIgnoreCase(String song);
	
	List<Music> findByAlbumIgnoreCase(String album);
	
	List<Music> findByArtistIgnoreCase(String artist);
	
	List<Music> findByReleaseDate(Integer releaseDate);
	
	List<Music> findByArtistCountryIgnoreCase(String artistCountry);
}

