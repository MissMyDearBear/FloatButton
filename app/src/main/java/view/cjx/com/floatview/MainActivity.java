package view.cjx.com.floatview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.cjx.myfloatview.FloatViewAttr;
import com.cjx.myfloatview.MyFloatView;

public class MainActivity extends AppCompatActivity {
    FloatViewAttr attr;
    MyFloatView myFloatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowManager mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        attr=new FloatViewAttr();
        attr.iniX = mWindowManager.getDefaultDisplay().getWidth()/2-50;
        attr.iniY = mWindowManager.getDefaultDisplay().getHeight()/2-50;
        System.out.println("x="+attr.iniX+",y="+attr.iniY);
        attr.viewLayoutID=R.layout.floatview;
        attr.viewID=R.id.myImg;
        myFloatView=new MyFloatView(this,attr);
        Button btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"你点击了按钮",Toast.LENGTH_SHORT).show();
            }
        });
                

    }
}
