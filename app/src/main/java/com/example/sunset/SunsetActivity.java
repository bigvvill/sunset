/**********************************************
 *
 * Will Curtis
 * CIT 238 - Week 13
 * SunsetActivity.java
 *
 * This Activity holds the SunsetFragment
 * Fragment.
 *
 **********************************************/

package com.example.sunset;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SunsetActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return SunsetFragment.newInstance();
    }
}
