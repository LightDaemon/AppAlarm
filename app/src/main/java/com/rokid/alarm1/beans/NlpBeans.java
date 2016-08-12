package com.rokid.alarm1.beans;

import java.io.Serializable;

/**
 * Created by ZY on 2016/6/13.
 * Description :
 */
public class NlpBeans implements Serializable{
    private String domain;
    private String intent;
    private SlotBeans slots;
    private int posStart;
    private int posEnd;
    private float confidence;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public SlotBeans getSlots() {
        return slots;
    }

    public void setSlots(SlotBeans slots) {
        this.slots = slots;
    }

    public int getPosStart() {
        return posStart;
    }

    public void setPosStart(int posStart) {
        this.posStart = posStart;
    }

    public int getPosEnd() {
        return posEnd;
    }

    public void setPosEnd(int posEnd) {
        this.posEnd = posEnd;
    }

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }
}
