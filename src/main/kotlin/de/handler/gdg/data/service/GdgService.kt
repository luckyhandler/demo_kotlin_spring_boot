package de.handler.gdg.data.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.handler.gdg.data.GdgEntity
import de.handler.gdg.data.ResultType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import java.util.*

@Service
class GdgService {
    private val gson = Gson()
    private val gdgs by lazy { loadGdgs() }

    @Autowired
    lateinit var resourceLoader: ResourceLoader

    fun gdgs(): List<GdgEntity?> = gdgs

    fun gdg(id: String): GdgEntity? = gdgs.firstOrNull { it?.id == id }

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