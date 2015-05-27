package exemplo.daiane.com.histograma;

import com.kimo.lib.alexei.Alexei;
import com.kimo.lib.alexei.Answer;
import com.kimo.lib.alexei.Utils;
import com.kimo.lib.alexei.calculus.ColorPaletteCalculus;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;


public class HistogramaActivity extends Activity {

    private ImageView mImage;
    private Button histogramaButton;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.histograma);
        mImage = (ImageView)findViewById(R.id.img);
        linearLayout = (LinearLayout)findViewById(R.id.info_area);
        histogramaButton = ((Button) findViewById(R.id.histogramaButton));
        histogramaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorPaletteCalculus(5);
            }
        });

    }

    private void colorPaletteCalculus(int numberOfColors) {
        Alexei.with(this)
                .analize(mImage)
                .perform(new ColorPaletteCalculus(Utils.getBitmapFromImageView(mImage),numberOfColors))
                .showMe(new Answer<List<Integer>>() {
                    @Override
                    public void beforeExecution() {
                    }

                    @Override
                    public void afterExecution(List<Integer> answer, long elapsedTime) {
                        try {
                            fillPalleteColors(answer);
                        } catch (NullPointerException e) {
                        }
                    }

                    @Override
                    public void ifFails(Exception error) {
                    }
                });
    }

    private void fillPalleteColors(List<Integer> colors) {
        LayoutInflater inflater = this.getLayoutInflater();
        for(int color : colors) {
            View palleteColor = inflater.inflate(R.layout.item_pallete, linearLayout, false);
            palleteColor.setBackgroundColor(color);
            linearLayout.addView(palleteColor);
        }
    }



}
