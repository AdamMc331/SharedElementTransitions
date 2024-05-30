package com.adammcneilly.set

fun getTestData(): List<Pokemon> {
    return listOf(
        "Bulbasaur",
        "Ivysaur",
        "Venusaur",
        "Charmander",
        "Charmeleon",
        "Charizard",
        "Squirtle",
        "Wartortle",
        "Blastoise",
    ).mapIndexed { index, name ->
        val pokedexNumber = index + 1
        Pokemon(
            name = name,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$pokedexNumber.png",
        )
    }
}
