package com.example.ordercake

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ordercake.model.Product

@Composable
fun SummaryScreen(
    product: Product,
    onClickSendOrderButton: (String, String) -> Unit,
    onClickCancelButton: () -> Unit
) {
    val subject = stringResource(id = R.string.new_cake_order)
    val summary = stringResource(id = R.string.order_details,
        product.quantity,
        product.flavor.optionInformation,
        product.date.optionInformation,
        product.subtotal
    )
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        ContentUnit(
            title = stringResource(id = R.string.summary_quantity),
            content = if (product.quantity <= 1) "${product.quantity} ${product.name}"
                        else "${product.quantity} ${product.name}s"
        )
        ContentUnit(title = stringResource(id = R.string.summary_flavor), content = product.flavor.optionInformation)
        ContentUnit(title = stringResource(id = R.string.summary_pickup_date), content = product.date.optionInformation)
        Text(
            text = stringResource(id = R.string.total_price, product.subtotal),
            fontSize = 25.sp,
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 15.dp)
                .align(alignment = Alignment.End),
        )
        Spacer(modifier = Modifier.height(300.dp))
        Button(
            onClick = { onClickSendOrderButton(subject, summary) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFAF1349)),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 10.dp)
                .width(350.dp),
        ) {
            Text(
                text = stringResource(id = R.string.share_order),
                color = Color.White,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
            )
        }
        Button(
            onClick = { onClickCancelButton() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 10.dp)
                .width(350.dp),
            elevation = ButtonDefaults.buttonElevation(10.dp),
        ) {
            Text(
                text = stringResource(id = R.string.cancel),
                color = Color(0xFFAF1349),
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun ContentUnit(title: String, content: String) {
    Column {
        Text(
            text = title,
            fontSize = 17.sp,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
        )
        Text(
            text = content,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
        )
        Divider(
            thickness = 1.dp,
            color = Color(0xFFF1B8B8),
            modifier = Modifier
                .width(400.dp)
                .padding(horizontal = 15.dp),
        )
    }
}
