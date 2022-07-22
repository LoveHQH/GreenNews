package com.hqh.greennews.lite.model

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Poster(
    @PrimaryKey val id: Long,
    val title: String,
    val des: String,
    val image: String,
    val crawlTime: String,
    val sourceTime: String,
    val url: String,
    val urlCode: String,
    val alias: String,
    val content: String,
    val group: String,
    val category: String,
    val sourceName: String,
    val listMetaRelation: List<MetaRelation>
){}
