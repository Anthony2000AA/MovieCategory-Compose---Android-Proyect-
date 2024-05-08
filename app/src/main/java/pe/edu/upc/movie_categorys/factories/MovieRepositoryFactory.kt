package pe.edu.upc.movie_categorys.factories

import pe.edu.upc.movie_categorys.repository.MovieRepository

class MovieRepositoryFactory {

    companion object{
        private var movieRepository: MovieRepository? = null

        fun getMovieRepositoryFactory(): MovieRepository{
            if(movieRepository==null){
                movieRepository= MovieRepository(
                    movieService= MovieServiceFactory.getMovieServiceFactory(),
                    movieDao= MovieDaoFactory.getMovieDaoFactory()

                )


            }
            return movieRepository as MovieRepository
        }
    }
}