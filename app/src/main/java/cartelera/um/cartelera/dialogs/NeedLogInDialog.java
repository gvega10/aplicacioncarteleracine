package cartelera.um.cartelera.dialogs;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import cartelera.um.cartelera.R;
import cartelera.um.cartelera.flow.FlowManager;
import cartelera.um.cartelera.services.ServiceLocator;
import cartelera.um.cartelera.services.ServiceLocatorFactory;

/**
 * Created by Christian on 26/01/2019.
 */

public class NeedLogInDialog extends DialogFragment {
    private TextView message;

    public NeedLogInDialog() {
    }

    public static NeedLogInDialog newInstance() {
        NeedLogInDialog frag = new NeedLogInDialog();
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createNewProductDialog();
    }


    public AlertDialog createNewProductDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.need_login_dialog, null);
        builder.setView(v);

        Button logIn = v.findViewById(R.id.log_in);
        Button cancel = v.findViewById(R.id.cancel_button);


        logIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ServiceLocator sl  = ServiceLocatorFactory.getInstance(getActivity().getApplicationContext());
                        Intent i = sl.getFlowManager().getIntent(getActivity(), FlowManager.LOG_IN_ACTIVITY);
                        startActivity(i);
                        dismiss();
                    }
                }
        );

        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                }

        );

        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.animDialog;
        return dialog;
    }

}
