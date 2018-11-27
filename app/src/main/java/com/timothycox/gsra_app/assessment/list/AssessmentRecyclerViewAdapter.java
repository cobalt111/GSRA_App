package com.timothycox.gsra_app.assessment.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.model.Assessment;

import java.util.List;

class AssessmentRecyclerViewAdapter extends RecyclerView.Adapter<AssessmentRecyclerViewAdapter.ViewHolder> {

    public List<Assessment> assessmentList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameText, dateText;

        public ViewHolder(View view) {
            super(view);
            nameText = view.findViewById(R.id.listingsNameTextView);
            dateText = view.findViewById(R.id.listingsSubTextView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AssessmentRecyclerViewAdapter(List<Assessment> dataset) {
        assessmentList = dataset;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public AssessmentRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

        holder.nameText.setText(assessment.getExaminee());
        holder.dateText.setText(assessment.getTimestamp());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return assessmentList.size();
    }
}
