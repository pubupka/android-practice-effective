package com.example.chooseyourhero.resourses
import com.example.chooseyourhero.R

data class Hero (
    val id: Int,
    val imageUrl: Int,
    val name: Int,
    val description: Int
)

object heroes {
    val sample = listOf(
        Hero(
            0,
            R.string.iron_man_image_url,
            R.string.iron_man_name,
            R.string.iron_man_description
        ),

        Hero(
            1,
            R.string.thor_image_url,
            R.string.thor_name,
            R.string.thor_description
        ),

        Hero(
            2,
            R.string.venom_image_url,
            R.string.venom_name,
            R.string.venom_description
        )
    )
}
