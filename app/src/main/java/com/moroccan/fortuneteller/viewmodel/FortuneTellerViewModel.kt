package com.moroccan.fortuneteller.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moroccan.fortuneteller.data.mock.MockData
import com.moroccan.fortuneteller.data.model.ChatMessage
import com.moroccan.fortuneteller.data.model.HoroscopeSign
import com.moroccan.fortuneteller.data.model.TarotCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class FortuneTellerUiState(
    val messages: List<ChatMessage> = emptyList(),
    val inputText: String = "",
    val isTyping: Boolean = false,
    val selectedSign: HoroscopeSign? = null,
    val drawnCards: List<TarotCard> = emptyList(),
    val isDrawingCards: Boolean = false
)

class FortuneTellerViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(FortuneTellerUiState())
    val uiState: StateFlow<FortuneTellerUiState> = _uiState.asStateFlow()

    private var messageIdCounter = 0

    init {
        val greeting = MockData.greetingMessages.random()
        _uiState.value = _uiState.value.copy(
            messages = listOf(ChatMessage(messageIdCounter++, greeting, isUser = false))
        )
    }

    fun onInputChange(text: String) {
        _uiState.value = _uiState.value.copy(inputText = text)
    }

    fun sendMessage() {
        val text = _uiState.value.inputText.trim()
        if (text.isBlank()) return

        val userMsg = ChatMessage(messageIdCounter++, text, isUser = true)
        _uiState.value = _uiState.value.copy(
            messages = _uiState.value.messages + userMsg,
            inputText = "",
            isTyping = true
        )

        viewModelScope.launch {
            delay((1500L..3000L).random())
            val replyTemplate = MockData.fortuneReplies.random()
            val reply = replyTemplate.replace("{num}", (1..99).random().toString())
            val botMsg = ChatMessage(messageIdCounter++, reply, isUser = false)
            _uiState.value = _uiState.value.copy(
                messages = _uiState.value.messages + botMsg,
                isTyping = false
            )
        }
    }

    fun selectSign(sign: HoroscopeSign) {
        _uiState.value = _uiState.value.copy(selectedSign = sign)
    }

    fun drawCards() {
        _uiState.value = _uiState.value.copy(isDrawingCards = true, drawnCards = emptyList())
        viewModelScope.launch {
            delay(1000L)
            val cards = MockData.tarotCards.shuffled().take(3)
            _uiState.value = _uiState.value.copy(drawnCards = cards, isDrawingCards = false)
        }
    }
}
