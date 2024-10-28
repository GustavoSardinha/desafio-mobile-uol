package com.sardinha.desafiouol.services

import android.content.Context
import android.graphics.Color
import android.widget.TextView
import androidx.cardview.widget.CardView

class ContentRatingManager {
    companion object {
        fun configureContentRating(
            context: Context,
            textView: TextView,
            cardView: CardView,
            contentRating: String
        ) {
            when (contentRating) {
                "10 anos" -> {
                    textView.text = "10 anos"
                    cardView.setCardBackgroundColor(Color.parseColor("#2196F3")) // Azul
                    textView.setTextColor(Color.WHITE)
                    textView.text = "10"
                }

                "12 anos" -> {
                    textView.text = "12 anos"
                    cardView.setCardBackgroundColor(Color.parseColor("#FF9800")) // Verde
                    textView.setTextColor(Color.WHITE)
                    textView.text = "12"
                }

                "14 anos" -> {
                    textView.text = "14 anos"
                    cardView.setCardBackgroundColor(Color.parseColor("#FF5722")) // Laranja
                    textView.setTextColor(Color.WHITE)
                    textView.text = "14"
                }

                "16 anos" -> {
                    textView.text = "16 anos"
                    cardView.setCardBackgroundColor(Color.parseColor("#D32F2F")) // Vermelho escuro
                    textView.setTextColor(Color.WHITE)
                    textView.text = "16"
                }

                "18 anos" -> {
                    textView.text = "18 anos"
                    cardView.setCardBackgroundColor(Color.parseColor("#000000")) // Vermelho
                    textView.setTextColor(Color.WHITE)
                    textView.text = "18"
                }

                else -> {
                    textView.text = "Livre"
                    cardView.setCardBackgroundColor(Color.parseColor("#4CAF50")) // Cinza para 'Livre' ou desconhecido
                    textView.setTextColor(Color.WHITE)
                    textView.text = "L"
                }
            }
        }
    }
}