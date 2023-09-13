package com.example.rickandmortyapp.view

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.UiState
import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.databinding.FragmentCharacterDetailBinding
import com.example.rickandmortyapp.viewModel.CharacterDetailVM


class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding
    private val rickAndMortyVM: CharacterDetailVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ltAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                // Animation started
            }

            override fun onAnimationEnd(animation: Animator) {
                binding.infoLayout.visibility = View.VISIBLE
                binding.ltAnimation.visibility = View.GONE
            }

            override fun onAnimationCancel(animation: Animator) {
                // Animation cancelled
            }

            override fun onAnimationRepeat(animation: Animator) {
                // Animation repeated
            }
        })


        if (arguments != null){
            val data = CharacterDetailFragmentArgs.fromBundle(requireArguments()).data
            rickAndMortyVM.getCharacter(data)
        }


        rickAndMortyVM.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.SuccessId -> loadData(it.dataId)
                else -> {}
            }
        }

        binding.backImg.setOnClickListener {
            findNavController().navigate(CharacterDetailFragmentDirections
                .actionCharacterDetailFragmentToCharacterListFragment())
        }
    }
    fun loadData(data:Character){
            binding.characterNameTxtDetail.text = data.name
            Glide.with(requireContext()).load(data.image).fitCenter().into(binding.characterImgDetail)
            binding.status.text = data.status
            binding.specy.text = data.species
            binding.gender.text = data.gender
            binding.created.text = data.created
    }
}
