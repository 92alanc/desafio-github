package com.alancamargo.desafiogithub.users.data.remote

import com.alancamargo.desafiogithub.users.data.api.UserApi
import com.alancamargo.desafiogithub.users.testtools.stubUser
import com.alancamargo.desafiogithub.users.testtools.stubUserResponse
import com.alancamargo.desafiogithub.users.testtools.stubUserSummaryList
import com.alancamargo.desafiogithub.users.testtools.stubUserSummaryResponseList
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response

class UserRemoteDataSourceImplTest {

    private val mockApi = mockk<UserApi>()
    private val remoteDataSource = UserRemoteDataSourceImpl(mockApi)

    @Test
    fun `when api responds with success getUsers should return users`() {
        // GIVEN
        coEvery {
            mockApi.getUsers()
        } returns Response.success(stubUserSummaryResponseList())

        // WHEN
        val actual = runBlocking { remoteDataSource.getUsers() }

        // THEN
        val expected = stubUserSummaryList()
        assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun `when api responds with null body getUsers should return empty list`() {
        // GIVEN
        coEvery {
            mockApi.getUsers()
        } returns Response.success(null)

        // WHEN
        val actual = runBlocking { remoteDataSource.getUsers() }

        // THEN
        assertThat(actual).isEmpty()
    }

    @Test
    fun `when api responds with error getUsers should return empty list`() {
        // GIVEN
        coEvery {
            mockApi.getUsers()
        } returns Response.error(404, "".toResponseBody())

        // WHEN
        val actual = runBlocking { remoteDataSource.getUsers() }

        // THEN
        assertThat(actual).isEmpty()
    }

    @Test
    fun `when api responds with success getUser should return user`() {
        // GIVEN
        coEvery {
            mockApi.getUser(userName = any())
        } returns Response.success(stubUserResponse())

        // WHEN
        val actual = runBlocking { remoteDataSource.getUser(userName = "user") }

        // THEN
        val expected = stubUser()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when api responds with null getUser should return null`() {
        // GIVEN
        coEvery {
            mockApi.getUser(userName = any())
        } returns Response.success(null)

        // WHEN
        val actual = runBlocking { remoteDataSource.getUser(userName = "user") }

        // THEN
        assertThat(actual).isNull()
    }

    @Test
    fun `when api responds with not found error getUser should return null`() {
        // GIVEN
        coEvery {
            mockApi.getUser(userName = any())
        } returns Response.error(404, "".toResponseBody())

        // WHEN
        val actual = runBlocking { remoteDataSource.getUser(userName = "user") }

        // THEN
        assertThat(actual).isNull()
    }

    @Test(expected = Throwable::class)
    fun `when api responds with different error getUser should throw exception`() {
        // GIVEN
        coEvery {
            mockApi.getUser(userName = any())
        } returns Response.error(500, "".toResponseBody())

        // THEN
        runBlocking { remoteDataSource.getUser(userName = "user") }
    }
}
