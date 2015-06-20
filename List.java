package superblaubeere27.Meinneues.Plugin;

import java.lang.reflect.Field;
import java.util.Vector;

import net.minecraft.server.v1_8_R1.PacketPlayOutBed;
import net.minecraft.server.v1_8_R1.PacketPlayOutNamedEntitySpawn;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.WeatherType;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class List implements Listener {
	Bukkit b;
	
	public List(Bukkit b) {
		
		this.b = b;
		b.getServer().getPluginManager().registerEvents(this, b);
	}
	private void setValue(String name, Object instance, Object value) {
		try {
			Field f = instance.getClass().getDeclaredField(name);
			f.setAccessible(true);
			f.set(instance, value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void Join(PlayerJoinEvent pje){
		
		System.out.println("List.Join()");
		Player p = pje.getPlayer();
		String display = p.getDisplayName();
		
		if(p.isOp()) {
			t = true;
			p.setDisplayName(ChatColor.DARK_RED + display + ChatColor.WHITE);
			p.setCustomName(ChatColor.DARK_RED + display + ChatColor.WHITE);
			p.setPlayerListName(ChatColor.DARK_RED + display + ChatColor.WHITE);
			p.getInventory().addItem(is1);
			p.sendMessage(ChatColor.GREEN + "Du bist Operator " + p.getDisplayName() + " :-)");
		}
	
		b.reloadConfig();
		if(b.getConfig().getLong("Spieler." + p.getUniqueId() + ".premium") > System.currentTimeMillis()) {
			p.setDisplayName(ChatColor.GOLD + display + ChatColor.WHITE);
			p.setCustomName(ChatColor.GOLD + display + ChatColor.WHITE);
			p.setPlayerListName(ChatColor.GOLD + display + ChatColor.WHITE);
			p.getInventory().addItem(is1);
			p.sendMessage(ChatColor.GREEN + "Du bist Premium " + p.getDisplayName() + " :-)");
			t = true;
		}
		if(b.getConfig().getLong("Spieler." + p.getUniqueId() + ".stuff") > System.currentTimeMillis()) {
			t = true;
			p.setDisplayName(ChatColor.DARK_PURPLE + display + ChatColor.WHITE);
			p.setCustomName(ChatColor.DARK_PURPLE + display + ChatColor.WHITE);
			p.setPlayerListName(ChatColor.DARK_PURPLE + display + ChatColor.WHITE);
			p.getInventory().addItem(is1);
			p.sendMessage(ChatColor.GREEN + "Du bist stuff " + p.getDisplayName() + " :-)");
		}
		
		if(!p.isOp() && !(b.getConfig().getLong("Spieler." + p.getUniqueId() + ".premium") > System.currentTimeMillis()) && b.getConfig().getLong("Spieler." + p.getUniqueId() + ".stuff") > System.currentTimeMillis()) {
			p.setDisplayName(ChatColor.DARK_PURPLE + p.getDisplayName() + ChatColor.WHITE);
			p.setCustomName(ChatColor.DARK_PURPLE + p.getCustomName() + ChatColor.WHITE);
			p.setPlayerListName(ChatColor.DARK_PURPLE + p.getPlayerListName() + ChatColor.WHITE);
			p.sendMessage(ChatColor.GREEN + "Du bist ganz normal " + p.getDisplayName() + " :->");
		}
		p.setGameMode(GameMode.CREATIVE);
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 3));
	}
	
	
	
}
