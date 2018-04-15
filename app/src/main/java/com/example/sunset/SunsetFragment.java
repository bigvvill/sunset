/**********************************************
 *
 * Will Curtis
 * CIT 238 - Week 13
 * SunsetFragment.java
 *
 * This Fragment displays a "sunrise" and
 * "sunset" by clicking on the View. It is used
 * to demonstrate animation and animator sets.
 *
 **********************************************/

package com.example.sunset;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

public class SunsetFragment extends Fragment {

    // declare views
    private View mSceneView;
    private View mSunView;
    private View mSkyView;

    // declare colors
    private int mBlueSkyColor;
    private int mSunsetColor;
    private int mNightSkyColor;

    // flag to determine sunrise or sunset
    boolean set = true;

    public static SunsetFragment newInstance() {
        return new SunsetFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sunset, container, false);

        // get views
        mSceneView = view;
        mSunView = view.findViewById(R.id.sun);
        mSkyView = view.findViewById(R.id.sky);

        // get colors
        Resources resources = getResources();
        mBlueSkyColor = resources.getColor(R.color.blue_sky);
        mSunsetColor = resources.getColor(R.color.sunset_sky);
        mNightSkyColor = resources.getColor(R.color.night_sky);

        // view listener
        mSceneView.setOnClickListener(new View.OnClickListener() {

            @Override
            // determine weather to "set" or "rise"
            public void onClick(View v) {
                if (set) {
                    startSetAnimation();
                } else {
                    startRiseAnimation();
                }
            }
        });
        return view;
    }

    // displays sun "setting"
    private void startSetAnimation() {
        float sunYStart = mSunView.getTop();
        float sunYEnd = mSkyView.getHeight();

        // animator to change position of sun
        ObjectAnimator heightAnimator = ObjectAnimator
                .ofFloat(mSunView, "y", sunYStart, sunYEnd).setDuration(3000);
        // interpolator to make sun accelerate
        heightAnimator.setInterpolator(new AccelerateInterpolator());

        // animator to change the color of the mSkyView while the sun is "setting"
        ObjectAnimator sunsetSkyAnimator = ObjectAnimator
                .ofInt(mSkyView, "backgroundColor", mBlueSkyColor, mSunsetColor)
                .setDuration(3000);
        // evaluator allows for smooth transition between colors
        sunsetSkyAnimator.setEvaluator(new ArgbEvaluator());

        // animator to change the color of the mSkyView after the sun "set"
        ObjectAnimator nightSkyAnimator = ObjectAnimator.ofInt(mSkyView,
                "backgroundColor", mSunsetColor, mNightSkyColor).setDuration(1500);
        // evaluator allows for smooth transition between colors
        nightSkyAnimator.setEvaluator(new ArgbEvaluator());

        // animator set that holds the order the animators are called
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(heightAnimator).with(sunsetSkyAnimator).before(nightSkyAnimator);
        animatorSet.start();
        // toggle flag
        set = false;
    }

    // displays sun "rising"
    private void startRiseAnimation() {
        float sunYStart = mSunView.getTop();
        float sunYEnd = mSkyView.getHeight();

        // animator to change position of sun
        ObjectAnimator heightAnimator = ObjectAnimator
                .ofFloat(mSunView, "y", sunYEnd, sunYStart).setDuration(3000);
        // interpolator to make sun accelerate
        heightAnimator.setInterpolator(new AccelerateInterpolator());

        // animator to change the color of the mSkyView while the sun is "rising"
        ObjectAnimator sunsetSkyAnimator = ObjectAnimator
                .ofInt(mSkyView, "backgroundColor", mSunsetColor, mBlueSkyColor)
                .setDuration(3000);
        // evaluator allows for smooth transition between colors
        sunsetSkyAnimator.setEvaluator(new ArgbEvaluator());

        // animator to change the color of the mSkyView after the sun "rise"
        ObjectAnimator nightSkyAnimator = ObjectAnimator.ofInt(mSkyView,
                "backgroundColor", mNightSkyColor, mSunsetColor).setDuration(1500);
        // evaluator allows for smooth transition between colors
        nightSkyAnimator.setEvaluator(new ArgbEvaluator());

        // animator set that holds the order the animators are called
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(nightSkyAnimator).with(heightAnimator).before(sunsetSkyAnimator);
        animatorSet.start();
        // toggle flag
        set = true;
    }
}
