package com.example.WeddingInvite.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.WeddingInvite.model.Song;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.example.WeddingInvite.repo.SongRepository;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SongServiceTest {
	
	@InjectMocks
	SongServiceImpl songSerivceImpl;
	
	@Mock
	SongRepository songRepository;

	Song song = new Song();

	@Before
	public void Setup() {
		song.setId(42);
		song.setTitle("Welcome to the Jungle");
	}

	@Test
	public void testfindById() {
		when(songRepository.findOne(song.getId())).thenReturn(song);

		Song returnedSong = songSerivceImpl.findById(42);
		assertEquals(song, returnedSong);
	}

	@Test
	public void testindByTitle() {
		when(songRepository.findByTitle(song.getTitle())).thenReturn(song);

		Song returnedSong = songSerivceImpl.findByTitle(song.getTitle());
		assertEquals(song, returnedSong);
	}

	@Test
	public void testsaveSong() {
		when(songRepository.save(song)).thenReturn(song);
		songSerivceImpl.saveSong(song);

		verify(songRepository).save(song);
	}

	@Test
	public void testdeleteSongById() {
		songSerivceImpl.deleteSongById(song.getId());

		verify(songRepository).delete(song.getId());
	}

}
