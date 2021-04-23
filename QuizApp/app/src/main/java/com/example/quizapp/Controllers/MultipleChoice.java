package com.example.quizapp.Controllers;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizapp.Models.QuestionHandler;
import com.example.quizapp.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MultipleChoice#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MultipleChoice extends Fragment {
    Button button1, button2, button3, button4;
    int counter;
    int score;
    TextView questionBox, scoreBoard;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MultipleChoice() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MultipleChoice.
     */
    // TODO: Rename and change types and number of parameters
    public static MultipleChoice newInstance(String param1, String param2) {
        MultipleChoice fragment = new MultipleChoice();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();

        String difficulty = (String) bundle.get("difficulty");
        int amount = (int) bundle.get("amount");
        int category = (int) bundle.get("category");
        String type = (String) bundle.get("type");

        questionBox = getActivity().findViewById(R.id.questionBox);
        scoreBoard = getActivity().findViewById(R.id.scoreBoard);

        Random rand = new Random();
        int selection = rand.nextInt(3);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    QuestionHandler quiz = new QuestionHandler(amount, category, difficulty, type);

                    quiz.setAmount(amount);
                    quiz.setType(type);
                    quiz.setDifficulty(difficulty);
                    quiz.setCategory(category);
                    quiz.generateQuestions();

                    while (counter < quiz.getAmount() - 1) {
                        forLoopHelper(quiz, selection);
                    }

                    Intent intent = new Intent();
                    intent.putExtra("score", score);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multiple_choice, container, false);
    }

    public void forLoopHelper(QuestionHandler handler, int selection) {

        scoreBoard.setText(score + "");
        questionBox.setText(handler.getQuestions().get(counter));
        button1 = getActivity().findViewById(R.id.button1);
        button2 = getActivity().findViewById(R.id.button2);
        button3 = getActivity().findViewById(R.id.button3);
        button4 = getActivity().findViewById(R.id.button4);

        switch (selection) {
            case 0:
                button1.setText(handler.getAnswers().get(counter));
                try {
                    button2.setText(handler.getIncorrectAnswers().get(counter).getString(0));
                    button3.setText(handler.getIncorrectAnswers().get(counter).getString(1));
                    button4.setText(handler.getIncorrectAnswers().get(counter).getString(2));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                button2.setText(handler.getAnswers().get(counter));
                try {
                    button1.setText(handler.getIncorrectAnswers().get(counter).getString(0));
                    button3.setText(handler.getIncorrectAnswers().get(counter).getString(1));
                    button4.setText(handler.getIncorrectAnswers().get(counter).getString(2));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                button3.setText(handler.getAnswers().get(counter));
                try {
                    button2.setText(handler.getIncorrectAnswers().get(counter).getString(0));
                    button1.setText(handler.getIncorrectAnswers().get(counter).getString(1));
                    button4.setText(handler.getIncorrectAnswers().get(counter).getString(2));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                button4.setText(handler.getAnswers().get(counter));
                try {
                    button2.setText(handler.getIncorrectAnswers().get(counter).getString(0));
                    button3.setText(handler.getIncorrectAnswers().get(counter).getString(1));
                    button1.setText(handler.getIncorrectAnswers().get(counter).getString(2));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter < handler.getAmount()) {
                    if (button1.getText() == handler.getAnswers().get(counter)) {
                        score++;
                    }
                    counter++;
                }

                System.out.println(counter);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter < handler.getAmount()) {
                    if (button2.getText() == handler.getAnswers().get(counter)) {
                        score++;
                    }
                    counter++;
                }

                System.out.println(counter);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter < handler.getAmount()) {
                    if (button3.getText() == handler.getAnswers().get(counter)) {
                        score++;
                    }
                    counter++;
                }

                System.out.println(counter);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter < handler.getAmount()) {
                    if (button4.getText() == handler.getAnswers().get(counter)) {
                        score++;
                    }
                    counter++;
                }

                System.out.println(counter);
            }
        });
    }
}