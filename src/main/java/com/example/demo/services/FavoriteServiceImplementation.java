package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Favorite;
import com.example.demo.repositories.FavoriteRepository;

@Service
public class FavoriteServiceImplementation implements FavoriteService {
	@Autowired
	FavoriteRepository repo;

	@Override
	public void addFavorite(Favorite favorite) {
		repo.save(favorite);
		
	}

	@Override
	public List<Favorite> fetchAllFavorite() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
