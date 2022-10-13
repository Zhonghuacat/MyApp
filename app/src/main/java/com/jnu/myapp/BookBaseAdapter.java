package com.jnu.myapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookBaseAdapter extends RecyclerView.Adapter<BookBaseAdapter.MyHolder> {

    private List<Book> mBookList;
    private Context mContext;
    private OnItemClickListener mItemClickListener;
    private BookBaseAdapter bookBaseAdapter=this;

    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.mItemClickListener=itemClickListener;
    }

    //构造函数初始化数据
    public BookBaseAdapter(List<Book>mBookList,Context mContext){
        this.mBookList=mBookList;
        this.mContext=mContext;
    }

    //创建Holder时候将去绑定一个Item
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    //给Holder的Item的View绑定值
    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder,int position) {
        Book book=mBookList.get(holder.getAdapterPosition());

        holder.bookName.setText(book.getName());
        holder.bookImage.setImageResource(book.getImageID());

        if(mItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(holder.itemView,holder.getAdapterPosition());
                }
            });
        }
    }

    //获取数据的量，以加载视图
    @Override
    public int getItemCount() {
        return mBookList.size();
    }

    //用Holder找到Item的中的view
    class MyHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
        private ImageView bookImage;
        private TextView bookName;
        public MyHolder(View view) {
            super(view);
            bookImage=view.findViewById(R.id.imageView);
            bookName=view.findViewById(R.id.textView);
            view.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            MenuItem star=contextMenu.add(Menu.NONE,1,1,"ADD");
            MenuItem edit=contextMenu.add(Menu.NONE,2,2,"Edit");
            MenuItem delete=contextMenu.add(Menu.NONE,3,3,"Delete");
            star.setOnMenuItemClickListener(this);
            edit.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int s1=mBookList.get(getAdapterPosition()).getID();
            switch (item.getItemId()){
                case 1:
                    Toast.makeText(mContext, "pressed on add", Toast.LENGTH_SHORT).show();
                    mBookList.add(getAdapterPosition(),new Book("newBook",R.drawable.book_1));
                    bookBaseAdapter.notifyItemInserted(getAdapterPosition());
                    break;
                case 2:
                    Toast.makeText(mContext, "pressed on edit", Toast.LENGTH_SHORT).show();
                    mBookList.set(getAdapterPosition(),new Book("EDITBOOK",R.drawable.book_2));
                    bookBaseAdapter.notifyItemChanged(getAdapterPosition());
                    break;
                case 3:
                    Toast.makeText(mContext, "pressed on delete", Toast.LENGTH_SHORT).show();
                    mBookList.remove(getAdapterPosition());
                    bookBaseAdapter.notifyItemRemoved(getAdapterPosition());
                    break;
            }
            return true;
        }

    }

}
