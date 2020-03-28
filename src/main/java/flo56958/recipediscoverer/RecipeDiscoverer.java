package flo56958.recipediscoverer;

import org.bukkit.Bukkit;
import org.bukkit.Keyed;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Iterator;

public final class RecipeDiscoverer extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        for (Player p : Bukkit.getOnlinePlayers()) {
            execute(p);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        execute(e.getPlayer());
    }

    private void execute(Player p) {
        if (!p.hasPermission("recipediscoverer.allow")) return;
        Iterator<Recipe> it = this.getServer().recipeIterator();
        while (it.hasNext()) {
            Recipe rec = it.next();
            if (rec instanceof Keyed) {
                p.discoverRecipe(((Keyed) rec).getKey());
            }
        }
    }
}
