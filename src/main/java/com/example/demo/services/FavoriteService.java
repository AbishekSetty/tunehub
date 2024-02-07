package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Favorite;

public interface FavoriteService {

	void addFavorite(Favorite favorite);

	List<Favorite> fetchAllFavorite();

}
