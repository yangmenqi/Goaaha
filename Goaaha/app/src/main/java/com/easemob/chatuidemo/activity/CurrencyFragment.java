package com.easemob.chatuidemo.activity;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.easemob.chatuidemo.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Lenovo on 2016/11/7.
 */
public class CurrencyFragment extends Fragment {
    private ImageView country1;
    private ImageView country2;
    private TextView name1;
    private TextView name2;
    private TextView currency1;
    private TextView currency2;
    private TextView date;
    private ImageView into;
    private EditText et1;
    private TextView tv2;
    private double rate1;
    private double rate2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency, container, false);

        name1 = (TextView) view.findViewById(R.id.name1);
        name2 = (TextView) view.findViewById(R.id.name2);
        currency1 = (TextView) view.findViewById(R.id.currency1);
        currency2 = (TextView) view.findViewById(R.id.currency2);
        country1 = (ImageView) view.findViewById(R.id.country1);
        country2 = (ImageView) view.findViewById(R.id.country2);

        country1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CurrencyList.class);
                intent.putExtra("countryid", 1);
                if(name1.getText().equals("CNY")){intent.putExtra("nameid1", 0);}
                if(name1.getText().equals("HKD")){intent.putExtra("nameid1", 1);}
                if(name1.getText().equals("MOP")){intent.putExtra("nameid1", 2);}
                if(name1.getText().equals("USD")){intent.putExtra("nameid1", 3);}
                if(name1.getText().equals("EUR")){intent.putExtra("nameid1", 4);}
                if(name1.getText().equals("JPY")){intent.putExtra("nameid1", 5);}
                if(name1.getText().equals("GBP")){intent.putExtra("nameid1", 6);}
                if(name1.getText().equals("AUD")){intent.putExtra("nameid1", 7);}
                if(name1.getText().equals("CAD")){intent.putExtra("nameid1", 8);}
                if(name1.getText().equals("THB")){intent.putExtra("nameid1", 9);}
                if(name1.getText().equals("SGD")){intent.putExtra("nameid1", 10);}
                if(name1.getText().equals("DKK")){intent.putExtra("nameid1", 11);}
                if(name1.getText().equals("SEK")){intent.putExtra("nameid1", 12);}
                if(name1.getText().equals("CHF")){intent.putExtra("nameid1", 13);}
                if(name1.getText().equals("KRW")){intent.putExtra("nameid1", 14);}
                if(name1.getText().equals("MYR")){intent.putExtra("nameid1", 15);}
                if(name1.getText().equals("NZD")){intent.putExtra("nameid1", 16);}
                if(name1.getText().equals("ZAR")){intent.putExtra("nameid1", 17);}
                startActivityForResult(intent, 1);
            }
        });

        country2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CurrencyList.class);
                intent.putExtra("countryid", 2);
                if(name2.getText().equals("CNY")){intent.putExtra("nameid2", 0);}
                if(name2.getText().equals("HKD")){intent.putExtra("nameid2", 1);}
                if(name2.getText().equals("MOP")){intent.putExtra("nameid2", 2);}
                if(name2.getText().equals("USD")){intent.putExtra("nameid2", 3);}
                if(name2.getText().equals("EUR")){intent.putExtra("nameid2", 4);}
                if(name2.getText().equals("JPY")){intent.putExtra("nameid2", 5);}
                if(name2.getText().equals("GBP")){intent.putExtra("nameid2", 6);}
                if(name2.getText().equals("AUD")){intent.putExtra("nameid2", 7);}
                if(name2.getText().equals("CAD")){intent.putExtra("nameid2", 8);}
                if(name2.getText().equals("THB")){intent.putExtra("nameid2", 9);}
                if(name2.getText().equals("SGD")){intent.putExtra("nameid2", 10);}
                if(name2.getText().equals("DKK")){intent.putExtra("nameid2", 11);}
                if(name2.getText().equals("SEK")){intent.putExtra("nameid2", 12);}
                if(name2.getText().equals("CHF")){intent.putExtra("nameid2", 13);}
                if(name2.getText().equals("KRW")){intent.putExtra("nameid2", 14);}
                if(name2.getText().equals("MYR")){intent.putExtra("nameid2", 15);}
                if(name2.getText().equals("NZD")){intent.putExtra("nameid2", 16);}
                if(name2.getText().equals("ZAR")){intent.putExtra("nameid2", 17);}
                startActivityForResult(intent, 1);
            }
        });

        date = (TextView) view.findViewById(R.id.date);
        into = (ImageView) view.findViewById(R.id.into);
        et1 = (EditText) view.findViewById(R.id.et1);
        tv2 = (TextView) view.findViewById(R.id.tv2);

        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://web.juhe.cn:8080/finance/exchange/rmbquot?key=8d573ae59a15f9d61a4f1024a282cf8c";
        client.get(getActivity().getApplicationContext(), url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                // response为返回的JSON对象
                System.out.println(response.toString());
                // 获得 response 中的 result 属性
                try {
                    JSONArray result = response.getJSONArray("result");
                    JSONObject data = result.getJSONObject(0);
                    JSONObject data0 = data.getJSONObject("data1");
                    String output = data0.getString("date");
                    date.setText(output);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        into.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et1.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"请输入所要兑换金额",Toast.LENGTH_SHORT).show();
                }else {
                    AsyncHttpClient client = new AsyncHttpClient();
                    String url = "http://web.juhe.cn:8080/finance/exchange/rmbquot?key=8d573ae59a15f9d61a4f1024a282cf8c";
                    client.get(getActivity().getApplicationContext(), url, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                            // response为返回的JSON对象
                            System.out.println(response.toString());
                            // 获得 response 中的 result 属性
                            try {
                                JSONArray result = response.getJSONArray("result");
                                JSONObject data = result.getJSONObject(0);
                                for (int i = 0; i < 20; i++) {
                                    int k = i + 1;
                                    String j = "" + k;
                                    JSONObject data1 = data.getJSONObject("data" + j);
                                    if (currency1.getText().equals("人民币")) {
                                        rate1 = 100;
                                    } else {
                                        if (data1.getString("name").equals(currency1.getText())) {
                                            rate1 = Double.parseDouble(data1.getString("fSellPri"));
                                        }
                                    }
                                    if (currency2.getText().equals("人民币")) {
                                        rate2 = 100;
                                    } else {
                                        if (data1.getString("name").equals(currency2.getText())) {
                                            rate2 = Double.parseDouble(data1.getString("fSellPri"));
                                        }
                                    }
                                }
                                double calculate = Double.parseDouble(et1.getText().toString()) / rate2 * rate1;
                                String calculate0 = "" + calculate;
                                tv2.setText(calculate0);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 0:
                name1.setText("CNY");
                currency1.setText("人民币");
                country1.setImageResource(R.drawable.china);
                break;
            case 1:
                name1.setText("HKD");
                currency1.setText("港币");
                country1.setImageResource(R.drawable.china);
                break;
            case 2:
                name1.setText("MOP");
                currency1.setText("澳门元");
                country1.setImageResource(R.drawable.china);
                break;
            case 3:
                name1.setText("USD");
                currency1.setText("美元");
                country1.setImageResource(R.drawable.usa);
                break;
            case 4:
                name1.setText("EUR");
                currency1.setText("欧元");
                country1.setImageResource(R.drawable.italy);
                break;
            case 5:
                name1.setText("JPY");
                currency1.setText("日元");
                country1.setImageResource(R.drawable.japan);
                break;
            case 6:
                name1.setText("GBP");
                currency1.setText("英镑");
                country1.setImageResource(R.drawable.uk);
                break;
            case 7:
                name1.setText("AUD");
                currency1.setText("澳大利亚元");
                country1.setImageResource(R.drawable.australia);
                break;
            case 8:
                name1.setText("CAD");
                currency1.setText("加拿大元");
                country1.setImageResource(R.drawable.canada);
                break;
            case 9:
                name1.setText("MOP");
                currency1.setText("泰国铢");
                country1.setImageResource(R.drawable.thailand);
                break;
            case 10:
                name1.setText("SGD");
                currency1.setText("新加坡元");
                country1.setImageResource(R.drawable.singapore);
                break;
            case 11:
                name1.setText("DKK");
                currency1.setText("丹麦克朗");
                country1.setImageResource(R.drawable.denmark);
                break;
            case 12:
                name1.setText("SEK");
                currency1.setText("瑞典克朗");
                country1.setImageResource(R.drawable.sweden);
                break;
            case 13:
                name1.setText("CHF");
                currency1.setText("瑞士法郎");
                country1.setImageResource(R.drawable.switzerland);
                break;
            case 14:
                name1.setText("KRW");
                currency1.setText("韩国元");
                country1.setImageResource(R.drawable.south_korea);
                break;
            case 15:
                name1.setText("MYR");
                currency1.setText("林吉特");
                country1.setImageResource(R.drawable.malaysia);
                break;
            case 16:
                name1.setText("NZD");
                currency1.setText("新西兰元");
                country1.setImageResource(R.drawable.new_zealand);
                break;
            case 17:
                name1.setText("ZAR");
                currency1.setText("南非兰特");
                country1.setImageResource(R.drawable.south_africa);
                break;
            case 18:
                name2.setText("CNY");
                currency2.setText("人民币");
                country2.setImageResource(R.drawable.china);
                break;
            case 19:
                name2.setText("HKD");
                currency2.setText("港币");
                country2.setImageResource(R.drawable.china);
                break;
            case 20:
                name2.setText("MOP");
                currency2.setText("澳门元");
                country2.setImageResource(R.drawable.china);
                break;
            case 21:
                name2.setText("USD");
                currency2.setText("美元");
                country2.setImageResource(R.drawable.usa);
                break;
            case 22:
                name2.setText("EUR");
                currency2.setText("欧元");
                country2.setImageResource(R.drawable.italy);
                break;
            case 23:
                name2.setText("JPY");
                currency2.setText("日元");
                country2.setImageResource(R.drawable.japan);
                break;
            case 24:
                name2.setText("GBP");
                currency2.setText("英镑");
                country2.setImageResource(R.drawable.uk);
                break;
            case 25:
                name2.setText("AUD");
                currency2.setText("澳大利亚元");
                country2.setImageResource(R.drawable.australia);
                break;
            case 26:
                name2.setText("CAD");
                currency2.setText("加拿大元");
                country2.setImageResource(R.drawable.canada);
                break;
            case 27:
                name2.setText("MOP");
                currency2.setText("泰国铢");
                country2.setImageResource(R.drawable.thailand);
                break;
            case 28:
                name2.setText("SGD");
                currency2.setText("新加坡元");
                country2.setImageResource(R.drawable.singapore);
                break;
            case 29:
                name2.setText("DKK");
                currency2.setText("丹麦克朗");
                country2.setImageResource(R.drawable.denmark);
                break;
            case 30:
                name2.setText("SEK");
                currency2.setText("瑞典克朗");
                country2.setImageResource(R.drawable.sweden);
                break;
            case 31:
                name2.setText("CHF");
                currency2.setText("瑞士法郎");
                country2.setImageResource(R.drawable.switzerland);
                break;
            case 32:
                name2.setText("KRW");
                currency2.setText("韩国元");
                country2.setImageResource(R.drawable.south_korea);
                break;
            case 33:
                name2.setText("MYR");
                currency2.setText("林吉特");
                country2.setImageResource(R.drawable.malaysia);
                break;
            case 34:
                name2.setText("NZD");
                currency2.setText("新西兰元");
                country2.setImageResource(R.drawable.new_zealand);
                break;
            case 35:
                name2.setText("ZAR");
                currency2.setText("南非兰特");
                country2.setImageResource(R.drawable.south_africa);
                break;
            default:
                break;
        }
    }
}
