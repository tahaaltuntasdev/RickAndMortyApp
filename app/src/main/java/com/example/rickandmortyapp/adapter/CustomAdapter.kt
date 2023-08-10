package com.example.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.data.model.Result
import com.example.rickandmortyapp.databinding.RecyclerItemRowBinding

class CustomAdapter(
    val characterNameList: ArrayList<Result>

):
    RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return characterNameList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.onBind(characterNameList[position])
    }
    inner class ViewHolder(var binding: RecyclerItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(result: Result) {
            binding.characterName.text = result.name
        }
    }

}