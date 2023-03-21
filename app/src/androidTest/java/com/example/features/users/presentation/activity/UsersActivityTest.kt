package com.example.features.users.presentation.activity

import com.example.features.utils.AndroidTestUtils.setupServerUrl
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test


class UsersActivityTest {

    private val server = MockWebServer()

    @Before
    fun setUp() {
        setupServerUrl(server)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun shouldDisplayTitle() {
        prepare {
            mockSuccess(server)
        }
        execute {
            initActivityAndMoveToResumed()
        }
        validate {
            validateTitle()
        }
    }

    @Test
    fun shouldDisplayListItem() {
        prepare {
            mockSuccess(server)
        }
        execute {
            initActivityAndMoveToResumed()
        }
        validate {
            validateRecycler()
        }
    }

    @Test
    fun shouldDisplayError() {
        prepare {
            mockError(server)
        }
        execute {
            initActivity()
        }
        validate {
            validateErrorMessage()
        }
    }
}