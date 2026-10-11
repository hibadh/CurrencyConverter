package com.example.currencyconverter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import com.example.currencyconverter.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val taux = mapOf(
        "EUR" to 1.0,
        "USD" to 1.17,
        "TND" to 3.40,
        "GBP" to 0.87,
        "JPY" to 172.50,
        "CAD" to 1.62
    )

    private var premierChiffre = true
    private var resultatAffiche = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        remplirDevises()
        configurerClavier()
        configurerConversion()
        configurerInversion()
    }

    private fun remplirDevises() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.devises,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.spDeviseSource.adapter = adapter
        binding.spDeviseCible.adapter = adapter

        // USD comme source et EUR comme cible
        binding.spDeviseSource.setSelection(0)
        binding.spDeviseCible.setSelection(1)
    }

    private fun configurerClavier() {
        val boutons = listOf(
            binding.btn0, binding.btn1, binding.btn2,
            binding.btn3, binding.btn4, binding.btn5,
            binding.btn6, binding.btn7, binding.btn8,
            binding.btn9, binding.btnPoint
        )

        for (bouton in boutons) {
            bouton.setOnClickListener {
                ajouterCaractere(bouton.text.toString())
            }
        }

        binding.btnC.setOnClickListener {
            binding.tvMontant.text = "0"
            binding.tvResultat.text = ""
            premierChiffre = true
            resultatAffiche = false
        }
    }

    private fun ajouterCaractere(c: String) {
        var montant = binding.tvMontant.text.toString()

        if (premierChiffre) {
            if (c == ".") {
                montant = "0."
            } else {
                montant = c
            }
            premierChiffre = false
        } else {
            if (montant.length >= 10) return
            if (c == "." && montant.contains(".")) return

            montant += c
        }

        binding.tvMontant.text = montant
        resultatAffiche = false
    }

    private fun configurerConversion() {
        binding.btnConvertir.setOnClickListener {
            val montant = binding.tvMontant.text.toString().toDouble()

            if (montant == 0.0) {
                Toast.makeText(
                    this,
                    getString(R.string.toast_montant),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            effectuerConversion()
        }
    }

    private fun convertir(
        montant: Double,
        source: String,
        cible: String
    ): Double {
        val montantEUR = montant / taux.getValue(source)
        return montantEUR * taux.getValue(cible)
    }

    private fun effectuerConversion() {
        val montant = binding.tvMontant.text.toString().toDouble()
        val source = binding.spDeviseSource.selectedItem.toString()
        val cible = binding.spDeviseCible.selectedItem.toString()

        val resultat = convertir(montant, source, cible)

        binding.tvResultat.text = String.format(
            Locale.US,
            "%.2f",
            resultat
        )

        resultatAffiche = true
    }

    private fun configurerInversion() {
        binding.btnInverser.setOnClickListener {
            val source = binding.spDeviseSource.selectedItemPosition
            val cible = binding.spDeviseCible.selectedItemPosition

            binding.spDeviseSource.setSelection(cible)
            binding.spDeviseCible.setSelection(source)

            if (resultatAffiche) {
                effectuerConversion()
            }
        }
    }
}
