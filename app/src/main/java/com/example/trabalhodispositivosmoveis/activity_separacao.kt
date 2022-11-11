package com.example.trabalhodispositivosmoveis

import android.R.attr.value
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator


class activity_separacao : AppCompatActivity() {

    private val obterResultado = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
        if (intentResult.contents != null) {

            val produtos = intentResult.contents

            val produtosDivididos = produtos.split("|",":")

            val intent = Intent(this, activity_listaprodutos::class.java)
            intent.putExtra("ItensDivididos",ArrayList(produtosDivididos))
            startActivity(intent) }

    }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_separacao)

            val btnLerQrCode: Button = findViewById(R.id.btnLerQrCode)
            btnLerQrCode.setOnClickListener {
                val integrator: IntentIntegrator = IntentIntegrator(this)
                integrator.setPrompt("Ler Produto a Separar")
                obterResultado.launch(integrator.createScanIntent())
            }

            val btnVoltar: Button = findViewById(R.id.btnVoltar)
            btnVoltar.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

}

