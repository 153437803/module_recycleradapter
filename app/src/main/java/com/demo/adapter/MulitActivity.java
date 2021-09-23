package com.demo.adapter;

import android.os.Bundle;
import com.demo.adapter.entity.NewMulitItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import lib.kalu.recyclerview.adapter.BaseCommonMultAdapter;
import lib.kalu.recyclerview.viewholder.RecyclerHolder;
import lib.kalu.recyclerview.manager.CrashGridLayoutManager;
import lib.kalu.recyclerview.model.MultModel;

/**
 * description: 分类型
 * created by kalu on 2017/12/5 3:34
 */
public class MulitActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private final ArrayList<NewMulitItem> mArrayList = new ArrayList<>();

    private final BaseCommonMultAdapter mBaseCommonMultAdapter = new BaseCommonMultAdapter<NewMulitItem>() {

        @Override
        public int onMerge(int position) {
            switch (position) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 12:
                case 13:
                case 14:
                case 15:
                    return 1;
                default:
                    return 4;
            }
        }

        @NonNull
        @Override
        protected List<NewMulitItem> onData() {
            return mArrayList;
        }


        @Override
        protected void onNext(RecyclerHolder holder, NewMulitItem model, int position) {
            holder.setText(R.id.position, position + " <=> " + holder.getItemViewType());
        }

        @Override
        protected void onMult() {
            addMult(1, R.layout.activity_mulit_type1);
            addMult(2, R.layout.activity_mulit_type2);
            addMult(3, R.layout.activity_mulit_type3);
            addMult(4, R.layout.activity_mulit_type4);
            addMult(5, R.layout.activity_mulit_type5);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulit);

        for (int i = 0; i < 50; i++) {

            NewMulitItem newMulitItem = new NewMulitItem();

            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 12:
                case 13:
                case 14:
                case 15:
                    newMulitItem.setItemType(MultModel.TYPE_1);
                    break;
                case 11:
                case 16:
                case 18:
                case 8:
                    newMulitItem.setItemType(MultModel.TYPE_2);
                    break;
                case 17:
                case 9:
                    newMulitItem.setItemType(MultModel.TYPE_3);
                    break;
                case 10:
                    newMulitItem.setItemType(MultModel.TYPE_4);
                    break;
                default:
                    newMulitItem.setItemType(MultModel.TYPE_5);
                    break;
            }

            mArrayList.add(newMulitItem);
        }

        mRecyclerView = findViewById(R.id.recycler);

        CrashGridLayoutManager gridLayoutManager = new CrashGridLayoutManager(getApplicationContext(), 4);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mBaseCommonMultAdapter);
    }
}
