package com.example.currencyconverter

import androidx.lifecycle.ViewModel

class CurrencyViewModel : ViewModel() {
    var montant = "0"
    var deviseSource = 0
    var deviseCible = 1
    var resultatAffiche = false
}
