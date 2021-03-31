package com.example.myapplicationbyujjwal1;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.JMock1Matchers.equalTo;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> First_Activity_Rule = new IntentsTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
       //Intents.init();
    }
    @Test
    //testcase 1.a
    public void test1() {
        onView(withId(R.id.msg)).perform(clearText());
        onView(withId(R.id.msg))
                .perform(typeText("Input message testing"), closeSoftKeyboard());
        onView(withId(R.id.sendButton)).perform(click());
        intended(allOf(toPackage("com.example.myapplicationbyujjwal1"), hasExtra("typedMsg","Input message testing")));

    }
    //testcase 1.b
    @Test
    public void test2() {
        String typedMsg="Input message testing";
        onView(withId(R.id.msg))
                .perform(clearText(),typeText(typedMsg), closeSoftKeyboard());
        onView(withId(R.id.sendButton)).perform(click());
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
        onView(withId(R.id.msg)).check(matches(withText(typedMsg)));
    }
    //test case 1.c
    @Test
    public void test3() {
        onView(withId(R.id.voiceButton)).perform(click());
        onView(withId(R.id.msg)).check(matches(withText("hello")));
    }


    @After
    public void tearDown() throws Exception {
    }
}