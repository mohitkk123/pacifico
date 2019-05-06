package com.example.pro226design;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.example.pro226design.activities.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import static org.junit.Assert.assertNotNull;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> obj =new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mobj=null;

    @Before
    public void setUp() throws Exception {
        mobj=obj.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        View v=mobj.findViewById(R.id.rv1);
        assertNotNull(v);
    }
}