package com.example.petcarereminder;

// Author: Burcu Arıcı
// Feature: Basic Instrumental Test (Android Context validation)

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test.
 * This test runs on an Android device/emulator and checks
 * whether the application context and package name are correct.
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void appContext_isCorrect() {

        // 🔹 Context of the app under test
        Context appContext =
                InstrumentationRegistry.getInstrumentation().getTargetContext();

        // 🔹 Context null olmamalı
        assertNotNull(appContext);

        // 🔹 Paket adı doğru mu?
        assertEquals(
                "com.example.petcarereminder",
                appContext.getPackageName()
        );
    }
}
