package com.timothycox.gsra_app.assessment.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timothycox.gsra_app.R;
import com.timothycox.gsra_app.model.Assessment;

import java.util.List;

public class ListingsAdapter extends RecyclerView.Adapter<ListingsAdapter.ViewHolder> {

    public List<Assessment> assessmentList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameText, dateText;


        public ViewHolder(View view) {
            super(view);
            nameText = view.findViewById(R.id.listingsNameView);
            dateText = view.findViewById(R.id.listingsDateView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListingsAdapter(List<Assessment> dataset) {
        assessmentList = dataset;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ListingsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

//        if (animal != null) {
//            String name = animal.get("name");
//            String type = animal.get("type");
//            String color = animal.get("color");
//            if ((name != null && !name.equals("")) && (color != null && !color.equals(""))) {
//                String colType = color.substring(0,1).toUpperCase() + color.substring(1) + " " + type;
//                holder.nameView.setText(name);
//                holder.typeView.setText(colType);
//            }
//            else if ((name != null && !name.equals(""))){
//                holder.nameView.setText(name);
//                holder.typeView.setText(type);
//            }
//            else if (color != null && !color.equals("")) {
//                String colType = color.substring(0,1).toUpperCase() + color.substring(1) + " " + type;
//                holder.nameView.setText(colType);
//            }
//            else holder.nameView.setText(type);
//
//            if (animal.get("thumbURL") != null) {
//                // todo do proper rotations
//                Picasso.get()
//                        .load(animal.get("thumbURL"))
//                        .resize(60,60)
//                        .centerCrop()
//                        .into(holder.imageView);
//            }
//            else {
//
//                Context context = holder.imageView.getContext();
//                holder.imageView.setMaxHeight(60);
//                holder.imageView.setMaxWidth(60);
//
//                switch (type) {
//                    case "Dog":
//                        holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.lnf_dog));
//                        break;
//                    case "Cat":
//                        holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.lnf_cat));
//                        break;
//                    case "Bird":
//                        holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.lnf_bird));
//                        break;
//                    case "Ferret":
//                        holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.lnf_ferret));
//                        break;
//                    case "Hamster/Guinea Pig":
//                        holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.lnf_hamster));
//                        break;
//                    case "Mouse/Rat":
//                        holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.lnf_mouse));
//                        break;
//                    case "Snake/Lizard":
//                        holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.lnf_snake));
//                        break;
//                    case "Other":
//                        holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.lnf_other));
//                        break;
//                    default:
//                        holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.lnf_other));
//                        break;
//                }
//            }
//        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return assessmentList.size();
    }
}
