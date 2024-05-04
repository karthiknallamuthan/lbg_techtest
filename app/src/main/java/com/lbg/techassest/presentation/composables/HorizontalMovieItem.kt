package com.lbg.techassest.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lbg.techassest.common.convertDateToFormattedString

@Composable
fun HorizontalMovieItem(
    title: String,
    description: String,
    imageUrl: String,
    realeaseDate: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        //elevation = 4.dp
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Card(modifier = Modifier
                .height(150.dp)
                .align(CenterVertically)
                .fillMaxWidth(0.3f),
                shape = RoundedCornerShape(0.dp),
                /*colors = CardDefaults.cardColors(
                    containerColor = Color.Gray.copy(alpha = 0.5f),
                    contentColor = Color.Gray.copy(alpha = 0.5f)
                )*/
            ){
                Column(
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                    )
                }

            }

            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                Text(
                    text = title,
                    maxLines = 1,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))

                //released date -- YYYY-MM-DD to
                Text(text = convertDateToFormattedString(realeaseDate))

            }
        }

    }
}

@Preview
@Composable
fun HorizontalMovieItemPrev(){
    HorizontalMovieItem(
        title = "Fast & Furious X",
        description = "During numerous more than impossible missions, Dom Toretto and his family have been able to outsmart, have more courage, and go faster than any enemy that came their way. But now they will have to face the most lethal opponent they have ever known: a terrible danger that resurfaces from the past, is driven by a bloody thirst for revenge and is ready to tear the family apart and destroy forever everything Dom holds dear. it matters.",
        imageUrl = "https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg",
        onClick = {},
        realeaseDate = "2021-05-19"
    )
}