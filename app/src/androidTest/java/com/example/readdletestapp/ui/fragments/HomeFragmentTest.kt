package com.example.readdletestapp.ui.fragments

import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.readdletestapp.R
import com.example.readdletestapp.data.MainRepository
import com.example.readdletestapp.databinding.ActivityMainBinding
import com.example.readdletestapp.databinding.ItemListBinding
import com.example.readdletestapp.ui.MainActivity
import com.example.readdletestapp.ui.adapters.MainAdapter
import com.example.readdletestapp.ui.viewmodels.MainViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeFragmentTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * Recyclerview shows on screen
     */
    @Test
    fun test_isListFragmentVisibleOnLaunch() {
        onView(withId(R.id.rvItems)).check(matches(isDisplayed()))
    }

    /**
     * Select item in a list
     * navigate to detail screen
     * check details
     */
    @Test
    fun test_selectListItem_inDetailFragmentVisible() {
        onView(withId(R.id.rvItems)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MainAdapter.ListViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.tvDetailedTitle)).check(matches(withText("Jack Jones")))
    }


    /**
     * Select item in a list
     * press back button
     */
    @Test
    fun test_backNavigation_toHomeFragment() {
        onView(withId(R.id.rvItems)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MainAdapter.ListViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.tvDetailedTitle)).check(matches(withText("Jack Jones")))

        pressBack()

        onView(withId(R.id.rvItems)).check(matches(isDisplayed()))
    }
}