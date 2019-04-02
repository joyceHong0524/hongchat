package com.junga.hongchat;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mManager;
    private RecyclerView.Adapter mAdapter; //이거 그냥 어댑터라서 나중에 만약에 메소드를 사용하고 싶으면 꼭 형변환을 해줘야 한다.
    private List<ChatData> mDataset;
    private String myNickName = "nick2";
    private EditText sendText;
    DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mManager);
        recyclerView.setHasFixedSize(true);

        Button sendButton = (Button) findViewById(R.id.button_send);
        sendButton.setOnClickListener(this);
        sendText= (EditText) findViewById(R.id.editText_chat);

        mDataset = new ArrayList<>();
        mAdapter = new ChatAdapter(this,mDataset,myNickName);
        recyclerView.setAdapter(mAdapter);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ChatData chat = dataSnapshot.getValue(ChatData.class);
                ((ChatAdapter) mAdapter).addChat(chat); //이거 정리
           }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.button_send){
            String chatMsg = sendText.getText().toString();

            if(chatMsg != null){
                ChatData chat = new ChatData();
                chat.setNickname(myNickName);
                chat.setMeg(chatMsg);
                myRef.push().setValue(chat);
            }
        }
    }
}
