package com.example.appver2;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;


import com.example.appver2.CCFragment.CCAGENCYFragment;
import com.example.appver2.CCFragment.CCNameFragment;
import com.example.appver2.CCFragment.CCNumberFragment;
import com.example.appver2.CCFragment.CCSecureCodeFragment;
import com.example.appver2.CCFragment.CCValidityFragment;
import com.example.appver2.Utils.CreditCardUtils;
import com.example.appver2.Utils.ViewPagerAdapter;
import com.example.appver2.ui.login.LoginActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.appver2.ui.login.LoginActivity.idbt;

public class CheckOutActivity extends FragmentActivity implements FragmentManager.OnBackStackChangedListener {

    @BindView(R.id.btnNext)
    Button btnNext;

//    MainFragment fragment1;
//    PaymentFragment fragment2;

    public CardFrontFragment cardFrontFragment;
    public CardBackFragment cardBackFragment;

    //This is our viewPager
    private ViewPager viewPager;

    CCAGENCYFragment agencyFragment;
    CCNumberFragment numberFragment;
    CCNameFragment nameFragment;
    CCValidityFragment validityFragment;
    CCSecureCodeFragment secureCodeFragment;

    int total_item;
    boolean backTrack = false;

    private boolean mShowingBack = false;

    String cardNumber, cardCVV, cardValidity, cardName, cardAgency;
//    EditText idbt;
    TextView tv_card_number,tv_cardagency,tv_member_name,tv_cvv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
//
//        fragment1 = new MainFragment();
//        fragment2 = new PaymentFragment();
        tv_card_number = findViewById(R.id.tv_card_number);
        tv_cardagency = findViewById(R.id.tv_cardagency);
        tv_member_name = findViewById(R.id.tv_member_name);
        tv_cvv = findViewById(R.id.tv_cvv);

        ButterKnife.bind(this);
        cardFrontFragment = new CardFrontFragment();
        cardBackFragment = new CardBackFragment();

