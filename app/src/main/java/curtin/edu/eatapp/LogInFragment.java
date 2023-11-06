package curtin.edu.eatapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LogInFragment extends Fragment {

    Button signupB, loginB;
    ImageView newprofile;

    EditText username, password, email;
    LogDBHelper db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_log_in, container, false);

        signupB = view.findViewById(R.id.signupHereButton);
        loginB = view.findViewById(R.id.loginButton);
        newprofile = view.findViewById(R.id.profilePhoto);

        username = view.findViewById(R.id.loginUsername);
        password = view.findViewById(R.id.loginPassword);
        email = view.findViewById(R.id.loginEmailAddress);

        db = new LogDBHelper(getActivity());


        signupB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fm.beginTransaction().replace(R.id.profileFrameLayout, new SignUpFragment(), null).addToBackStack(null).commit();
            }
        });

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.fm.findFragmentById(R.id.profileFrameLayout) != null) {
                    String user = username.getText().toString();
                    String pass = password.getText().toString();
                    String emailAdd = email.getText().toString();

                    if(user.equals("") || pass.equals("") || emailAdd.equals("")){
                        Toast.makeText(getActivity(), "Please enter all details.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Boolean checkemailpass = db.checkemailpassword(emailAdd, pass);
                        if(checkemailpass==true){
                            //Toast.makeText(getActivity(), "Successful Log-In", Toast.LENGTH_SHORT).show();
                            MainActivity.fm.beginTransaction().remove(MainActivity.fm.findFragmentById(R.id.profileFrameLayout)).commit();
                            //newprofile.setBackgroundResource(R.drawable.profile);
                            Toast.makeText(getActivity(), "Logged In", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getActivity(), "Acoount not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });
        return view;

    }
}