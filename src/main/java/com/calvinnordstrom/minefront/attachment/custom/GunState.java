package com.calvinnordstrom.minefront.attachment.custom;

import com.calvinnordstrom.minefront.item.custom.GunItem;

public class GunState {
    public GunItem heldGun;
    public int heldSlot = -1;
    public boolean triggerHeld;
    public int cooldownTicks;
    public int burstShotsRemaining;
    public boolean needsCycling;
}
