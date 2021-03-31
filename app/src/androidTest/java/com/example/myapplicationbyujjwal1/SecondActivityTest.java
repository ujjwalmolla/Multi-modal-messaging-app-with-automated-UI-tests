package com.example.myapplicationbyujjwal1;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.*;

public class SecondActivityTest {
    @Rule
    public IntentsTestRule<SecondActivity> Second_Activity_Rule = new IntentsTestRule<SecondActivity>(SecondActivity.class);

    @Before
    public void setUp() throws Exception {
    }
    @Test
    public void test1() {
        String errorMsg="Enter valid Phone no or Email address";
        onView(withId(R.id.phoneEmail)).perform(typeText("11111"), closeSoftKeyboard());
        onView(withId(R.id.confirmButton)).perform(click());
        onView(withId(R.id.phoneEmail)).check(matches(hasErrorText(errorMsg)));
        onView(withId(R.id.phoneEmail)).perform(clearText());
    }

    @Test
    public void test2() {

        onView(withId(R.id.phoneEmail)).perform( typeText("ujjwal@"));
        closeSoftKeyboard();
        onView(withId(R.id.confirmButton)).perform(click());
        onView(withId(R.id.phoneEmail)).check(matches(hasErrorText("Enter valid Phone no or Email address")));
        onView(withId(R.id.phoneEmail)).perform(clearText());

    }
    @After
    public void tearDown() throws Exception {
    }
}