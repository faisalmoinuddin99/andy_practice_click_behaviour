package com.example.lemonadeapp

import android.os.Bundle
import android.os.ParcelFileDescriptor
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    LemonTextAndImageSkeleton(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}


@Composable
fun LemonTextAndImageSkeleton(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

        var currentStep by remember {
            mutableStateOf(1)
        }

        var squeezeCount by remember {
            mutableStateOf(0)
        }

        when (currentStep) {
            1 -> LemonTextAndImageBody(textLabelResourceId = R.string.para1,
                drawableResourceId = R.drawable.lemon_tree,
                contentDescriptionResourceId = R.string.lemon_tree) {
                currentStep = 2
                squeezeCount = (2..4).random()

            }
            2 -> LemonTextAndImageBody(textLabelResourceId = R.string.para2,
                drawableResourceId = R.drawable.lemon_squeeze,
                contentDescriptionResourceId = R.string.lemon) {
                squeezeCount--
                if (squeezeCount == 0) {
                    currentStep = 3
                }

            }
            3 -> LemonTextAndImageBody(textLabelResourceId = R.string.para3,
                drawableResourceId = R.drawable.lemon_drink,
                contentDescriptionResourceId = R.string.glass) {
                currentStep = 4
            }
            4 -> LemonTextAndImageBody(textLabelResourceId = R.string.para4,
                drawableResourceId = R.drawable.lemon_restart,
                contentDescriptionResourceId = R.string.empty) {
                currentStep = 1
            }
        }

    }
}

/**
 * Composable that displays a text label above an image that is clickable.
 *
 * @param textLabelResourceId is the resource ID for the text string to display
 * @param drawableResourceId is the resource ID for the image drawable to display below the text
 * @param contentDescriptionResourceId is the resource ID for the string to use as the content
 * description for the image
 * @param onImageClick will be called when the user clicks the image
 * @param modifier modifiers to set to this composable
 */

@Composable
fun LemonTextAndImageBody(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
) {
    Text(
        text = stringResource(textLabelResourceId)
    )
    Spacer(modifier = Modifier.heightIn(16.dp))

    Image(painter = painterResource(drawableResourceId),
        contentDescription = stringResource(contentDescriptionResourceId),
        modifier = Modifier.clickable(onClick = onImageClick)
    )
}

@Preview
@Composable
fun DefaultPreview() {
    LemonadeAppTheme {
        LemonApp()
    }
}