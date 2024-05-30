package com.adammcneilly.set

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PokemonListItem(
    pokemon: Pokemon,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
) {
    with(sharedTransitionScope) {
        ListItem(
            headlineContent = {
                Text(
                    text = pokemon.name,
                    modifier = Modifier
                        .sharedBounds(
                            rememberSharedContentState(
                                key = "${pokemon.name}-text",
                            ),
                            animatedVisibilityScope = animatedVisibilityScope,
                            enter = fadeIn(),
                            exit = fadeOut(),
                            resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds(),
                        ),
                )
            },
            leadingContent = {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(pokemon.imageUrl)
                        .crossfade(true)
                        .placeholderMemoryCacheKey("${pokemon.name}-image") //  same key as shared element key
                        .memoryCacheKey("${pokemon.name}-image") // same key as shared element key
                        .build(),
                    contentDescription = "${pokemon.name} image",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(48.dp)
                        .sharedElement(
                            rememberSharedContentState(key = "${pokemon.name}-image"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        ),
                )
            },
            modifier = modifier,
        )
    }
}
