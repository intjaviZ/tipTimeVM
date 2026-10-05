package com.javiviz.tiptimeviewmodel.tiptime.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.NumberFormat
import kotlin.text.toDoubleOrNull

class TipTimeViewModel : ViewModel() {
    private var _amountInput = MutableStateFlow("")
    private var _tipInput = MutableStateFlow("")
    var _roundUp = MutableStateFlow(false)

    val amountInput : StateFlow<String> = _amountInput.asStateFlow()
    val tipInput : StateFlow<String> = _tipInput.asStateFlow()
    val roundUp : StateFlow<Boolean> = _roundUp.asStateFlow()

    fun setAmountInput(amountText: String) {
        _amountInput.value = amountText
    }

    fun setTipInput(tipText: String) {
        _tipInput.value = tipText
    }

    fun setRoundUp(round : Boolean) {
        _roundUp.value = round
    }

    fun calculateTip(): String {
        val amount = _amountInput.value.toDoubleOrNull() ?: 0.0
        val tipPercent = _tipInput.value.toDoubleOrNull() ?: 0.0
        var tip = tipPercent / 100 * amount
        if (roundUp.value) {
            tip = kotlin.math.ceil(tip)
        }
        return NumberFormat.getCurrencyInstance().format(tip)
    }
}