        if (savedInstanceState == null) {
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, cardFrontFragment).commit();

        } else {
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        }

        getFragmentManager().addOnBackStackChangedListener(this);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(4);
        setupViewPager(viewPager);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == total_item)
                    btnNext.setText("SUBMIT");
                else
                    btnNext.setText("NEXT");

                Log.d("track", "onPageSelected: " + position);

                if (position == total_item) {
                    flipCard();
                    backTrack = true;
                } else if (position == total_item - 1 && backTrack) {
                    flipCard();
                    backTrack = false;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                int pos = viewPager.getCurrentItem();
                if (pos < total_item) {
                    viewPager.setCurrentItem(pos + 1);
                } else {
                    checkEntries();

                }

            }
        });


    }

    public void checkEntries() {
        cardName = nameFragment.getName();
        cardAgency = agencyFragment.getAgency();
        //cardnumber 공백제거하기
        cardNumber = numberFragment.getCardNumber().replace(" ","");

        cardValidity = validityFragment.getValidity();
        cardCVV = secureCodeFragment.getValue();


        String id = idbt.getText().toString();
        String cardno = String.valueOf(cardNumber);
        String cardname = String.valueOf(cardName);
        String cardagency = String.valueOf(cardAgency);


        if (TextUtils.isEmpty(cardName)) {
            Toast.makeText(CheckOutActivity.this, "Enter Valid Name", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(cardAgency)  ) {
            Toast.makeText(CheckOutActivity.this, "Enter Valid card agency", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(cardNumber) || !CreditCardUtils.isValid(cardNumber.replace(" ",""))) {
            Toast.makeText(CheckOutActivity.this, "Enter Valid card number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cardValidity)||!CreditCardUtils.isValidDate(cardValidity)) {
            Toast.makeText(CheckOutActivity.this, "Enter correct validity", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cardCVV)||cardCVV.length()<3) {
            Toast.makeText(CheckOutActivity.this, "Enter valid security number", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(CheckOutActivity.this, "Your card is added", Toast.LENGTH_SHORT).show();
                  Toast.makeText(CheckOutActivity.this, "card Agency:"+cardAgency+"cardName"+cardName+"'s Info:"+"\n"+"Card Number:"+cardNumber+"\n"+"card cvv:"+cardCVV+"\n"+
                    "card validity:"+cardValidity,Toast.LENGTH_LONG).show();


            //2초후 main 이동, 값 받아오기
            Handler hand = new Handler();
             hand.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(CheckOutActivity.this, MainActivity.class);

                i.putExtra("cardname",cardName);
                i.putExtra("cardAgency",cardAgency);
                i.putExtra("cardNumber",cardNumber);
                i.putExtra("cardCVV",cardCVV);
                i.putExtra("cardValidity",cardValidity);
                startActivity(i);
                finish();
            }
        },2000);


        CardTask cardTask = new CardTask();
        cardTask.setURL(cardno,cardname,id,cardagency);
//        Log.d("------",cardno.trim());

        cardTask.execute();

            Log.d("--------------","cardagency:"+cardAgency+"cardname:"+cardName+"cardnumber:"+cardNumber+"cardcvv:"+cardCVV+"cardvalid:"+cardValidity);

    }

    @Override
    public void onBackStackChanged() {
        mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        agencyFragment = new CCAGENCYFragment();
        numberFragment = new CCNumberFragment();
        nameFragment = new CCNameFragment();
        validityFragment = new CCValidityFragment();
        secureCodeFragment = new CCSecureCodeFragment();
        adapter.addFragment(agencyFragment);
        adapter.addFragment(numberFragment);
        adapter.addFragment(nameFragment);
        adapter.addFragment(validityFragment);
        adapter.addFragment(secureCodeFragment);

        total_item = adapter.getCount() - 1;
        viewPager.setAdapter(adapter);

    }

    private void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            return;
        }
        // Flip to the back.
        //setCustomAnimations(int enter, int exit, int popEnter, int popExit)

        mShowingBack = true;

        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .replace(R.id.fragment_container, cardBackFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        int pos = viewPager.getCurrentItem();
        if (pos > 0) {
            viewPager.setCurrentItem(pos - 1);
        } else
            super.onBackPressed();
    }

    public void nextClick() {
        btnNext.performClick();
    }


    //-------------------

//
//    public void onFragmentChange(int index){
//        if(index ==0){
//            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();
//
//        }else if(index ==1){
//            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment2).commit();
//        }
//    }
//-----------------

    // --------------------------------------------------------------------------------------------------------------

    public class CardTask extends AsyncTask<String, Void, String> {
     public String id;
        private  String receiveMsg;
        private  String urlstr2;

        public void setURL(String cardno, String cardname, String id , String cardagency) {
            Log.d("---------------------","CardPayment AsyncTask 연결 ");


            urlstr2 = "http://70.12.226.146/oracledb/androidCard.jsp?cardno="+cardno+"&cardname="+cardname
                    +"&id="+id+"&cardagency="+cardagency;
//
//            urlstr2 = "http://172.30.1.31/oracledb/androidCard.jsp?cardno="+cardno+"&cardname="+cardname
//                    +"&id="+id+"&cardagency="+cardagency;
            Log.d("----------------","usl연결확인되었습니다.");
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL(urlstr2);
                Log.d("---------","url="+urlstr2);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.connect();

                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                    Log.d("---------","에러Checking....");
                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d("---------","receiveMsg="+receiveMsg);
            return receiveMsg;
        }

        @Override
        protected void onPostExecute(String s) {

            if(s.trim().equals("11")) {
                Toast.makeText(CheckOutActivity.this, "카드정보를 성공적으로 등록했습니다.", Toast.LENGTH_SHORT).show();

                //select cardno from card where userid = id01?
            }else if(s.trim().equals("00")){
                Log.d("=======","s=0 :"+s);
                Toast.makeText(CheckOutActivity.this,"이미 존재하거나 잘못된 카드정보 입니다."+"\n"+"다시 시도하세요.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CheckOutActivity.this,DriveActivity.class);
//                i.putExtra("id",id);
                startActivity(i);

//                getSupportFragmentManager().beginTransaction().replace(R.id.container,MainFragment.newInstance()).commit();


            }

        }
    }









}
