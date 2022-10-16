package com.jnu.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Book>mlist=new ArrayList<Book>();
    BookBaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBook();
        RecyclerView recyclerView=findViewById(R.id.recycle_view);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter=new BookBaseAdapter(mlist,this);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
        });
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initBook() {
        for(int i=0;i<5;i++){
            Book book01=new Book("信息安全数学基础（第2版）",R.drawable.book_1);
            Book book02=new Book("软件项目管理案例教程（第4版）",R.drawable.book_2);
            Book book03=new Book("创新工程实践",R.drawable.book_no_name);
            mlist.add(book01);
            mlist.add(book02);
            mlist.add(book03);
        }
    }

}