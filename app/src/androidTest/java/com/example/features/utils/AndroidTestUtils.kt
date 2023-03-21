package com.example.features.utils

import com.example.features.app.service.WebService
import io.mockk.every
import io.mockk.mockkObject
import okhttp3.mockwebserver.MockWebServer

object AndroidTestUtils {

    fun setupServerUrl(server: MockWebServer) {
        mockkObject(WebService)
        every { WebService.retrofit(WebService.URL_GITHUB) } returns WebService.retrofit(
            server.url("/").toString()
        )
    }
}