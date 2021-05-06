package com.expediagroup.graphql.examples.server.ktor.schema.models

import com.expediagroup.graphql.generator.scalars.ID
import java.util.*

data class Collection(
    val id: ID,
    val title: String,
    val items: MutableList<Item> = mutableListOf()
) {
    companion object {
        val data = listOf(
            Collection(ID("1"), "Hats").apply {
                items.addAll(listOf(
                    Item(ID(UUID.randomUUID().toString()), "Brown Brim", 2500, "", this),
                    Item(ID(UUID.randomUUID().toString()), "Blue Beanie", 1800, "", this),
                    Item(ID(UUID.randomUUID().toString()), "Brown Cowboy", 3500, "",  this),
                ))
            },
            Collection(ID("2"), "Jackets"),
            Collection(ID("3"), "Mens"),
            Collection(ID("4"), "Sneakers"),
            Collection(ID("5"), "Womens"),
        )
    }
}
