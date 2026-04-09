package com.moroccan.fortuneteller.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moroccan.fortuneteller.data.model.TarotCard
import com.moroccan.fortuneteller.ui.theme.*
import com.moroccan.fortuneteller.viewmodel.FortuneTellerViewModel

@Composable
fun TarotScreen(viewModel: FortuneTellerViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    val rotation by rememberInfiniteTransition(label = "spin").animateFloat(
        initialValue = -3f, targetValue = 3f,
        animationSpec = infiniteRepeatable(tween(1500), RepeatMode.Reverse),
        label = "card_rock"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandBackground)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BrandSurface)
                .padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
            Text("🃏 Tarot Reading", style = MaterialTheme.typography.headlineMedium, color = BrandWhite)
        }

        Spacer(Modifier.height(24.dp))

        // Crystal ball / deck art
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(60.dp))
                .background(BrandSurfaceAlt)
                .border(2.dp, BrandPrimary, RoundedCornerShape(60.dp))
                .rotate(if (uiState.isDrawingCards) rotation else 0f),
            contentAlignment = Alignment.Center
        ) {
            Text("🔮", fontSize = 52.sp)
        }

        Spacer(Modifier.height(20.dp))

        Text(
            "Draw 3 cards to reveal your fate",
            style = MaterialTheme.typography.bodyMedium,
            color = BrandGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = viewModel::drawCards,
            enabled = !uiState.isDrawingCards,
            colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary),
            modifier = Modifier.padding(horizontal = 40.dp).fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                if (uiState.isDrawingCards) "Reading the cards…" else "Draw Cards",
                modifier = Modifier.padding(vertical = 6.dp),
                fontSize = 16.sp
            )
        }

        Spacer(Modifier.height(28.dp))

        if (uiState.drawnCards.isNotEmpty()) {
            Text("Your Reading", style = MaterialTheme.typography.titleMedium, color = BrandPrimary)
            Spacer(Modifier.height(16.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 20.dp)
            ) {
                items(uiState.drawnCards) { card ->
                    TarotCardView(card = card, position = uiState.drawnCards.indexOf(card))
                }
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun TarotCardView(card: TarotCard, position: Int) {
    val positionLabel = listOf("Past", "Present", "Future")[position]
    Column(
        modifier = Modifier
            .width(150.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(BrandSurfaceAlt)
            .border(1.dp, BrandPrimary, RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(positionLabel, style = MaterialTheme.typography.labelSmall, color = BrandGold)
        Spacer(Modifier.height(8.dp))
        Text(card.emoji, fontSize = 40.sp)
        Spacer(Modifier.height(8.dp))
        Text(card.name, style = MaterialTheme.typography.titleMedium, color = BrandWhite, textAlign = TextAlign.Center)
        Spacer(Modifier.height(6.dp))
        Text(card.meaning, style = MaterialTheme.typography.labelSmall, color = BrandGray, textAlign = TextAlign.Center)
    }
}
