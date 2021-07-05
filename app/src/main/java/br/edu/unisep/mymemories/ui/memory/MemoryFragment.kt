package br.edu.unisep.mymemories.ui.memory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.edu.unisep.mymemories.databinding.FragmentMemoryBinding

class MemoryFragment : Fragment() {

    private val memoryViewModel: MemoryViewModel by viewModels<MemoryViewModel>()

    private val binding: FragmentMemoryBinding by lazy {
        FragmentMemoryBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

}