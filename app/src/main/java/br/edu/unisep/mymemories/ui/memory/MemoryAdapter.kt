package br.edu.unisep.mymemories.ui.memory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.unisep.mymemories.R
import br.edu.unisep.mymemories.databinding.ItemMemoryBinding
import br.edu.unisep.mymemories.dto.MemoryDto
import java.time.format.DateTimeFormatter

class MemoryAdapter : RecyclerView.Adapter<MemoryAdapter.MemoriesViewHolder>(){

    var memories: List<MemoryDto> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoriesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_memory, parent, false)
        return MemoriesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MemoriesViewHolder, position: Int) {
        holder.bind(memories[position])
    }

    override fun getItemCount() = memories.size

    inner class MemoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMemoryBinding.bind(itemView)

        fun bind(memory: MemoryDto) {
            val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            binding.tvTitle.text = memory.city + memory.memoryDate
            binding.tvDescription.text = memory.description
        }
    }
}