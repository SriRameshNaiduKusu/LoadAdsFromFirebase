package in.cyberworldtechnologies.sampmessage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {

    String Admobadunit;
    private Firebase mRef, mRef1;
    private Button button;
    private InterstitialAd interstitialAd1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );


        MobileAds.initialize( this, "ca-app-pub-3940256099942544~3347511713" );


        button = findViewById( R.id.button );

        FirebaseApp.initializeApp( this );
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference( "message" );
        mRef = new Firebase( "https://sampmessage.firebaseio.com/addunit" );


        mRef.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Admobadunit = dataSnapshot.getValue( String.class );

                interstitialAd1 = new InterstitialAd( Main2Activity.this );
                interstitialAd1.setAdUnitId( Admobadunit );
                interstitialAd1.loadAd( new AdRequest.Builder().build() );

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        } );

        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (interstitialAd1.isLoaded()) {
                    interstitialAd1.show();
                } else {
                    Toast.makeText( Main2Activity.this, "Click again to load ads", Toast.LENGTH_SHORT ).show();


                }

                interstitialAd1.setAdListener( new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        // Code to be executed when an ad finishes loading.
                        interstitialAd1.loadAd( new AdRequest.Builder().build() );
                    }

                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // Code to be executed when an ad request fails.
                    }

                    @Override
                    public void onAdOpened() {
                        // Code to be executed when the ad is displayed.
                        interstitialAd1.loadAd( new AdRequest.Builder().build() );
                    }

                    @Override
                    public void onAdLeftApplication() {
                        // Code to be executed when the user has left the app.
                    }

                    @Override
                    public void onAdClosed() {
                        // Code to be executed when when the interstitial ad is closed.

                        interstitialAd1.loadAd( new AdRequest.Builder().build() );
                    }
                } );

            }
        } );


    }


}
