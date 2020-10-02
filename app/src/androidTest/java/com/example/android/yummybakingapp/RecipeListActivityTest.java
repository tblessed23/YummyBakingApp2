package com.example.android.yummybakingapp;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RecipeListActivityTest {
    @Rule
    public ActivityTestRule<RecipeListActivity> mActivityTestRule =
            new ActivityTestRule<>(RecipeListActivity.class);

    @Test
    public void clickRecipeNutellaPie() {

        // Check that cardview button work when clicked
        onView((withId(R.id.my_recycler_view))).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));


    }

    @Test
    public void clickRecipeBrownies() {
        // Check that cardview button work when clicked
        onView((withId(R.id.my_recycler_view))).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }
    @Test
    public void clickRecipeYellowCake() {
        // Check that cardview button work when clicked
        onView((withId(R.id.my_recycler_view))).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
    }
    @Test
    public void clickCheesecake() {
        // Check that cardview button work when clicked
        onView((withId(R.id.my_recycler_view))).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
    }



}
