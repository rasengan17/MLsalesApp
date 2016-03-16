package activity;


import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mpro.heroes.mlsalesapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PointsCalculatorInitialPointsDialog extends DialogFragment {


    public PointsCalculatorInitialPointsDialog() {
        // Required empty public constructor
    }

    public static PointsCalculatorInitialPointsDialog newInstance(){
        return new PointsCalculatorInitialPointsDialog();
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
                        // sign in the user ...
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //LoginDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


}
