package com.nine__square.property.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.nine__square.property.Fragments.PostAnswer;
import com.nine__square.property.Models.QuestionAnswerDataModel;
import com.nine__square.property.R;
import com.nine__square.property.Utils.ListingData;
import com.nine__square.property.Utils.VolleySingleton;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionAnswerAdapter extends RecyclerView.Adapter<QuestionAnswerAdapter.ShowData>{

    private List<QuestionAnswerDataModel> dataSet;
    private Context context;
    List<String> getvalue;




    public QuestionAnswerAdapter(
            List<QuestionAnswerDataModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_answer_layout, parent, false);
        return new ShowData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShowData holder, final int position) {

//        System.out.println("datasetssss"+ dataSet.get(position).getDatanull());

        if(dataSet.get(position).getAnswer().equals("No data")){
            holder.answerlay.setVisibility(View.GONE);
        }


        holder.replylay.setVisibility(View.GONE);


        if(ListingData.getInstance(context).getinstance() != null){
            if(ListingData.getInstance(context).getinstance().equals("Reply")){
                holder.replylay.setVisibility(View.VISIBLE);
            }else{
                holder.replylay.setVisibility(View.GONE);
            }
        }




        holder.question.setText(dataSet.get(position).getQuestion());
        String value= dataSet.get(position).getAnswer();

        String assign = value.replace("[","").replace("]","");
        String result = assign.replaceAll("^[\"']+|[\"']+$", "");
        List<String> getvalue = new ArrayList<String>(Arrays.asList(result.split(",")));
        String value1 = "";

            for (int i = 0; i< getvalue.size(); i++){
                String result1 = getvalue.get(i).replaceAll("^[\"']+|[\"']+$", "");

                if(i+1 < getvalue.size()){
                    value1 = value1 + (i+1)+ ". "+ result1 +"."+ "\n";
                }else {
                    value1 = value1 + (i+1)+ ". "+ result1 +".";
                }
            }
        holder.answer.setText(value1);
        holder.name.setText(dataSet.get(position).getName());

        holder.replylay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, PostAnswer.class);
//                intent.putExtra("queid",dataSet.get(position).getQueid());
//                intent.putExtra("question",dataSet.get(position).getQuestion());
//                intent.putExtra("projectid",dataSet.get(position).getProjectid());
//                intent.putExtra("developerid",VolleySingleton.getInstance(context).id());
//                context.startActivity(intent);


                Bundle bundle = new Bundle();
                bundle.putString("question", dataSet.get(position).getQuestion());
                bundle.putString("queid", dataSet.get(position).getQueid());
                bundle.putString("propertyid", dataSet.get(position).getPropertyid());
                bundle.putString("userid", VolleySingleton.getInstance(context).id());

                Fragment fragment = new PostAnswer();
                fragment.setArguments(bundle);
                FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.containerfragment, fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });




    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class ShowData extends RecyclerView.ViewHolder {
        TextView question, answer, name;
        LinearLayout answerlay;
        ImageView replylay;




        public ShowData(@NonNull View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.question);
            answer = itemView.findViewById(R.id.answer);
            name = itemView.findViewById(R.id.name);
            answerlay = itemView.findViewById(R.id.answerlay);
            replylay = itemView.findViewById(R.id.reply);


        }
    }

}



