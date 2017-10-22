package com.example.WeddingInvite.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.WeddingInvite.model.Song;

@Service
public interface SongService {

    Song findById(Integer id);
    
    Song findByTitle(String title);
 
    void saveSong(Song song);
 
//    void updateSong(Song song);
 
    void deleteSongById(Integer id);
 
//    void deleteAllSongs();
 
    Iterable<Song> findAllSongs();
 
    boolean isSongExist(Song song);
}
