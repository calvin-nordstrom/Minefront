package com.calvinnordstrom.minefront.item.custom;

import net.minecraft.world.item.Item;

public class GunItem extends Item {
    private float fovModifier;
    private float aimFovModifier;
    private double sensitivityCoefficient;
    private double aimSensitivityCoefficient;

    private GunItem(Properties properties, Builder gunProperties) {
        super(properties);

        aimFovModifier = gunProperties.aimFovModifier;
        aimSensitivityCoefficient = gunProperties.aimSensitivityCoefficient;
    }

    public float getFovModifier() {
        return fovModifier;
    }

    public float getAimFovModifier() {
        return aimFovModifier;
    }

    public double getSensitivityCoefficient() {
        return sensitivityCoefficient;
    }

    public double getAimSensitivityCoefficient() {
        return aimSensitivityCoefficient;
    }

    public static class Builder {
        private float fovModifier = 1.0f;
        private float aimFovModifier = 1.0f;
        private double sensitivityCoefficient = 1.0f;
        private double aimSensitivityCoefficient = 1.0f;

        public Builder fovModifier(float fovModifier) {
            this.fovModifier = fovModifier;
            return this;
        }

        public Builder aimFovModifier(float aimFovModifier) {
            this.aimFovModifier = aimFovModifier;
            return this;
        }

        public Builder sensitivityCoefficient(double sensitivityCoefficient) {
            this.sensitivityCoefficient = sensitivityCoefficient;
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
