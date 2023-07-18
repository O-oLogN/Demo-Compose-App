package com.example.ordercake

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ordercake.common.TopAppOrderCakeBar
import com.example.ordercake.model.DataSource
import com.example.ordercake.ui.theme.OrderCakeTheme


@Composable
fun OrderCakeApp(
    appViewModel: AppViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val product by appViewModel.product.collectAsState()
    var currentIndexOfCakeList by rememberSaveable { mutableStateOf(0) }
    var currentScreen by rememberSaveable { mutableStateOf("Start") }
    Scaffold(
        topBar = {
            TopAppOrderCakeBar(
                title = currentScreen,
                isStartScreen = currentScreen == "Start",
                onClickNavigationBackButton = {
                    when(currentScreen) {
                        DataSource.Screen.Flavor.name -> {
                            currentScreen = DataSource.Screen.Start.name
                            appViewModel.resetProduct()
                        }
                        DataSource.Screen.Date.name -> currentScreen = DataSource.Screen.Flavor.name
                        DataSource.Screen.Summary.name -> currentScreen = DataSource.Screen.Date.name
                    }
                    navController.navigateUp()
                }
            )
        },
        content = {
            NavHost(
                navController = navController,
                startDestination = DataSource.Screen.Start.name,
                modifier = Modifier.padding(it)
            ) {
                composable(route = DataSource.Screen.Start.name) {
                    StartScreen(
                        currentCake = DataSource.cakeList[currentIndexOfCakeList],
                        onClickQuantityButton = { base, name, price ->
                            currentScreen = DataSource.Screen.Flavor.name
                            navController.navigate(route = currentScreen)
                            appViewModel.updateProductSubtotalAndQuantityAndName(base, name, price)
                        },
                        onClickNextCake = { ++currentIndexOfCakeList }
                    )
                }
                composable(route = DataSource.Screen.Flavor.name) {
                    SelectCakeFlavorScreen(
                        currentFlavorOptionList = DataSource.flavorOptionList,
                        currentScreen = currentScreen,
                        product = product,
                        onClickNextButton = {
                            currentScreen = DataSource.Screen.Date.name
                            navController.navigate(route = currentScreen)
                        },
                        onClickCancelButton = { onClickCancelButton(navController, appViewModel) },
                        onClickRadioButton = { option -> appViewModel.updateProductFlavorAndSubtotal(option) },
                        onClickDeleteButton = { appViewModel.updateProductOnFlavorDeleteButton() }
                    )
                }
                composable(route = DataSource.Screen.Date.name) {
                    SelectOrderDateScreen(
                        currentDateOptionList = DataSource.dateOptionList,
                        currentScreen = currentScreen,
                        product = product,
                        onClickNextButton = {
                            currentScreen = DataSource.Screen.Summary.name
                            navController.navigate(route = currentScreen)
                        },
                        onClickCancelButton = { onClickCancelButton(navController, appViewModel) },
                        onClickRadioButton = { option ->
                            appViewModel.updateProductDate(option)
                        },
                        onClickDeleteButton = { appViewModel.updateProductOnDateDeleteButton() }
                    )
                }
                composable(route = DataSource.Screen.Summary.name) {
                    val context = LocalContext.current
                    SummaryScreen(
                        product = product,
                        onClickCancelButton = {
                            currentScreen = DataSource.Screen.Start.name
                            currentIndexOfCakeList = 0
                            onClickCancelButton(navController, appViewModel)
                        },
                        onClickSendOrderButton = { subject, summary ->
                            shareOrderBetweenApps(
                                context = context,
                                subject = subject,
                                summary = summary
                            )
                        }
                    )
                }
            }
        }
    )
}
private fun onClickCancelButton(
    navController: NavHostController,
    appViewModel: AppViewModel
) {
    navController.popBackStack(
        route = DataSource.Screen.Start.name,
        inclusive = false
    )
    appViewModel.resetProduct()
}
private fun shareOrderBetweenApps(
    context: Context,
    subject: String,
    summary: String,
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.new_cake_order)
        )
    )
}
@Preview(showBackground = true)
@Composable
fun PreviewOrderCakeApp() {
    OrderCakeTheme {
        OrderCakeApp()
    }
}