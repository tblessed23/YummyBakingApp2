package com.example.android.yummybakingapp;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.android.yummybakingapp.fragments.RecipeStepsFragment;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.EnumSet;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static java.util.EnumSet.allOf;

@RunWith(AndroidJUnit4.class)
public class RecipeStepsActivityTest {



        @Rule
        public ActivityTestRule<RecipeStepsActivity> mActivityTestRule =
                new ActivityTestRule<>(RecipeStepsActivity.class);

        @Test
        public void clickRecipeFirstStep() {

                onView(withId(R.id.ingredients_TextView))
                        .inRoot(RootMatchers.withDecorView(
                                Matchers.is(mActivityTestRule.getActivity().getWindow().getDecorView())
                        ))
                        .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

                // Check that cardview button work when clicked
                //onView((withId(R.id.my_recycler_view))).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

//                ViewInteraction recyclerView = onView(
//                        allOf(withId(R.id.my_recycler_view), isDescendantOfA(withId(R.id.fragment_recipe_steps_id))
//                                .
//                                childAtPosition(
//                                        withClassName(is("android.widget.LinearLayout")),
//                                        1)));
//                recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
//
//                onView(allOf(withId(R.id.my_recycler_view), isDescendantOfA(withId(R.id.fragment_recipe_steps_id))
//                        .
//                        View.perform(RecyclerViewActions.actionOnItemAtPosition(0, click()))));
        }
//        @Test
//        public void clickRecipeSecondStep() {
//                // Check that cardview button work when clicked
//                onView((withId(R.id.my_recycler_view))).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
//        }
//        @Test
//        public void clickRecipeThirdStep() {
//                // Check that cardview button work when clicked
//                onView((withId(R.id.my_recycler_view))).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
//        }

}
