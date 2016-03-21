package de.LPmitFelix.StarWars.Core.util;

import com.mojang.authlib.GameProfile;
import de.LPmitFelix.StarWars.Core.API;

import java.util.UUID;

public class PlayerProfile {

    private GameProfile profile;

    @SuppressWarnings("static-access")
    public PlayerProfile(UUID uuid) {
        profile = new GameProfile(uuid, API.UUIDAPI().getName(uuid));

    }
}
