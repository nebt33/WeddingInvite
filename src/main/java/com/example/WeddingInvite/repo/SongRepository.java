package com.example.WeddingInvite.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.WeddingInvite.model.Song;

//public interface SongRepo extends JpaRepository<Song, Integer>{
//	
//}
@Transactional
public interface SongRepository extends PagingAndSortingRepository<Song, Integer>{
	
	Song findByTitle(String title);
//	Page<Song> findByTitle(Pageable pageable, String title);
//	
//	@Query(value = "SELECT S FROM Song")
//	Page<Song> listAll(Pageable pageable);
//
//	List<Song> findByTitle(String title);
	
}