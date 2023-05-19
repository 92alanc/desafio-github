package com.alancamargo.desafiogithub.users.data.local

import com.alancamargo.desafiogithub.users.data.database.UserDao
import com.alancamargo.desafiogithub.users.data.database.UserSummaryDao
import com.alancamargo.desafiogithub.users.testtools.stubDbUser
import com.alancamargo.desafiogithub.users.testtools.stubDbUserSummary
import com.alancamargo.desafiogithub.users.testtools.stubDbUserSummaryList
import com.alancamargo.desafiogithub.users.testtools.stubUser
import com.alancamargo.desafiogithub.users.testtools.stubUserSummary
import com.alancamargo.desafiogithub.users.testtools.stubUserSummaryList
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UserLocalDataSourceImplTest {

    private val mockUserSummaryDao = mockk<UserSummaryDao>(relaxed = true)
    private val mockUserDao = mockk<UserDao>(relaxed = true)

    private val localDataSource = UserLocalDataSourceImpl(
        mockUserSummaryDao,
        mockUserDao
    )

    @Test
    fun `when database returns users getUsers should return users`() {
        // GIVEN
        coEvery { mockUserSummaryDao.selectUsers() } returns stubDbUserSummaryList()

        // WHEN
        val actual = runBlocking { localDataSource.getUsers() }

        // THEN
        val expected = stubUserSummaryList()
        assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun `when database returns null getUsers should return empty list`() {
        // GIVEN
        coEvery { mockUserSummaryDao.selectUsers() } returns null

        // WHEN
        val actual = runBlocking { localDataSource.getUsers() }

        // THEN
        assertThat(actual).isEmpty()
    }

    @Test
    fun `when database returns user getUser should return user`() {
        // GIVEN
        coEvery { mockUserDao.selectUser(userName = any()) } returns stubDbUser()

        // WHEN
        val actual = runBlocking { localDataSource.getUser(userName = "user") }

        // THEN
        val expected = stubUser()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when database returns null getUser should return null`() {
        // GIVEN
        coEvery { mockUserDao.selectUser(userName = any()) } returns null

        // WHEN
        val actual = runBlocking { localDataSource.getUser(userName = "user") }

        // THEN
        assertThat(actual).isNull()
    }

    @Test
    fun `when user summary exists saveUserSummary should update record in database`() {
        // GIVEN
        coEvery { mockUserSummaryDao.getUserCount(userName = any()) } returns 1

        // WHEN
        val userSummary = stubUserSummary()
        runBlocking { localDataSource.saveUserSummary(userSummary) }

        // THEN
        val expected = stubDbUserSummary()
        coVerify { mockUserSummaryDao.updateUserSummary(expected) }
    }

    @Test
    fun `when user summary does not exist saveUserSummary should insert record in database`() {
        // GIVEN
        coEvery { mockUserSummaryDao.getUserCount(userName = any()) } returns 0

        // WHEN
        val userSummary = stubUserSummary()
        runBlocking { localDataSource.saveUserSummary(userSummary) }

        // THEN
        val expected = stubDbUserSummary()
        coVerify { mockUserSummaryDao.insertUserSummary(expected) }
    }

    @Test
    fun `when user exists saveUser should update record in database`() {
        // GIVEN
        coEvery { mockUserDao.getUserCount(userName = any()) } returns 1

        // WHEN
        val user = stubUser()
        runBlocking { localDataSource.saveUser(user) }

        // THEN
        val expected = stubDbUser()
        coVerify { mockUserDao.updateUser(expected) }
    }

    @Test
    fun `when user does not exist saveUser should insert record in database`() {
        // GIVEN
        coEvery { mockUserDao.getUserCount(userName = any()) } returns 0

        // WHEN
        val user = stubUser()
        runBlocking { localDataSource.saveUser(user) }

        // THEN
        val expected = stubDbUser()
        coVerify { mockUserDao.insertUser(expected) }
    }

    @Test
    fun `deleteUserSummaries should delete all records from database`() {
        // WHEN
        runBlocking { localDataSource.deleteUserSummaries() }

        // THEN
        coVerify { mockUserSummaryDao.deleteUserSummaries() }
    }

    @Test
    fun `deleteUsers should delete all records from database`() {
        // WHEN
        runBlocking { localDataSource.deleteUsers() }

        // THEN
        coVerify { mockUserDao.deleteUsers() }
    }
}
