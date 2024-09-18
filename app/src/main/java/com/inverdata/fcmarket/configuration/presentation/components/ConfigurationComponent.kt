package com.inverdata.fcmarket.configuration.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inverdata.fcmarket.R
import com.inverdata.fcmarket.configuration.presentation.ConfigurationState

@Composable
fun ConfigurationComponent(
    state: ConfigurationState,
    onLogOutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "Account circle icon",
                modifier = Modifier.size(160.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (state.userEmail.isNotBlank()) {
                Text(
                    state.userEmail,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Button(
            onClick = {
                onLogOutClick()
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = stringResource(R.string.log_out_text)
            )
        }
    }
}

/*@Preview
@Composable
fun ConfigurationPreview() {
    ConfigurationComponent(
        userEmail = "adm@gmail.com",
        onLogOutClick = {}
    )
}*/
