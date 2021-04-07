package com.wzq.sample.data.model

/**
 * Created by wzq on 2019-07-24
 *
 */
data class Category(
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val visible: Int,
    val children: List<Category>
) {
    fun fetchChildrenName(): String {
        var name = ""
        children.forEach {
            name += "${it.name}    "
        }
        return name
    }
}
