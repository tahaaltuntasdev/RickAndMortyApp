package com.example.rickandmortyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.rickandmortyapp.databinding.FragmentCharacterListBinding
import com.example.rickandmortyapp.viewModel.CharacterListVM

class CharacterListFragment : Fragment() {

    private lateinit var binding : FragmentCharacterListBinding
    private lateinit var rickAndMortyVM : CharacterListVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rickAndMortyVM = ViewModelProvider(this)[CharacterListVM::class.java]
        lifecycleScope.launchWhenCreated {
            rickAndMortyVM.getNameData()
        }


    }

}