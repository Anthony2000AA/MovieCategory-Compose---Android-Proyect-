package pe.edu.upc.movie_categorys.repository

import android.util.Log
import pe.edu.upc.movie_categorys.model.data.ApiResponse
import pe.edu.upc.movie_categorys.model.data.Movie
import pe.edu.upc.movie_categorys.model.local.MovieDao
import pe.edu.upc.movie_categorys.model.local.MovieEntity
import pe.edu.upc.movie_categorys.model.remote.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository (
    private val movieService: MovieService,
    private val movieDao: MovieDao
){
    fun getMoviesForCategory(category:String, callback:(ApiResponse?)->Unit){

        val searchMovies= movieService.getMoviesCategory(category=category)

        searchMovies.enqueue(object: Callback<ApiResponse>{
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if(response.isSuccessful){
                    //val movies = response.body()?.results ?: emptyList()    si quiero solo la lista, arriba en los parametros de esta funcion, debo poner que solo queiro la lista List<Movies>
                    val movies = response.body()

                    //Esto es para que cuando se cargue la lista de peliculas, se muestre si es favorita o no
                    for (movie in movies?.results ?: emptyList()){
                        movie.isFavorite = isFavorite(movie.id)
                    }


                    callback(movies)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                t.message?.let {
                    Log.d("MovieRepository", it)
                }
            }
        })
    }


    fun insertMovie(id: Int, title: String, overview: String, posterPath: String, backdropPath: String, voteAverage: Double, releaseDate: String, popularity: Double) {
        movieDao.insert(MovieEntity(
            id = id,
            title = title,
            overview = overview,
            posterPath = posterPath,
            backdropPath = backdropPath,
            voteAverage = voteAverage,
            releaseDate = releaseDate,
            popularity = popularity
        ))
    }

    fun deleteMovie(id:Int){
        movieDao.delete(MovieEntity(
            id = id,
            title = "",
            overview = "",
            posterPath = "",
            backdropPath = "",
            voteAverage = 0.0,
            releaseDate = "",
            popularity = 0.0
        ))
    }

    fun isFavorite(id: Int):Boolean{
        return (movieDao.getMovieById(id) != null)
    }

    fun getAllMovies(): List<MovieEntity>{
        return movieDao.getAllMovies()
    }
}
