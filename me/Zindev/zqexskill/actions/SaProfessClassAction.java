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

@QuestActionMark(actionID ="SkillAPIProfessClass")
public class SaProfessClassAction extends QuestAction{
	private static final long serialVersionUID = 1L;
	
	
	private String plClass;
	public SaProfessClassAction() {
		plClass = "noClass";
	}
	@Override
	public ArrayList<String> getWikiDesc() {
		return new ArrayList<String>(Arrays.asList(
				"&bAdd a class to the player"
				));
	}

	public String getPlClass() {
		return plClass;
	}
	public void setPlClass(String plClass) {
		this.plClass = plClass;
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
	public void execute(Player p) {
		com.sucy.skill.api.player.PlayerData pd  = SkillAPI.getPlayerData(p);
		if(Main.hasClass(p,plClass))return;
		if(!SkillAPI.getClasses().containsKey(plClass))return;
		pd.profess(SkillAPI.getClasses().get(pd));
}

	@Override
	public ArrayList<Object> getFields() {
		return getFields(new ArrayList<Object>(Arrays.asList(
				new ChestField( 
						Gerekli.yapEsya(new ItemStack(Material.GOLD_INGOT), "&3&lClass", 
								new ArrayList<String>(Arrays.asList(
										"&bWhich class do you want ?",
										"&bIf Player has this class",
										"&bthis action will do nothing",
										"&3&lCurrently:&b<value>"
										))
								, (short)0)
						
						, null, null, "plClass", "&bClass", 0, 0,
						new ArrayList<String>(SkillAPI.getClasses().keySet())
						
					))));
		
	}
}
