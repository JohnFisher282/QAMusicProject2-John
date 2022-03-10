# QA Academy - Project 2 - Music Book

Author: John Fisher

Presentation Link: https://docs.google.com/presentation/d/1iFPe1wsMtDTD5ldQptG-MNOQ8zlvHWDKkhynSZ8er7k/edit#slide=id.p (Also in Git Repo)

Jira Board Link: https://jont.atlassian.net/jira/software/projects/JMP/boards/2

Images and examples of the Project's development are contained within the presentation.

Two git repositories were set up for this project - one for Front-End Development and the other for Back-End. This README.md and any other documentation will be shared between the two repositories. 
Back-End: (https://github.com/JohnFisher282/QAMusicProject2-John)
Front-End: (https://github.com/JohnFisher282/QA-MusicProject2-FrontEnd)

Introduction:
-------------

The goal of this project was to create a CRUD application using the technologies and modules we have learned thus far in training. These technologies/modules include:

Jira - Used for Project Management and planning; creating a fleshed out Scrum/Kanban board to manage time and effort and to plan the requirements for the project.

GoogleDocs & Draw.io - Used to create documentation for the project during and after the planning phase. Used to create the final presentation for the project.

mySQL - Used to create and manage databases set up for the app.

Git & GitHub - Used for Version Control and for implementing a feature-branch model. Allowed for the project to be continuously updated to better manage workload and to ensure versions of the app were always available to roll-back to.

Java & Spring Boot - Used to create the application back-end with a OOP language. Requires a suitable IDE i.e. Eclipse, IntelliJ.

MockMVC - Used for testing the back-end application to ensure the app works and meets a good level of test coverage.

Postman & Front-End Development - Used Postman to test the functionality of the app and APIs prior to creation of the front-end webpage. Used VSCode plug-in Live Server to run the website created in HTML, JavaScript, and CSS.

This app will allow a user to perform CRUD functions with a database to make a 'Music Book' of their favourite songs, artists, etc. Entries can also be found by searching by ID, Song, Artist, Album, Release Date, and Artist Country.

## Recommended Prerequisites

To use and/or develop the app further, the following technologies are recommended:

```
Java 11+ JDK
Maven
SpringBoot
mySQL
VSCode (w/LiveServer)
Postman
A suitable IDE (e.g. Eclipse, IntelliJ)
Git / GitHub
```

### Planning Phase

Using Jira, I created a Scrum board with 5 Epics and a number of User Stories for each (including child issues for each User Story). This allowed me to break up the project into manageable tasks, and prioritise each task accordingly. A link to the Jira Roadmap can be found here: https://jont.atlassian.net/jira/software/projects/JMP/boards/2/roadmap

Further to this, a Risk Assessment and UML were created to further identify any potential issues/risks I may encounter with the project. PDF versions of each of these can be found in this Git Repository as part of the project.

### Database

Both a H2 database and a mySQL database were created for this project. The H2 Database allowed for back-end testing, whereas the mySQL database was used to store persistent data from the app in the database.

The following code snippet is from the music-schema.sql file:

```
drop table if exists music CASCADE;
create table music (id integer generated by default as identity, album varchar(255) not null, artist varchar(255) not null, artist_country varchar(255) not null, release_date integer not null, song varchar(255) not null, primary key (id));
```
*You may need to change 'generated by default as identity' to 'AUTO_INCREMENT' in mySQL.*

The following code snippet is from the music-data.sql file:

```
insert into music (album, artist, artist_country, release_date, song) values ('The Volatile Utopian Real Estate Market', 'Pat the Bunny', 'USA', 2014, 'From Here to Utopia');
insert into music (album, artist, artist_country, release_date, song) values ('Volume 1', 'Amigo the Devil', 'USA', 2018, 'Hell and You');
```

### Back-End

The back-end of this app was created using Java in Eclipse IDE, using Spring Boot Framework. This includes a domain (Music) class, a MusicRepo class, a MusicService class and ServiceInterface class, and a MusicController class.

All relevant mapping is carried out to ensure CRUD functionality. Please see an example of this in the following snippet from MusicController.java:

```
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
```

### Testing

The back-end of this app was tested using MockMVC tests. Currently, the back-end has a coverage of %94.5.
These tests cover the entire MusicController class.
Please see the below example of some tests carried out:

```
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
```

### Front-End

VSCode was used to create HTML, CSS, and Javascript files to create the Front-End of this app. Bootstrap was utilised for some elements, such as Buttons. HTML was used to structure the webpage, and CSS was used to create a better UI.

Forms and tables were primarily used to interact with the Front-End, using CSS and Bootstrap to beautify these.

Prior to Front-End Development, Postman was used to test the API's functionality and ensure the app worked prior to beginning work on the Front-End.

The following code snippet is from the script.js file, showing how the app first GETs the JSON data, then shows this data in the front-end.

```
let getData = async () => {
    let response = await fetch('http://localhost:8080/getAll');
    if (response.status !== 200) {
        throw new Error("Request Failed");

    }
    console.log("Request Successful");
    let jsonData = await response.json();
    console.log(jsonData);
    return jsonData;
}

let showData = async (i) => {
    let returnedData 
    if (i == 1) {returnedData = await getData();}
    if (i == 2) {returnedData = await getById(); return;}
    if (i == 3) {returnedData = await getBySong();}
    if (i == 4) {returnedData = await getByAlbum();}
    if (i == 5) {returnedData = await getByArtist();}
    if (i == 6) {returnedData = await getByReleaseDate();}
    if (i == 7) {returnedData = await getByArtistCountry();}
    let allTable =
        `<tr>
            <th>Id</th>
            <th>Song</th>
            <th>Album</th>
            <th>Artist</th>
            <th>Release Date</th>
            <th>Artist Country</th>
        </tr>`;
    for (let d=0;d<returnedData.length;d++) {
            allTable += `<tr>
            <td>`+returnedData[d].id+`</td>
            <td>`+returnedData[d].song+`</td>
            <td>`+returnedData[d].album+`</td>
            <td>`+returnedData[d].artist+`</td>
            <td>`+returnedData[d].releaseDate+`</td>
            <td>`+returnedData[d].artistCountry+`</td>
        </tr>`;
        }
        console.log(allTable);
        document.getElementById("music").innerHTML = allTable;
    }
```

### Acknowledgements

I would like to thank QA Academy and my Trainers over the past weeks who have given me the skills and help required to finish this project.

