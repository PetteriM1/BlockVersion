package me.petterim1.blockversion;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.plugin.PluginBase;

import java.util.List;

public class Plugin extends PluginBase implements Listener {

    private String message;
    private List<String> versions;

    public void onEnable() {
        saveDefaultConfig();
        message = getConfig().getString("BlockedMessage");
        versions = getConfig().getStringList("BlockedVersions");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        String version = e.getPlayer().getLoginChainData().getGameVersion();
        for (String blocked : versions) {
            if (version.startsWith(blocked)) {
                e.getPlayer().kick(message, false);
                return;
            }
        }
    }
}
