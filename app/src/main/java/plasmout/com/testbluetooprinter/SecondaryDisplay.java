package plasmout.com.testbluetooprinter;

import android.app.Activity;
import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondaryDisplay extends Presentation {
    public SecondaryDisplay(Context outerContext, Display display) {
        super(outerContext, display);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_display);
        try {
            Button testButton = (Button) findViewById(R.id.test);
            //mHelloToast = (Button) findViewById(R.id.btn_presentation_hello_toast);
            testButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast = Toast.makeText(getContext(), "hello presentation", Toast.LENGTH_SHORT );
                    toast.show();
                }
            });


        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
