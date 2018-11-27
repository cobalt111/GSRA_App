package com.timothycox.gsra_app.examinees;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.model.Examinee;

import java.util.List;

class ExamineesRecyclerViewAdapter extends RecyclerView.Adapter<ExamineesRecyclerViewAdapter.ViewHolder> {

    public List<Examinee> examineeList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameText, ageText, ageLabel;
        ImageView boyFace, girlFace;

        public ViewHolder(View view) {
            super(view);
            nameText = view.findViewById(R.id.listingsNameTextView);
            ageText = view.findViewById(R.id.listingsSubTextView);
            ageLabel = view.findViewById(R.id.examineeRowBoyAgeLabel);
            boyFace = view.findViewById(R.id.examineeRowBoyface);
            girlFace = view.findViewById(R.id.examineeRowGirlface);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ExamineesRecyclerViewAdapter(List<Examinee> dataset) {
        examineeList = dataset;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ExamineesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_examinees_listing_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element


        Examinee examinee = examineeList.get(position);
        String examineAge = String.valueOf(examinee.getAge());

        holder.nameText.setText(examinee.getName());
        holder.ageText.setText(examineAge + " months");

        if (examinee.getGender().equals("Male")) {
            holder.girlFace.setVisibility(View.GONE);
        } else if (examinee.getGender().equals("Female")) {
            holder.boyFace.setVisibility(View.GONE);
        } else {
            holder.boyFace.setVisibility(View.GONE);
            holder.girlFace.setVisibility(View.GONE);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return examineeList.size();
    }
}
