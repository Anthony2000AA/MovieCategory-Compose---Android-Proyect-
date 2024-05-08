package pe.edu.upc.movie_categorys.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upc.movie_categorys.model.local.MovieDao
import pe.edu.upc.movie_categorys.model.local.MovieEntity


@Database(entities=[MovieEntity::class],version =1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
}