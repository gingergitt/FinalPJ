package com.example.cardcheckflow.CCFragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cardcheckflow.CardFrontFragment;
import com.example.cardcheckflow.CheckOutActivity;
import com.example.cardcheckflow.R;
import com.example.cardcheckflow.Utils.CreditCardEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CCAGENCYFragment extends Fragment {

    @BindView(R.id.et_agency)
    CreditCardEditText et_agency;
    TextView tv_agency;

    CheckOutActivity activity;
    CardFrontFragment cardFrontFragment;

    public CCAGENCYFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ccagency, container, false);
        ButterKnife.bind(this, view);

        activity = (CheckOutActivity) getActivity();
        cardFrontFragment = activity.cardFrontFragment;

        tv_agency = cardFrontFragment.getAgency();

        et_agency.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(tv_agency!=null)
                {
                    if (TextUtils.isEmpty(editable.toString().trim()))
                        tv_agency.setText("CARD AGENCY");
                    else
                        tv_agency.setText(editable.toString());

                }

            }
        });

        et_agency.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    if(activity!=null)
                    {
                        activity.nextClick();
                        return true;
                    }

                }
                return false;
            }
        });


        et_agency.setOnBackButtonListener(new CreditCardEditText.BackButtonListener() {
            @Override
            public void onBackClick() {
                if(activity!=null)
                    activity.onBackPressed();
            }
        });

        return view;
    }

    public String getAgency()
    {
        if(et_agency!=null)
            return et_agency.getText().toString().trim();

        return null;
    }

}
