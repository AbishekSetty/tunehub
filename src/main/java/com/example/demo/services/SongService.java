package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Song;

public interface SongService {

	public void addSong(Song song);

	public List<Song> fetchAllSongs();

	public boolean songExists(String name);

	public void updateSong(Song s);

	public Song getSongById(int songId);

	public void deleteSong(Song songToDelete);

	public List<Song> getSongsByIds(int[] songIds);

	

	

}
