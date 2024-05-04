package com.lbg.techassest.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lbg.techassest.R
import com.lbg.techassest.domain.GenreDomain
import com.lbg.techassest.ui.theme.Green40

@Composable
fun DetailsMovieContent(
    onClickBack: () -> Unit,
    onClickFavorite: () -> Unit,
    title: String,
    description: String,
    imagePoster: String,
    isFavoriteMovie: Boolean,
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically

        ) {

            Icon(
                modifier = Modifier.clickable {
                    onClickBack()
                },
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = null,
                tint = Color.Black,
            )
            //title
            Text(
                text = "Details",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
                modifier = Modifier.padding(start = 24.dp)
            )

            Icon(
                modifier = Modifier.clickable {
                    onClickFavorite()
                },
                painter = painterResource(id = if(isFavoriteMovie) R.drawable.ic_love else R.drawable.ic_love_border),
                contentDescription = null,
                tint = Color.Black,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box {
            androidx.compose.material3.Card(
                modifier = Modifier
                    .offset(x = 29.dp, y = 150.dp)
                    .width(95.dp)
                    .height(120.dp),
                colors = CardDefaults.cardColors(
                    containerColor = androidx.compose.ui.graphics.Color.Gray),
                shape = RoundedCornerShape (16.dp),
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp),
                    model = imagePoster,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                )
            }

            //Title
            Text(
                modifier = Modifier
                    .width(210.dp)
                    .height(48.dp)
                    .offset(x = 140.dp, y = 220.dp),
                text = title,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
                fontWeight = FontWeight(600),
            )

        }

        Spacer(modifier = Modifier.height(150.dp))

        //Description Title
        Text(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            text = "Description",
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
            fontWeight = FontWeight(600),
        )

        Spacer(modifier = Modifier.height(25.dp))

        //Description
        Text(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            text = description,
            textAlign = TextAlign.Justify,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
            fontWeight = FontWeight(400),
        )

    }
}

@Preview
@Composable
fun DetailsMovieContentPrev() {
    DetailsMovieContent(
        title = "Spiderman No Way Home",
        description = "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
        imagePoster = "https://image.tmdb.org/t/p/w500/eLzStFuergouErSQlfABthuQHCJ.jpg",
        onClickBack = {},
        onClickFavorite = {},
        isFavoriteMovie = false
    )
}