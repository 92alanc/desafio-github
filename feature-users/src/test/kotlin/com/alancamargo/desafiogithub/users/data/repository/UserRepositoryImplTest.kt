package com.alancamargo.desafiogithub.users.data.repository

import com.alancamargo.desafiogithub.users.data.local.UserLocalDataSource
import com.alancamargo.desafiogithub.users.data.remote.UserRemoteDataSource
import com.alancamargo.desafiogithub.users.testtools.stubRepositoryList
import com.alancamargo.desafiogithub.users.testtools.stubUser
import com.alancamargo.desafiogithub.users.testtools.stubUserSummaryList
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UserRepositoryImplTest {

    private val mockRemoteDataSource = mockk<UserRemoteDataSource>()
    private val mockLocalDataSource = mockk<UserLocalDataSource>(relaxed = true)

    private val repository = UserRepositoryImpl(
        mockRemoteDataSource,
        mockLocalDataSource
    )

    @Test
    fun `when remote data source returns users getUsers should return users`() {
        // GIVEN
        val expected = stubUserSummaryList()
        coEvery { mockRemoteDataSource.getUsers() } returns expected

        // WHEN
        val actual = runBlocking { repository.getUsers() }

        // THEN
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when remote data source returns users getUsers should save users in local`() {
        // GIVEN
        coEvery { mockRemoteDataSource.getUsers() } returns stubUserSummaryList()

        // WHEN
        runBlocking { repository.getUsers() }

        // THEN
        coVerify { mockLocalDataSource.saveUserSummary(userSummary = any()) }
    }

    @Test
    fun `when remote data source throws exception getUsers should return users from local`() {
        // GIVEN
        coEvery { mockRemoteDataSource.getUsers() } throws Throwable()
        val expected = stubUserSummaryList()
        coEvery { mockLocalDataSource.getUsers() } returns expected

        // WHEN
        val actual = runBlocking { repository.getUsers() }

        // THEN
        assertThat(actual).isEqualTo(expected)
    }

    @Test(expected = Throwable::class)
    fun `when remote data source throws exception and local returns empty list getUsers should throw exception`() {
        // GIVEN
        coEvery { mockRemoteDataSource.getUsers() } throws Throwable()
        coEvery { mockLocalDataSource.getUsers() } returns emptyList()

        // THEN
        runBlocking { repository.getUsers() }
    }

    @Test
    fun `when remote data source returns user getUser should return user`() {
        // GIVEN
        val expected = stubUser()
        coEvery { mockRemoteDataSource.getUser(userName = any()) } returns expected

        // WHEN
        val actual = runBlocking { repository.getUser(userName = "user") }

        // THEN
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when remote data source returns user getUser should save user in local`() {
        // GIVEN
        coEvery { mockRemoteDataSource.getUser(userName = any()) } returns stubUser()

        // WHEN
        runBlocking { repository.getUser(userName = "user") }

        // THEN
        coVerify { mockLocalDataSource.saveUser(user = any()) }
    }

    @Test
    fun `when remote data source throws exception getUser should return user from local`() {
        // GIVEN
        coEvery { mockRemoteDataSource.getUser(userName = any()) } throws Throwable()
        val expected = stubUser()
        coEvery { mockLocalDataSource.getUser(userName = any()) } returns expected

        // WHEN
        val actual = runBlocking { repository.getUser(userName = "user") }

        // THEN
        assertThat(actual).isEqualTo(expected)
    }

    @Test(expected = Throwable::class)
    fun `when remote data source throws exception and local returns null getUser should throw exception`() {
        // GIVEN
        coEvery { mockRemoteDataSource.getUser(userName = any()) } throws Throwable()
        coEvery { mockLocalDataSource.getUser(userName = any()) } returns null

        // THEN
        runBlocking { repository.getUser(userName = "user") }
    }

    @Test
    fun `when remote data source returns user repositories getUserRepositories should return user repositories`() {
        // GIVEN
        val expected = stubRepositoryList()
        coEvery {
            mockRemoteDataSource.getUserRepositories(ownerUserName = any())
        } returns expected

        // WHEN
        val actual = runBlocking { repository.getUserRepositories(ownerUserName = "user") }

        // THEN
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when remote data source returns user repositories getUserRepositories should save user repositories in local`() {
        // GIVEN
        coEvery {
            mockRemoteDataSource.getUserRepositories(ownerUserName = any())
        } returns stubRepositoryList()

        // WHEN
        runBlocking { repository.getUserRepositories(ownerUserName = "user") }

        // THEN
        coVerify { mockLocalDataSource.saveRepository(repository = any()) }
    }

    @Test
    fun `when remote data source throws exception getUserRepositories should return user repositories from local`() {
        // GIVEN
        coEvery {
            mockRemoteDataSource.getUserRepositories(ownerUserName = any())
        } throws Throwable()
        val expected = stubRepositoryList()
        coEvery { mockLocalDataSource.getUserRepositories(ownerUserName = any()) } returns expected

        // WHEN
        val actual = runBlocking { repository.getUserRepositories(ownerUserName = "user") }

        // THEN
        assertThat(actual).isEqualTo(expected)
    }

    @Test(expected = Throwable::class)
    fun `when remote data source throws exception and local returns empty list getUserRepositories should throw exception`() {
        // GIVEN
        coEvery {
            mockRemoteDataSource.getUserRepositories(ownerUserName = any())
        } throws Throwable()
        coEvery {
            mockLocalDataSource.getUserRepositories(ownerUserName = any())
        } returns emptyList()

        // THEN
        runBlocking { repository.getUserRepositories(ownerUserName = "user") }
    }
}
