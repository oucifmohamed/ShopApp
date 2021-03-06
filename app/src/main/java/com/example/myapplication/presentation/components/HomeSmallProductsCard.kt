package com.example.myapplication.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.myapplication.R
import com.example.myapplication.domain.models.Product
import com.example.myapplication.util.ProductCardType

@Composable
fun HomeSmallProductsCard(
    modifier: Modifier,
    product: Product,
    onSelect: (String) -> Unit,
    onToggleLikeButton: (Product) -> Unit,
    cardType: ProductCardType
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .clickable {
                onSelect(product.id)
            }
            .height(88.dp),
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 17.dp)
        ) {
            Row {
                Image(
                    painter = rememberImagePainter(
                        request = ImageRequest
                            .Builder(LocalContext.current)
                            .placeholder(R.color.grey)
                            .data(product.image)
                            .build()
                    ),
                    modifier = Modifier
                        .background(Color(0xFFF9FAFF))
                        .size(56.dp),
                    contentDescription = "",
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.h6
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = if (product.promotionPrice == 0f)
                            "Price: ${product.originalPrice} DA"
                        else
                            "Price: ${product.promotionPrice} DA",
                        style = MaterialTheme.typography.body2,
                        color = Color.Black.copy(alpha = 0.6f)
                    )
                }
            }

            if(cardType == ProductCardType.FAVORITEPRODUCT) {
                IconButton(
                    onClick = {
                        onToggleLikeButton(product)
                    },
                    modifier = Modifier
                        .size(48.dp,48.dp)
                        .align(Alignment.CenterEnd)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp, 24.dp),
                        tint = Color.Red
                    )
                }
            }
        }
    }
}