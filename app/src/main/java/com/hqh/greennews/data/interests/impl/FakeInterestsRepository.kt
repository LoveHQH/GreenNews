package com.hqh.greennews.data.interests.impl

import com.hqh.greennews.data.interests.InterestSection
import com.hqh.greennews.data.interests.InterestsRepository
import com.hqh.greennews.data.interests.TopicSelection
import com.hqh.greennews.utils.addOrRemove
import com.hqh.greennews.data.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


/**
 * Implementation of InterestRepository that returns a hardcoded list of
 * topics, people and publications synchronously.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class FakeInterestsRepository : InterestsRepository {

    private val topics by lazy {
        listOf(
            InterestSection("Android", listOf("Jetpack Compose", "Kotlin", "Jetpack")),
            InterestSection(
                "Programming",
                listOf("Kotlin", "Declarative UIs", "Java", "Unidirectional Data Flow", "C++")
            ),
            InterestSection("Technology", listOf("Pixel", "Google"))
        )
    }

    private val people by lazy {
        listOf(
            "Kobalt Toral",
            "K'Kola Uvarek",
            "Kris Vriloc",
            "Grala Valdyr",
            "Kruel Valaxar",
            "L'Elij Venonn",
            "Kraag Solazarn",
            "Tava Targesh",
            "Kemarrin Muuda"
        )
    }

    private val publications by lazy {
        listOf(
            "Kotlin Vibe",
            "Compose Mix",
            "Compose Breakdown",
            "Android Pursue",
            "Kotlin Watchman",
            "Jetpack Ark",
            "Composeshack",
            "Jetpack Point",
            "Compose Tribune"
        )
    }

    // for now, keep the selections in memory
    private val selectedTopics = MutableStateFlow(setOf<TopicSelection>())
    private val selectedPeople = MutableStateFlow(setOf<String>())
    private val selectedPublications = MutableStateFlow(setOf<String>())

    // Used to make suspend functions that read and update state safe to call from any thread
    private val mutex = Mutex()

    override suspend fun getTopics(): Result<List<InterestSection>> {
        return Result.Success(topics)
    }

    override suspend fun getPeople(): Result<List<String>> {
        return Result.Success(people)
    }

    override suspend fun getPublications(): Result<List<String>> {
        return Result.Success(publications)
    }

    override suspend fun toggleTopicSelection(topic: TopicSelection) {
        mutex.withLock {
            val set = selectedTopics.value.toMutableSet()
            set.addOrRemove(topic)
            selectedTopics.value = set
        }
    }

    override suspend fun togglePersonSelected(person: String) {
        mutex.withLock {
            val set = selectedPeople.value.toMutableSet()
            set.addOrRemove(person)
            selectedPeople.value = set
        }
    }

    override suspend fun togglePublicationSelected(publication: String) {
        mutex.withLock {
            val set = selectedPublications.value.toMutableSet()
            set.addOrRemove(publication)
            selectedPublications.value = set
        }
    }

    override fun observeTopicsSelected(): Flow<Set<TopicSelection>> = selectedTopics

    override fun observePeopleSelected(): Flow<Set<String>> = selectedPeople

    override fun observePublicationSelected(): Flow<Set<String>> = selectedPublications
}
