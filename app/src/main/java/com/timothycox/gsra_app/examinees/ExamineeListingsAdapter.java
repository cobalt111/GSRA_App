package com.timothycox.gsra_app.examinees;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.model.Examinee;

import java.util.List;

class ExamineeListingsAdapter extends RecyclerView.Adapter<ExamineeListingsAdapter.ViewHolder> {

    public List<Examinee> examineeList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameText, ageText;

        public ViewHolder(View view) {
            super(view);
            nameText = view.findViewById(R.id.listingsNameTextView);
            ageText = view.findViewById(R.id.listingsSubTextView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ExamineeListingsAdapter(List<Examinee> dataset) {
        examineeList = dataset;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ExamineeListingsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_assessment_listing_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Examinee examinee = examineeList.get(position);
        holder.nameText.setText(examinee.getName());
        holder.ageText.setText(examinee.getAge());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return examineeList.size();
    }
}
