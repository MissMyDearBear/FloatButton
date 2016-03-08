package view.cjx.com.floatview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.cjx.myfloatview.FloatActionInterFace;
import com.cjx.myfloatview.FloatViewAttr;
import com.cjx.myfloatview.MyFloatView;

import java.util.HashMap;

public class MainActivity extends Activity implements FloatActionInterFace {
    FloatViewAttr attr;
    MyFloatView myFloatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowManager mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        attr = new FloatViewAttr();
        attr.iniX = mWindowManager.getDefaultDisplay().getWidth() / 2 - 50;
        attr.iniY = mWindowManager.getDefaultDisplay().getHeight() / 2 - 50;
        System.out.println("x=" + attr.iniX + ",y=" + attr.iniY);
        attr.viewLayoutID = R.layout.floatview;
        attr.viewID = R.id.myImg;
        myFloatView = new MyFloatView(this, attr, "float");

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "你点击了按钮", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void floatAction(String flag, Object obj) {
        if (flag.equals("float")) {
            HashMap<String, Object> params = (HashMap<String, Object>) obj;
            Toast.makeText(this, params.get("message").toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
