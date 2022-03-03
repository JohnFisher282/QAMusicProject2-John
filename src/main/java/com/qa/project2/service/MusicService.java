package com.qa.project2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.qa.project2.domain.Music;
import com.qa.project2.repo.MusicRepo;

@Service
public class MusicService implements ServiceInterface<Music> {
	
	private MusicRepo MRepo;
	
	@Autowired
	public MusicService(MusicRepo MRepo) {
		this.MRepo=MRepo;
	}
	
	//Creates new entry
	public Music create(Music m) {
		Music created = this.MRepo.save(m);
		return created;
	}
	
	//Reads all entries
	public List<Music> getAll(){
		return this.MRepo.findAll();
	}
	
	//Reads entry with specified ID
	public Music getIndividual(Integer id) {
		Optional<Music> found = this.MRepo.findById(id);
		return found.get();
	}
	
	//Updates existing entry
	public Music update(Integer id, Music newMusic) {
		Music existing = this.MRepo.findById(id).get();
		existing.setSong(newMusic.getSong());
		existing.setAlbum(newMusic.getAlbum());
		existing.setArtist(newMusic.getArtist());
		existing.setReleaseDate(newMusic.getReleaseDate());
		existing.setArtistCountry(newMusic.getArtistCountry());
		Music updated = this.MRepo.save(existing);
		return updated;
	}
	
	//Deletes an entry
	public void delete(@PathVariable Integer id) {
		this.MRepo.deleteById(id);
	}
	
	//Reads all entries by Song
	public List<Music> getAllMusicBySong(String song) {
		List<Music> found = this.MRepo.findBySongIgnoreCase(song);
		return found;
	}
	
	//Reads all entries by Album
	public List<Music> getAllMusicByAlbum(String album) {
		List<Music> found = this.MRepo.findByAlbumIgnoreCase(album);
		return found;
	}
	
	//Reads all entries by Artist
	public List<Music> getAllMusicByArtist(String artist) {
		List<Music> found = this.MRepo.findByArtistIgnoreCase(artist);
		return found;
	}
	
	//Reads all entries by Release Date
	public List<Music> getAllMusicByReleaseDate(Integer releaseDate) {
		List<Music> found = this.MRepo.findByReleaseDate(releaseDate);
		return found;
	}
	
	//Reads all entries by Artist Country
	public List<Music> getAllMusicByArtistCountry(String artistCountry) {
		List<Music> found = this.MRepo.findByArtistCountryIgnoreCase(artistCountry);
		return found;
	}
}
