package com.mouse.wallet.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mouse.wallet.ui.theme.Black
import com.mouse.wallet.ui.theme.BlueSmooth
import com.mouse.wallet.ui.theme.LightGraySmooth
import com.mouse.wallet.util.toDpSize

private const val LOADING_ASSET = "loading_anim.json"

@Composable
fun WalletButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    shape: Shape = ButtonDefaults.shape,
    color: Color = BlueSmooth,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = BorderStroke(1.dp, LightGraySmooth),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    val textStyle = TextStyle(if (isSystemInDarkTheme()) Color.White else Black)

    val isButtonEnabled = enabled && !loading
    val animatedButtonColor = animateColorAsState(
        targetValue = if (isButtonEnabled) color else LightGraySmooth,
        animationSpec = tween(300, 0, LinearEasing)
    )
    var sizeInstantiated by remember { mutableStateOf(false) }
    var size by remember { mutableStateOf(IntSize.Zero) }
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset(LOADING_ASSET)
    )

    ProvideTextStyle(textStyle) {
        Button(
            onClick = onClick,
            modifier = modifier.onPlaced { coordinates ->
                if (!sizeInstantiated) {
                    size = coordinates.size
                    sizeInstantiated = true
                }
            },
            enabled = isButtonEnabled,
            shape = shape,
            colors = ButtonDefaults.buttonColors(
                containerColor = animatedButtonColor.value,
                disabledContainerColor = animatedButtonColor.value
            ),
            elevation = elevation,
            border = border,
            contentPadding = if (loading) PaddingValues(all = 0.dp) else contentPadding,
            interactionSource = interactionSource,
        ) {
            val verticalPadding =
                contentPadding.calculateTopPadding() + contentPadding.calculateBottomPadding()
            val dpSize = size.toDpSize() - DpSize(width = 0.dp, height = verticalPadding)
            if (loading) {
                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = modifier
                        .requiredSize(dpSize),
                )
            } else content()
        }
    }
}