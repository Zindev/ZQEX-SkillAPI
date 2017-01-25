package me.Zindev.zqexskill.actions;
import java.util.ArrayList;
import java.util.Arrays;

import me.Zindev.zqexskill.Main;
import me.Zindev.zquest.Gerekli;
import me.Zindev.zquest.chestlib.ChestField;
import me.Zindev.zquest.objects.QuestAction;
import me.Zindev.zquest.objects.extension.QuestActionMark;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.player.PlayerClass;

@QuestActionMark(actionID ="SkillAPIOperateLevel")
public class SaOperateLevelAction extends QuestAction{
	private static final long serialVersionUID = 1L;
	
	private Integer amount;
	private String plClass;
	private String operation;
	public SaOperateLevelAction() {
		plClass = "noClass";
		amount = 2;
		operation = "INCREASE";
	}
	@Override
	public ArrayList<String> getWikiDesc() {
		return new ArrayList<String>(Arrays.asList(
				"&bOperate level of a class",
				"&bwith given operations."
				));
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
				"amount:"+amount+
				",operation:"+operation+
				",plClass:"+plClass+
				")";
	}

	@Override
	public void execute(Player p) {
		com.sucy.skill.api.player.PlayerData pd  = SkillAPI.getPlayerData(p);
		if(!Main.hasClass(p,plClass))return;
		if(!SkillAPI.getClasses().containsKey(plClass))return;
		PlayerClass cl = pd.getClass(plClass);
		switch (operation) {
		case "SET":
			cl.setLevel(amount);
			break;

		case "DECREASE":
			int current = cl.getLevel();
			cl.setLevel((current-amount > 0?current-amount:1));
			break;
		case "INCREASE":
			cl.giveLevels(amount);
			
			break;
		}
}

	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getPlClass() {
		return plClass;
	}
	public void setPlClass(String plClass) {
		this.plClass = plClass;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
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
						
					),
					new ChestField( 
							Gerekli.yapEsya(new ItemStack(Material.DIAMOND), "&5&lAmount", 
									new ArrayList<String>(Arrays.asList(
											"&dOperation will use",
											"&dthis value on Level",
											"&5&lCurrently:&d<value>"
											))
									, (short)0)
							
							, null, null, "amount", "&dAmount", 1, 9999999),
					new ChestField( 
							Gerekli.yapEsya(new ItemStack(Material.GOLD_INGOT), "&3&lOperation", 
									new ArrayList<String>(Arrays.asList(
											"&bWhat operation you",
											"&bwant to do ?",
											"&3&lCurrently:&b<value>"
											))
									, (short)0)
							
							, null, null, "operation", "&cOperation", 0, 0,new ArrayList<String>(
									Arrays.asList(
											"INCREASE","SET","DECREASE"
											)
									
									))
				
				)));
		
	}
}
