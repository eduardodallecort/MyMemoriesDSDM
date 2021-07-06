package br.edu.unisep.mymemories.ui.memory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.unisep.mymemories.databinding.FragmentMemoryBinding
import br.edu.unisep.mymemories.dto.MemoryDto
import br.edu.unisep.mymemories.ui.newmemory.NewMemoryViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MemoryFragment : Fragment() {

    private val viewModel: MemoryViewModel by viewModel()

    private val adapter: MemoryAdapter by inject()

    private val binding: FragmentMemoryBinding by lazy {
        FragmentMemoryBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupList()
        setupEvents()

        viewModel.listMemories()
    }

    private fun setupList() {
        binding.rvMemories.adapter = adapter
        binding.rvMemories.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMemories.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    private fun setupEvents() {

        viewModel.memoriesResult.observe(this.viewLifecycleOwner) { result -> onListSuccess(result) }
        viewModel.memoriesError.observe(this.viewLifecycleOwner) { onListError()}

    }

    private fun onListSuccess(memories: List<MemoryDto>) {
        adapter.memories = memories
    }

    private fun onListError() {
        Snackbar.make(binding.root, "Erro ao listar Memories", Snackbar.LENGTH_LONG).show()
    }

}