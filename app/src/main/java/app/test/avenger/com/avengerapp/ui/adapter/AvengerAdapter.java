package app.test.avenger.com.avengerapp.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.test.avenger.com.avengerapp.R;
import app.test.avenger.com.avengerapp.model.AvengerModel;
import app.test.avenger.com.avengerapp.ui.AvengerDetailActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dinesh on 4/27/18.
 */

public class AvengerAdapter extends RecyclerView.Adapter<AvengerAdapter.AvengerHolder> {

    List<AvengerModel> mAvengerModel;
    private Activity mActivity;

    public AvengerAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public AvengerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AvengerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_avenger_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AvengerHolder holder, int position) {
        AvengerModel item = mAvengerModel.get(position);
        holder.setIvAImage(item.getAvengerImage());
        holder.setTvAName(item.getAvengerName());
        holder.setTvARating(item.getAverageRatingTxt());
    }

    @Override
    public int getItemCount() {
        return mAvengerModel == null ? 0 : mAvengerModel.size();
    }

    public void update(List<AvengerModel> mData) {
        this.mAvengerModel = mData;
        notifyDataSetChanged();
    }

    public void update(int listPosition, AvengerModel model) {
        if (listPosition == -1)
            return;
        mAvengerModel.get(listPosition).setAvengerRating(model.getAvengerRating());
        notifyItemChanged(listPosition);
    }

    class AvengerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivAvengerImage)
        ImageView ivAImage;

        @BindView(R.id.tvAvengerName)
        TextView tvAName;

        @BindView(R.id.tvAvengerRating)
        TextView tvARating;

        public AvengerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            setListner();
        }

        private void setIvAImage(int imageId) {
            if (imageId > 0)
                ivAImage.setImageResource(imageId);
        }

        private void setTvAName(String name) {
            tvAName.setText(name);
        }

        private void setTvARating(String ratingTag) {
            tvARating.setText(ratingTag);
        }

        private void setListner() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAvengerModel.get(getLayoutPosition()).setListPosition(getLayoutPosition());
                    Intent intent = new Intent(mActivity, AvengerDetailActivity.class);
                    intent.putExtra("data", mAvengerModel.get(getLayoutPosition()));
                    mActivity.startActivityForResult(intent, 100);
                }
            });
        }
    }
}
