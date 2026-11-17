package com.calvinnordstrom.minefront.gun;


public class GunState {
    public GunItem heldGun;
    public int heldSlot = -1;
    public boolean triggerHeld;
    public int cooldownTicks;
    public int burstShotsRemaining;
    public boolean needsCycling;
}
