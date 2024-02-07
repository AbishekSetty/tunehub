package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SongController {
	@Autowired
	SongService service;
	@Autowired
	PlaylistService playlistService;
	@PostMapping("/addSong")
 public String addSong(@ModelAttribute Song song)
 {
		boolean songStatus=service.songExists(song.getName());
		if(songStatus == false)
		{
			service.addSong(song);
			System.out.println("Song added succesfully");
		}
		else
		{	
			System.out.println("Song already exist");
			 
		}
		return "adminHome"; 
	
 }
	@GetMapping("/viewSongs")
	 public String viewSongs(Model model)
	 {
		 List<Song> songsList=service.fetchAllSongs();
		model.addAttribute("songs", songsList);
		 return "displaySongs";
	 }
	@GetMapping("/playSongs")
	 public String playSongs(Model model)
	 {
		boolean premiumUser =true;
		if(premiumUser==true)
		{
			List<Song> songsList=service.fetchAllSongs();
			model.addAttribute("songs", songsList);
			 return "displaySongs";
		}
		else {
			return "makePayment";
		}
	 }
	 @GetMapping("/deleteSong")
	 public String deleteSong(Model model) {
		 List<Song> songList=service.fetchAllSongs();
		 model.addAttribute("songs", songList);
	 	return "deleteSong";
	 }
	 
	 @PostMapping("deleteSongSelected")
	 public String deleteSongSelected(@RequestParam(name = "selectedSongs") int[] selectedSongs) {
	 	
		 for (int songId : selectedSongs) {
	            Song songToDelete = service.getSongById(songId);

	            if (songToDelete != null) {
	                // Remove the song from associated playlists
	                List<Playlist> playlists = songToDelete.getPlaylists();
	                for (Playlist playlist : playlists) {
	                    playlist.getSongs().remove(songToDelete);
	                    playlistService.updatePlaylist(playlist);
	                }

	                // Delete the song
	                service.deleteSong(songToDelete);
	            }
	        }

	        return "adminHome";
	    }
	 }
	 
	 

