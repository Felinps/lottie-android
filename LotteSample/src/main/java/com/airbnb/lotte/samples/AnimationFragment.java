package com.airbnb.lotte.samples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import com.airbnb.lotte.LotteAnimationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class AnimationFragment extends Fragment {
    private static final String ARG_FILE_NAME = "file_name";

    static AnimationFragment newInstance(String fileName) {
        AnimationFragment frag = new AnimationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FILE_NAME, fileName);
        frag.setArguments(args);
        return frag;
    }

    @BindView(R.id.animation_view) LotteAnimationView animationView;
    @BindView(R.id.seek_bar) AppCompatSeekBar seekBar;
    @BindView(R.id.loop_button) ToggleButton loopButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animation, container, false);
        ButterKnife.bind(this, view);

        String fileName = getArguments().getString(ARG_FILE_NAME);
        animationView.setAnimation(fileName);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                animationView.setProgress(progress / 100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return view;
    }

    @OnClick(R.id.play)
    public void onPlayClicked() {
        animationView.play();
    }

    @OnCheckedChanged(R.id.loop_button)
    public void onLoopChanged(boolean loop) {
        animationView.loop(loop);
    }
}
