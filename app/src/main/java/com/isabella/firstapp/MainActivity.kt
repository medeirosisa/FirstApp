package com.isabella.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.isabella.firstapp.databinding.ActivityMainBinding
import com.isabella.firstapp.databinding.TelaLinearBinding
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnviar.setOnClickListener {
            var email = binding.edtEmail2.editableText.toString()
            var telefone = binding.edtTelefone2.editableText.toString()

            if (email.contains("@") && email.contains(".com")) {
                binding.tvEmail.text = "Email: " + email

            } else {
                binding.tvEmail.text = "Email inválido: " + email
            }

            if (telefone.length == 11) {
                binding.tvTelefone.text = "Telefone válido: " + telefone

            } else {
                binding.tvTelefone.text = "Telefone inválido: " + telefone
            }
        }
    }
}


//
//            var nome = binding.edtNome.editableText.toString()
//
//            binding.tvNome.text = "Nome: " + nome
//
//            var AnoNascimento = binding.edtAnoNascimento.editableText.toString()
//            var anoAtual = LocalDateTime.now().year
//            var idade = 2024 - AnoNascimento.toInt()
//
//            binding.tvIdade.text = "Idade: ${idade}" }
