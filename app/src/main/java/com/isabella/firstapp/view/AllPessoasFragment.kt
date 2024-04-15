package com.isabella.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.isabella.firstapp.R
import com.isabella.firstapp.databinding.FragmentAllPessoasBinding
import com.isabella.firstapp.view.adapter.PessoaAdapter
import com.isabella.firstapp.viewmodel.AllPessoasViewModel

class AllPessoasFragment : Fragment() {

    //Chamar a viewmodel
    private val  viewModel: AllPessoasViewModel by viewModels()

    //Chamar o adapter
    private lateinit var adapter: PessoaAdapter

    //criar o binding
    private var _binding: FragmentAllPessoasBinding? = null
    private val binding: FragmentAllPessoasBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //configurar o binding
        _binding = FragmentAllPessoasBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Quando clicar em algum item da lista
        adapter = PessoaAdapter(viewModel.pessoaList.value){

        }

        //Cofigurar a recycler
        val recycler = binding.rvPessoas
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        //observa para adicionar um item na lista quando for adicionado
        viewModel.pessoaList.observe(viewLifecycleOwner){
            adapter.updatePessoas(it)
        }

        //Navegar para tela de cadstro de pessoas
        binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.pessoaFragment)
        }

        //carregar as pessoas cadastradas
        viewModel.loadPessoas()
    }
}
