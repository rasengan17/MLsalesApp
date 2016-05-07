package com.mpro.heroes.mlsalesapp.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.mpro.heroes.mlsalesapp.R;

/**
 * Created by cmacias on 5/1/16.
 */
public class ProductDetailDialog extends DialogFragment {
    private ProductDetailDialogListener mListener;

    public ProductDetailDialog() {
    }

    public static DialogFragment newInstance(ProductDetailDialogListener listener) {
        ProductDetailDialog productDetailDialog = new ProductDetailDialog();
        productDetailDialog.mListener = listener;
        return productDetailDialog;
    }

    public interface ProductDetailDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);

        void onDialogNegativeClick(DialogFragment dialog);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.fragment_product_detail_dialog, null))
                .setMessage(R.string.current_points_dialog_message)
                .setTitle(R.string.current_points_dialog_title)
                // Add action buttons
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(ProductDetailDialog.this);
                        //PreferenceManager.setInt(AppConstants.CURRENT_POINTS, Integer.parseInt(currentPointsEditText.getText().toString()), getContext());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //LoginDialogFragment.this.getDialog().cancel();
                        mListener.onDialogNegativeClick(ProductDetailDialog.this);
                    }
                });


        return builder.create();
    }
}
