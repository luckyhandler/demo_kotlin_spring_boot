package de.handler.gdg.controller

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.handler.gdg.data.GdgEntity
import de.handler.gdg.data.ResultType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/api/gdgs")
class GdgController {
    private val gson = Gson()

    @Autowired
    lateinit var resourceLoader: ResourceLoader

    fun gdgs(): List<GdgEntity?> = loadGdgs()
    @GetMapping

    private fun loadGdgs(): List<GdgEntity?> {
        val file = resourceLoader.getResource("classpath:static/gdgs.json").file
        val json = file.readText()
        val type = object : TypeToken<List<GdgEntity?>?>() {}.type
        val gdgs = gson.fromJson<List<GdgEntity>>(json, type)
        return gdgs
            .orEmpty()
            .filter { it.resultType == ResultType.CHAPTER }
    }
}