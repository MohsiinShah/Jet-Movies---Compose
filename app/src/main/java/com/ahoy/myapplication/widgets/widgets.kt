package com.ahoy.myapplication.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.ahoy.myapplication.model.Movie
import com.ahoy.myapplication.model.getMovies
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle

@Preview
@Composable
fun MovieRow(movie: Movie = getMovies()[0], OnItemClick: (String) -> Unit = {}){
    
    var expanded by remember{
        mutableStateOf(false)
    }
    
    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(6.dp)
        .clickable {
            OnItemClick(movie.id)
        },
        shape = RoundedCornerShape(CornerSize(6.dp)),
        elevation = 10.dp
    ) {

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Surface(modifier = Modifier
                .size(100.dp)
                .padding(12.dp),
                shape = RectangleShape,
                elevation = 0.dp) {

                Image(painter = rememberAsyncImagePainter(ImageRequest.Builder(LocalContext.current)
                    .data(movie.images[0])
                    .crossfade(true)
                    .transformations(CircleCropTransformation())
                    .build()
                ), contentDescription = "Movie Image", alignment = Alignment.TopCenter)
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                Text(text =  "Released: ${movie.year}", style = MaterialTheme.typography.caption)

                Icon(imageVector = if (!expanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp, contentDescription = "Arrow Down",
                    Modifier
                        .clickable { expanded = !expanded }
                        .size(25.dp), tint = Color.DarkGray)

                AnimatedVisibility(visible = expanded) {
                    Column(Modifier.padding(bottom = 6.dp)) {
                        Text(buildAnnotatedString {

                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)) {
                                append("Plot: ")
                            }

                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp, fontWeight = FontWeight.Light)) {
                                append(movie.plot)
                            }
                        }, modifier = Modifier.padding(6.dp))
                        
                        Divider(Modifier.padding(6.dp))
                        
                        Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                        Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.caption)

                        RatingBar(
                            modifier = Modifier.padding(top = 10.dp),
                            value = movie.rating.toFloat()/2,
                            config = RatingBarConfig()
                                .style(RatingBarStyle.HighLighted),
                            onValueChange = {

                            },
                            onRatingChanged = {
                            }
                        )
                        Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.caption)
                    }
                }
            }
        }
    }
}