package com.sardinha.desafiouol

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test

class MovieSearchUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun search_for_movie_displays_filtered_results() {
        // Encontra o campo de busca e digita "man"
        onView(withId(R.id.editTextText))
            .perform(typeText("man"), closeSoftKeyboard())

        // Verifica se o primeiro item da lista contém "man"
        onView(withId(R.id.recycler_view))
            .check(matches(hasDescendant(withText(containsString("man")))))
    }
}
