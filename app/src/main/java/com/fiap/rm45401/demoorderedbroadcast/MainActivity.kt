package com.fiap.rm45401.demoorderedbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var receiver : BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceivers()

        btEnviar.setOnClickListener{
            sendBroadcast(Intent("MeuOrderedBroadcast"))
        }
    }

    private fun registerReceivers() {
        var filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)

        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                /*if (intent?.action.equals(Intent.ACTION_POWER_CONNECTED)) {
                    tvUSBStatus.text = "Cabo conectado!"
                } else {
                    tvUSBStatus.text = "Cabo desconectado!"
                }*/
                when (intent?.action) {
                    Intent.ACTION_POWER_CONNECTED -> tvUSBStatus.text = "Cabo conectado!"
                    Intent.ACTION_POWER_DISCONNECTED -> tvUSBStatus.text = "Cabo desconectado!"
                }
            }
        }
        registerReceiver(receiver, filter)
    }
}
