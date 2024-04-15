package com.isabella.firstapp.service.repository

import android.content.Context
import com.isabella.firstapp.service.model.Pessoa
import com.isabella.firstapp.service.repository.local.FirstAppDataBase

class PessoaRepository(context: Context) {
    private val firstAppDb = FirstAppDataBase.getDataBase(context).pessoaDao()

    suspend fun insertPessoa(pessoa: Pessoa){
        firstAppDb.insert(pessoa)
    }

    suspend fun getPessoa(id: Int): Pessoa{
        return firstAppDb.getPessoa(id)
    }

    suspend fun  getPessoas(): List<Pessoa> {
        return firstAppDb.getAll()
    }

    suspend fun updatePessoa(pessoa: Pessoa){
        firstAppDb.upDate(pessoa)
    }

    suspend fun deletePessoa(id: Int) {
        firstAppDb.delete(id)
    }
}
