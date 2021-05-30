package com.example.readdletestapp.data

import com.example.readdletestapp.IMG_URLS
import com.example.readdletestapp.Item
import com.example.readdletestapp.NAMES
import com.example.readdletestapp.SURNAMES
import kotlin.random.Random

class MainRepository {
    suspend fun simulateDataChanges(items: List<Item>): List<Item> {
        val list = items.toMutableList()
        val rand = Random(System.currentTimeMillis())
        val switch = rand.nextBoolean()
        val range = rand.nextInt(5, 15)

        if (list.size > range) repeat(range) {
            if (switch) list.removeAt(rand.nextInt(list.size))
            else {
                val pos = rand.nextLong(list.size.toLong())
                list.add(
                    pos.toInt(),
                    Item(
                        pos,
                        "${NAMES[rand.nextInt(until = NAMES.size - 1)]} ${SURNAMES[rand.nextInt(until = SURNAMES.size - 1)]}",
                        IMG_URLS[rand.nextInt(until = IMG_URLS.size - 1)],
                        rand.nextBoolean()
                    )
                )
            }
        }
        return list
    }


    suspend fun generateData(size: Int): List<Item> {
        val list = mutableListOf<Item>()
        list.add(Item(0, "Jack Jones", IMG_URLS[Random.nextInt(until = IMG_URLS.size - 1)], true))
        for (i in 1 until size) {
            list += Item(
                imageUrl = IMG_URLS[Random.nextInt(until = IMG_URLS.size - 1)],
                text = "${NAMES[Random.nextInt(until = NAMES.size - 1)]} ${SURNAMES[Random.nextInt(until = SURNAMES.size - 1)]}",
                id = i.toLong(),
                status = Random.nextBoolean()
            )
        }
        return list
    }

}