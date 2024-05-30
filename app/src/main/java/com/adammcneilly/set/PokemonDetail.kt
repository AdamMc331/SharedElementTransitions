package com.adammcneilly.set

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PokemonDetail(
    pokemon: Pokemon,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
) {
    with(sharedTransitionScope) {
        Box(
            modifier = modifier
                .fillMaxSize(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.Center),
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(pokemon.imageUrl)
                        .crossfade(true)
                        .placeholderMemoryCacheKey("${pokemon.name}-image") //  same key as shared element key
                        .memoryCacheKey("${pokemon.name}-image") // same key as shared element key
                        .build(),
                    contentDescription = "${pokemon.name} image",
                    modifier = Modifier
                        .sharedElement(
                            rememberSharedContentState(key = "${pokemon.name}-image"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        )
                        .clip(CircleShape)
                        .size(80.dp),
                )

                Text(
                    text = pokemon.name,
                    style = MaterialTheme.typography.headlineLarge,
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
            }
        }
    }
}
