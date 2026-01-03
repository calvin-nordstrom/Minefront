package com.calvinnordstrom.minefront.item.custom;

import net.minecraft.world.item.Item;

public class GunItem extends Item {
    private double velocity;
    private float aimFovModifier;
    private double aimSensitivityCoefficient;

    private GunItem(Properties properties, Builder gunProperties) {
        super(properties);

        velocity = gunProperties.velocity;
        aimFovModifier = gunProperties.aimFovModifier;
        aimSensitivityCoefficient = gunProperties.aimSensitivityCoefficient;
    }

    public double getVelocity() {
        return velocity;
    }

    public float getAimFovModifier() {
        return aimFovModifier;
    }

    public double getAimSensitivityCoefficient() {
        return aimSensitivityCoefficient;
    }

    public static class Builder {
        private double velocity = 0.0f;
        private float aimFovModifier = 1.0f;
        private double aimSensitivityCoefficient = 1.0f;

        public Builder velocity(double velocity) {
            this.velocity = velocity;
            return this;
        }

        public Builder aimFovModifier(float aimFovModifier) {
            this.aimFovModifier = aimFovModifier;
            return this;
        }

        public Builder aimSensitivityCoefficient(double aimSensitivityCoefficient) {
            this.aimSensitivityCoefficient = aimSensitivityCoefficient;
            return this;
        }

        public GunItem build(Properties properties) {
            return new GunItem(properties, this);
        }
    }
}
