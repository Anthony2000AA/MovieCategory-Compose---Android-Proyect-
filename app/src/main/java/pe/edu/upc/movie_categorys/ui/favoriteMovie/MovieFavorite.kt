package pe.edu.upc.movie_categorys.ui.favoriteMovie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pe.edu.upc.movie_categorys.factories.MovieRepositoryFactory
import pe.edu.upc.movie_categorys.model.data.Movie
import pe.edu.upc.movie_categorys.model.local.MovieEntity

@Composable
fun MovieFavorite() {

    var movieFavoriteList: List<MovieEntity> = emptyList()
    val movieRepository = MovieRepositoryFactory.getMovieRepositoryFactory()


    Scaffold(
        containerColor = Color.White,
    ) {paddingValues->

        movieFavoriteList = movieRepository.getAllMovies()

        if (movieFavoriteList.isEmpty()) {
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No hay películas favoritas",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp),
                    color = Color.Gray
                )
            }

        } else {
            LazyColumn (modifier= Modifier.padding(paddingValues)) {
                items(movieFavoriteList) { movie ->
                    MovieFavoriteItem(movie = movie)
                }
            }
        }


    }

}

@Composable
fun MovieFavoriteItem(movie: MovieEntity) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(text = movie.title)

    }
}