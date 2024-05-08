package pe.edu.upc.movie_categorys.factories

import pe.edu.upc.movie_categorys.model.remote.MovieService

class MovieServiceFactory {


    companion object{
        private var movieService: MovieService? = null

        fun getMovieServiceFactory(): MovieService{
            if(movieService== null){
                movieService = RetrofitFactory.getRetrofitFactory().create(MovieService::class.java)
            }

            return movieService as MovieService
        }
    }

}
