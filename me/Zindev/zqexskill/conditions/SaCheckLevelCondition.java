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
import com.sucy.skill.api.player.PlayerClass;

@QuestConditionMark(conditionID = "checkSkillAPILevel")
public class SaCheckLevelCondition extends QuestCondition{
	private static final long serialVersionUID = 1L;
	private Integer amount;
	private String plClass;
	private String operation;
	public SaCheckLevelCondition() {
		setVariables(new String[3]);
		setVariable("<name>", "name of the class", 0);
		setVariable("<amount>", "condition amount", 1);
		setVariable("<operation>", "operation's name", 2);
		operation = "EQUAL";
		plClass = "nothing";
		amount = 5;
		setDisplayName("&eYour <name>'s level must <operation> to <amount>");
		setErrMessage("&eYour <name>'s level is not <operation> to <amount>");
	}
	@Override
	public String getDisplayName() {
		return super.getDisplayName().replaceAll("<amount>", ""+amount).replaceAll("<name>", plClass)
				.replaceAll("<operation>", operation);
	}
	@Override
	public String getErrMessage() {
		// TODO Auto-generated method stub
		return super.getErrMessage().replaceAll("<amount>", ""+amount).replaceAll("<name>", plClass)
				.replaceAll("<operation>", operation);
	}

	@Override
	public ArrayList<String> getWikiDesc() {
		return new ArrayList<String>(Arrays.asList(
				"&bCheck level of a class",
				"&bwith given operations."
				));
	}
	@Override
	public boolean check(Player p) {
		com.sucy.skill.api.player.PlayerData pd  = SkillAPI.getPlayerData(p);
		if(!Main.hasClass(p,plClass))return false;
		if(!SkillAPI.getClasses().containsKey(plClass))return false;
		PlayerClass cl = pd.getClass(plClass);
		Integer current = cl.getLevel();
		switch (operation) {
		case "EQUAL":
			return amount == current;
		case "LARGER":
			return current > amount;
		case "SMALLER":
			return current < amount;
		case "LARGER_EQUAL":
			return current >= amount;
		case "SMALLER_EQUAL":
			return current <= amount;
		default:
			break;
		}
		
		return false;
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
		return "(amount:"+amount+
				",plClass:"+plClass+
				",operation:"+operation+
				")";
	}
	@Override
	public ArrayList<Object> getFields() {
		return getFields(new ArrayList<Object>(Arrays.asList(
				new ChestField( 
						Gerekli.yapEsya(new ItemStack(Material.DIAMOND), "&5&lAmount", 
								new ArrayList<String>(Arrays.asList(
										"&dOperation will use",
										"&dthis value on level",
										"&5&lCurrently:&d<value>"
										))
								, (short)0)
						
						, null, null, "amount", "&dExperience", 0, 9999999),
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
						Gerekli.yapEsya(new ItemStack(Material.IRON_INGOT), "&3&lOperation", 
								new ArrayList<String>(Arrays.asList(
										"&bWhat do you want to check ?",
										"&3&lCurrently:&b<value>"
										))
								, (short)0)
						
						, null, null, "operation", "&dOperation", 0, 0,new ArrayList<String>(Arrays.asList(
								"EQUAL","LARGER","SMALLER","LARGER_EQUAL","SMALLER_EQUAL"
								)))
						
					)));
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





}
