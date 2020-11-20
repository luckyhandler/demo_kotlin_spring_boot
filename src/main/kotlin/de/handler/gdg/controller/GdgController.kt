package de.handler.gdg.controller

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.handler.gdg.data.GdgEntity
import de.handler.gdg.data.ResultType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/v1/api/gdgs")
class GdgController {
    private val gson = Gson()
    private val gdgs by lazy { loadGdgs() }

    @Autowired
    lateinit var resourceLoader: ResourceLoader

    @GetMapping
    fun gdgs(): List<GdgEntity?> = gdgs

    @GetMapping("/{id}")
    fun gdg(@PathVariable(value = "id") id: String): GdgEntity? = gdgs.firstOrNull { it?.id == id }

    private fun loadGdgs(): List<GdgEntity?> {
        val file = resourceLoader.getResource("classpath:static/gdgs.json").file
        val json = file.readText()
        val type = object : TypeToken<List<GdgEntity?>?>() {}.type
        val gdgs = gson.fromJson<List<GdgEntity>>(json, type)
        return gdgs
            .orEmpty()
            .filter { it.resultType == ResultType.CHAPTER }
            .map { it.apply { id = UUID.randomUUID().toString() } }
    }
}