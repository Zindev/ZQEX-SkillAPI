package me.Zindev.zqexskill.conditions;

import java.util.ArrayList;
import java.util.Arrays;

import me.Zindev.zqexskill.Main;
import me.Zindev.zquest.Gerekli;
import me.Zindev.zquest.chestlib.ChestField;
import me.Zindev.zquest.objects.QuestCondition;
import me.Zindev.zquest.objects.extension.QuestConditionMark;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.sucy.skill.SkillAPI;

@QuestConditionMark(conditionID = "checkSkillAPIClass")
public class SaCheckClassCondition extends QuestCondition{
	private static final long serialVersionUID = 1L;
	private String plClass;
	public SaCheckClassCondition() {
		setVariables(new String[1]);
		setVariable("<name>", "name of the class", 0);
		plClass = "nothing";
		setDisplayName("&eYour have to be <name>");
		setErrMessage("&eYour have to be in <name> class !");
	}
	@Override
	public String getDisplayName() {
		return super.getDisplayName().replaceAll("<name>", plClass);
	}
	@Override
	public String getErrMessage() {
		return super.getErrMessage().replaceAll("<name>", plClass);
	}

	@Override
	public ArrayList<String> getWikiDesc() {
		return new ArrayList<String>(Arrays.asList(
				"&bCheck if player has",
				"&bgiven class."
				));
	}
	@Override
	public boolean check(Player p) {
		return Main.hasClass(p,plClass);
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
				"plClass:"+plClass+
				")";
	}
	@Override
	public ArrayList<Object> getFields() {
		return getFields(new ArrayList<Object>(Arrays.asList(
						new ChestField( 
								Gerekli.yapEsya(new ItemStack(Material.GOLD_INGOT), "&3&lClass", 
										new ArrayList<String>(Arrays.asList(
												"&bWhich class do you want ?",
												"&3&lCurrently:&b<value>"
												))
										, (short)0)
								
								, null, null, "plClass", "&bClass", 0, 0,
								new ArrayList<String>(SkillAPI.getClasses().keySet())
								
							)
						
					)));
	}
	
	public String getPlClass() {
		return plClass;
	}
	public void setPlClass(String plClass) {
		this.plClass = plClass;
	}
	





}
