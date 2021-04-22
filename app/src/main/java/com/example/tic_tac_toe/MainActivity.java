package com.example.tic_tac_toe;

import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
    TextView headerText;

    int player_0 = 0;
    int player_x = 1;
    int active_player = player_0;
    int[] posFilled = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

    boolean GameIsActive = true;
    //private Object white;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headerText = findViewById(R.id.headertext);

        btn0 = findViewById(R.id.button);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        if(!GameIsActive)
            return;

        //logic
        Button clickedBtn = findViewById(v.getId());
        int clickedTag = Integer.parseInt(v.getTag().toString());

        if(posFilled[clickedTag] != -1){
            return;
        }

        posFilled[clickedTag] = active_player;

        if(active_player == player_0){
            clickedBtn.setText("O");
            clickedBtn.setBackground(getDrawable(android.R.color.holo_orange_light));
            active_player = player_x;
            headerText.setText("X's Turn");
        }
        else{
            clickedBtn.setText("X");
            clickedBtn.setBackground(getDrawable(android.R.color.holo_blue_bright));
            active_player = player_0;
            headerText.setText("O's Turn");
        }
        checkWinner();

    }
    private void checkWinner(){
        //we have to define the winning positions first
        int[][] winPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

        for(int i=0; i<8; i++){
            int val0 = winPos[i][0];
            int val1 = winPos[i][1];
            int val2 = winPos[i][2];

            if ((posFilled[val0]) == posFilled[val1] && posFilled[val1] == posFilled[val2]) {

                if(posFilled[val0] != -1){

                    GameIsActive = false;

                    if(posFilled[val0] == player_0)
                        showDialog("O is winner");
                    else
                        showDialog("X is winner");
                }
            }
        }
        int count = 0;
        for(int i=0; i<9; i++){
            if (posFilled[i] != -1){
                count++;
            }
        }
        if(count == 9){
            showDialog("DRAW");
        }

    }
    private void showDialog(String wintext){
        new AlertDialog.Builder(this)
                .setTitle(wintext)
                .setPositiveButton("RESTART", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gameRestart();

                    }
                })
                .show();
    }
    //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void gameRestart(){
        active_player = player_0;
        headerText.setText("O'Turn");
        posFilled = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,};
        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");

        btn0.setBackground(getDrawable(android.R.color.white));
        btn1.setBackground(getDrawable(android.R.color.white));
        btn2.setBackground(getDrawable(android.R.color.white));
        btn3.setBackground(getDrawable(android.R.color.white));
        btn4.setBackground(getDrawable(android.R.color.white));
        btn5.setBackground(getDrawable(android.R.color.white));
        btn6.setBackground(getDrawable(android.R.color.white));
        btn7.setBackground(getDrawable(android.R.color.white));
        btn8.setBackground(getDrawable(android.R.color.white));

        GameIsActive = true;



    }
    
}
