package com.jio.jiolocalizationsdkandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jio.jiolocalizationsdkandroid.ui.theme.JioLocalizationSDKAndroidTheme

class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModels { MainViewModel.Factory }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            LaunchedEffect(Unit) {
                viewModel.initJioTranslate(context)
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (viewModel.isLoading) {
                    // Display loading indicator
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                } else {

                    //load a key
//                    val message = viewModel.getLocalizedString("callDeclined")
                    // load a key with default value
//                    val message = viewModel.getLocalizedString("callDeclined", "Default Value")
                    //load a key that includes a dynamic value placeholder
                    val message = viewModel.getLocalizedString("bottom_message_num_selected", mapOf("###" to "English"), "Default Value")
                    LocalizedMessage(message = message)
                }
            }
        }
    }
}
@Composable
fun LocalizedMessage(message: String, modifier: Modifier = Modifier) {
    // Use Box to center the content
    Box(
        modifier = Modifier
            .fillMaxSize() // Take up the full screen size
            .padding(20.dp), // Add padding to the entire box
        contentAlignment = Alignment.Center // Center the content
    ) {
        Column {
            Text(
                text = message,
                modifier = modifier,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JioLocalizationSDKAndroidTheme {
        LocalizedMessage("Android")
    }
}