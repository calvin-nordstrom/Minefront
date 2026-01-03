package com.calvinnordstrom.minefront.gun;

public class GunStats {
    private double velocity;
    private int fireDelay;
    private int burstDelay;
    private float aimFovModifier;
    private double aimSensitivityCoefficient;

    public GunStats(double velocity, int fireDelay, int burstDelay, float aimFovModifier, double aimSensitivityCoefficient) {
        this.velocity = velocity;
        this.fireDelay = fireDelay;
        this.burstDelay = burstDelay;
        this.aimFovModifier = aimFovModifier;
        this.aimSensitivityCoefficient = aimSensitivityCoefficient;
    }

    public double getVelocity() {
        return velocity;
    }

    public int getFireDelay() {
        return fireDelay;
    }

    public int getBurstDelay() {
        return burstDelay;
    }

    public float getAimFovModifier() {
        return aimFovModifier;
    }

    public double getAimSensitivityCoefficient() {
        return aimSensitivityCoefficient;
    }
}
