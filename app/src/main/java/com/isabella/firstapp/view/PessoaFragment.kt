package com.isabella.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.isabella.firstapp.databinding.FragmentPessoaBinding
import com.isabella.firstapp.service.model.Pessoa
import com.isabella.firstapp.viewmodel.PessoaViewModel
import java.time.LocalDateTime

class PessoaFragment : Fragment() {

    private val viewModel: PessoaViewModel by viewModels()

    private var _binding: FragmentPessoaBinding? = null
    private val binding: FragmentPessoaBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPessoaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEnviar.setOnClickListener {
            var nome = binding.edtNome.editableText.toString()
            var AnoNascimento = binding.edtAnoNascimento.editableText.toString()
            var sexo = binding.edtSexo.editableText.toString()
            var faixaEtaria = " "


            if (nome != "" && AnoNascimento!= ""){


                var anoAtual = LocalDateTime.now().year
                var idade = 2024 - AnoNascimento.toInt()

                if(idade < 12){
                    faixaEtaria = "Criança"
                }

                else if (idade >= 13){
                    faixaEtaria = "Adolescente"
                }

                else if (idade < 65){
                    faixaEtaria = "Adulto"
                }

                else{
                    faixaEtaria = "Idoso"
                }



                val pessoa = Pessoa(
                    nome = nome,
                    idade = idade,
                    sexo = sexo,
                    faixaEtaria = faixaEtaria
                )
                viewModel.insert(pessoa)

                binding.edtNome.editableText.clear()
                binding.edtAnoNascimento.editableText.clear()

            } else{
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }
        }
    }
}