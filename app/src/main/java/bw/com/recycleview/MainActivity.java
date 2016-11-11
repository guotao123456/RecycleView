package bw.com.recycleview;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager vp;
    private Button but1,but2,but3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //适配器
        vp.setAdapter(new FragmentPagerAdapter(
                getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 3;
            }
            //条目展示类型.
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                fragment=new Fragment1();
                return fragment;
            }
        });
        //viewpager的监听事件
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        switchBut(1);
                        break;
                    case 1:
                        switchBut(2);
                        break;
                    case 2:
                        switchBut(3);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    private void init() {
        vp = (ViewPager)findViewById(R.id.vp);
        but1 = (Button) findViewById(R.id.but1);
        but2 = (Button) findViewById(R.id.but2);
        but3 = (Button) findViewById(R.id.but3);
        but1.setOnClickListener(this);
        but2.setOnClickListener(this);
        but3.setOnClickListener(this);
    }
    private void switchBut(int i) {
        switch (i){
            case 1:
                but1.setBackgroundColor(Color.YELLOW);
                but2.setBackgroundColor(Color.WHITE);
                but3.setBackgroundColor(Color.WHITE);
                break;
            case 2:
                but1.setBackgroundColor(Color.WHITE);
                but2.setBackgroundColor(Color.YELLOW);
                but3.setBackgroundColor(Color.WHITE);
                break;
            case 3:
                but1.setBackgroundColor(Color.WHITE);
                but2.setBackgroundColor(Color.WHITE);
                but3.setBackgroundColor(Color.YELLOW);
                break;
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.but1:
                switchBut(1);
                vp.setCurrentItem(0);
                break;
            case R.id.but2:
                switchBut(2);
                vp.setCurrentItem(1);
                break;
            case R.id.but3:
                switchBut(3);
                vp.setCurrentItem(2);
                break;
        }
      }
    }
