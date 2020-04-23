package com.example.appver2;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//public class RegisterRequest extends StringRequest {
//
//    final static private String URL = "http://70.12.113.248/oracledb/androidDB.jsp";
//    private Map<String, String> parameters;
//
//    public RegisterRequest(String userid, String userpwd, String userage, String usergender, String userphone,
//                           String useremail, String useragree, Response.Listener<String> listener){
//        super(Method.POST, URL, listener, null);//해당 URL에 POST방식으로 파마미터들을 전송함
//        parameters = new HashMap<>();
//        parameters.put("userid", userid);
//        parameters.put("userpwd", userpwd);
//        parameters.put("userage", userage);
//        parameters.put("usergender", usergender);
//        parameters.put("userphone", userphone);
//        parameters.put("useremail", useremail);
//        parameters.put("useragree", useragree);
//
//    }
//
//    @Override
//    protected Map<String, String> getParams() throws AuthFailureError {
//        return parameters;
//    }
//}


