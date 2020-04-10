package entities;

import annotation.Secured;

public class Entity {

    public void call(){
    }

    @Secured(num = 1,info = "some info")
    public void sendSMS(){
    }

    @Secured(num = 2)
    private void record(){
    }

    private void setRate(){
    }
}
