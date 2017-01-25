package me.Zindev.zqexskill.objectives;

import java.util.ArrayList;
import java.util.Arrays;

import me.Zindev.zquest.Gerekli;
import me.Zindev.zquest.chestlib.ChestField;
import me.Zindev.zquest.objects.QuestObjective;
import me.Zindev.zquest.objects.extension.QuestObjectiveMark;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.sucy.skill.SkillAPI;

@QuestObjectiveMark(objectiveID="SkillAPIExpGainObjective",hideSuccess= true)
public class SaGainExpObjective extends QuestObjective{
	private static final long serialVersionUID = 1L;
	private Integer amt;
	private String plClass;
	
	
	public SaGainExpObjective(){
		setVariables(new String[2]);
		setVariable("<amount>", "remaining exp", 0);
		setVariable("<name>", "name of the class", 1);
		setCompleteMessage("&aYou just completed a gain exp Objective !");
		setDisplayName("&dGain <amount> exp in <name> class");
		this.amt = 50;
		plClass = "noClass";
	}
	@Override
	public void success() {
		check();
		
	}

	@Override
	public boolean check() {
		if(amt > 0){return false;}

		return true;
	}
	public boolean checkIn(String scl,Integer cms,Player p){
		if(!SkillAPI.getClasses().get(plClass).getName().equals(scl))return false;
		if(!checkConditions(p))return false;
		amt = amt>cms?amt-cms:0;
		success();
		if(amt <= 0)Gerekli.yollaMesaj(p, getCompleteMessage());
		return true;
	}

	@Override
	public String buildString() {
		return "(amount:"+amt+",class:"+plClass+")";
	}
	public int getAmt() {
		return amt;
	}
	public void setAmt(Integer amt) {
		this.amt = amt;
	}
	@Override
	public String getDisplayName() {
		return super.getDisplayName().replaceAll("<amount>", ""+amt).replaceAll("<name>", plClass);
	}
	@Override
	public String getSuccessMessage() {
		if(amt == 0){
			return super.getCompleteMessage();
		}
		return super.getSuccessMessage().replaceAll("<amount>", ""+amt).replaceAll("<name>", plClass);
	}
	@Override
	public String getCompleteMessage() {
		return super.getCompleteMessage().replaceAll("<amount>", ""+amt).replaceAll("<name>", plClass);
	}
	@Override
	public ArrayList<Object> getFields() {
		return getFields(new ArrayList<Object>(Arrays.asList(
				new ChestField( 
						Gerekli.yapEsya(new ItemStack(Material.DIAMOND), "&5&lAmount", 
								new ArrayList<String>(Arrays.asList(
										"&dHow much exp player",
										"&dshould gain ?",
										"&5&lCurrently:&d<value>"
										))
								, (short)0)
						
						, null, null, "amt", "&dRequired Experience", 0, 9999999),
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
	@Override
	public String getWikiName() {
		return "&5"+getID();
	}
	@Override
	public Material getWikiMaterial() {
		return Material.IRON_AXE;
	}
	@Override
	public ArrayList<String> getWikiDesc() {
		return new ArrayList<String>(Arrays.asList(
				"&dPlayer needs to collect",
				"&damount of choosen skill's xp."
				));

	}
	public String getPlClass() {
		return plClass;
	}
	public void setPlClass(String plClass) {
		this.plClass = plClass;
	}

}
