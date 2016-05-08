package com.mpro.heroes.mlsalesapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.TextView;

import com.mpro.heroes.mlsalesapp.activity.ExampleFragment;
import com.mpro.heroes.mlsalesapp.activity.MyProductRecyclerViewAdapter;
import com.mpro.heroes.mlsalesapp.activity.PointsCalculatorInitialPointsDialog;
import com.mpro.heroes.mlsalesapp.activity.ProductDetailDialog;
import com.mpro.heroes.mlsalesapp.activity.ProductFragment;
import com.mpro.heroes.mlsalesapp.config.AppConstants;
import com.mpro.heroes.mlsalesapp.model.CatalogItem;
import com.mpro.heroes.mlsalesapp.services.CatalogService;
import com.mpro.heroes.mlsalesapp.services.response.CatalogResponse;
import com.mpro.heroes.mlsalesapp.utils.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PointsCalculatorActivity extends AppCompatActivity implements ProductFragment.OnListFragmentInteractionListener {

    private static final int PRODUCT_FRAGMENT = 1;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_calculator);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        setupViewPager();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(mViewPager);
        }

        getCatalogs();

        showStartingPointsDialog();

    }

    private void getCatalogs() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

// add logging as last interceptor
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mlm-api-git-betomaru.c9users.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        CatalogService service = retrofit.create(CatalogService.class);
        Call<List<CatalogResponse>> call = service.getCatalogs();

        call.enqueue(new Callback<List<CatalogResponse>>() {
            @Override
            public void onResponse(Call<List<CatalogResponse>> call, Response<List<CatalogResponse>> response) {
                List<CatalogResponse> catalogResponse = response.body();
            }

            @Override
            public void onFailure(Call<List<CatalogResponse>> call, Throwable t) {
                Call<List<CatalogResponse>> calll = call;
            }
        });
    }

    private void setupViewPager() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //TODO: change fragment name to static constant 0, 1, 2
        adapter.addFragment(new ProductFragment(), PRODUCT_FRAGMENT, "ONE");
        adapter.addFragment(new ExampleFragment(), 2, "TWO");
        adapter.addFragment(new ExampleFragment(), 3, "THREE");
        mViewPager.setAdapter(adapter);
    }

    private void showStartingPointsDialog() {
        PointsCalculatorInitialPointsDialog.PointsCalculatorInitialPointsDialogListener listener = new PointsCalculatorInitialPointsDialog.PointsCalculatorInitialPointsDialogListener() {
            EditText currentPointsEditText;

            @Override
            public void onDialogPositiveClick(DialogFragment dialog) {
                currentPointsEditText = (EditText) dialog.getDialog().findViewById(R.id.current_points);
                if (currentPointsEditText != null)
                    PreferenceManager.setInt(AppConstants.CURRENT_POINTS, Integer.parseInt(currentPointsEditText.getText().toString()), getBaseContext());

                Log.d("FENIX", Integer.toString(PreferenceManager.getInt(AppConstants.CURRENT_POINTS, 0, getBaseContext())));
            }

            @Override
            public void onDialogNegativeClick(DialogFragment dialog) {
                dialog.getDialog().cancel();
            }
        };
        DialogFragment newFragment = PointsCalculatorInitialPointsDialog.newInstance(listener);
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    private void showProductDetailDialog(CatalogItem catalogItem) {
        ProductDetailDialog.ProductDetailDialogListener listener = new ProductDetailDialog.ProductDetailDialogListener() {

            @Override
            public void onDialogPositiveClick(CatalogItem catalogItem) {
                updateItemInformation(catalogItem);
            }

            @Override
            public void onDialogNegativeClick(DialogFragment dialog) {
                dialog.getDialog().cancel();
            }
        };
        DialogFragment newFragment = ProductDetailDialog.newInstance(listener, catalogItem);
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    private void updateItemInformation(CatalogItem catalogItem) {
        ProductFragment productFragment = adapter.getFragmentById(PRODUCT_FRAGMENT);
        productFragment.updateItem(catalogItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_points_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(MyProductRecyclerViewAdapter.ViewHolder holder) {
        System.out.println(holder.catalogItem.getProductName());
        System.out.println(holder.catalogItem.getQuantity());
        System.out.println(holder.catalogItem.getSmallDescription());

        showProductDetailDialog(holder.catalogItem);


    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_points_calculator2, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        private final FragmentManager manager;
        private List<String> mFragmentNameList = new ArrayList<>();
        private List<Integer> mFragmentIdList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
            this.manager = manager;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, int id, String title) {
            mFragmentIdList.add(id);
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final long itemId = getItemId(position);
            String name = makeFragmentName(container.getId(), itemId);
            Fragment fragment = manager.findFragmentByTag(name);
            if (fragment == null) {
                mFragmentNameList.add(name);
            }
            return super.instantiateItem(container, position);
        }

        private String makeFragmentName(int viewId, long id) {
            return "android:switcher:" + viewId + ":" + id;
        }

        public ProductFragment getFragmentById(Integer fragmentId) {
            ProductFragment productFragment = (ProductFragment) getSupportFragmentManager().findFragmentByTag(mFragmentNameList.get(mFragmentIdList.indexOf(fragmentId)));
            return productFragment;
        }
    }
}
