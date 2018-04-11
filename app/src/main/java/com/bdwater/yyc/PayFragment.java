package com.bdwater.yyc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.inthecheesefactory.thecheeselibrary.fragment.support.v4.app.StatedFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PayFragment extends StatedFragment {
    private Arguments arguments;
    protected View view;

    @BindView(R.id.titleTextView)
    TextView titleTextView;
    @BindView(R.id.payTextView)
    TextView payTextView;

    public PayFragment() {
        // Required empty public constructor
        setRetainInstance(true);
    }

    public static Fragment newInstance() {
        PayFragment fragment = new PayFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pay, container, false);
        ButterKnife.bind(this, view);

        // show no payment info
        arguments = new Arguments();
        arguments.customerNo = getString(R.string.pay_no_info);
        titleTextView.setText(arguments.customerNo);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void update(Arguments args) {
        // show some demo data
        arguments = args;
        arguments.pay = "218.00";
        titleTextView.setText(arguments.customerNo);
        payTextView.setText(arguments.pay);
    }

    @Override
    protected void onSaveState(Bundle outState) {
        super.onSaveState(outState);

        String json = new Gson().toJson(arguments);
        outState.putString(Arguments.TAG, json);
    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        super.onRestoreState(savedInstanceState);

        String json = savedInstanceState.getString(Arguments.TAG);
        arguments = new Gson().fromJson(json, Arguments.class);
        titleTextView.setText(arguments.customerNo);
        payTextView.setText(arguments.pay);
    }

    public static class Arguments {
        public static final String TAG = "Arguments";
        public String customerNo;
        public String pay;
    }
}
