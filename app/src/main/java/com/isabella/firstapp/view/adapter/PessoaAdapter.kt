package com.isabella.firstapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.isabella.firstapp.R
import com.isabella.firstapp.databinding.ListItemPessoaBinding
import com.isabella.firstapp.service.model.Pessoa

class PessoaAdapter(pessoas: List<Pessoa>?, private val ClickListListener: (Pessoa) -> Unit) :
    RecyclerView.Adapter<PessoaAdapter.PessoasViewHolder>() {

        //criar uma lista vazia de pessoas
   private var pessoaList: List<Pessoa> = arrayListOf()
    class PessoasViewHolder(private val binding: ListItemPessoaBinding) :
        RecyclerView.ViewHolder(binding.root) {

            //carrega as informações da pessoa na lista
        fun bind(pessoa: Pessoa, ClickListListener: (Pessoa) -> Unit) {
            binding.tvNome.text = pessoa.nome
            binding.tvIdade.text = pessoa.idade.toString() + " anos"
            binding.tvfaixaEtaria.text = pessoa.faixaEtaria

            //método 1 para econder ou mostrar uma imagem
            if(pessoa.sexo == "Masculino"){
                binding.imgMasculino.visibility = View.VISIBLE
                binding.imgFeminino.visibility = View.GONE
            } else{
                binding.imgMasculino.visibility = View.GONE
                binding.imgFeminino.visibility = View.VISIBLE
            }

            //método 2 para esconder uma imagem
//            if(pessoa.sexo == "masc"){
//                binding.imgMasculino.setImageResource(R.drawable.baseline_boy_24)
//            } else{
//                binding.imgFeminino.setImageResource(R.drawable.baseline_woman_24)
//            }

                //configura o click de algum item da lista
            binding.root.setOnClickListener {
                ClickListListener(pessoa)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoasViewHolder {
        //configura o binding da lista
        val listItemPessoaBinding = ListItemPessoaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PessoasViewHolder(listItemPessoaBinding)
    }

    override fun getItemCount(): Int {
        //retorna a quantidade de itens
        return pessoaList.count()
    }

    override fun onBindViewHolder(holder: PessoasViewHolder, position: Int) {
        holder.bind(pessoaList[position], ClickListListener)
    }

    // carrega a lista de pessoas para ser exibidos
    fun updatePessoas(list: List<Pessoa>) {
        pessoaList = list
        notifyDataSetChanged()
    }
}