package in.cyberworldtechnologies.sampmessage;



import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class AppFirebaseInstanceIDService extends FirebaseInstanceIdService{
    private  final static String TAG="FCM Token";
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        sendRegistrationToServer(refreshedToken);
    }
    private void sendRegistrationToServer(String token){
        // TODO: Implement this method to send any registration to app's server.
    }
}


