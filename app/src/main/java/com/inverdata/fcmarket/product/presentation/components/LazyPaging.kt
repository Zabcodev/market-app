package com.inverdata.fcmarket.product.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.inverdata.fcmarket.stock.domain.model.Product

@Composable
fun LazyPagingProduct(
    data: LazyPagingItems<Product>,
    modifier: Modifier = Modifier
) {
    data.loadState.apply {
        when {
            refresh is LoadState.Loading && data.itemCount == 0 -> {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            refresh is LoadState.NotLoading && data.itemCount == 0 -> {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Error al cargar productos"
                    )
                }
            }

            hasError -> {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Ha ocurrido un error"
                    )
                }
            }

            else -> {
                ProductList(products = data)

                if (append is LoadState.Loading) {
                    Box(
                        modifier = modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}