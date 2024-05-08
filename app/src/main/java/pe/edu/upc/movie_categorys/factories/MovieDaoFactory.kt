package pe.edu.upc.movie_categorys.factories

import pe.edu.upc.movie_categorys.MyApplication
import pe.edu.upc.movie_categorys.model.local.MovieDao

class MovieDaoFactory {

    companion object{
        private var movieDa: MovieDao? = null

        fun getMovieDaoFactory():MovieDao{
            if(movieDa == null){
                movieDa = AppDatabaseFactory.getAppDatabase(MyApplication.getContext()).movieDao()
            }
            return movieDa as MovieDao
        }
    }
}