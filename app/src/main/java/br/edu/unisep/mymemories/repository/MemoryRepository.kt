package br.edu.unisep.mymemories.repository

import br.edu.unisep.mymemories.dto.MemoryDto
import br.edu.unisep.mymemories.dto.RegisterMemoryDto

class MemoryRepository(get: Any) {

    fun save(memoryDto: RegisterMemoryDto) {

    }

    fun getList(): List<MemoryDto>  {
        return emptyList()
    }

}