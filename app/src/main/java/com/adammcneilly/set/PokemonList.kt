package com.adammcneilly.set

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PokemonList(
    pokemonList: List<Pokemon>,
    onClick: (Pokemon) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        itemsIndexed(pokemonList) { index, pokemon ->
            PokemonListItem(
                pokemon = pokemon,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                modifier = Modifier
                    .clickable {
                        onClick.invoke(pokemon)
                    },
            )

            if (index != pokemonList.lastIndex) {
                HorizontalDivider()
            }
        }
    }
}
