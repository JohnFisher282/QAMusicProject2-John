package com.qa.project2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.project2.domain.Music;
import com.qa.project2.service.MusicService;

@RestController
public class MusicController {
	
	private MusicService service;
	
	@Autowired //Tells spring to fetch the MusicService
	public MusicController(MusicService service) {
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Music> createMusic(@RequestBody Music m) {
		Music created = this.service.create(m);
		ResponseEntity<Music> response = new ResponseEntity<Music>(created, HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Music>> getAllMusic() {
		return ResponseEntity.ok(this.service.getAll());
	}
	
	@GetMapping("/get/{id}")
	public Music getMusic(@PathVariable Integer id) {
		return this.service.getIndividual(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Music> updateMusic(@PathVariable Integer id, @RequestBody Music newMusic) {
		Music body = this.service.update(id, newMusic);
		ResponseEntity<Music> response = new ResponseEntity<Music>(body, HttpStatus.ACCEPTED);
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteMusic(@PathVariable Integer id) {
		this.service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/getBySong/{song}")
	public ResponseEntity<List<Music>> getMusicBySong(@PathVariable String song) {
		List<Music> found = this.service.getAllMusicBySong(song);
		return ResponseEntity.ok(found);
	}
	
	@GetMapping("/getByAlbum/{album}")
	public ResponseEntity<List<Music>> getMusicByAlbum(@PathVariable String album) {
		List<Music> found = this.service.getAllMusicByAlbum(album);
		return ResponseEntity.ok(found);
	}
	
	@GetMapping("/getByArtist/{artist}")
	public ResponseEntity<List<Music>> getMusicByArtist(@PathVariable String artist) {
		List<Music> found = this.service.getAllMusicByArtist(artist);
		return ResponseEntity.ok(found);
	}
	
	@GetMapping("/getByReleaseDate/{releaseDate}")
	public ResponseEntity<List<Music>> getMusicByReleaseDate(@PathVariable Integer releaseDate) {
		List<Music> found = this.service.getAllMusicByReleaseDate(releaseDate);
		return ResponseEntity.ok(found);
	}
	
	@GetMapping("/getByArtistCountry/{artistCountry}")
	public ResponseEntity<List<Music>> getMusicByArtistCountry(@PathVariable String artistCountry) {
		List<Music> found = this.service.getAllMusicByArtistCountry(artistCountry);
		return ResponseEntity.ok(found);
	}
}
