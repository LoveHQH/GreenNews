package com.hqh.greennews.ui.utils

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

class Text2Speak {
    lateinit var tts : TextToSpeech
//    lateinit var context: Context
    fun text2Speech(context: Context, speakRate: Float){
        tts = TextToSpeech(context, TextToSpeech.OnInitListener {
            tts.language = Locale.getDefault()
        })
    }
}