package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Playlist;
import com.example.demo.repositories.PlaylistRepository;

@Service
public class PlaylistServiceImplementation implements PlaylistService {
	@Autowired
	PlaylistRepository repo;
	
	@Override
	public void addPlaylist(Playlist playlist) {
		repo.save(playlist);
		
	}

	@Override
	public List<Playlist> fetchAllPlaylist() {
		
		return repo.findAll();
	}


	@Override
	public Playlist getPlaylistById(int playlistId) {
	return repo.getPlaylistById(playlistId);
	}

	@Override
	public void deletePlaylist(Playlist playlistToDelete) {
		repo.delete(playlistToDelete);
		
	}

	@Override
	public void updatePlaylist(Playlist playlist) {
		repo.save(playlist);
		
	}

	

}
