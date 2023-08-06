package com.example.rickandmortyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.adapter.CustomAdapter
import com.example.rickandmortyapp.databinding.FragmentCharacterListBinding
import com.example.rickandmortyapp.viewModel.CharacterListVM

class CharacterListFragment : Fragment() {

    private lateinit var binding : FragmentCharacterListBinding
    private lateinit var rickAndMortyVM : CharacterListVM
    var customAdapter = CustomAdapter(arrayListOf())

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
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerView.adapter = customAdapter
        }
        observeLiveData()

    }
    private fun observeLiveData(){
        rickAndMortyVM.characterNameList.observe(viewLifecycleOwner, Observer{
            it.let {
                val name = it
                for (i in name){
                    customAdapter.characterNameList.add(i)
                }
            }
        })
    }

}