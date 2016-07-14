package all.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.cardviewtest.R;

import java.util.List;

import all.domain.CardItem;

/**
 * Created by Administrator on 2016/7/13.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ItemViewHolder> {
    private Context context;
    private List<CardItem> data;

    public MyRecyclerAdapter(Context context, List<CardItem> data) {
        this.context = context;
        this.data = data;

    }

    @Override
    public MyRecyclerAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recyclerview_item_layout, null);


        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapter.ItemViewHolder holder, int position) {

        holder.textView.setText(data.get(position).getDesStr());
        holder.imageView.setImageResource(data.get(position).getImageRes());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        //不需要判断类型
        return super.getItemViewType(position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.recyclerview_item_imageview);
            textView = (TextView) itemView.findViewById(R.id.recyclerview_item_textview);
        }
    }
}

