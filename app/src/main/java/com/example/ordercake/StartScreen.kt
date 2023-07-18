package com.example.ordercake

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ordercake.model.Cake


@Composable
fun StartScreen(
    currentCake: Cake,
    onClickNextCake: () -> Unit=  {},
    onClickQuantityButton: (base: Int, name: String, price: Int) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        DisplayCake(
            cake = currentCake,
            onClickNextCake = { onClickNextCake() }
        )
        Text(
            text = "Order  ${currentCake.cakeName}s",
            fontSize = 40.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 80.dp),
            fontFamily = FontFamily(Font(R.font.scriptin)),
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(90.dp))
        QuantityButton(
            number = "One",
            cake =  currentCake,
            onClickBuyCakeButton = {
                onClickQuantityButton(1, currentCake.cakeName, currentCake.cakePrice)
            },
        )
        QuantityButton(
            number = "Six",
            cake = currentCake,
            onClickBuyCakeButton = {
                onClickQuantityButton(6, currentCake.cakeName, currentCake.cakePrice)
            },
        )
        QuantityButton(
            number = "Twelve",
            cake = currentCake,
            onClickBuyCakeButton = {
                onClickQuantityButton(12, currentCake.cakeName, currentCake.cakePrice)
            },
        )
    }
}
@Composable
fun DisplayCake(
    cake: Cake,
    onClickNextCake: () -> Unit)
{
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = cake.cakeImageId),
            contentDescription = null,
            modifier = Modifier
                .width(350.dp)
                .height(350.dp)
                .weight(4f)
                .padding(start = 80.dp),
        )
        IconButton(
            onClick = { onClickNextCake() },
            modifier = Modifier
                .weight(1f)
                .size(40.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_navigate_next_24),
                contentDescription = null,
                tint = Color(0xFFEB6868)
            )
        }
    }
}
@Composable
fun QuantityButton(number: String, cake: Cake, onClickBuyCakeButton:() -> Unit) {
    Button(
        onClick = { onClickBuyCakeButton() },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA3104B)),
        modifier = Modifier
            .padding(vertical = 10.dp)
            .width(350.dp),
    ) {
        Text(
            text = if (number == "One") "$number ${cake.cakeName}" else "$number ${cake.cakeName}s",
            color = Color.White,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        )
    }
}
