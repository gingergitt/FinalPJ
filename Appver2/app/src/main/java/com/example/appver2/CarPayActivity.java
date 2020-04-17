package com.example.appver2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
// CarPayment 액티비티 - 차량 내 결제 , 카드등록 액티비티


public class CarPayActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_pay);

        //값 받아와야 할 것들 : 카드등록하기 (card.io? )
        // 차이름 , 번호판?(랜덤처리?) //선택해지(아직미정) : 선택해지시 텍스트 없어지게
        // 날짜 - date 받아오기
        // 결제위치 , 금액  , 이달의 사용 금액 -> 어떻게 받아올건지??
        // 차이름, 번호판-  필요한지? 받아와야 하는지?
        // 여기서 광고 보고 결제 한것 어떻게 가져올 건지?
        // FCM 와서 누르면 - 바로 광고 보는 액티비티로 이동 -> 쿠폰받기 / 결제하기 에서
        // 결제하기 누르면 -> 결제되었습니다! (토스트) 만 뜨고 -> 결제 금액이나 위치를 가져와야하는?
        //차량 번호 - 회원가입시에 차량 이름, 번호 쓰도록 해서 그 값 가져오기.









    }


}
