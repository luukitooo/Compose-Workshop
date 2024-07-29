package com.luukitoo.compose_examples.effect_handlers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DisposableEffectExample() {

    val lowBatteryBroadcastReceiver =  remember { LowBatteryBroadcastReceiver() }
    val context = LocalContext.current

    DisposableEffect(Unit) {
        context.registerReceiver(lowBatteryBroadcastReceiver, IntentFilter(Intent.ACTION_BATTERY_LOW))
        onDispose {
            context.unregisterReceiver(lowBatteryBroadcastReceiver)
        }
    }
}

class LowBatteryBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BATTERY_LOW) {
            Toast.makeText(context, "Please charge your phone!", Toast.LENGTH_SHORT).show()
        }
    }
}

@Preview
@Composable
private fun DisposableEffectExamplePreview() = MaterialTheme {
    DisposableEffectExample()
}
