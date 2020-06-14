package com.example.currencyconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = arrayOf("USD", "RUB")
        var active_conv = "USD"
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                active_conv = parent.getItemAtPosition(position).toString()
                if (active_conv == "USD") second.text = "RUB"
                else second.text = "USD"

                convert_button.text = "Конвертировать!"
            }

            override fun onNothingSelected(parent: AdapterView<*>){
            }
        }

        convert_button.setOnClickListener {
            if (active_conv == "USD") {
                try {
                val save = DecimalFormat("#0.00").format(number.text.toString().toFloat() * 74.0)
                val ready = "${number.text} USD = $save RUB"
                    convert_button.text = ready
                } catch (e: Exception) {
                    convert_button.text = "Некорректный ввод"
                }
            }

            else {
                try {
                    val save = round(number.text.toString().toFloat() / 74 * 100) / 100
                    val ready = "${number.text} RUB = $save USD"
                    convert_button.setText(ready)
                } catch (e: Exception) { convert_button.setText("Некорректный ввод") }
            }
        }

        number.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                convert_button.text = "Конвертировать!"
            }

        })

    }
}