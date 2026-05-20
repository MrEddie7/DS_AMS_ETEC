package org.example.project.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Cores premium inspiradas em design cyber-fitness
val DarkBackground = Color(0xFF0F0F12)
val DarkSurface = Color(0xFF1C1C24)
val DarkSurfaceVariant = Color(0xFF282834)

val NeonLime = Color(0xFFCCFF00) // Destaques e sucesso
val NeonPurple = Color(0xFF8A2BE2) // Primária - Ações importantes
val CyberCyan = Color(0xFF00E5FF) // Secundária - Info e detalhes
val MutedText = Color(0xFF8E8E9F)
val WhiteText = Color(0xFFF5F5F7)
val ErrorColor = Color(0xFFFF453A)

private val DarkColorScheme = darkColorScheme(
    primary = NeonPurple,
    onPrimary = Color.White,
    secondary = CyberCyan,
    onSecondary = Color.Black,
    tertiary = NeonLime,
    onTertiary = Color.Black,
    background = DarkBackground,
    onBackground = WhiteText,
    surface = DarkSurface,
    onSurface = WhiteText,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = WhiteText,
    outline = Color(0xFF3E3E50),
    error = ErrorColor
)

private val LightColorScheme = lightColorScheme(
    primary = NeonPurple,
    onPrimary = Color.White,
    secondary = CyberCyan,
    onSecondary = Color.Black,
    tertiary = NeonLime,
    onTertiary = Color.Black,
    background = Color(0xFFF2F2F7),
    onBackground = Color(0xFF1C1C1E),
    surface = Color.White,
    onSurface = Color(0xFF1C1C1E),
    surfaceVariant = Color(0xFFE5E5EA),
    onSurfaceVariant = Color(0xFF1C1C1E),
    outline = Color(0xFFC7C7CC),
    error = Color(0xFFFF3B30)
)

@Composable
fun ControladorTreinoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Forçamos o tema escuro para dar uma estética premium uniforme (cyber-fitness), 
    // mas deixamos o parâmetro para flexibilidade futura.
    val colorScheme = if (darkTheme) DarkColorScheme else DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
