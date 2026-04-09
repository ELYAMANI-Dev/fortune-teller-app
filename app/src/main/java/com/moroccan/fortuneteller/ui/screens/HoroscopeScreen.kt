package com.moroccan.fortuneteller.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moroccan.fortuneteller.data.model.HoroscopeSign
import com.moroccan.fortuneteller.ui.theme.*
import com.moroccan.fortuneteller.viewmodel.FortuneTellerViewModel

@Composable
fun HoroscopeScreen(viewModel: FortuneTellerViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val selectedSign = uiState.selectedSign

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BrandSurface)
                .padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
            Text("⭐ Daily Horoscope", style = MaterialTheme.typography.headlineMedium, color = BrandWhite)
        }

        if (selectedSign == null) {
            Text(
                "Choose your sign",
                style = MaterialTheme.typography.titleMedium,
                color = BrandPrimaryLight,
                modifier = Modifier.padding(16.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.weight(1f).padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(com.moroccan.fortuneteller.data.mock.MockData.horoscopes) { sign ->
                    SignCard(sign = sign, onClick = { viewModel.selectSign(sign) })
                }
            }
        } else {
            SignDetailView(sign = selectedSign, onBack = { viewModel.selectSign(selectedSign).let {
                viewModel.uiState.value.copy() } })
        }
    }
}

@Composable
private fun SignCard(sign: HoroscopeSign, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .aspectRatio(0.9f)
            .clip(RoundedCornerShape(14.dp))
            .background(BrandSurfaceAlt)
            .clickable(onClick = onClick)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(sign.emoji, fontSize = 32.sp)
        Spacer(Modifier.height(4.dp))
        Text(sign.name, style = MaterialTheme.typography.labelSmall, color = BrandWhite, textAlign = TextAlign.Center)
        Text(sign.dateRange, style = MaterialTheme.typography.labelSmall, color = BrandGray, fontSize = 9.sp, textAlign = TextAlign.Center)
    }
}

@Composable
private fun SignDetailView(sign: HoroscopeSign, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(sign.emoji, fontSize = 72.sp)
        Spacer(Modifier.height(8.dp))
        Text(sign.name, style = MaterialTheme.typography.headlineMedium, color = BrandWhite)
        Text(sign.dateRange, style = MaterialTheme.typography.labelSmall, color = BrandGray)
        Spacer(Modifier.height(20.dp))

        // Reading card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(BrandSurfaceAlt)
                .border(1.dp, BrandPrimary, RoundedCornerShape(16.dp))
                .padding(20.dp)
        ) {
            Column {
                Text("🔮 Today's Reading", style = MaterialTheme.typography.titleMedium, color = BrandPrimary)
                Spacer(Modifier.height(10.dp))
                Text(sign.dailyReading, style = MaterialTheme.typography.bodyMedium, color = BrandWhite)
            }
        }

        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            InfoChip("🍀 Lucky #", sign.luckNumber.toString())
            InfoChip("🎨 Color", sign.luckColor)
        }

        Spacer(Modifier.height(24.dp))
        OutlinedButton(
            onClick = onBack,
            colors = ButtonDefaults.outlinedButtonColors(contentColor = BrandPrimary),
            border = androidx.compose.foundation.BorderStroke(1.dp, BrandPrimary)
        ) {
            Text("← Back to signs")
        }
    }
}

@Composable
private fun InfoChip(label: String, value: String) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(BrandSurface)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(label, style = MaterialTheme.typography.labelSmall, color = BrandGray)
        Text(value, style = MaterialTheme.typography.titleMedium, color = BrandGold)
    }
}
