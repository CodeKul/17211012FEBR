package com.codekul.dialogs;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import is.arontibo.library.ElasticDownloadView;
import is.arontibo.library.ProgressDownloadView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppDialog extends DialogFragment {

    public static final String DIALOG_ALERT = "alert";
    public static final String DIALOG_DATE = "date";
    public static final String DIALOG_TIME = "time";
    public static final String DIALOG_PROGRESS = "progress";
    public static final String DIALOG_CUSTOM = "custom";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog;

        if (getTag().equals(DIALOG_ALERT)) dialog = createAlert();
        else if (getTag().equals(DIALOG_DATE)) dialog = datePicker();
        else if(getTag().equals(DIALOG_TIME)) dialog = timePicker();
        else if(getTag().equals(DIALOG_PROGRESS)) dialog = progress();
        else dialog = custom();

        return dialog;
    }

    private Dialog createAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setMessage(R.string.msg)
                .setTitle(R.string.title)
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton(R.string.btnYes, (di, which) -> di.dismiss());

        return builder.create();
    }

    private Dialog datePicker() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (view, year, month, dayOfMonth) -> mt("" + dayOfMonth + " - " + month + " - " + year), 2017, 2, 16);

        return datePickerDialog;
    }

    private Dialog timePicker() {

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), (view, hourOfDay, minute) -> mt("" + hourOfDay + " : " + minute), 12, 8, false);

        return timePickerDialog;
    }

    private Dialog progress() {

        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage(getResources().getString(R.string.msg));
        dialog.setTitle(getResources().getString(R.string.title));
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        return  dialog;
    }

    private Dialog custom() {

        View view = getActivity().getLayoutInflater().inflate(R.layout.custom_dialog, null, false);
        ElasticDownloadView elasticDownloadView = (ElasticDownloadView) view.findViewById(R.id.elasticDownloader);
        new Handler().post(elasticDownloadView::startIntro);
        new Handler().postDelayed(elasticDownloadView::success, 2 * ProgressDownloadView.ANIMATION_DURATION_BASE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view);

        return builder.create();
    }

    private void mt(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
