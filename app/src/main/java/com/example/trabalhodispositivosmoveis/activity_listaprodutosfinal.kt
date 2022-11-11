package com.example.trabalhodispositivosmoveis

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_listaprodutos.*


private lateinit var adapter: ArrayAdapter<String>
private val listViewDosProd = ArrayList<String>()
private lateinit var listaProd: ListView

class activity_listaprodutosfinal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listaprodutosfinal)


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

        listaProd = findViewById(R.id.listaDeProdutos)

        listViewDosProd.clear()

        var qtd = 0

        if (produtosLidos != null) {
            for (i in produtosLidos) {
                for (x in produtosMemoria) {
                    if (i == x[0]) {
                        listViewDosProd.add(""+x[1])
                        atualizarLista()
                        if (qtdProdutosLidos != null) {
                            listViewDosProd.add("Quantidade retirada: "+qtdProdutosLidos.get(qtd))
                            var quantidade = qtdProdutosLidos?.get(qtd)?.toInt()
                            var qtdrestante = 1000 - quantidade!!
                            listViewDosProd.add("Quantidade restante: "+qtdrestante)

                        }
                        listViewDosProd.add("")

                        qtd++
                    }
                }
            }
        }

        val btnNovaSep: Button = findViewById(R.id.btnNovaSep)
        btnNovaSep.setOnClickListener{
            val intent = Intent(this, activity_separacao::class.java)
            startActivity(intent)
        }

        val btnEncerrar: Button = findViewById(R.id.btnEncerrar)
        btnEncerrar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun atualizarLista() {
        adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listViewDosProd)
        listaProd.setAdapter(adapter)
    }
}


