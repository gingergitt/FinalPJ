package com.example.appver2;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateRequest extends StringRequest {

    final static private String URL = "http://70.12.113.248/oracledb/androidDB.jsp";
    private Map<String, String> parameters;

    public ValidateRequest(String userid, Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener, null);//해당 URL에 POST방식으로 파마미터들을 전송함
        parameters = new HashMap<>();
        parameters.put("userID", userid);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }


}
