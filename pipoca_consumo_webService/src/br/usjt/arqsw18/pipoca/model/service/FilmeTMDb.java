package br.usjt.arqsw18.pipoca.model.service;

import java.util.Arrays;

public class FilmeTMDb {
	private String poster_path;
	private boolean adult;
	private String overview;
	private String realese_date;
	private String title;
	private double popularity;
	private int [] genre_ids;
	@Override
	public String toString() {
		return "FilmeTMDb [poster_path=" + poster_path + ", adult=" + adult + ", overview=" + overview
				+ ", realese_date=" + realese_date + ", title=" + title + ", popularity=" + popularity + ", genre_ids="
				+ Arrays.toString(genre_ids) + "]";
	}
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}
	public boolean isAdult() {
		return adult;
	}
	public void setAdult(boolean adult) {
		this.adult = adult;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getRealese_date() {
		return realese_date;
	}
	public void setRealese_date(String realese_date) {
		this.realese_date = realese_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPopularity() {
		return popularity;
	}
	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}
	public int[] getGenre_ids() {
		return genre_ids;
	}
	public void setGenre_ids(int[] genre_ids) {
		this.genre_ids = genre_ids;
	}
	
}
