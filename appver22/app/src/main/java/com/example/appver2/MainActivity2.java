//package com.example.appver2;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentActivity;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import butterknife.ButterKnife;
//
//public class MainActivity2 extends FragmentActivity {
//   // MainFragment fragment1;
//   // PaymentFragment fragment2;
//
//    private FragmentManager fragmentManager;
//
//    private Fragment fragment1, fragment2;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ButterKnife.bind(this);
//
//        fragmentManager = getSupportFragmentManager();
//
//        fragment1 = new MainFragment();
//        fragment2 = new PaymentFragment();
//
//        getSupportFragmentManager().beginTransaction().add(R.id.container,fragment1).commit();
//
////        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
////        fragmentTransaction.add(R.id.container, MainFragment.newInstance()).commit();
////        fragmentManager.beginTransaction().replace(R.id.container,fragment1).commit();
//
//
//        //원격제어버튼 - 시동on/off
//        Button button = (Button)findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.container,MainFragment.newInstance()).commit();
//
//                if(fragment1 == null) {
//
//                    fragmentManager.beginTransaction().add(R.id.container, fragment1).commit();
//                }
//
//                if(fragment1 != null) fragmentManager.beginTransaction().show(fragment1).commit();
//                if(fragment2 != null) fragmentManager.beginTransaction().hide(fragment2).commit();
//                Log.d("-----------","mainactivity_button_fragment1 OK");
//
//            }
//
//            });
//
//
//        //페이먼트버튼 - 카드등록, 결제이력 조회
//        Button button2 = (Button)findViewById(R.id.button2);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                getSupportFragmentManager().beginTransaction().replace(R.id.container,PaymentFragment.newInstance()).commit();
//
//
//                if(fragment2 == null) {
//
//                    fragmentManager.beginTransaction().add(R.id.container, fragment2).commit();
//                }
//
//                if(fragment2 != null) fragmentManager.beginTransaction().show(fragment2).commit();
//                if(fragment1 != null) fragmentManager.beginTransaction().hide(fragment1).commit();
//                Log.d("-----------","mainactivity_button_fragment2 OK");
//
//
//
//            }
//        });
//
//
//    }
//
//    public void replaceFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.container, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
//    }
//
//
//
//    public void onFragmentChange(int index){
//        if(index ==0){
//            getSupportFragmentManager().beginTransaction().replace(R.id.container,MainFragment.newInstance()).commit();
//
//        }else if(index ==1){
//            getSupportFragmentManager().beginTransaction().replace(R.id.container,PaymentFragment.newInstance()).commit();
//        }
//    }
//
//}
