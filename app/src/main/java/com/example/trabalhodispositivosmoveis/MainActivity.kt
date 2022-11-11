package com.example.trabalhodispositivosmoveis

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val usuarios = HashMap <String, String>()
        usuarios["alan"] = "alan|Alan Nunes de Oliveira|1"
        usuarios["eliana"] = "eliana|Eliana Morato Silva|2"
        usuarios["kaue"] = "kaue|Kaue Zanerato da Silva|3"
        usuarios["kayky"] = "kayky|Kayky da Silva Dantas|4"
        usuarios["lucas"] = "lucas|Lucas Gonzalez do Nascimento Souza|5"
        usuarios["henrique"] = "henrique|Luiz Henrique Zorzan|6"
        usuarios["matheus"] = "matheus|Matheus André Bezerra da Costa|7"
        usuarios["eric"] = "eric|Eric Fagundes Cardoso|8"
        usuarios["pedro"] = "pedro|Pedro Guilherme Chaves Geraldino|9"

        val login: EditText = findViewById(R.id.login)
        login.requestFocus()
        val senha: EditText = findViewById(R.id.senha)
        val btnEntrar: Button = findViewById(R.id.btnEntrar)
        btnEntrar.setOnClickListener {
            val strUsuario: String = login.text.toString()
            val strSenha: String = senha.text.toString()
            val usuario = usuarios[strUsuario]?.split("|")
            if (usuario?.get(0)?.compareTo(strUsuario) !=0) {
                Toast.makeText(applicationContext, "Usuário Incorreto", Toast.LENGTH_LONG).show()
                login.setText("")
            } else if (usuario?.get(2)?.compareTo(strSenha) !=0) {
                Toast.makeText(applicationContext,"Senha Incorreta", Toast.LENGTH_LONG).show()
                senha.setText("")
            } else {
                Toast.makeText(applicationContext,"Logado com sucesso: "+usuario.get(1).toString()+".",
                    Toast.LENGTH_LONG).show()
                val intent = Intent(this, activity_separacao::class.java)
                startActivity(intent) }

        }
    }
}