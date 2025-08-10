package com.example.movie_catalogue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.movie_catalogue.service.TmdbClient;

@SpringBootTest(classes = MovieCatalogueApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE, properties = {
		"tmdb.api.key=dummy",
		"tmdb.base.url=https://api.themoviedb.org/3",
		"tmdb.image.base=https://image.tmdb.org/t/p/w500"
})
class MovieCatalogueApplicationTests {

	// Prevent real HTTP calls during context startup
	@MockBean
	TmdbClient tmdbClient;

	@Test
	void contextLoads() {
		// If the Spring context starts, this test passes.
	}
}
