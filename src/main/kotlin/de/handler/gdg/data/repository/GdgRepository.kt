package de.handler.gdg.data.repository

import de.handler.gdg.data.GdgEntity
import de.handler.gdg.data.service.GdgService
import org.springframework.stereotype.Repository

@Repository
class GdgRepository(private val service: GdgService) : ObjectRepository<GdgEntity> {
    private val cache = mutableListOf<GdgEntity?>()

    override fun findAll(): List<GdgEntity?> = when {
        cache.isEmpty() -> service.gdgs().apply {
            cache.clear()
            cache.addAll(this)
        }
        else -> cache
    }

    override fun findById(id: String): GdgEntity? {
        val gdgs = when {
            cache.isEmpty() -> service.gdgs().apply {
                cache.clear()
                cache.addAll(this)
            }
            else -> cache
        }
        return gdgs.firstOrNull { it?.id == id }
    }
}

interface ObjectRepository<T> {
    fun findAll(): List<T?>
    fun findById(id: String): T?
}
