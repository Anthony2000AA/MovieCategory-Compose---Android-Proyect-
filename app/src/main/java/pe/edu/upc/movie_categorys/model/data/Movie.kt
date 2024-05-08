package pe.edu.upc.movie_categorys.model.data

import com.google.gson.annotations.SerializedName


data class ApiResponse(
    val page: Int,

    val results: List<Movie>
)


data class Movie(

    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int,

    //Agregamos un atributo para saber si la pelicula es favorito o no
    var isFavorite: Boolean = false



)

/*
* Movie:
*           "adult": false,
            "backdrop_path": "/bWIIWhnaoWx3FTVXv6GkYDv3djL.jpg",
            "genre_ids": [
                878,
                27,
                28
            ],
            "id": 940721,
            "original_language": "ja",
            "original_title": "ゴジラ-1.0",
            "overview": "Postwar Japan is at its lowest point when a new crisis emerges in the form of a giant monster, baptized in the horrific power of the atomic bomb.",
            "popularity": 1994.551,
            "poster_path": "/hkxxMIGaiCTmrEArK7J56JTKUlB.jpg",
            "release_date": "2023-11-03",
            "title": "Godzilla Minus One",
            "video": false,
            "vote_average": 7.832,
            "vote_count": 762*/