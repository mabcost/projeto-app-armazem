package com.example.trabalhodispositivosmoveis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.zxing.integration.android.IntentIntegrator
import java.util.*
import kotlin.collections.ArrayList
import android.os.Handler;
import android.os.Looper
import java.sql.Time


private lateinit var adapter: ArrayAdapter<String>
private val listViewInfo = ArrayList<String>()
private lateinit var listaInfoProd: ListView

class activity_leitura2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leitura)


        val produtosLidos = intent.getStringArrayListExtra("produtosLidos")
        val qtdProdutosLidos = intent.getStringArrayListExtra("qtdProdutosLidos")
        val produtosMemoria = arrayOf(
            arrayOf("7894900010015", "REFRIGERANTE COCA-COLA LATA 350ML","A","1","1",1000),//[0][0,1,2,3,4]
            arrayOf("7894900011517", "REFRIGERANTE COCA-COLA GARRAFA 2L","A","1","2",1000), //[1][0,1,2,3,4]
            arrayOf("7891991000833", "REFRIGERANTE SODA LIMONADA ANTARTIC LATA 350ML","A","1","3",1000), //[2][0,1,2,3,4]
            arrayOf("7891991011020", "REFRIGERANTE GUARANA ANTARCTICA LATA 350ML","A","2","1",1000), //[3][0,1,2,3,4]
            arrayOf("7898712836870", "REFRIGERANTE GUARANA ANTARCTICA 2L","A","2","2",1000), //[4][0,1,2,3,4]
            arrayOf("7894900039924", "REFRIGERANTE FANTA LARANJA 2L","A","2","3",1000), //[5][0,1,2,3,4]
            arrayOf("7894900031201", "REFRIGERANTE FANTA LARANJA LATA 350ML","A","2","4",1000), //[6][0,1,2,3,4]
            arrayOf("7892840800079", "REFRIGERANTE PEPSI LATA 350ML","A","3","1",1000), //[7][0,1,2,3,4]
            arrayOf("7892840813017", "REFRIGERANTE PEPSI 2L","A","3","2",1000), //[8][0,1,2,3,4]
            arrayOf("7896004000855", "SUCRILHOS KELLOGG'S ORIGINAL 250G","B","1","1",1000), //[9][0,1,2,3,4]
            arrayOf("7896004003979", "SUCRILHOS KELLOGG'S CHOCOLATE 320G","B","1","2",1000), //[10][0,1,2,3,4]
            arrayOf("7896110005140", "PAPEL HIGIÊNICO PERSONAL FOLHA SIMPLES NEUTRO 60 METROS 4 UNIDADES","B","2","1",1000), //[11][0,1,2,3,4]
            arrayOf("7896104998953", "PAPEL HIGIÊNICO MILI 4R","B","2","2",1000), //[12][0,1,2,3,4]
            arrayOf("7896076002146", "PAPEL HIGIENICO DAMA 60MTR","B","2","3",1000), //[13][0,1,2,3,4]
            arrayOf("7896276060021", "ARROZ AGULHINHA ARROZAL T1 5KG","C","1","1",1000), //[14][0,1,2,3,4]
            arrayOf("7898295150189", "ARROZ SABOROSO 5KG","C","1","2",1000), //[15][0,1,2,3,4]
            arrayOf("7896086423030", "ARROZ TRIMAIS 5KG","C","1","3",1000), //[16][0,1,2,3,4]
            arrayOf("7896864400192", "FEIJAO PICININ 1KG","C","2","1",1000), //[17][0,1,2,3,4]
            arrayOf("7897924800877", "FEIJAO PRETO VENEZA 1KG","C","2","2"), //[18][0,1,2,3,4]
            arrayOf("7898084090030", "FEIJÃO PEREIRA CARIOQUINHA 1KG","C","2","3",1000), //[19][0,1,2,3,4]
            arrayOf("7891959004415", "AÇUCAR REFINADO DOÇULA 1KG","D","1","1",1000), //[20][0,1,2,3,4]
            arrayOf("7896032501010", "AÇÚCAR REFINADO DA BARRA 1KG","D","1","2",1000), //[21][0,1,2,3,4]
            arrayOf("7896109801005", "AÇÚCAR REFINADO ESPECIAL GUARANI 1KG","D","1","3",1000), //[22][0,1,2,3,4]
            arrayOf("7896319420546", "ACUCAR REFINADO CLARION 1KG","D","2","1",1000), //[23][0,1,2,3,4]
            arrayOf("7896089028935", "CAFÉ TORRADO MOÍDO POUCHE CAFÉ DO PONTO 500G","D","2","2",1000), //[24][0,1,2,3,4]
            arrayOf("7898286200077", "CAFE MARATA 500G","D","2","3",1000), //[25][0,1,2,3,4]
            arrayOf("7891910010905", "CAFE CABOCLO 500G","D","3","1",1000), //[26][0,1,2,3,4]
            arrayOf("7898079250012", "CAFE FIORENZA 500G","D","3","2",1000), //[27][0,1,2,3,4]
            arrayOf("7891107000504", "OLEO DE SOJA SOYA 1L","E","1","1",1000), //[28][0,1,2,3,4]
            arrayOf("7896334200550", "OLEO DE SOJA GRANOL 1L","E","2","1",1000), //[29][0,1,2,3,4]
            arrayOf("7896036090107", "OLEO DE SOJA VELEIRO 1L","E","3","1",1000) //[30][0,1,2,3,4]
        )

        listaInfoProd = findViewById(R.id.infoProd)

        listViewInfo.clear()

        var qtd = 0

        if (produtosLidos != null) {
            var n = 0;
            for (i in produtosLidos) {
                for (x in produtosMemoria) {
                    if (i == x[0]) {

                        listViewInfo.clear()
                        listViewInfo.add("" + x[1])
                        listViewInfo.add("Rua: " + x[2])
                        listViewInfo.add("Número: " + x[3])
                        listViewInfo.add("Andar: " + x[4])
                        listViewInfo.add("Quantidade: " + (qtdProdutosLidos?.get(qtd)))
                        atualizarLista()


                        var quantidade = qtdProdutosLidos?.get(qtd)?.toInt()


                        val obterProduto50 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                    val intent = Intent(this, activity_leitura3::class.java)
                                    intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                    intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                    startActivity(intent)

                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto49 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 50")
                                            obterProduto50.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto48 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 49")
                                            obterProduto49.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto47 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 48")
                                            obterProduto48.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto46 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 47")
                                            obterProduto47.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto45 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 46")
                                            obterProduto46.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto44 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 45")
                                            obterProduto45.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto43 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 44")
                                            obterProduto44.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto42 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 43")
                                            obterProduto43.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto41 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 42")
                                            obterProduto42.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto40 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 41")
                                            obterProduto41.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto39 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 40")
                                            obterProduto40.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto38 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 39")
                                            obterProduto39.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto37 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 38")
                                            obterProduto38.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto36 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 37")
                                            obterProduto37.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto35 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 36")
                                            obterProduto36.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto34 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 35")
                                            obterProduto35.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto33 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 34")
                                            obterProduto34.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto32 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 33")
                                            obterProduto33.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto31 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 32")
                                            obterProduto32.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto30 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 31")
                                            obterProduto31.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto29 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 30")
                                            obterProduto30.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto28 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 29")
                                            obterProduto29.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto27 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 28")
                                            obterProduto28.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto26 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 27")
                                            obterProduto27.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto25 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 26")
                                            obterProduto26.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto24 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 25")
                                            obterProduto25.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto23 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 24")
                                            obterProduto24.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto22 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 23")
                                            obterProduto23.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto21 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 22")
                                            obterProduto22.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto20 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 21")
                                            obterProduto21.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }


                        val obterProduto19 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 20")
                                            obterProduto20.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }


                        val obterProduto18 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 19")
                                            obterProduto19.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }


                        val obterProduto17 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 18")
                                            obterProduto18.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto16 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 17")
                                            obterProduto17.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }


                        val obterProduto15 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 16")
                                            obterProduto16.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }


                        val obterProduto14 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 15")
                                            obterProduto15.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto13 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 14")
                                            obterProduto14.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto12 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 13")
                                            obterProduto13.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterProduto11 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 12")
                                            obterProduto12.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }


                        val obterProduto10 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 11")
                                            obterProduto11.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }


                        val obterProduto9 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 10")
                                            obterProduto10.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }


                        val obterProduto8 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 9")
                                            obterProduto9.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }


                        val obterProduto7 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 8")
                                            obterProduto8.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }


                        val obterProduto6 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 7")
                                            obterProduto7.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }


                        val obterProduto5 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 6")
                                            obterProduto6.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }


                        val obterProduto4 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 5")
                                            obterProduto5.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }


                        val obterProduto3 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 4")
                                            obterProduto4.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }



                        val obterProduto2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0) {
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 3")
                                            obterProduto3.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }


                        val obterProduto = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == x[0]) {
                                    if (quantidade != null) {
                                        if (quantidade > 0 && quantidade != 1) {
                                            quantidade--
                                            Toast.makeText(applicationContext, "Produto lido", Toast.LENGTH_LONG).show()
                                            val integrator: IntentIntegrator = IntentIntegrator(this)
                                            integrator.setPrompt("Ler Produto 2")
                                            obterProduto2.launch(integrator.createScanIntent())
                                            quantidade--
                                        }
                                        else {
                                            Toast.makeText(applicationContext, "Produto lido com sucesso", Toast.LENGTH_LONG).show()
                                            val intent = Intent(this, activity_leitura3::class.java)
                                            intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                                            intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                else run {
                                    Toast.makeText(applicationContext,"Produto incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        val obterAndar = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == "R"+x[2]+"-N"+x[3]+"-"+x[4]+"A") {
                                    val integrator: IntentIntegrator = IntentIntegrator(this)
                                    integrator.setPrompt("Ler Produto")
                                    obterProduto.launch(integrator.createScanIntent())
                                }

                                else run {
                                    Toast.makeText(applicationContext,"Andar Incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }

                            }
                        }

                        val obterNumero = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == "R"+x[2]+"-N"+x[3]) {
                                    val integrator: IntentIntegrator = IntentIntegrator(this)
                                    integrator.setPrompt("Ler Andar")
                                    obterAndar.launch(integrator.createScanIntent())
                                }


                                else run {
                                    Toast.makeText(applicationContext,"Número Incorreto, tente novamente",Toast.LENGTH_LONG).show()
                                }
                            }
                        }



                        val obterRua = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                            val intentResult = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                            if (intentResult.contents != null) {
                                if (intentResult.contents == "R"+x[2]) {
                                    val integrator: IntentIntegrator = IntentIntegrator(this)
                                    integrator.setPrompt("Ler Número")
                                    obterNumero.launch(integrator.createScanIntent())
                                }

                                else run {
                                    Toast.makeText(applicationContext,"Rua Incorreta, tente novamente",Toast.LENGTH_LONG).show()
                                }

                            }
                        }

                        val btnComecarLeitura: Button = findViewById(R.id.btnComecarLeitura)
                        btnComecarLeitura.setOnClickListener {
                            val integrator: IntentIntegrator = IntentIntegrator(this)
                            integrator.setPrompt("Ler Rua")
                            obterRua.launch(integrator.createScanIntent())
                        }

                        qtd = qtd + 1
                        n++

                    }
                }

                if (n == 2) {
                    break
                }
            }
            val lastn = n

            if (lastn != 2) {val intent = Intent(this, activity_listaprodutosfinal::class.java)
                intent.putExtra("produtosLidos",ArrayList(produtosLidos))
                intent.putExtra("qtdProdutosLidos",qtdProdutosLidos)
                startActivity(intent)}
        }


    }

    private fun atualizarLista() {
        adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listViewInfo)
        listaInfoProd.setAdapter(adapter)
    }
}