package com.isabella.firstapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.isabella.firstapp.databinding.ListItemPessoaBinding
import com.isabella.firstapp.service.model.Pessoa

class PessoaAdapter(pessoas: List<Pessoa>?, private val ClickListListener: (Pessoa) -> Unit) :
    RecyclerView.Adapter<PessoaAdapter.PessoasViewHolder>() {

   private var pessoaList: List<Pessoa> = arrayListOf()
    class PessoasViewHolder(private val binding: ListItemPessoaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pessoa: Pessoa, ClickListListener: (Pessoa) -> Unit) {
            binding.tvNome.text = pessoa.nome
            binding.tvIdade.text = pessoa.idade.toString()
            binding.tvfaixaEtaria.text = pessoa.faixaEtaria

            if(pessoa.sexo == "masc"){
                binding.imgSexoM.visibility = View.VISIBLE
                binding.imgSexoF.visibility = View.GONE
            } else{
                binding.imgSexoF.visibility = View.VISIBLE
                binding.imgSexoM.visibility = View.GONE
            }

            binding.root.setOnClickListener {
                ClickListListener(pessoa)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoasViewHolder {
        val listItemPessoaBinding = ListItemPessoaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PessoasViewHolder(listItemPessoaBinding)
    }

    override fun getItemCount(): Int {
        return pessoaList.count()
    }

    override fun onBindViewHolder(holder: PessoasViewHolder, position: Int) {
        holder.bind(pessoaList[position], ClickListListener)
    }

    fun updatePessoas(list: List<Pessoa>) {
        pessoaList = list
        notifyDataSetChanged()
    }
}