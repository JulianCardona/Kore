package com.pixelatedleaft.kore.eventbus;

import android.content.DialogInterface;

/**
 * @author Julian Cardona on 9/5/17.
 */

public class EventAlertDialog {

    public static EventAlertDialog.Builder getBuilder() {
        return new EventAlertDialog.Builder();
    }

    private String title = "";
    private String message = "";
    private String positiveButtonText;
    private DialogInterface.OnClickListener positiveListener;
    private String negativeButtonText;
    private DialogInterface.OnClickListener negativeListener;
    private boolean cancellable = true;

    private EventAlertDialog(Builder builder) {
        this.title = builder.title;
        this.message = builder.message;
        this.cancellable = builder.cancelable;
        this.positiveButtonText = builder.positiveButtonText;
        this.positiveListener = builder.positiveListener;
        this.negativeButtonText = builder.negativeButtonText;
        this.negativeListener = builder.negativeListener;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public boolean isCancellable() {
        return cancellable;
    }

    public String getPositiveButtonText() {
        return positiveButtonText;
    }

    public DialogInterface.OnClickListener getPositiveListener() {
        return positiveListener;
    }

    public String getNegativeButtonText() {
        return negativeButtonText;
    }

    public DialogInterface.OnClickListener getNegativeListener() {
        return negativeListener;
    }


    public static class Builder {
        private String title = "";
        private String message = "";
        private String positiveButtonText;
        private DialogInterface.OnClickListener positiveListener;
        private String negativeButtonText;
        private DialogInterface.OnClickListener negativeListener;
        private boolean cancelable = true;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withPositiveButton(String buttonText, DialogInterface.OnClickListener buttonListener) {
            this.positiveButtonText = buttonText;
            this.positiveListener = buttonListener;
            return this;
        }

        public Builder withNegativeButton(String buttonText, DialogInterface.OnClickListener buttonListener) {
            this.negativeButtonText = buttonText;
            this.negativeListener = buttonListener;
            return this;
        }

        public Builder withCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public EventAlertDialog build() {
            return new EventAlertDialog(this);
        }
    }

}
