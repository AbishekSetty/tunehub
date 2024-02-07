package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.entities.Favorite;
import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.entities.Users;
import com.example.demo.services.FavoriteService;
import com.example.demo.services.SongService;
import com.example.demo.services.UsersService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class FavoriteController {
	@Autowired
	UsersService userService;
@Autowired
FavoriteService favoriteService;
@Autowired
SongService songService;
@GetMapping("/favorite")
public String favorite() {
    return "favorite";
}
@GetMapping("/createFavorite")
public String createFavorite(Model model) {
	List<Song> songList=songService.fetchAllSongs();
	model.addAttribute("songs", songList);
    return "createFavorite";
}

@GetMapping("/editFavorite")
public String editFavorite(Model model) {
	List<Song> songList=songService.fetchAllSongs();
	model.addAttribute("songs", songList);
    return "editFavorite";
}

@PostMapping("addFavorite")
public String addFavorite(@RequestParam(name = "selectedSongs") int[] songIds,
		@RequestParam(name = "email") String email) {

    // Fetch the user based on the user ID
   Users user = userService.getUser(email);

    // Fetch the selected songs based on the song IDs
    List<Song> selectedSongs = songService.getSongsByIds(songIds);

    // Create a new Favorite instance
   Favorite favorite = new Favorite();
    favorite.setUser(user);
    favorite.setSongs(selectedSongs);
    // Save the new Favorite to the database
    favoriteService.addFavorite(favorite);

    // Update the Song table to mark selected songs as favorites
   for( Song s: selectedSongs)
	{
		
		s.getFavorite().setId(favorite.getId());
		// update song object in db
		songService.updateSong(s);
	}
    // Redirect to the customer home page or any other appropriate page
    return "favorite";
}


    
@GetMapping("/displayFavorite")
public String displayFavorite(Model model) {
	 List<Favorite> favoriteList = favoriteService.fetchAllFavorite();
	    model.addAttribute("favorites", favoriteList);
    return "displayFavorite";
}


}
