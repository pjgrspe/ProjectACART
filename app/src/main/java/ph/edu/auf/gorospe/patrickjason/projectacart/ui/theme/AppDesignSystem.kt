package ph.edu.auf.gorospe.patrickjason.projectacart.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

//color
data class AppColorScheme(
    //general colors
    val background: Color,
    val onBackground: Color,
    val primary: Color,
    val onPrimary: Color,
    val primaryDark: Color,
    val onPrimaryDark: Color,
    val secondary: Color,
    val onSecondary: Color,
    val accent: Color,
    val onAccent: Color,

    //error and success
    val error: Color,
    val onError: Color,
    val success: Color,
    val onSuccess: Color
)
//typography
data class AppTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val h6: TextStyle,
    val subtitle1: TextStyle,
    val subtitle2: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val button: TextStyle,
    val caption: TextStyle,
)

//shape
data class AppShape(
    val container: Shape,
    val button: Shape,
    val textField: Shape,
    val card: Shape
)

//size
data class AppSize(
    val tiny: Dp,
    val small: Dp,
    val normal: Dp,
    val medium: Dp,
    val large: Dp
)

val LocalAppColorScheme = staticCompositionLocalOf<AppColorScheme> {
    AppColorScheme(
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        primaryDark = Color.Unspecified,
        onPrimaryDark = Color.Unspecified,
        secondary = Color.Unspecified,
        onSecondary = Color.Unspecified,
        accent = Color.Unspecified,
        onAccent = Color.Unspecified,
        error = Color.Unspecified,
        onError = Color.Unspecified,
        success = Color.Unspecified,
        onSuccess = Color.Unspecified
    )
}

val LocalAppTypography = staticCompositionLocalOf<AppTypography> {
    AppTypography(
        h1 = TextStyle.Default,
        h2 = TextStyle.Default,
        h3 = TextStyle.Default,
        h4 = TextStyle.Default,
        h5 = TextStyle.Default,
        h6 = TextStyle.Default,
        subtitle1 = TextStyle.Default,
        subtitle2 = TextStyle.Default,
        body1 = TextStyle.Default,
        body2 = TextStyle.Default,
        button = TextStyle.Default,
        caption = TextStyle.Default
    )
}

val LocalAppShape = staticCompositionLocalOf<AppShape> {
    AppShape(
        container = RectangleShape,
        button = RectangleShape,
        textField = RectangleShape,
        card = RectangleShape
    )
}

val LocalAppSize = staticCompositionLocalOf<AppSize> {
    AppSize(
        tiny = Dp.Unspecified,
        small = Dp.Unspecified,
        normal = Dp.Unspecified,
        medium = Dp.Unspecified,
        large = Dp.Unspecified
    )
}
