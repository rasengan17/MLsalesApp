package com.mpro.heroes.mlsalesapp.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.Spinner;
import android.widget.TextView;

import com.mpro.heroes.mlsalesapp.R;
import com.mpro.heroes.mlsalesapp.model.CatalogItem;

/**
 * Created by cmacias on 5/1/16.
 */
public class ProductDetailDialog extends DialogFragment {
    private ProductDetailDialogListener mListener;
    private CatalogItem catalogItem;
    Spinner quantitySpinner;
    private TextView smallDescriptionTextView;
    private TextView productNameTextView;

    public ProductDetailDialog() {
    }

    public static DialogFragment newInstance(ProductDetailDialogListener listener, CatalogItem catalogItem) {
        ProductDetailDialog productDetailDialog = new ProductDetailDialog();
        productDetailDialog.mListener = listener;
        productDetailDialog.catalogItem = catalogItem;
        return productDetailDialog;
    }

    public interface ProductDetailDialogListener {
        void onDialogPositiveClick(CatalogItem catalogItem);

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
                .setTitle(catalogItem.getProductName())
                // Add action buttons
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        catalogItem.setQuantity(quantitySpinner.getSelectedItemPosition());
                        mListener.onDialogPositiveClick(catalogItem);
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

    @Override
    public void onResume() {
        fillView(getDialog());
        super.onResume();
    }

    private void fillView(Dialog view) {
        if (catalogItem != null) {
            smallDescriptionTextView = (TextView) view.findViewById(R.id.small_description);
            productNameTextView = (TextView) view.findViewById(R.id.product_name);
            quantitySpinner = (Spinner) view.findViewById(R.id.quantity);

            smallDescriptionTextView.setText(catalogItem.getSmallDescription());
            productNameTextView.setText(catalogItem.getProductName());
            quantitySpinner.setSelection(catalogItem.getQuantity());
        }
    }
}
