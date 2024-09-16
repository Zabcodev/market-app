package com.inverdata.fcmarket.product.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.inverdata.fcmarket.R
import com.inverdata.fcmarket.stock.domain.model.Product

@Composable
fun ProductItemList(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (image, description, quantity, price) = createRefs()

            Box(
                modifier = Modifier
                    .width(110.dp)
                    .height(100.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.no_image),
                    contentDescription = stringResource(R.string.app_name),
                )
            }

            Text(
                modifier = Modifier.constrainAs(description) {
                    start.linkTo(image.end)
                    top.linkTo(parent.top, margin = 12.dp)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                },
                text = product.description,
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                maxLines = 2
            )

            Text(
                modifier = Modifier.constrainAs(quantity) {
                    start.linkTo(image.end)
                    top.linkTo(description.bottom, margin = 4.dp)
                },
                text = "Disponible: ${product.salesQuantity} unidades.",
                fontSize = 13.sp,
                fontWeight = FontWeight.W500
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Blue)
                    .padding(8.dp)
                    .constrainAs(price) {
                        start.linkTo(image.end)
                        top.linkTo(quantity.bottom, margin = 8.dp)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${product.productPriceOne}$",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }


        }
        /*Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .width(140.dp)
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.no_image),
                    contentDescription = stringResource(R.string.app_name),
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = product.description,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W600
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Disponible: ${product.salesQuantity} unidades.",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W500
                )
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Blue)
                        .padding(8.dp)
                ) {
                    Text(
                        text = "${product.productPriceOne}$",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }*/
    }
}

/*@Preview(showBackground = true)
@Composable
fun ProductItemListPreview() {
    ProductItemList()
}*/
