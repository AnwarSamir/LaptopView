package Adapter;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asi.laptopview.R;

import java.util.List;

import Models.ApiModel;


public class RecyclerViewForListOFApis extends RecyclerView
        .Adapter<RecyclerViewForListOFApis
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private static MyClickListener myClickListener;
    private Activity activity;
    private List<ApiModel> Apislist;


    public RecyclerViewForListOFApis(Activity activity, List<ApiModel> Apislist) {
        this.activity = activity;
        this.Apislist = Apislist;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView name;
        TextView price;
        TextView hd;
        TextView ram;

        public DataObjectHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView);
            price = (TextView) itemView.findViewById(R.id.textView2);
            hd = (TextView) itemView.findViewById(R.id.tagName);
            ram= (TextView) itemView.findViewById(R.id.link);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v);
        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.api_item_of_list, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.name.setText(Apislist.get(position).getName());
        holder.price.setText(Apislist.get(position).getPrice());
        holder.hd.setText(Apislist.get(position).getHd());
        holder.ram.setText(Apislist.get(position).getRam());
    }

    public void addItem(ApiModel dataObj, int index) {
        Apislist.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        Apislist.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return Apislist.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}