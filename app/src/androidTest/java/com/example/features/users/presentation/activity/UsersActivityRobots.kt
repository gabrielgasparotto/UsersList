package com.example.features.users.presentation.activity

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.features.utils.RecyclerViewMatchers
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import com.example.userslist.R

fun prepare(func: MainActivityPrepare.() -> Unit) = MainActivityPrepare().apply { func() }
fun execute(func: MainActivityExecute.() -> Unit) = MainActivityExecute().apply { func() }
fun validate(func: MainActivityValidate.() -> Unit) = MainActivityValidate().apply { func() }

class MainActivityPrepare {

    fun mockSuccess(server: MockWebServer) {
        val responseCodeSuccess = 200
        val body =
            "[{\"id\":1001,\"name\":\"Eduardo Santos\",\"img\":\"https://randomuser.me/api/portraits/men/9.jpg\",\"username\":\"@eduardo.santos\"}]"
        addRequest(server, responseCodeSuccess, body)
    }

    fun mockError(server: MockWebServer) {
        val responseCodeError = 404
        addRequest(server, responseCodeError)
    }

    private fun addRequest(server: MockWebServer, statusCode: Int, body: String = "") {
        server.enqueue(
            MockResponse().apply {
                setResponseCode(statusCode)
                setBody(body)
            }
        )
    }
}

class MainActivityExecute {

    fun initActivity() {
        launchActivity<UsersActivity>()
    }

    fun initActivityAndMoveToResumed() {
        launchActivity<UsersActivity>().apply {
            moveToState(Lifecycle.State.RESUMED)
        }
    }
}

class MainActivityValidate {

    fun validateTitle(): ViewInteraction = onView(withText(R.string.title))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    fun validateRecycler() = RecyclerViewMatchers.atPosition(
        0,
        withText("@eduardo.santos")
    )

    fun validateErrorMessage(): ViewInteraction = onView(withText(R.string.error))
}