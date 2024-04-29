package com.isabella.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.isabella.firstapp.R
import com.isabella.firstapp.databinding.FragmentAllPessoasBinding
import com.isabella.firstapp.databinding.FragmentPessoaDetailBinding
import com.isabella.firstapp.view.adapter.PessoaAdapter
import com.isabella.firstapp.viewmodel.AllPessoasViewModel
import com.isabella.firstapp.viewmodel.PessoaViewModel
import java.time.LocalDateTime

class PessoaDetailFragment : Fragment() {

    //Chamar a viewmodel para pega dados
    private val viewModel: PessoaViewModel by viewModels()


    //criar o binding para pega os elementos da tela
    private var _binding: FragmentPessoaDetailBinding? = null
    private val binding: FragmentPessoaDetailBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //configurar o binding
        _binding = FragmentPessoaDetailBinding.inflate(inflater, container, false)
        return binding.root


    }
    //chamar a função onViewCreated onde vamos implementar o código
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //pegar o id da pessoa que foi enviado pela AllPessoasFragment
        //setar a pessoa para ser carregada na tela
        arguments?.let {
            viewModel.getPessoa(it.getInt("pessoaId"))
        }

        //carregar as informações da pessoa selecionada
        viewModel.pessoa.observe(viewLifecycleOwner) { pessoa ->
            binding.tvNome.setText(pessoa.nome)
            binding.tvIdade.setText(pessoa.idade.toString())
            binding.tvfaixaEtaria.setText(pessoa.faixaEtaria)
            binding.tvAnoNascimento.setText((LocalDateTime.now().year - pessoa.idade).toString())


            //método 1 para econder ou mostrar uma imagem
            if (pessoa.sexo == "masculino") {
                binding.imgMasculino.visibility = View.VISIBLE
                binding.imgFeminino.visibility = View.GONE
            } else {
                binding.imgMasculino.visibility = View.GONE
                binding.imgFeminino.visibility = View.VISIBLE
            }
        }
    }
}