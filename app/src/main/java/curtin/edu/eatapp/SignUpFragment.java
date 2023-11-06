package curtin.edu.eatapp;

import static curtin.edu.eatapp.R.id.signupUsername;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SignUpFragment extends Fragment {

    Button signupB, loginB;
    ImageView newprofile;

    EditText username, password, email;
    LogDBHelper db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =   inflater.inflate(R.layout.fragment_sign_up, container, false);

        signupB = view.findViewById(R.id.thesignupButton);
        newprofile = view.findViewById(R.id.profilePhoto);

        username = view.findViewById(signupUsername);
        password = view.findViewById(signupUsername);
        email = view.findViewById(signupUsername);

        db = new LogDBHelper(getActivity());


        signupB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String emailAdd = email.getText().toString();

                //if user left anything out
                if(user.equals("") || pass.equals("") || emailAdd.equals("")){
                    Toast.makeText(getActivity(), "Please enter all details.", Toast.LENGTH_SHORT).show();
                }
                else if(user.equals(db.checkusername(user)) || emailAdd.equals(db.checkemail(emailAdd))){
                    Toast.makeText(getActivity(), "Already Signed-Up", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean insertData = db.insertUserData(user, pass, emailAdd);
                    if(insertData==true){
                        Toast.makeText(getActivity(), "Successful Sign-Up", Toast.LENGTH_SHORT).show();
                    }
                }

                MainActivity.fm.beginTransaction().replace(R.id.profileFrameLayout, new SignUpFragment(), null).addToBackStack(null).commit();
            }
        });
        return view;
    }
}