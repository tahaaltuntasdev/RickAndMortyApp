package com.example.rickandmortyapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.UiState
import com.example.rickandmortyapp.adapter.CustomAdapter
import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.databinding.FragmentCharacterListBinding
import com.example.rickandmortyapp.viewModel.CharacterListVM

class CharacterListFragment : Fragment(R.layout.fragment_character_list),
    CustomAdapter.RecyclerViewEvent {

    private lateinit var binding: FragmentCharacterListBinding
    private val rickAndMortyVM: CharacterListVM by viewModels()
    val customAdapter = CustomAdapter(ArrayList(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = customAdapter

        rickAndMortyVM.uiState.observe(viewLifecycleOwner) {
            when (it) {
                UiState.Loading -> loading()
                is UiState.Success -> dataLoaded(it.data)
                else -> {}
            }
        }

    }

    override fun onItemClick(id: Int) {
        findNavController().navigate(
            CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(id)
        )
    }

    private fun loading() {
        with(binding) {
            pbLoading.visibility = View.VISIBLE
        }
    }

    private fun dataLoaded(data: List<Character>) {
        with(binding) {
            pbLoading.visibility = View.GONE
            customAdapter.addItems(ArrayList(data))
        }
    }
}