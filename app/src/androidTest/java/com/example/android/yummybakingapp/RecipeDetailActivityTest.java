package com.example.android.yummybakingapp;

import android.content.Intent;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RecipeDetailActivityTest {
    @Rule
    public ActivityTestRule<RecipeDetailActivity> mActivityTestRule =
            new ActivityTestRule<>(RecipeDetailActivity.class, false, true);

//    @Before
//    public void init(){
//        RecipeDetailActivity.class
//                .get
//    }
//@Override
//protected Intent getActivityIntent() {
//    Intent intent = new Intent(InstrumentationRegistry.getContext(),MainActivity.class);
//    intent.putExtra("Key","Value");
//    return intent;
//}
    @Test
    public void clickPreviousButton() {

        //Click on Previous Button
        onView((withId(R.id.previousButton)))
                .perform(click());


    }

    @Test
    public void clickNextButton() {
        //Click on Next Button
        onView((withId(R.id.nextButton)))
                .perform(click());
    }

}
