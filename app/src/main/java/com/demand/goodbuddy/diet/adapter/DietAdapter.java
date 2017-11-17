package com.demand.goodbuddy.diet.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.diet.presenter.DietPresenter;
import com.demand.goodbuddy.dto.DiagnosisCategory;
import com.demand.goodbuddy.dto.DietDiagnosisCategory;
import com.demand.goodbuddy.dto.DietSelfDiagnosis;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ㅇㅇ on 2017-04-26.
 */

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.DietViewHolder> {
    private DietPresenter dietPresenter;

    private ArrayList<DietDiagnosisCategory> dietDiagnosisCategoryList;
    private ArrayList<DiagnosisCategory> diagnosisCategoryList;
    private Context context;

    public DietAdapter(ArrayList<DietDiagnosisCategory> dietDiagnosisCategoryList, Context context, DietPresenter dietPresenter) {
        this.dietDiagnosisCategoryList = dietDiagnosisCategoryList;
        this.context = context;
        this.dietPresenter = dietPresenter;
    }

    @Override
    public DietViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DietViewHolder dietViewHolder = new DietViewHolder(LayoutInflater.from(context).inflate(R.layout.item_diet, parent, false));
        return dietViewHolder;
    }

    @Override
    public void onBindViewHolder(DietViewHolder holder, int position) {
        String dietDiagnosisCategoryName = dietDiagnosisCategoryList.get(position).getName();
        String dietDiagnosisCategoryId = dietDiagnosisCategoryList.get(position).getId() + ".";

        holder.tvDietId.setText(dietDiagnosisCategoryId);
        holder.tvDietQuestion.setText(dietDiagnosisCategoryName);

//        // answer
        dietPresenter.setDiagnosisCategoryList(holder);
    }

    public void setDiagnosisCategory(DietViewHolder holder, int position){
        String diagnosisCategoryName = diagnosisCategoryList.get(position).getName();
        holder.radiobuttons[position].setText(diagnosisCategoryName);
    }

    @Override
    public int getItemCount() {
        return dietDiagnosisCategoryList.size();
    }

    public void setDiagnosisCategoryList(ArrayList<DiagnosisCategory> diagnosisCategories) {
        this.diagnosisCategoryList = diagnosisCategories;
    }


    public class DietViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener {
        private TextView tvDietId;
        private TextView tvDietQuestion;
        private RadioGroup rgDietAnswer;
        private RadioButton rbDietStronglyAgree;
        private RadioButton rbDietStronglyDisagree;
        private RadioButton rbDietAgree;
        private RadioButton rbDietDisagree;
        private RadioButton rbDietNatural;

        private RadioButton[] radiobuttons;

        public DietViewHolder(View itemView) {
            super(itemView);

            tvDietId = (TextView) itemView.findViewById(R.id.tv_diet_question_id);
            tvDietQuestion = (TextView) itemView.findViewById(R.id.tv_diet_question);

            rgDietAnswer = (RadioGroup) itemView.findViewById(R.id.rg_diet);
            rbDietStronglyAgree = (RadioButton) itemView.findViewById(R.id.rb_diet_stronglyagree);
            rbDietStronglyDisagree = (RadioButton) itemView.findViewById(R.id.rb_diet_stronglydisagree);
            rbDietAgree = (RadioButton) itemView.findViewById(R.id.rb_diet_agree);
            rbDietDisagree = (RadioButton) itemView.findViewById(R.id.rb_diet_disagree);
            rbDietNatural = (RadioButton) itemView.findViewById(R.id.rb_diet_natural);

            radiobuttons = new RadioButton[5];
            radiobuttons[0] = rbDietStronglyDisagree;
            radiobuttons[1] = rbDietDisagree;
            radiobuttons[2] = rbDietNatural;
            radiobuttons[3] = rbDietAgree;
            radiobuttons[4] = rbDietStronglyAgree;

            rgDietAnswer.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            int dietDiagnosisCategoryId = dietDiagnosisCategoryList.get(getAdapterPosition()).getId();

            DietSelfDiagnosis dietSelfDiagnosis = new DietSelfDiagnosis();
            dietSelfDiagnosis.setDietDiagnosisCategoryId(dietDiagnosisCategoryId);

            switch (checkedId) {
                case R.id.rb_diet_stronglydisagree:
                    dietSelfDiagnosis.setDiagnosisCategoryId(1);
                    dietPresenter.onCheckedDiagnosisCategory(dietSelfDiagnosis);
                    break;

                case R.id.rb_diet_disagree:
                    dietSelfDiagnosis.setDiagnosisCategoryId(2);
                    dietPresenter.onCheckedDiagnosisCategory(dietSelfDiagnosis);
                    break;

                case R.id.rb_diet_natural:
                    dietSelfDiagnosis.setDiagnosisCategoryId(3);
                    dietPresenter.onCheckedDiagnosisCategory(dietSelfDiagnosis);
                    break;

                case R.id.rb_diet_agree:
                    dietSelfDiagnosis.setDiagnosisCategoryId(4);
                    dietPresenter.onCheckedDiagnosisCategory(dietSelfDiagnosis);
                    break;

                case R.id.rb_diet_stronglyagree:
                    dietSelfDiagnosis.setDiagnosisCategoryId(5);
                    dietPresenter.onCheckedDiagnosisCategory(dietSelfDiagnosis);
                    break;
            }
        }
    }

}
