package com.example.rickandmortyapp.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.databinding.RecyclerItemRowBinding

class CustomAdapter(
    private var characterNameList: ArrayList<Character>,
    val listener: RecyclerViewEvent
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
        val url = characterNameList[position].image
        Glide.with(holder.itemView.context).load(url).fitCenter().into(holder.binding.characterImg)
    }
    inner class ViewHolder(var binding: RecyclerItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(character: Character) {
            binding.characterNameTxt.text = character.name
            binding.characterSpeciesTxt.text = character.species
            when(character.gender){
                "Male" -> binding.genderImg.setImageDrawable(AppCompatResources.getDrawable(binding.root.context,R.drawable.male_con))
                "Female" -> binding.genderImg.setImageDrawable(AppCompatResources.getDrawable(binding.root.context,R.drawable.female_con))
                "unknown" -> binding.genderImg.setImageDrawable(AppCompatResources.getDrawable(binding.root.context,R.drawable.question_con))
            }

            val sharedPref: SharedPreferences = binding.root.context.getSharedPreferences(
                "Favorite", Context.MODE_PRIVATE)
            val sp = sharedPref.edit()
            val id = sharedPref.getInt(character.id.toString(), -1)
            if (id == -1){
                binding.favoriteStar.progress = 0.0F
            }else{
                binding.favoriteStar.progress = 1.0F
            }

            binding.favoriteStar.setOnClickListener {
                if(binding.favoriteStar.progress == 0.0F){
                    binding.favoriteStar.playAnimation()
                    sp.putInt(character.id.toString(), character.id)
                }else{
                    binding.favoriteStar.progress = 0.0F
                    sp.remove(character.id.toString())
                }
                sp.commit()
            }
        }
        init {
            binding.rvItem.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(characterNameList.get(position).id)
                }
            }
        }
    }
    interface RecyclerViewEvent {
        fun onItemClick(id: Int)
    }

    fun addItems(characterList: ArrayList<Character>){
        characterNameList = characterList
        notifyDataSetChanged()
    }
}