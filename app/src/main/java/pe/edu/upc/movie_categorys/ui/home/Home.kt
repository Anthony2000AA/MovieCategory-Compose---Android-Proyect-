package pe.edu.upc.movie_categorys.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.movie_categorys.ui.favoriteMovie.MovieFavorite
import pe.edu.upc.movie_categorys.ui.movieSearch.MovieSearch

@Composable
fun Home() {

    val navController = rememberNavController()


    Scaffold(
        bottomBar = {
            SimpleBottomNavigation(navController = navController)
        }
    ) {paddingValues ->
        Box(
            modifier= Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ){

            NavHost(navController = navController, startDestination = Routes.MoviesSearch.route) {
                composable(Routes.MoviesSearch.route){
                    MovieSearch()
                }
                composable(Routes.MovieFavorite.route){
                   MovieFavorite()
                }

            }


        }

    }







}


sealed class Routes(val route: String) {
    data object MoviesSearch : Routes("Search")
    data object MovieFavorite : Routes("Favorite")

    data object MovieDetail : Routes("Detail") {
        const val routeWithArgument = "MovieDetail/{id}"
        const val argument = "id"
    }


}




@Composable
fun SimpleBottomNavigation(navController: NavController) {
    val menuItems = listOf(
        Routes.MoviesSearch,
        Routes.MovieFavorite
    )

    NavigationBar(
        containerColor = Color(0xFF6200EE),
        contentColor = Color.White
    ) {
        menuItems.forEach { route ->
            val isSelected = navController.currentDestination?.route == route.route
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = when (route) {
                            Routes.MoviesSearch -> Icons.Default.Search
                            Routes.MovieFavorite -> Icons.Default.Favorite
                            else -> Icons.Default.AccountBox// Manejar otros casos
                        },
                        contentDescription = null
                    )
                },
                label = { Text(text = route.route) }, // Cambia esto por la etiqueta correcta
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navController.navigate(route.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}