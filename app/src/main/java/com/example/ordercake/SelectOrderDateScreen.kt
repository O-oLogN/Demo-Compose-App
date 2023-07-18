package com.example.ordercake

import androidx.compose.runtime.Composable
import com.example.ordercake.common.UISelectScreen
import com.example.ordercake.model.Option
import com.example.ordercake.model.Product

@Composable
fun SelectOrderDateScreen(
    currentDateOptionList: List<Option>,
    currentScreen: String,
    product: Product,
    onClickNextButton: () -> Unit,
    onClickCancelButton: () -> Unit,
    onClickRadioButton: (Option) -> Unit,
    onClickDeleteButton: () -> Unit
) {
    UISelectScreen(
        currentOptionList = currentDateOptionList,
        currentScreen = currentScreen,
        product = product,
        onClickNextButton = onClickNextButton,
        onClickCancelButton = onClickCancelButton,
        onClickRadioButton = { onClickRadioButton(it) },
        onClickDeleteButton = onClickDeleteButton
    )
}