package me.zachary.spawn.storage;

import me.zachary.spawn.Spawn;
import xyz.theprogramsrc.supercoreapi.spigot.SpigotModule;
import xyz.theprogramsrc.supercoreapi.spigot.utils.storage.SpigotYMLConfig;

public class StorageFile extends SpigotModule {
    private SpigotYMLConfig cfg;
    private Spawn spawn;

    public StorageFile(Spawn spawn) {
        this.spawn = spawn;
    }

    @Override
    public void onLoad() {
        this.cfg = new SpigotYMLConfig(this.getPluginFolder(), "storage.yml");
    }

    public void set(String path, Object object) {
        this.cfg.set(path, object);
    }

    public Object get(String path) {
        return this.cfg.get(path);
    }
}
