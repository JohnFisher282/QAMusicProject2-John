package com.qa.project2.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.project2.domain.Music;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //sets up MockMVC Object
@Sql(scripts= {"classpath:music-schema.sql", "classpath:music-data.sql"}, executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class MusicControllerIntegrationTest {
	
	@Autowired //pulls MockMvc object from context
	private MockMvc mvc; //Performs request (acts as Postman)
	
	@Autowired
	private ObjectMapper mapper; //Java to JSON converter that Spring uses
	
	@Test
	void testCreate() throws Exception {
		Music testMusic = new Music(null, "Alpha Incipiens", "Zopilote Machine", "The Mountain Goats", 1994, "USA");
		String testMusicAsJSON = this.mapper.writeValueAsString(testMusic);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testMusicAsJSON);
		
		Music testCreatedMusic = new Music (3,"Alpha Incipiens", "Zopilote Machine", "The Mountain Goats", 1994, "USA");
		String testCreatedMusicAsJSON = this.mapper.writeValueAsString(testCreatedMusic);
		ResultMatcher checkStatus = status().isCreated(); //201 - Created
		ResultMatcher checkBody = content().json(testCreatedMusicAsJSON); //Checks Body
		
		//Sends Requests and Checks
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
		
	@Test
	void getAllTest() throws Exception {
		RequestBuilder req = get("/getAll");
		List<Music> testMusic = List.of(new Music(1, "From Here to Utopia", "The Volatile Utopian Real Estate Market", "Pat the Bunny", 2014, "USA"), new Music(2, "Hell and You", "Volume 1", "Amigo the Devil", 2018, "USA"));
		String json = this.mapper.writeValueAsString(testMusic);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getMusicTest() throws Exception {
		RequestBuilder req = get("/get/2");
		Music testMusic = new Music(2, "Hell and You", "Volume 1", "Amigo the Devil", 2018, "USA");
		String json = this.mapper.writeValueAsString(testMusic);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testUpdate() throws Exception {
		Music testMusic = new Music(null, "From Here to Utopia", "The Volatile Utopian Real Estate Market", "Pat the Bunny", 2014, "U.S.A.");
		String testMusicAsJSON = this.mapper.writeValueAsString(testMusic);
		RequestBuilder req = put("/update/1").contentType(MediaType.APPLICATION_JSON).content(testMusicAsJSON);
		
		Music testUpdatedMusic = new Music(1, "From Here to Utopia", "The Volatile Utopian Real Estate Market", "Pat the Bunny", 2014, "U.S.A.");
		String testUpdatedMusicAsJSON = this.mapper.writeValueAsString(testUpdatedMusic);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testUpdatedMusicAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testDelete() throws Exception {
		this.mvc.perform(delete("/delete/1")).andExpect(status().isNoContent());
	}
	
	@Test
	void getMusicBySongTest() throws Exception {
		RequestBuilder req = get("/getBySong/Hell and You");
		List<Music> testMusic = List.of(new Music(2, "Hell and You", "Volume 1", "Amigo the Devil", 2018, "USA"));
		String json = this.mapper.writeValueAsString(testMusic);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getMusicByAlbumTest() throws Exception {
		RequestBuilder req = get("/getByAlbum/Volume 1");
		List<Music> testMusic = List.of(new Music(2, "Hell and You", "Volume 1", "Amigo the Devil", 2018, "USA"));
		String json = this.mapper.writeValueAsString(testMusic);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getMusicByArtistTest() throws Exception {
		RequestBuilder req = get("/getByArtist/Pat the Bunny");
		List<Music> testMusic = List.of(new Music(1, "From Here to Utopia", "The Volatile Utopian Real Estate Market", "Pat the Bunny", 2014, "USA"));
		String json = this.mapper.writeValueAsString(testMusic);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getMusicByReleaseDateTest() throws Exception {
		RequestBuilder req = get("/getByReleaseDate/2014");
		List<Music> testMusic = List.of(new Music(1, "From Here to Utopia", "The Volatile Utopian Real Estate Market", "Pat the Bunny", 2014, "USA"));
		String json = this.mapper.writeValueAsString(testMusic);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getMusicByArtistCountry() throws Exception {
		RequestBuilder req = get("/getByArtistCountry/USA");
		List<Music> testMusic = List.of(new Music(1, "From Here to Utopia", "The Volatile Utopian Real Estate Market", "Pat the Bunny", 2014, "USA"), new Music(2, "Hell and You", "Volume 1", "Amigo the Devil", 2018, "USA"));
		String json = this.mapper.writeValueAsString(testMusic);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
}
