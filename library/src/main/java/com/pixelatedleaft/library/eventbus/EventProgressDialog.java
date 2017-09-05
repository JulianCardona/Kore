package com.pixelatedleaft.library.eventbus;

/**
 * @author Julian Cardona on 9/5/17.
 */

public class EventProgressDialog {

    public static Builder getBuilder() {
        return new Builder();
    }

    private String message = "";
    private boolean isCancelable = false;
    private boolean isDismiss = false;

    private EventProgressDialog(Builder builder){
        this.message = builder.message;
        this.isCancelable = builder.isCancelable;
        this.isDismiss = builder.isDismiss;
    }

    public String getMessage() {
        return message;
    }

    public boolean isCancelable() {
        return isCancelable;
    }

    public boolean isDismiss() {
        return isDismiss;
    }

    public static class Builder {

        private String message;
        private boolean isDismiss = false;
        private boolean isCancelable = false;

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withCancelable(boolean cancelable){
            this.isCancelable = cancelable;
            return this;
        }

        public Builder dismiss(){
            this.isDismiss = true;
            return this;
        }

        public EventProgressDialog build() {
            return new EventProgressDialog(this);
        }
    }

}
