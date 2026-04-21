package com.example.app_basic_kotlin.data

import androidx.room.*
import androidx.room.Delete

@Dao
interface ProdutoDao {
    @Query("SELECT * FROM produtos")
    suspend fun getAll(): List<Produto>

    @Insert
    suspend fun insert(produto: Produto)

    @Update
    suspend fun update(produto: Produto)

    @Delete
    suspend fun delete(produto: Produto)
}
