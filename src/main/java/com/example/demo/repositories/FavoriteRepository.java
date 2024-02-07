package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer>{

}
