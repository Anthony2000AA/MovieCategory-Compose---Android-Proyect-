package pe.edu.upc.movie_categorys.model.remote

import pe.edu.upc.movie_categorys.model.data.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {



    @GET("{category}")
    fun getMoviesCategory(
        @Path("category") category:String,//path porque es parte de la url
        @Query("api_key") api_key:String="3cae426b920b29ed2fb1c0749f258325"//query porque es un parametro, y el nombre tiene que ser igual al endpoint que te den
        //el que va en "" es lo importante , ese tiene que estar igual, el nombre que este afeura es cualquiera si quieres
    ): Call<ApiResponse>

}