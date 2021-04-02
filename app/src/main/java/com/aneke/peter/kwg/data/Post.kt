package com.aneke.peter.kwg.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class Post(
    @PrimaryKey(autoGenerate = true)
    val database_id : Int = 0,
    val postId: Long,
    val id: Long,
    val name: String,
    val email: String,
    val body: String
)