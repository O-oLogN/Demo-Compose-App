package com.example.ordercake.common

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ordercake.R

@Composable
fun TopAppOrderCakeBar(
    title: String,
    isStartScreen: Boolean,
    onClickNavigationBackButton: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 25.sp,
                color = Color.White,
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(Color(0xFFEC6A6A)),
        navigationIcon = {
            if (!isStartScreen) {
                IconButton(
                    onClick = { onClickNavigationBackButton() },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_navigate_before_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(45.dp),
                    )
                }
            }
        }
    )
}
