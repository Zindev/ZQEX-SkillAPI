package me.Zindev.zqexskill;

import me.Zindev.zqexskill.actions.SaGiveExpAction;
import me.Zindev.zqexskill.actions.SaOperateLevelAction;
import me.Zindev.zqexskill.actions.SaOperatePointAction;
import me.Zindev.zqexskill.actions.SaProfessClassAction;
import me.Zindev.zqexskill.conditions.SaCheckClassCondition;
import me.Zindev.zqexskill.conditions.SaCheckGroupCondition;
import me.Zindev.zqexskill.conditions.SaCheckLevelCondition;
import me.Zindev.zqexskill.conditions.SaCheckSkillPointCondition;
import me.Zindev.zqexskill.conditions.SaCheckTotalExpCondition;
import me.Zindev.zqexskill.conditions.SaPlayerIsEmpty;
import me.Zindev.zqexskill.objectives.SaGainExpObjective;
import me.Zindev.zquest.objects.extension.ZQuestAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.classes.RPGClass;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		ZQuestAPI.registerExtension(SaOperateLevelAction.class, this);
		ZQuestAPI.registerExtension(SaOperatePointAction.class, this);
		ZQuestAPI.registerExtension(SaGiveExpAction.class, this);
		ZQuestAPI.registerExtension(SaProfessClassAction.class, this);
		ZQuestAPI.registerExtension(SaCheckClassCondition.class, this);
		ZQuestAPI.registerExtension(SaCheckGroupCondition.class, this);
		ZQuestAPI.registerExtension(SaCheckLevelCondition.class, this);
		ZQuestAPI.registerExtension(SaCheckSkillPointCondition.class, this);
		ZQuestAPI.registerExtension(SaCheckTotalExpCondition.class, this);
		ZQuestAPI.registerExtension(SaPlayerIsEmpty.class, this);
		ZQuestAPI.registerExtension(SaGainExpObjective.class, this);
		Bukkit.getPluginManager().registerEvents(new MyListener(), this);
		
	}
	@Override
	public void onDisable() {
		if(Bukkit.getPluginManager().isPluginEnabled("ZQuest")){
			ZQuestAPI.unregisterAll(this);
		}
	}
	public static boolean hasClass(Player p ,String plClass){
		com.sucy.skill.api.player.PlayerData pd  = SkillAPI.getPlayerData(p);
		if(!SkillAPI.getClasses().containsKey(plClass))return false;
		RPGClass cl = SkillAPI.getClasses().get(plClass);
		String group = cl.getGroup();
		return pd.hasClass(group);
	}


}
