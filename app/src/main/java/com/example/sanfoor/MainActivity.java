package com.example.sanfoor;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etMessage;
    private FloatingActionButton btnSend;

    private RecyclerView rvMessages;
    private ChatAdapter adapter;

    private View welcomeContainer;

    private final ArrayList<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        View root = findViewById(R.id.main);
        if (root != null) {
            ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // ✅ Bind views (IMPORTANT: use the field, not a local variable)
        welcomeContainer = findViewById(R.id.welcomeContainer);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);

        rvMessages = findViewById(R.id.rvMessages);
        rvMessages.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChatAdapter(messages);
        rvMessages.setAdapter(adapter);

        // Optional: initial bot message (keep welcome visible at start)
        messages.add(new Message("Hi Mahmoud 👋 Ask me anything.", false));
        adapter.notifyItemInserted(messages.size() - 1);

        btnSend.setOnClickListener(v -> {
            String msg = etMessage.getText() != null ? etMessage.getText().toString().trim() : "";
            if (msg.isEmpty()) return;

            // ✅ Hide welcome ONLY when user sends first message
            if (welcomeContainer != null && welcomeContainer.getVisibility() == View.VISIBLE) {
                welcomeContainer.setVisibility(View.GONE);
            }

            // Add user message
            messages.add(new Message(msg, true));
            int pos = messages.size() - 1;
            adapter.notifyItemInserted(pos);
            rvMessages.scrollToPosition(pos);

            etMessage.setText("");

            // Fake bot reply (optional)
            messages.add(new Message("Got it. I’ll help you with that.", false));
            int pos2 = messages.size() - 1;
            adapter.notifyItemInserted(pos2);
            rvMessages.scrollToPosition(pos2);
        });
    }
}
