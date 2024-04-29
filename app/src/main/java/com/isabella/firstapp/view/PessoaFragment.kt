package com.isabella.firstapp.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.isabella.firstapp.databinding.FragmentPessoaBinding
import com.isabella.firstapp.service.model.Pessoa
import com.isabella.firstapp.viewmodel.PessoaViewModel
import java.time.LocalDateTime

class PessoaFragment : Fragment() {

    private val viewModel: PessoaViewModel by viewModels()

    private var _binding: FragmentPessoaBinding? = null
    private val binding: FragmentPessoaBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPessoaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //caregar a pessoa caso tenha selecionado
        arguments?.let {
            viewModel.getPessoa(it.getInt("pessoaId"))
        }

        binding.btnEnviar.setOnClickListener {
            var nome = binding.edtNome.editableText.toString()
            var AnoNascimento = binding.edtAnoNascimento.editableText.toString()
            var faixaEtaria = " "
            var sexo = " "


            if (nome != "" && AnoNascimento != "" && binding.rbMasculino.isChecked || binding.rbFeminino.isChecked)

                if (nome != "" && AnoNascimento != "") {

                    if (binding.rbMasculino.isChecked) {
                        sexo = "Masculino"
                    } else {
                        sexo = "Feminino"
                    }

                    var anoAtual = LocalDateTime.now().year
                    var idade = 2024 - AnoNascimento.toInt()

                    if (idade < 12) {
                        faixaEtaria = "Criança"
                    } else if (idade < 18) {
                        faixaEtaria = "Adolescente"
                    } else if (idade < 65) {
                        faixaEtaria = "Adulto"
                    } else {
                        faixaEtaria = "Idoso"
                    }


                    val pessoa = Pessoa(
                        nome = nome, idade = idade, faixaEtaria = faixaEtaria, sexo = sexo

                    )

                    viewModel.pessoa.value?.let {
                        pessoa.id = it.id
                        viewModel.update(pessoa)

                    } ?: run {
                        viewModel.insert(pessoa)
                    }

                    viewModel.insert(pessoa)

                    binding.edtNome.editableText.clear()
                    binding.edtAnoNascimento.editableText.clear()

                } else {
                    Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
                }
        }

        binding.btnDeletar.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Exclusão de pessoa")
                .setMessage("você realmente deseja excluir ?")
                .setPositiveButton("sim") { _, _ ->
                    viewModel.delete(viewModel.pessoa.value?.id ?: 0)
                    findNavController().navigateUp()
                }
                .setNegativeButton("não") { _, _ -> }
                .show()
        }

        viewModel.pessoa.observe(viewLifecycleOwner) { pessoa ->
            binding.edtNome.setText(pessoa.nome)
            binding.edtAnoNascimento.setText((LocalDateTime.now().year - pessoa.idade).toString())

            if (pessoa.sexo == "Masculino") {
                binding.rbMasculino.isChecked = true
            } else {
                binding.rbFeminino.isChecked = true
            }

            binding.btnDeletar.visibility = View.VISIBLE
        }
    }
}