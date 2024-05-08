package pe.edu.upc.movie_categorys.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MovieDao {

    @Insert
    fun insert(movieEntity: MovieEntity)

    @Delete
    fun delete(movieEntity: MovieEntity)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Int): MovieEntity?
}