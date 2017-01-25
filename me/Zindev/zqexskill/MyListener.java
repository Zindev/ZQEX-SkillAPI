package me.Zindev.zqexskill;

import me.Zindev.zqexskill.objectives.SaGainExpObjective;
import me.Zindev.zquest.objects.extension.ZQuestAPI;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.sucy.skill.api.event.PlayerExperienceGainEvent;


public class MyListener implements Listener{
	@EventHandler
	public void expGain(PlayerExperienceGainEvent e){
		if(!e.getPlayerData().getPlayer().isOnline())return;
		Player p = e.getPlayerData().getPlayer();
		if(!ZQuestAPI.playerIsMakingQuest(p.getUniqueId()))return;
		SaGainExpObjective ob = ZQuestAPI.playerHasObjective(p.getUniqueId(), SaGainExpObjective.class,
				true);
		if(ob != null)ob.checkIn(e.getPlayerClass().getData().getName(),new Double(e.getExp()).intValue(), p);
	}

}
