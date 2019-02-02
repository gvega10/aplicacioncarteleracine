package cartelera.um.cartelera.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import cartelera.um.cartelera.R;
import cartelera.um.cartelera.auth.AuthenticationManagerFactory;
import cartelera.um.cartelera.entities.Review;
import cartelera.um.cartelera.entities.User;
import cartelera.um.cartelera.listeners.AddReviewListener;

/**
 * Created by Christian on 02/02/2019.
 */

public class ReviewDialog extends DialogFragment {
    public static final String REVIEW = "review";
    private EditText reviewMessage;
    private Review reviewEdit;
    private RatingBar ratingBar;
    private float currentScore;

    public ReviewDialog() {
    }

    public static ReviewDialog newInstance(Review currentReview) {
        ReviewDialog frag = new ReviewDialog();
        Bundle args = new Bundle();
        args.putParcelable(REVIEW, currentReview);
        frag.setArguments(args);
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
        View v = inflater.inflate(R.layout.add_review_layout, null);
        builder.setView(v);

        Button add = v.findViewById(R.id.add_button);
        Button cancel = v.findViewById(R.id.cancel_button);
        reviewMessage = v.findViewById(R.id.review_message);
        ratingBar = v.findViewById(R.id.rating_bar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                currentScore = rating;
            }
        });

        try{
            reviewEdit = getArguments().getParcelable(REVIEW);
            reviewMessage.setText(reviewEdit.getMessage());
        }catch (NullPointerException e){
            reviewEdit = null;
        }

        final User userLoged = AuthenticationManagerFactory.getIntance(getActivity()).getCurrentUser();

        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userReview = reviewMessage.getText().toString();

                        if("".equalsIgnoreCase(userReview)){
                            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.fill_review_message), Toast.LENGTH_SHORT).show();
                        }else if(!(currentScore > 0)){
                            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.fill_score), Toast.LENGTH_SHORT).show();
                        }else{
                            AddReviewListener addReviewListener = (AddReviewListener) getActivity();
                            if(reviewEdit != null){
                                reviewEdit.setMessage(userReview);
                                reviewEdit.setScore(currentScore);
                                addReviewListener.editReview(reviewEdit);
                            }else{
                                Review newReview = new Review(userReview, currentScore,userLoged);
                                addReviewListener.addReview(newReview);
                            }
                            dismiss();
                        }
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
