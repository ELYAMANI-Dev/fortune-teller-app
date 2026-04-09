package com.moroccan.fortuneteller.data.mock

import com.moroccan.fortuneteller.data.model.HoroscopeSign
import com.moroccan.fortuneteller.data.model.TarotCard

object MockData {

    val fortuneReplies = listOf(
        "The stars speak of a great opportunity approaching you very soon... 🌟",
        "I see a strong positive energy surrounding you today. Trust your instincts. ✨",
        "The cards reveal a journey — not of distance, but of the heart. ❤️",
        "Someone from your past is thinking of you right now... 👁️",
        "Your luck number today is {num}. Keep it close. 🔢",
        "The moon whispers secrets only you can hear tonight. 🌙",
        "A surprise is coming before the next full moon. Be ready. 🎁",
        "I see financial changes — patience will bring great rewards. 💰",
        "Your aura shines bright purple — a sign of spiritual growth. 💜",
        "The future holds three blessings waiting to unfold. 🌸",
        "Beware of false friends — the cards warn of jealousy nearby. ⚠️",
        "Love is closer than you think. Open your heart to it. 💕",
        "Your wish... yes, I see it. The universe is listening. 🌌",
        "A new door will open when you stop looking at the closed one. 🚪",
        "The crystal ball shows a happy family gathering approaching. 🏠"
    )

    val greetingMessages = listOf(
        "Welcome, dear soul. I am Chowafa, the Moroccan fortune teller. Ask me anything about your future... 🔮",
        "The spirits have brought you to me today. What does your heart seek? 🌙",
        "I have been expecting you. The stars told me you would come. Ask freely... ✨"
    )

    val horoscopes = listOf(
        HoroscopeSign("Aries", "♈", "Mar 21 – Apr 19",
            "Today is a powerful day for you, Aries. A bold decision you make will open unexpected doors. Trust your fire.",
            7, "Red"),
        HoroscopeSign("Taurus", "♉", "Apr 20 – May 20",
            "Stability and comfort are your rewards today. A financial matter resolves itself in your favor.",
            4, "Green"),
        HoroscopeSign("Gemini", "♊", "May 21 – Jun 20",
            "Communication is your superpower today. An important conversation will change your perspective completely.",
            11, "Yellow"),
        HoroscopeSign("Cancer", "♋", "Jun 21 – Jul 22",
            "Your intuition is sharper than ever. A dream from last night holds a message — reflect on it.",
            2, "Silver"),
        HoroscopeSign("Leo", "♌", "Jul 23 – Aug 22",
            "The spotlight finds you today. Share your talents boldly — recognition is near.",
            1, "Gold"),
        HoroscopeSign("Virgo", "♍", "Aug 23 – Sep 22",
            "Details matter today. A small adjustment in your routine brings a big improvement in results.",
            6, "Navy Blue"),
        HoroscopeSign("Libra", "♎", "Sep 23 – Oct 22",
            "Balance is restored. A relationship matter reaches a harmonious conclusion today.",
            9, "Pink"),
        HoroscopeSign("Scorpio", "♏", "Oct 23 – Nov 21",
            "Secrets rise to the surface. Truth is revealed and brings unexpected freedom.",
            8, "Crimson"),
        HoroscopeSign("Sagittarius", "♐", "Nov 22 – Dec 21",
            "Adventure calls! An opportunity to expand your horizons arrives — say yes to it.",
            3, "Purple"),
        HoroscopeSign("Capricorn", "♑", "Dec 22 – Jan 19",
            "Your hard work is noticed. A reward or promotion is closer than you think.",
            10, "Brown"),
        HoroscopeSign("Aquarius", "♒", "Jan 20 – Feb 18",
            "Innovation and originality set you apart today. An unusual idea is worth pursuing.",
            5, "Electric Blue"),
        HoroscopeSign("Pisces", "♓", "Feb 19 – Mar 20",
            "Your compassion heals someone close to you today. Creative inspiration flows freely.",
            12, "Sea Green")
    )

    val tarotCards = listOf(
        TarotCard("The Fool", "🃏", "New beginnings, innocence, a leap of faith"),
        TarotCard("The Magician", "🎩", "Willpower, skill, manifestation of desires"),
        TarotCard("The High Priestess", "🌙", "Intuition, mystery, inner knowledge"),
        TarotCard("The Empress", "👑", "Fertility, abundance, nurturing energy"),
        TarotCard("The Emperor", "⚔️", "Authority, structure, fatherly protection"),
        TarotCard("The Lovers", "💞", "Love, harmony, important life choices"),
        TarotCard("The Chariot", "🏆", "Victory, determination, overcoming obstacles"),
        TarotCard("Strength", "🦁", "Courage, patience, inner strength"),
        TarotCard("The Hermit", "🕯️", "Soul-searching, introspection, guidance"),
        TarotCard("Wheel of Fortune", "🎡", "Cycles, fate, turning points"),
        TarotCard("Justice", "⚖️", "Fairness, truth, cause and effect"),
        TarotCard("The Star", "⭐", "Hope, renewal, spiritual insight"),
        TarotCard("The Moon", "🌕", "Illusion, fear, the subconscious"),
        TarotCard("The Sun", "☀️", "Joy, success, positivity, vitality"),
        TarotCard("The World", "🌍", "Completion, achievement, wholeness")
    )
}
