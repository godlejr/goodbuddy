package com.demand.goodbuddy.recommend;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.dto.Recommend;

import java.util.List;

/**
 * Created by ㅇㅇ on 2017-10-30.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder> {
    private Context context;
    private List<Recommend> recommends;

    public RecommendAdapter(Context context, List<Recommend> recommends) {
        this.context = context;
        this.recommends = recommends;
    }

    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecommendViewHolder view = new RecommendViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recommend, parent, false), context);
        return view;
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, int position) {
        final Recommend recommend = recommends.get(position);
        String title = recommend.getTitle();
        String subTitle = recommend.getSubTitle();
        final String content = recommend.getContent();

        holder.tv_recommend_title.setText(title);
        holder.tv_recommend_content.setText(subTitle);
        holder.ll_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecommendDetailActivity.class);
                intent.putExtra("recommend", recommend);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recommends.size();
    }

    public static class RecommendViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout ll_recommend;
        private TextView tv_recommend_title;
        private TextView tv_recommend_content;
        private ImageView iv_recommend;
        private Context context;

        public RecommendViewHolder(View itemView, Context context) {
            super(itemView);

            this.context = context;
            ll_recommend = (LinearLayout)itemView.findViewById(R.id.ll_recommend);
            tv_recommend_title = (TextView) itemView.findViewById(R.id.tv_recommend_title);
            tv_recommend_content = (TextView) itemView.findViewById(R.id.tv_recommend_content);
            iv_recommend = (ImageView) itemView.findViewById(R.id.iv_recommend);

        }


    }
}
