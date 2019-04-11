package com.timothycox.gsra_app.profile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.model.Assessment;

import java.util.List;

class ExamineeProfileRecyclerViewAdapter extends RecyclerView.Adapter<ExamineeProfileRecyclerViewAdapter.ViewHolder> {

    public List<Assessment> assessmentList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView assessmentTimestampText, completionStatusText;

        public ViewHolder(View view) {
            super(view);
            assessmentTimestampText = view.findViewById(R.id.listingsNameTextView);
            completionStatusText = view.findViewById(R.id.listingsSubTextView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ExamineeProfileRecyclerViewAdapter(List<Assessment> dataset) {
        assessmentList = dataset;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ExamineeProfileRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        Assessment assessment = assessmentList.get(position);
        holder.assessmentTimestampText.setText(assessment.getTimestamp());
        if (assessment.isCompleted()) {
            holder.completionStatusText.setText("Completed");
        }
        else holder.completionStatusText.setText("Incomplete");

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return assessmentList.size();
    }
}
