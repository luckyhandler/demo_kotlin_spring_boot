package de.handler.gdg.controller

import de.handler.gdg.data.GdgEntity
import de.handler.gdg.data.repository.GdgRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/api/gdgs")
class GdgController(private val repository: GdgRepository) {
    @GetMapping
    fun gdgs(): List<GdgEntity?> = repository.findAll()

    @GetMapping("/{id}")
    fun gdg(@PathVariable(value = "id") id: String): GdgEntity? = repository.findById(id)
}