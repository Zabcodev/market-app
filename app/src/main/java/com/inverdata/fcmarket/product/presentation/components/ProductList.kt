package com.inverdata.fcmarket.product.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.inverdata.fcmarket.stock.domain.model.Product

@Composable
fun ProductList(
    products: LazyPagingItems<Product>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products.itemCount) { index ->
            products[index]?.let { product ->
                /*ProductItemList(product = product)*/
            }
        }
    }
}