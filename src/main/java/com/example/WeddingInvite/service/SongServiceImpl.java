package com.example.WeddingInvite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.WeddingInvite.model.Song;
import com.example.WeddingInvite.repo.SongRepository;

@Service("songService")
@Transactional
public class SongServiceImpl implements SongService {
	
	@Autowired
    private SongRepository songRepository;
 
    public Song findById(Integer id) {
        return songRepository.findOne(id);
    }
 
    public Song findByTitle(String title) {
        return songRepository.findByTitle(title);
    }
 
    public void saveSong(Song song) {
        songRepository.save(song);
    }

//    public void updateSong(Song song){
//        saveSong(song);
//    }

    public void deleteSongById(Integer id){
        songRepository.delete(id);
    }
 
//    public void deleteAllSongs(){
//        songRepository.deleteAll();
//    }
 
    public List<Song> findAllSongs(){
        return songRepository.findAll();
    }
 
    public boolean isSongExist(Song song) {
        return findByTitle(song.getTitle()) != null;
    }

}
