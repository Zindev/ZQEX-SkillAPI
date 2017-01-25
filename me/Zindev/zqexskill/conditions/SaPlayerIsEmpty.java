package me.Zindev.zqexskill.conditions;

import java.util.ArrayList;
import java.util.Arrays;

import me.Zindev.zquest.objects.QuestCondition;
import me.Zindev.zquest.objects.extension.QuestConditionMark;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.sucy.skill.SkillAPI;

@QuestConditionMark(conditionID = "checkSkillAPINoClass")
public class SaPlayerIsEmpty extends QuestCondition{
	private static final long serialVersionUID = 1L;
	public SaPlayerIsEmpty() {
		setDisplayName("&eYou must have no class");
		setErrMessage("&eYou must have no class !");
	}
	@Override
	public String getDisplayName() {
		return super.getDisplayName();
	}
	@Override
	public String getErrMessage() {
		return super.getErrMessage();
	}

	@Override
	public ArrayList<String> getWikiDesc() {
		return new ArrayList<String>(Arrays.asList(
				"&bCheck if player doesn't",
				"&bhave any class.",
				"&bReturns true if player",
				"&bdoesn't have any."
				));
	}
	@Override
	public boolean check(Player p) {
		return !SkillAPI.getPlayerData(p).hasClass();
	}
	@Override
	public String getWikiName() {
		return "&3"+getID();
	}
	@Override
	public Material getWikiMaterial() {
		return Material.CACTUS;
	}

	@Override
	public String buildString() {
		return "("+
				")";
	}
	@Override
	public ArrayList<Object> getFields() {
		return getFields(new ArrayList<Object>());
	}
	





}
