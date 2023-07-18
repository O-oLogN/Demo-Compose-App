package com.example.ordercake.common

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ordercake.R
import com.example.ordercake.model.DataSource
import com.example.ordercake.model.Option
import com.example.ordercake.model.Product

@Composable
fun OptionCard(
    option: Option,
    nameOfSelectedOption: String,
    onClickButton: (Option) -> Unit
) {
    Log.d("NAME OF SELECTED OPTION", nameOfSelectedOption)
    Row(
        modifier = Modifier.selectable (
            selected = option.optionInformation == nameOfSelectedOption,
            onClick = {
                onClickButton(option)
            },
        )
    ) {
        RadioButton(
            selected = option.optionInformation == nameOfSelectedOption,
            onClick = {
                onClickButton(option)
            },
            colors = RadioButtonDefaults.colors(Color(0xFFF06060))
        )
        Text(
            text = option.optionInformation,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp),
        )
        Spacer(modifier = Modifier.height(5.dp))
    }
}
@Composable
fun UISelectScreen(
    currentOptionList: List<Option>,
    currentScreen: String = "",
    product: Product = Product(),
    onClickRadioButton: (Option) -> Unit = {},
    onClickNextButton: () -> Unit = {},
    onClickCancelButton: () -> Unit = {},
    onClickDeleteButton: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        currentOptionList.forEach {
            OptionCard(
                option = it,
                nameOfSelectedOption = if (currentScreen == DataSource.Screen.Flavor.name) product.flavor.optionInformation
                else product.date.optionInformation,
                onClickButton = { option -> onClickRadioButton(option) },
            )
        }
        Divider(
            thickness = 1.dp,
            modifier = Modifier
                .width(400.dp)
                .padding(horizontal = 15.dp),
            color = Color(0xFFF1A2A2)
        )
        Row {
            TextButton(
                onClick = { onClickDeleteButton() },
                enabled = (currentScreen == DataSource.Screen.Flavor.name && product.flavor.optionInformation.isNotEmpty())
                        || (currentScreen == DataSource.Screen.Date.name && product.date.optionInformation.isNotEmpty()),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color(0xFF1F38C2),
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                ),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_playlist_remove_24),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp),
                )
                Text(
                    text = stringResource(id = R.string.delete),
                    fontSize = 17.sp,
                    modifier = Modifier.padding(vertical = 14.dp),
                )
            }
            Text(
                text = stringResource(id = R.string.total_price, product.subtotal),
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(top = 90.dp, start = 20.dp),
            )
        }
        Spacer(modifier = Modifier.height(350.dp))
        Row {
            Button(
                onClick = { onClickCancelButton() },
                colors = ButtonDefaults.buttonColors(Color.White),
                elevation = ButtonDefaults.buttonElevation(10.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .width(160.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    color = Color(0xFFAF1349),
                    fontSize = 20.sp,
                )
            }
            Button(
                onClick = { onClickNextButton() },
                colors = ButtonDefaults.buttonColors(Color(0xFFAF1349)),
                elevation = ButtonDefaults.buttonElevation(10.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .width(170.dp),
                enabled =
                    (currentScreen == DataSource.Screen.Flavor.name && product.flavor.optionInformation.isNotEmpty())
                            || (currentScreen == DataSource.Screen.Date.name && product.date.optionInformation.isNotEmpty()),
            ) {
                Text(
                    text = stringResource(id = R.string.next),
                    color = Color.White,
                    fontSize = 20.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSelectionUI() {
    UISelectScreen(DataSource.flavorOptionList)
}