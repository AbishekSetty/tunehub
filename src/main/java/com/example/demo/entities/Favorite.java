package com.example.demo.entities;

import java.util.List;

import jakarta.persistence.*;
@Entity
public class Favorite {
	 	@Id
	 	@GeneratedValue(strategy = GenerationType.AUTO)
	    public int id;
	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    public Users user;
	    @OneToMany
	    List<Song> songs;
		public Favorite() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Favorite(int id, Users user, List<Song> songs) {
			super();
			this.id = id;
			this.user = user;
			this.songs = songs;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public Users getUser() {
			return user;
		}
		public void setUser(Users user) {
			this.user = user;
		}
		public List<Song> getSongs() {
			return songs;
		}
		public void setSongs(List<Song> songs) {
			this.songs = songs;
		}
		@Override
		public String toString() {
			return "Favorite [id=" + id + ", user=" + user + ", songs=" + songs + "]";
		}
	    
}
