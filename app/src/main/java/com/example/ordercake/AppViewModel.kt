package com.example.ordercake

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ordercake.model.Option
import com.example.ordercake.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel: ViewModel() {
    private val _product: MutableStateFlow<Product> = MutableStateFlow(Product())
    private val _lastFlavorOption: MutableStateFlow<Option> = MutableStateFlow(Option())

    val product: StateFlow<Product> = _product.asStateFlow()

    /**
     * Update [product] with Name, Quantity and Subtotal
     */
    fun updateProductSubtotalAndQuantityAndName(base: Int, name: String, price: Int) {
        _product.value = Product(
            name = name,
            quantity = _product.value.quantity + base,
            subtotal = _product.value.subtotal + base * price,
            date = _product.value.date,
            flavor = _product.value.flavor
        )
    }
    /**
     * Update [product] with Flavor and Subtotal
     */
    fun updateProductFlavorAndSubtotal(option: Option) {
        Log.d("PRODUCT STATE BEFORE", _product.value.toString())
        _product.value = Product(
            name = _product.value.name,
            quantity = _product.value.quantity,
            date = _product.value.date,
            subtotal = _product.value.subtotal + option.optionPrice - _lastFlavorOption.value.optionPrice,
            flavor = Option(
                optionInformation = option.optionInformation,
                optionPrice = option.optionPrice
            )
        )
        Log.d("PRODUCT STATE AFTER", _product.value.toString())
        _lastFlavorOption.value = option
    }
    /**
     * Update [product] with Flavor by re-init Flavor.optionInformation
     * and Flavor.optionPrice and Subtotal
     */
    fun updateProductOnFlavorDeleteButton() {
        _product.value = Product(
            name = _product.value.name,
            quantity = _product.value.quantity,
            date = _product.value.date,
            subtotal = _product.value.subtotal - _lastFlavorOption.value.optionPrice,
            flavor = Option()
        )
        _lastFlavorOption.value = Option()
    }
    /**
     * Update [product] with Date by update Date.optionInformation
     */
    fun updateProductDate(option: Option) {
        _product.value = Product(
            name = _product.value.name,
            quantity = _product.value.quantity,
            date = Option(optionInformation = option.optionInformation),
            subtotal = _product.value.subtotal,
            flavor = _product.value.flavor
        )
    }
    /**
     * Update [product] with Date by re-init Date.information
     */
    fun updateProductOnDateDeleteButton() {
        _product.value = Product(
            name = _product.value.name,
            quantity = _product.value.quantity,
            date = Option(),
            subtotal = _product.value.subtotal,
            flavor = _product.value.flavor
        )
    }
    /**
     * Fully reset [product] back to init state
     */
    fun resetProduct() {
        _product.value = Product()
        _lastFlavorOption.value = Option()
    }
}