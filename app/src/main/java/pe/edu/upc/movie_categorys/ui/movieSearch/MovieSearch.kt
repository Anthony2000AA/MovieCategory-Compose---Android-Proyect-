package pe.edu.upc.movie_categorys.ui.movieSearch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.upc.movie_categorys.factories.MovieRepositoryFactory
import pe.edu.upc.movie_categorys.model.data.Movie


@Composable
fun MovieSearch(){

    val movieList= remember{
        mutableStateOf(emptyList<Movie>())
    }

    val category=remember{
        mutableStateOf("")
    }


    //para el combobox
    val isExpanded= remember{
        mutableStateOf(false)
    }


    Scaffold(
        containerColor = Color.White,
    ) { paddingValues->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(), // Para ocupar todo el espacio disponible en la pantalla
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Spacer(modifier = Modifier.height(16.dp)) // Agrega un espacio de 16dp antes del Search

            Search(category,movieList,isExpanded)
            MovieList(movieList)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    category: MutableState<String>,
    movieList: MutableState<List<Movie>>,
    isExpanded: MutableState<Boolean>
) {
    val endpoints = listOf("upcoming", "popular", "top_rated")

    val movieRepository = MovieRepositoryFactory.getMovieRepositoryFactory()


    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded.value,
            onExpandedChange = { isExpanded.value = it },
            modifier = Modifier
                .width(145.dp)
                .height(45.dp)
                .padding(0.1.dp)
        ) {
            TextField(
                value = category.value,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded.value)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor(),
                placeholder = { Text(text = "Select Category", style = TextStyle(fontSize = (10.sp))) }
            )
            ExposedDropdownMenu(
                expanded = isExpanded.value,
                onDismissRequest = { isExpanded.value = false },
                modifier = Modifier.width(145.dp)
            ) {

                endpoints.forEach { endpoint ->
                    DropdownMenuItem(
                        text = { Text(text = endpoint, style = TextStyle(fontSize = (10.sp))) },
                        onClick = {
                            category.value = endpoint
                            isExpanded.value = false
                        }
                    )


                }
                //creo que aqui puede ir el codigo de asignar el valor seleccionado al modelo
            }


        }

        //Boton para buscar
        Button(onClick = {
            movieRepository.getMoviesForCategory( category.value){
                movieList.value=it?.results ?: emptyList()
            }
        }) {
            Text(text = "Search")
        }
    }

}


@Composable
fun MovieList(movieList: MutableState<List<Movie>>) {
    val movieRepository = MovieRepositoryFactory.getMovieRepositoryFactory()

    LazyColumn {
        items(movieList.value) { movie ->
            val isFavorite= remember{
                mutableStateOf(false)
            }

            isFavorite.value=movie.isFavorite

            Card(modifier = Modifier.padding(4.dp).fillMaxWidth()) {
                Row {
                    Column(modifier= Modifier
                        .padding(4.dp)
                        .weight(4f))
                    {
                        Text(text = movie.title)
                        Text(text = "Pelicula para adultos: "+movie.adult.toString())
                    }

                    IconButton(onClick = {
                        isFavorite.value=!isFavorite.value

                        movie.isFavorite=isFavorite.value

                        if(movie.isFavorite){
                            movieRepository.insertMovie(movie.id,movie.title,movie.overview,movie.posterPath,movie.backdropPath,movie.voteAverage,movie.releaseDate,movie.popularity)
                        }else{
                            movieRepository.deleteMovie(movie.id)
                        }

                    }) {
                        Icon(Icons.Filled.Favorite,"Favorite", tint=if(isFavorite.value)Color.Red else Color.Gray)

                    }
                }
            }
        }
    }
}