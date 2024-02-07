 package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PlaylistController {
	@Autowired
	SongService songService;
	@Autowired
	PlaylistService playlistService;
	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model) {
		
		List<Song> songList=songService.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "createPlaylist";
	}
	
	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		//updating playlist table
		playlistService.addPlaylist(playlist);
		
		//updating song table
		List<Song> songList=playlist.getSongs();
		for( Song s: songList)
		{
			
			s.getPlaylists().add(playlist);
			// update song object in db
			songService.updateSong(s);
		}
		return "adminHome";
	}
	@GetMapping("/viewPlaylist")
	public String viewPlaylist(Model model) {
			List<Playlist> allPlaylist=playlistService.fetchAllPlaylist();
			model.addAttribute("allplaylist", allPlaylist);
		return "displayPlaylist";
	}
	@GetMapping("/deletePlaylist")
	public String deletePlaylist(Model model) {
			List<Playlist> allPlaylist=playlistService.fetchAllPlaylist();
			model.addAttribute("allplaylist", allPlaylist);
			
			
		return "deletePlaylist";
	}
	@PostMapping("/deleteSelectedPlaylist")
	public String delete(@RequestParam(name = "selectedPlaylists", required = false) int[] selectedPlaylists,  Model model) {
		 for (int playlistId : selectedPlaylists) {
		        Playlist playlistToDelete = playlistService.getPlaylistById(playlistId);
		        if (playlistToDelete != null) {
		            // Remove the playlist from associated songs
		            List<Song> songs = playlistToDelete.getSongs();
		            for (Song song : songs) {
		                song.getPlaylists().remove(playlistToDelete);
		                songService.updateSong(song);
		            }

		            // Remove the songs from the playlist
		            playlistToDelete.getSongs().clear();

		            // Delete the playlist
		            playlistService.deletePlaylist(playlistToDelete);
		        }
		    }

		    // Refresh the list of playlists
		    List<Playlist> allPlaylists = playlistService.fetchAllPlaylist();
		    model.addAttribute("allplaylist", allPlaylists);

		    return "adminHome";
		
	}
	
	
}
