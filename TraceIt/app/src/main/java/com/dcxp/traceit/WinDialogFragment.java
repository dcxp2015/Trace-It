package com.dcxp.traceit;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dcxp.traceit.activities.GameActivity;
import com.dcxp.traceit.activities.LevelActivity;
import com.dcxp.traceit.activities.MenuActivity;

/**
 * Created by Daniel on 7/8/2015.
 */
public class WinDialogFragment extends DialogFragment {
    public static final String TAG = "com.dcxp.traceit";
    private IWinDialogListener listener;

    public static interface IWinDialogListener {
        void onNextArrowClicked();
    }

    public WinDialogFragment(IWinDialogListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Good Job!");

        View inflatedView = inflater.inflate(R.layout.win_dialog_layout, container, false);

        ((ImageButton) inflatedView.findViewById(R.id.btn_home)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MenuActivity.class));
            }

        });

        ((ImageButton) inflatedView.findViewById(R.id.btn_arrow)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onNextArrowClicked();
            }

        });

        return inflatedView;
    }

}
