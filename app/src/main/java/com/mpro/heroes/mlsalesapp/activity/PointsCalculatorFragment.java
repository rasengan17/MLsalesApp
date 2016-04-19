package com.mpro.heroes.mlsalesapp.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mpro.heroes.mlsalesapp.R;
import com.mpro.heroes.mlsalesapp.config.AppConstants;
import com.mpro.heroes.mlsalesapp.utils.PreferenceManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PointsCalculatorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PointsCalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

@Deprecated
public class PointsCalculatorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PointsCalculatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PointsCalculatorFragment newInstance(String param1, String param2) {
        PointsCalculatorFragment fragment = new PointsCalculatorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PointsCalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        showDialog();
    }

    private void showDialog() {
        PointsCalculatorInitialPointsDialog.PointsCalculatorInitialPointsDialogListener listener = new PointsCalculatorInitialPointsDialog.PointsCalculatorInitialPointsDialogListener() {
            EditText currentPointsEditText;
            @Override
            public void onDialogPositiveClick(DialogFragment dialog) {
                currentPointsEditText = (EditText) dialog.getDialog().findViewById(R.id.current_points);
                if (currentPointsEditText != null)
                PreferenceManager.setInt(AppConstants.CURRENT_POINTS, Integer.parseInt(currentPointsEditText.getText().toString()), getContext());

                Log.d("FENIX",Integer.toString(PreferenceManager.getInt(AppConstants.CURRENT_POINTS, 0, getContext())));
            }

            @Override
            public void onDialogNegativeClick(DialogFragment dialog) {
                dialog.getDialog().cancel();
            }
        };
        DialogFragment newFragment = PointsCalculatorInitialPointsDialog.newInstance(listener);
        newFragment.show(getFragmentManager(), "dialog");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_points_calculator, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
