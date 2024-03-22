package com.app.hasnain.biometricauth

import android.content.Intent
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.media.audiofx.BassBoost.Settings
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.tooling.preview.Preview
import com.app.hasnain.biometricauth.ui.theme.BioMetricAuthTheme

class MainActivity : AppCompatActivity() {
    private val promptManager by lazy {
        BiometricPromptManager(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BioMetricAuthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val biometricResult by promptManager.promptResult.collectAsState(
                        initial = null
                    )

                    val enrollLauncher = rememberLauncherForActivityResult(
                       contract = ActivityResultContracts.StartActivityForResult(),
                        onResult = {
                            println("Activity result: $it")
                        }
                    )

                    LaunchedEffect(biometricResult){
                        if (biometricResult is BiometricPromptManager.BiometricResult.AuthenticationNotSet){
                            if (Build.VERSION.SDK_INT >= 30){
                                val enrollIntent = Intent(android.provider.Settings.ACTION_BIOMETRIC_ENROLL).apply {
                                    putExtra(
                                        android.provider.Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                                        )
                                }
                                enrollLauncher.launch(enrollIntent)
                            }
                        }
                    }

                    Column (
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                        )
                    {
                        Button(onClick = {
                            promptManager.showBiometricPrompt(
                                title="Sample Prompt",
                                description = "Sample prompt description"
                                )
                        }){
                            Text(text = "Authenticate")
                        }
                        biometricResult?.let { result ->

                            Text(
                                text = when(result){
                                    is BiometricPromptManager.BiometricResult.AuthenticationError -> {
                                        result.error
                                    }
                                    BiometricPromptManager.BiometricResult.AuthenticationFailed ->{
                                      "Authentication Failed"
                                    }
                                    BiometricPromptManager.BiometricResult.AuthenticationNotSet -> {
                                        "Authentication not set"
                                    }
                                    BiometricPromptManager.BiometricResult.AuthenticationSuccess -> {
                                        "Authentication Success"
                                    }
                                    BiometricPromptManager.BiometricResult.AuthenticationUnavailable -> {
                                        "Authentication Unavailable"
                                    }
                                    BiometricPromptManager.BiometricResult.FeatureUnavailable -> {
                                        "Feature Unavailable"
                                    }
                                    BiometricPromptManager.BiometricResult.HardwareUnavailable -> {
                                        "Hardware Unavailable"
                                    }
                                })

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BioMetricAuthTheme {
        Greeting("Android")
    }
}