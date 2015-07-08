package com.dcxp.traceit;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.dcxp.traceit.activities.LevelActivity;
import com.dcxp.traceit.activities.MenuActivity;

/**
 * Created by Daniel on 7/8/2015.
 */
public class LoseDialogFragment extends DialogFragment {
    public static final String TAG = "com.dcxp.traceit";
    public static final String LISTENER = "listener";
    private ILoseDialogListener listener;

    public static interface ILoseDialogListener {
        void onReplayClicked();
    }

    public LoseDialogFragment(ILoseDialogListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Game Over!");

        View inflatedView = inflater.inflate(R.layout.lose_dialog_layout, container, false);

        ((ImageButton) inflatedView.findViewById(R.id.btn_home)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MenuActivity.class));
            }

        });

        ((ImageButton) inflatedView.findViewById(R.id.btn_replay)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onReplayClicked();
                dismiss();
            }

        });

        ((ImageButton) inflatedView.findViewById(R.id.btn_levels)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LevelActivity.class));
                dismiss();
            }

        });

        return inflatedView;
    }

}
