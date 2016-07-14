package all.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/14.
 */
public class CustomeRefreshHeaderView extends TextView implements SwipeTrigger , SwipeRefreshTrigger {


    public CustomeRefreshHeaderView(Context context) {
        super(context);
    }

    public CustomeRefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomeRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private String getCurrentTimeStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();


        return sdf.format(date);
    }

    @Override
    public void onRefresh() {
        setText("正在获取数据中...\r\n" + getCurrentTimeStr());

    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {

    }

    @Override
    public void onRelease() {

        setText("取消刷新！\r\n" + getCurrentTimeStr());

    }

    @Override
    public void onComplete() {
        setText("刷新成功!\r\n" + getCurrentTimeStr());

    }

    @Override
    public void onReset() {

    }

}
