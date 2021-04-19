package com.example.quizapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.util.List;

public class SwipeAdapter extends RecyclerView.Adapter<SwipeAdapter.SwipeViewHolder>{
    //List<QuestionHandler>

    private final DatabaseHelper db;
    private final Context context;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private  List<QuestionHandler> questionHandlerList;

    public SwipeAdapter(Context context, List<QuestionHandler> questionHandlerList) {
        this.context = context;
        this.questionHandlerList = questionHandlerList;
        db = new DatabaseHelper(context);
    }

    public void setData(List<QuestionHandler> questionHandlerList) {
        this.questionHandlerList = questionHandlerList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SwipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_list, parent, false);
        return new SwipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SwipeViewHolder holder, int position) {
        viewBinderHelper.setOpenOnlyOne(true);

        viewBinderHelper.bind(holder.swipeLayout, String.valueOf(questionHandlerList.get(position).getCategory()));
        viewBinderHelper.closeLayout(String.valueOf(questionHandlerList.get(position).getCategory()));
        viewBinderHelper.bind(holder.swipeLayout, String.valueOf(questionHandlerList.get(position).getDifficulty()));
        viewBinderHelper.closeLayout(String.valueOf(questionHandlerList.get(position).getDifficulty()));
        viewBinderHelper.bind(holder.swipeLayout, String.valueOf(questionHandlerList.get(position).getType()));
        viewBinderHelper.closeLayout(String.valueOf(questionHandlerList.get(position).getType()));
        viewBinderHelper.bind(holder.swipeLayout, String.valueOf(questionHandlerList.get(position).getAmount()));
        viewBinderHelper.closeLayout(String.valueOf(questionHandlerList.get(position).getAmount()));

        holder.bindData(questionHandlerList.get(position));

        db.updatePositionOnRecycler(String.valueOf(questionHandlerList.get(position).getId()), (position + 1));
        holder.queueNumber.setText("#" + (position + 1));
    }

    @Override
    public int getItemCount() { return questionHandlerList.size(); }

    public class SwipeViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView queueNumber;
        TextView category;
        TextView difficulty;
        TextView type;
        TextView amount;
        Button play;
        Button delete;
        SwipeRevealLayout swipeLayout;

        public SwipeViewHolder(@NonNull View ItemView) {
            super(ItemView);

            id = ItemView.findViewById(R.id.idText);
            queueNumber = ItemView.findViewById(R.id.queueNumberText);
            category = ItemView.findViewById(R.id.categoryText);
            difficulty = ItemView.findViewById(R.id.difficultyText);
            type = ItemView.findViewById(R.id.typeText);
            amount = ItemView.findViewById(R.id.amountText);
            play = ItemView.findViewById(R.id.queuePlayButton);
            delete = ItemView.findViewById(R.id.queueDeleteButton);
            swipeLayout = ItemView.findViewById(R.id.swipeLayout);

            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    db.deleteQuiz(String.valueOf(id.getText()));
//                    questionHandlerList.remove(getAdapterPosition());ebra
//                    notifyItemRemoved(getAdapterPosition());
//                    notifyItemRangeChanged(getAdapterPosition(), questionHandlerList.size());
                    Toast.makeText(context, "Started Quiz", Toast.LENGTH_SHORT).show();
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.deleteQuiz(String.valueOf(id.getText()));
                    questionHandlerList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), questionHandlerList.size());
                    Toast.makeText(v.getContext(), "Quiz Deleted.", Toast.LENGTH_SHORT).show();
                }
            });

        }

        void bindData(QuestionHandler questionHandler) {
            id.setText(String.valueOf(questionHandler.getId()));
            switch (String.valueOf(questionHandler.getCategory())) {
                case "9":
                    category.setText("Category: General Knowledge");
                    break;
                case "10":
                    category.setText("Category: Books");
                    break;
                case "11":
                    category.setText("Category: Films");
                    break;
                case "12":
                    category.setText("Category: Music");
                    break;
                case "13":
                    category.setText("Category: Musical and Theatres");
                    break;
                case "14":
                    category.setText("Category: Television");
                    break;
                case "15":
                    category.setText("Category: Video Games");
                    break;
                case "16":
                    category.setText("Category: Board Games");
                    break;
                case "17":
                    category.setText("Category: Science and Nature");
                    break;
                case "18":
                    category.setText("Category: Computers");
                    break;
                case "19":
                    category.setText("Category: Math");
                    break;
                case "20":
                    category.setText("Category: Mythology");
                    break;
                case "21":
                    category.setText("Category: Sports");
                    break;
                case "22":
                    category.setText("Category: Geography");
                    break;
                case "23":
                    category.setText("Category: Politics");
                    break;
                case "24":
                    category.setText("Category: Art");
                    break;
                case "25":
                    category.setText("Category: Celebrities");
                    break;
                case "27":
                    category.setText("Category: Animals");
                    break;
                case "28":
                    category.setText("Category: Vehicles");
                    break;
                case "29":
                    category.setText("Category: Comics");
                    break;
                case "30":
                    category.setText("Category: Gadgets");
                    break;
                case "31":
                    category.setText("Category: Japanese Anime and Manga");
                    break;
                case "32":
                    category.setText("Category: Cartoons and Animations");
                    break;
                default:
            }
            switch (String.valueOf(questionHandler.getDifficulty())) {
                case "easy":
                    difficulty.setText("Difficulty: Easy");
                    break;
                case "medium":
                    difficulty.setText("Difficulty: Medium");
                    break;
                case "hard":
                    difficulty.setText("Difficulty: Hard");
                    break;
                default:
            }
            switch (String.valueOf(questionHandler.getType())) {
                case "boolean":
                    type.setText("Type: True/False");
                    break;
                case "multiple":
                    type.setText("Type: Multiple Choice");
                    break;
                default:
            }
            switch (String.valueOf(questionHandler.getAmount())) {
                case "10":
                    amount.setText("Amount of Questions: 10");
                    break;
                case "20":
                    amount.setText("Amount of Questions: 20");
                    break;
                case "30":
                    amount.setText("Amount of Questions: 30");
                    break;
                case "40":
                    amount.setText("Amount of Questions: 40");
                    break;
                case "50":
                    amount.setText("Amount of Questions: 50");
                    break;
                default:
            }
        }
    }

}
