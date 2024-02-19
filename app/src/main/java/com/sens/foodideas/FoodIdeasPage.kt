package com.sens.foodideas

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sens.foodideas.model.Recipe
import com.sens.foodideas.model.recipes
import com.sens.foodideas.ui.theme.FoodIdeasTheme

@Composable
fun FoodIdeasPage(recipes: List<Recipe>, modifier: Modifier = Modifier) {
    Scaffold(topBar = { FoodIdeasTopBar() }) {
        LazyColumn(contentPadding = it) {
            items(recipes) {recipe ->
                FoodIdeasItem(recipe = recipe)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodIdeasTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row {
//                Image(painter = TODO(), contentDescription = null)
                Text(
                    text = stringResource(id = R.string.top_bar),
                    style = MaterialTheme.typography.displayMedium
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun FoodIdeasItem(recipe: Recipe, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_small))
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column {
            Text(
                text = stringResource(id = recipe.nameRes),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
            )
            FoodIdeasImage(
                imagerRes = recipe.imageRes,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            ) {
                expanded = !expanded
            }
            if (expanded)
                Text(
                    text = stringResource(id = R.string.burger_desc),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )
        }
    }
}

@Composable
fun FoodIdeasImage(
    @DrawableRes imagerRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = imagerRes),
            contentDescription = null,
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .clickable { onClick() }
        )
    }
}

@Preview("Light", showBackground = true, showSystemUi = true)
@Preview("Dark", showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    FoodIdeasTheme {
        FoodIdeasPage(recipes = recipes)
    }
}