package pt.ulisboa.tecnico.sise.insure.insureapp.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.R;
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSCallLogOut;

public class MainActivity extends AppCompatActivity {

    Context _context = this;
    private Button btnCustomerInfo;
    private Button btnListClaims;
    private Button btnNewClaim;
    private Button buttonlogOut;
    public Intent notIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notIntent = new Intent(this, NotificationService.class);
        startService(notIntent);

        setContentView(R.layout.activity_main);
        buttonlogOut = (Button) findViewById(R.id.logoutButton);

        btnCustomerInfo = (Button) findViewById(R.id.btn_customer_info);
        btnCustomerInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                Intent intent = new Intent(_context, CustomerInfoActivity.class);
                _context.startActivity(intent);
            }
        });

        btnListClaims = (Button) findViewById(R.id.btn_claims_list);
        btnListClaims.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(_context, ListClaimsActivity.class);
                _context.startActivity(intent);
            }
        });

        btnNewClaim = (Button) findViewById(R.id.btn_new_claim);
        btnNewClaim.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                Intent intent = new Intent(_context, NewClaimActivity.class);
                _context.startActivity(intent);
            }
        });

        buttonlogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                new WSCallLogOut(MainActivity.this).execute(GlobalState.getCustomer().getSessionId());

            }
        });
    }
}
