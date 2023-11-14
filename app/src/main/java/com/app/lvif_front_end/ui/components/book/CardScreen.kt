package com.app.lvif_front_end.ui.components.book

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.app.lvif_front_end.models.book.BookModel
import java.net.URLEncoder

@Composable
fun CardScreen(
    modifier: Modifier,
    book: BookModel,
    navController: NavController
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.elevatedCardElevation(10.dp),

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = book.user.image,
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                        alignment = Alignment.CenterStart,
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.padding(start = 16.dp))
                    Text(
                        text = book.user.alias ?: "Anonymous",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row {
                    IconButton(
                        content = {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "UPGRADE",
                                tint = Color.Blue,
                            )
                        },
                        onClick = {
                            navController.navigate(
                                "book?id=${book.bookId}&action=2&name=${book.name}&description=${book.description}"
                            )
                        }
                    )
                    IconButton(
                        content = {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "DELETE",
                                tint = Color.Red,
                            )
                        },
                        onClick = {}
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = book.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = book.description,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

