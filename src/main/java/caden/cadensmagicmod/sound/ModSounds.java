package caden.cadensmagicmod.sound;

import caden.cadensmagicmod.CadensMagicMod;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;


public class ModSounds {

    public static final SoundEvent URANIUM_FEVER = registerSoundEvent("uranium_fever");
    public static final RegistryKey<JukeboxSong> URANIUM_FEVER_KEY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(CadensMagicMod.MOD_ID, "uranium_fever"));

    public static final SoundEvent WE_WANT_A_ROCK = registerSoundEvent("we_want_a_rock");
    public static final RegistryKey<JukeboxSong> WE_WANT_A_ROCK_KEY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(CadensMagicMod.MOD_ID, "we_want_a_rock"));

    public static final SoundEvent GHOST_RIDERS = registerSoundEvent("ghost_riders");
    public static final RegistryKey<JukeboxSong> GHOST_RIDERS_KEY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(CadensMagicMod.MOD_ID, "ghost_riders"));

    public static final SoundEvent SISYPHUS = registerSoundEvent("sisyphus");
    public static final RegistryKey<JukeboxSong> SISYPHUS_KEY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(CadensMagicMod.MOD_ID, "sisyphus"));

    public static void registerSounds() {
        CadensMagicMod.LOGGER.info("Registering the airwaves for " + CadensMagicMod.MOD_ID);
    }

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(CadensMagicMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}
