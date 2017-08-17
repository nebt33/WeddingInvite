package com.example.WeddingInvite.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.WeddingInvite.error.CustomErrorType;
import com.example.WeddingInvite.model.Song;
import com.example.WeddingInvite.service.SongService;


@RestController
@RequestMapping("/api")
public class RestWeddingInviteController {
	
	public static final Logger logger = LoggerFactory.getLogger(RestWeddingInviteController.class);
	 
    @Autowired
    SongService songService; //Service which will do all data retrieval/manipulation work
 
    // -------------------Retrieve All Songs---------------------------------------------
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/song/", method = RequestMethod.GET)
    public ResponseEntity<List<Song>> listAllSongs() {
        List<Song> songs = songService.findAllSongs();
        if (songs.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Song>>(songs, HttpStatus.OK);
    }
 
    // -------------------Retrieve Single Song------------------------------------------
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/song/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSong(@PathVariable("id") int id) {
        logger.info("Fetching Song with id {}", id);
        Song song = songService.findById(id);
        if (song == null) {
            logger.error("Song with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Song with id " + id 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Song>(song, HttpStatus.OK);
    }
 
    // -------------------Request a Song-------------------------------------------
 
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/song/", method = RequestMethod.POST)
    public ResponseEntity<?> requestSong(@RequestBody Song song, UriComponentsBuilder ucBuilder) {
        logger.info("Requesting Song : {}", song);
 
        if (songService.isSongExist(song)) {
            logger.error("Unable to create. A Song with name {} already exist", song.getTitle());
            return new ResponseEntity(new CustomErrorType("Unable to request. A Song with name " + 
            song.getTitle() + " already exist."),HttpStatus.CONFLICT);
        }
        songService.saveSong(song);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/song/{id}").buildAndExpand(song.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
 
    // ------------------- Update a Song ------------------------------------------------
 
//    @SuppressWarnings({ "rawtypes", "unchecked" })
//	@RequestMapping(value = "/song/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<?> updateSong(@PathVariable("id") int id, @RequestBody Song song) {
//        logger.info("Updating Song with id {}", id);
//
//        Song currentSong = songService.findById(id);
//
//        if (currentSong == null) {
//            logger.error("Unable to update. Song with id {} not found.", id);
//            return new ResponseEntity(new CustomErrorType("Unable to upate. Song with id " + id + " not found."),
//                    HttpStatus.NOT_FOUND);
//        }
//
//        currentSong.setTitle(song.getTitle());
//        currentSong.setArtist(song.getArtist());
//        currentSong.setPopularity(song.getPopularity());
//
//        songService.updateSong(currentSong);
//        return new ResponseEntity<Song>(currentSong, HttpStatus.OK);
//    }
 
    // ------------------- Delete a Song-----------------------------------------
 
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/song/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSong(@PathVariable("id") int id) {
        logger.info("Fetching & Deleting Song with id {}", id);
 
        Song song = songService.findById(id);
        if (song == null) {
            logger.error("Unable to delete. Song with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Song with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        songService.deleteSongById(id);
        return new ResponseEntity<Song>(HttpStatus.NO_CONTENT);
    }
 
    // ------------------- Delete All Users-----------------------------
 
//    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
//    public ResponseEntity<User> deleteAllUsers() {
//        logger.info("Deleting All Users");
// 
//        userService.deleteAllUsers();
//        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//    }

}
