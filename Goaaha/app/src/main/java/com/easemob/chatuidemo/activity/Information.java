package com.easemob.chatuidemo.activity;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.easemob.chatuidemo.DemoApplication;
import com.easemob.chatuidemo.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Information extends Activity {
    private static final int PHOTO_REQUEST_CAMERA = 1;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;

    private ImageView mFace;
    private Bitmap bitmap;

    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;

    private LinearLayout back;
    public static String F2;
    private Spinner spinner_f_2;
    private ArrayAdapter<String> spinner_adapter;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        btn = (Button) findViewById(R.id.btn);
        back = (LinearLayout) findViewById(R.id.back);
        spinner_f_2 = (Spinner) findViewById(R.id.id_spinner_f_2);
        spinner_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Information.this.getResources().getStringArray(R.array.s_spinner));
        spinner_f_2.setAdapter(spinner_adapter);

        this.mFace = (ImageView) this.findViewById(R.id.iv_image);
        mFace.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showChoosePicDialog();
            }
        });

        Thread thread = new Thread(new GetdefaultLanguage(handler));
        thread.start();
        selectLanguage();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable(){
                    public void run() {
                        String id = DemoApplication.getInstance().getUserName();
                        HttpClient httpClient = new DefaultHttpClient();
                        HttpPost httpPost = new HttpPost("http://192.168.1.102/blog/selectLanguage?id=" + id+"&from="+ F2);

                        try {
                            HttpResponse httpResponse = httpClient.execute(httpPost);
                            HttpEntity httpEntity = httpResponse.getEntity();
                            StringBuffer stringBuffer = new StringBuffer("");
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent()));
                            String str1 = null;
                            while ((str1 = bufferedReader.readLine()) != null) {
                                stringBuffer.append(str1);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                Toast.makeText(Information.this,"提交成功",Toast.LENGTH_LONG).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                finish();
            }
        });
    }

    protected void showChoosePicDialog() {
        View view;
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("上传头像");
        String[] items = { "从相册中选择图片", "拍照" };
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:// CHOOSE_PICTURE
                        gallery();
                        break;
                    case 1:// TAKE_PICTURE
                        camera();
                        break;
                }
            }
        });
        builder.create().show();
    }

    public void gallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    public void camera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

        if (hasSdcard()) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(Environment
                            .getExternalStorageDirectory(), PHOTO_FILE_NAME)));
        }
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                Uri uri = data.getData();
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAMERA) {
            if (hasSdcard()) {
                tempFile = new File(Environment.getExternalStorageDirectory(),
                        PHOTO_FILE_NAME);
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(Information.this, "δ�ҵ��洢�����޷��洢��Ƭ��", 0).show();
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            try {
                bitmap = data.getParcelableExtra("data");
                this.mFace.setImageBitmap(bitmap);
                boolean delete = tempFile.delete();
                System.out.println("delete = " + delete);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    private void crop(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");

        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 315);
        intent.putExtra("outputY", 315);

        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    private class GetdefaultLanguage implements Runnable {
        private Handler handler;

        public GetdefaultLanguage(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            String id = DemoApplication.getInstance().getUserName();
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://192.168.1.102/blog/getdefaultlanguage?id=" + id);

            try {
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                StringBuffer stringBuffer = new StringBuffer("");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent()));
                String str1 = null;
                while ((str1 = bufferedReader.readLine()) != null) {
                    stringBuffer.append(str1);
                }
                F2 = stringBuffer.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0);
        }

    }
    private void selectLanguage(){
        spinner_f_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String a = Information.this.getResources().getStringArray(R.array.s_spinner)[position];
                if (a.equals("中文")) {
                    F2 = "zh";
                } else if (a.equals("英语")) {
                    F2 = "en";
                } else if (a.equals("粤语")) {
                    F2 = "yue";
                } else if (a.equals("文言文")) {
                    F2 = "wyw";
                } else if (a.equals("日语")) {
                    F2 = "jp";
                } else if (a.equals("韩语")) {
                    F2 = "kor";
                } else if (a.equals("法语")) {
                    F2 = "fra";
                } else if (a.equals("西班牙")) {
                    F2 = "spa";
                } else if (a.equals("泰语")) {
                    F2 = "th";
                } else if (a.equals("阿拉伯语")) {
                    F2 = "ara";
                } else if (a.equals("俄语")) {
                    F2 = "ru";
                } else if (a.equals("葡萄牙语")) {
                    F2 = "pt";
                } else if (a.equals("德语")) {
                    F2 = "de";
                } else if (a.equals("意大利语")) {
                    F2 = "it";
                } else if (a.equals("希腊语")) {
                    F2 = "el";
                } else if (a.equals("荷兰语")) {
                    F2 = "nl";
                } else if (a.equals("波兰语")) {
                    F2 = "pl";
                } else if (a.equals("保加利亚语")) {
                    F2 = "bul";
                } else if (a.equals("爱沙尼亚语")) {
                    F2 = "est";
                } else if (a.equals("丹麦语")) {
                    F2 = "dan";
                } else if (a.equals("芬兰语")) {
                    F2 = "fin";
                } else if (a.equals("捷克语")) {
                    F2 = "cs";
                } else if (a.equals("罗马尼亚语")) {
                    F2 = "rom";
                } else if (a.equals("斯洛文尼亚语")) {
                    F2 = "slo";
                } else if (a.equals("瑞典语")) {
                    F2 = "swe";
                } else if (a.equals("匈牙利语")) {
                    F2 = "hu";
                } else if (a.equals("繁体中文")) {
                    F2 = "cht";
                } else if (a.equals("越南语")) {
                    F2 = "vie";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (F2.equals("zh")) {
                        spinner_f_2.setSelection(0);
                    } else if ( F2.equals("en")) {
                        spinner_f_2.setSelection(1);
                    } else if ( F2.equals("yue")) {
                        spinner_f_2.setSelection(2);
                    } else if (F2.equals("wyw")) {
                        spinner_f_2.setSelection(3);
                    } else if ( F2.equals("jp")) {
                        spinner_f_2.setSelection(4);
                    } else if ( F2.equals("kor")) {
                        spinner_f_2.setSelection(5);
                    } else if ( F2.equals("fra")) {
                        spinner_f_2.setSelection(6);
                    } else if ( F2.equals("spa")) {
                        spinner_f_2.setSelection(7);
                    } else if ( F2.equals("th")) {
                        spinner_f_2.setSelection(8);
                    } else if ( F2.equals("ara")) {
                        spinner_f_2.setSelection(9);
                    } else if (F2.equals("ru")) {
                        spinner_f_2.setSelection(10);
                    } else if ( F2.equals("pt")) {
                        spinner_f_2.setSelection(11);
                    } else if ( F2.equals("de")) {
                        spinner_f_2.setSelection(12);
                    } else if ( F2.equals("it")) {
                        spinner_f_2.setSelection(13);
                    } else if ( F2.equals("el")) {
                        spinner_f_2.setSelection(14);
                    } else if ( F2.equals("nl")) {
                        spinner_f_2.setSelection(15);
                    } else if ( F2.equals("pl")) {
                        spinner_f_2.setSelection(16);
                    } else if ( F2.equals("bul")) {
                        spinner_f_2.setSelection(17);
                    } else if ( F2.equals("est")) {
                        spinner_f_2.setSelection(18);
                    } else if ( F2.equals("dan")) {
                        spinner_f_2.setSelection(19);
                    } else if ( F2.equals("fin")) {
                        spinner_f_2.setSelection(20);
                    } else if ( F2.equals("cs")) {
                        spinner_f_2.setSelection(21);
                    } else if ( F2.equals("rom")) {
                        spinner_f_2.setSelection(22);
                    } else if ( F2.equals("slo")) {
                        spinner_f_2.setSelection(23);
                    } else if ( F2.equals("swe")) {
                        spinner_f_2.setSelection(24);
                    } else if ( F2.equals("hu")) {
                        spinner_f_2.setSelection(25);
                    } else if ( F2.equals("cht")) {
                        spinner_f_2.setSelection(26);
                    } else if ( F2.equals("vie")) {
                        spinner_f_2.setSelection(27);
                    }
            }


        }
    };


}
