package com.example.spaceflightnews.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.spaceflightnews.MainCoroutineRule
import com.example.spaceflightnews.getOrAwaitValueTest
import com.example.spaceflightnews.repository.FakeNewsAppRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsAppViewModelTest {


    private lateinit var viewModel: NewsAppViewModel
    private lateinit var fakeNewsAppRepository: FakeNewsAppRepository

    @get:Rule
    var rule = InstantTaskExecutorRule()


    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        fakeNewsAppRepository = FakeNewsAppRepository()
        viewModel = NewsAppViewModel(fakeNewsAppRepository)
    }

    @Test
    fun returnListOfArticles() {
        viewModel.getNews()
        val response = viewModel.articles.getOrAwaitValueTest()
        assertThat(response.data?.get(0)?.id).isEqualTo("id")
        assertThat(response.data?.get(0)?.imageUrl).isEqualTo("imageUrl")
        assertThat(response.data?.size).isEqualTo(1)

    }

    @Test
    fun returnNetworkError() {
        fakeNewsAppRepository.setShouldReturnNetworkError(true)
        viewModel.getNews()
        val response = viewModel.articles.getOrAwaitValueTest()
        assertThat(response.data).isEqualTo(null)
        assertThat(response.message).isNotEmpty()
    }


}