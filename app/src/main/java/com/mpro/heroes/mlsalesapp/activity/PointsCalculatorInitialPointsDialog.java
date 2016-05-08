package com.mpro.heroes.mlsalesapp.activity;


import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mpro.heroes.mlsalesapp.R;
import com.mpro.heroes.mlsalesapp.config.AppConstants;
import com.mpro.heroes.mlsalesapp.utils.PreferenceManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class PointsCalculatorInitialPointsDialog extends DialogFragment {
    public PointsCalculatorInitialPointsDialogListener mListener;

    public interface PointsCalculatorInitialPointsDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    public PointsCalculatorInitialPointsDialog() {
        // Required empty public constructor
    }

    public static PointsCalculatorInitialPointsDialog newInstance(PointsCalculatorInitialPointsDialogListener listener) {
        PointsCalculatorInitialPointsDialog pointsCalculatorInitialPointsDialog = new PointsCalculatorInitialPointsDialog();
        pointsCalculatorInitialPointsDialog.mListener = listener;
        return pointsCalculatorInitialPointsDialog;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.fragment_points_calculator_initial_points_dialog, null))
                .setMessage(R.string.current_points_dialog_message)
                .setTitle(R.string.current_points_dialog_title)
                // Add action buttons
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(PointsCalculatorInitialPointsDialog.this);
                        //PreferenceManager.setInt(AppConstants.CURRENT_POINTS, Integer.parseInt(currentPointsEditText.getText().toString()), getContext());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //LoginDialogFragment.this.getDialog().cancel();
                        mListener.onDialogNegativeClick(PointsCalculatorInitialPointsDialog.this);
                    }
                });


        return builder.create();
    }

}
