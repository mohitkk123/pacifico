package com.example.pro226design.activities;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.example.pro226design.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> obj =new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mobj=null;

    @Before
    public void setUp() throws Exception {
        mobj=obj.getActivity();
    }
    @Test
    public void Testlaunch(){
        View v=mobj.findViewById(R.id.rv1);
        assertNotNull(v);

    }

    @After
    public void tearDown() throws Exception {

    }
}