package com.example.calculadoraviagem

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referenciando os elementos do layout
        val editTextDistancia = findViewById<EditText>(R.id.editTextDistancia)
        val editTextVelocidade = findViewById<EditText>(R.id.editTextVelocidade)
        val editTextPedagios = findViewById<EditText>(R.id.editTextPedagios)
        val buttonCalcular = findViewById<Button>(R.id.buttonCalcular)
        val textViewResultado = findViewById<TextView>(R.id.textViewResultado)

        // Preço do combustível e do pedágio
        val precoCombustivel = 5.85
        val precoPedagio = 10.50
        val consumoPorKm = 12.0 // O carro faz 12 km por litro

        // Ação de clique no botão "Calcular"
        buttonCalcular.setOnClickListener {
            // Obtendo os valores dos EditTexts
            val distancia = editTextDistancia.text.toString().toDoubleOrNull() ?: 0.0
            val velocidadeMedia = editTextVelocidade.text.toString().toDoubleOrNull() ?: 0.0
            val quantidadePedagios = editTextPedagios.text.toString().toIntOrNull() ?: 0

            if (distancia > 0 && velocidadeMedia > 0) {
                // Cálculos
                val tempoGasto = distancia / velocidadeMedia
                val litrosUtilizados = distancia / consumoPorKm
                val custoCombustivel = litrosUtilizados * precoCombustivel
                val custoPedagios = quantidadePedagios * precoPedagio
                val custoTotal = custoCombustivel + custoPedagios

                // Exibindo o resultado no TextView
                val resultado = """
                    Tempo gasto: %.2f horas
                    Velocidade média: %.2f km/h
                    Distância percorrida: %.2f km
                    Quantidade de litros utilizados: %.2f litros
                    Custo com combustível: R$ %.2f
                    Custo com pedágios: R$ %.2f
                    Custo total da viagem: R$ %.2f
                """.trimIndent().format(
                    tempoGasto, velocidadeMedia, distancia,
                    litrosUtilizados, custoCombustivel, custoPedagios, custoTotal
                )
                textViewResultado.text = resultado
            } else {
                textViewResultado.text = "Por favor, insira valores válidos."
            }
        }
    }
}
