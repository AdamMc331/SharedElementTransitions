package com.adammcneilly.set

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adammcneilly.set.theme.SETTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ConfigureEdgeToEdgeWindow()

            SETTheme {
                Surface {
                    val navController = rememberNavController()
                    val pokemonList = getTestData()

                    NavHost(
                        startDestination = "home",
                        navController = navController,
                    ) {
                        composable("home") {
                            PokemonList(
                                pokemonList = pokemonList,
                                onClick = { pokemon ->
                                    navController.navigate("detail/${pokemon.name}")
                                },
                            )
                        }

                        composable("detail/{pokemonName}") {
                            val pokemon = pokemonList.first { pok ->
                                pok.name == it.arguments?.getString("pokemonName")
                            }

                            PokemonDetail(pokemon)
                        }
                    }
                }
            }
        }
    }

    /**
     * Enables edge to edge support of this activity.
     *
     * If you'd like, you can override the default behavior of system bars by
     * customizing the parameters in the call to [enableEdgeToEdge].
     */
    @Composable
    private fun ConfigureEdgeToEdgeWindow() {
        DisposableEffect(Unit) {
            enableEdgeToEdge()
            onDispose {}
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
