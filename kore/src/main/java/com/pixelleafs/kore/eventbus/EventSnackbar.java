package com.pixelleafs.kore.eventbus;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * @author Julian Cardona on 9/5/17.
 */

public class EventSnackbar {

    public static final int NONE_VIEW = -1;

    public static Builder getBuilder() {
        return new Builder();
    }

    private String message;
    private String actionText;
    private int viewId = NONE_VIEW;
    private View.OnClickListener actionListener;
    private Snackbar.Callback callback;

    private EventSnackbar(Builder builder) {
        this.message = builder.message;
        this.actionText = builder.actionText;
        this.actionListener = builder.actionListener;
        this.callback = builder.callback;
    }

    public String getMessage() {
        return message;
    }

    public String getActionText() {
        return actionText;
    }

    public View.OnClickListener getActionListener() {
        return actionListener;
    }

    public Snackbar.Callback getCallback() {
        return callback;
    }

    public int getViewId() {
        return viewId;
    }

    public static class Builder {

        private String message;
        private String actionText;
        private View.OnClickListener actionListener;
        private Snackbar.Callback callback;

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withActionText(String actionText) {
            this.actionText = actionText;
            return this;
        }

        public Builder withActionListener(View.OnClickListener actionListener) {
            this.actionListener = actionListener;
            return this;
        }

        public Builder withCallback(Snackbar.Callback callback) {
            this.callback = callback;
            return this;
        }

        public EventSnackbar build() {
            return new EventSnackbar(this);
        }
    }

}
