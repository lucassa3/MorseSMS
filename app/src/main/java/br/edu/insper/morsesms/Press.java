package br.edu.insper.morsesms;

/**
 * Created by Lucas Scarlato Astur on 19/10/2016.
 */

public class Press {
    private long activationTime;

    public void setActivationTime() {
        activationTime = System.currentTimeMillis();
    }

    public long getActivationTime () { return this.activationTime;}
}
