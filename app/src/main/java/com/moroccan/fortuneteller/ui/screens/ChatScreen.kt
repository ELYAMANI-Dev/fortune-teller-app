package com.moroccan.fortuneteller.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moroccan.fortuneteller.data.model.ChatMessage
import com.moroccan.fortuneteller.ui.theme.*
import com.moroccan.fortuneteller.viewmodel.FortuneTellerViewModel
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(viewModel: FortuneTellerViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(uiState.messages.size) {
        if (uiState.messages.isNotEmpty()) {
            scope.launch { listState.animateScrollToItem(uiState.messages.size - 1) }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandBackground)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BrandSurface)
                .padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🔮", fontSize = 28.sp)
                Spacer(Modifier.width(10.dp))
                Column {
                    Text("Chowafa", style = MaterialTheme.typography.titleMedium, color = BrandWhite)
                    Text("Moroccan Fortune Teller", style = MaterialTheme.typography.labelSmall, color = BrandPrimaryLight)
                }
            }
        }

        // Messages
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f).padding(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.messages) { msg ->
                ChatBubble(msg)
            }
            if (uiState.isTyping) {
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("🔮", fontSize = 18.sp)
                        Spacer(Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp, 12.dp, 12.dp, 2.dp))
                                .background(BotBubble)
                                .padding(horizontal = 14.dp, vertical = 10.dp)
                        ) {
                            Text("✦ ✦ ✦", color = BrandPrimaryLight, fontSize = 14.sp)
                        }
                    }
                }
            }
        }

        // Input bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(BrandSurface)
                .navigationBarsPadding()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = uiState.inputText,
                onValueChange = viewModel::onInputChange,
                placeholder = { Text("Ask your question…", color = BrandGray, fontSize = 13.sp) },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BrandPrimary,
                    unfocusedBorderColor = BrandSurfaceAlt,
                    focusedTextColor = BrandWhite,
                    unfocusedTextColor = BrandWhite,
                    cursorColor = BrandPrimary
                ),
                maxLines = 3,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = { viewModel.sendMessage() })
            )
            Spacer(Modifier.width(8.dp))
            FloatingActionButton(
                onClick = viewModel::sendMessage,
                containerColor = BrandPrimary,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(Icons.Default.Send, contentDescription = "Send", modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
private fun ChatBubble(msg: ChatMessage) {
    val arrangement = if (msg.isUser) Arrangement.End else Arrangement.Start
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.Bottom
    ) {
        if (!msg.isUser) {
            Text("🔮", fontSize = 18.sp, modifier = Modifier.padding(end = 6.dp))
        }
        Box(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .clip(
                    if (msg.isUser) RoundedCornerShape(12.dp, 12.dp, 2.dp, 12.dp)
                    else RoundedCornerShape(12.dp, 12.dp, 12.dp, 2.dp)
                )
                .background(if (msg.isUser) UserBubble else BotBubble)
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            Text(
                text = msg.text,
                color = BrandWhite,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
