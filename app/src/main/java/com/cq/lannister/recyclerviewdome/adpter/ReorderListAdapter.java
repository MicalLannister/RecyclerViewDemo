package com.cq.lannister.recyclerviewdome.adpter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.cq.lannister.recyclerviewdome.model.entity.Cheese;

/**
 * create by lym on 2019/10/30.
 */
public class ReorderListAdapter extends ListAdapter<Cheese, ReorderListAdapter.CheeseViewHolder> {

    protected ReorderListAdapter(@NonNull DiffUtil.ItemCallback<Cheese> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CheeseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CheeseViewHolder holder, int position) {

    }

    public static class CheeseViewHolder extends RecyclerView.ViewHolder{

        float currentVelocity = 0f;
        SpringAnimation rotation;
        SpringAnimation translationX;
        TextView tv;
        ImageView image;

        public CheeseViewHolder(@NonNull View itemView) {
            super(itemView);
            rotation = new SpringAnimation(itemView, SpringAnimation.ROTATION)
                    .setSpring(new SpringForce()
                    .setFinalPosition(0f)
                    .setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY)
                    .setStiffness(SpringForce.STIFFNESS_LOW))
                    .addUpdateListener((animation, value, velocity) -> currentVelocity = velocity);

            translationX = new SpringAnimation(itemView, SpringAnimation.TRANSLATION_X)
                    .setSpring(new SpringForce()
                    .setFinalPosition(0f)
                    .setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY)
                    .setStiffness(SpringForce.STIFFNESS_LOW));
        }



    }
}
