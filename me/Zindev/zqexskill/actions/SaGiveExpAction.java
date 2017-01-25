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
import com.sucy.skill.api.enums.ExpSource;
import com.sucy.skill.api.player.PlayerClass;

@QuestActionMark(actionID ="SkillAPIGiveExp")
public class SaGiveExpAction extends QuestAction{
	private static final long serialVersionUID = 1L;
	
	private Integer amount;
	private String plClass;
	public SaGiveExpAction() {
		plClass = "noClass";
		amount = 50;
	}
	@Override
	public ArrayList<String> getWikiDesc() {
		return new ArrayList<String>(Arrays.asList(
				"&bGive some exp to a class"
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
				",plClass:"+plClass+
				")";
	}

	@Override
	public void execute(Player p) {
		com.sucy.skill.api.player.PlayerData pd  = SkillAPI.getPlayerData(p);
		if(!SkillAPI.getClasses().containsKey(plClass))return;
		if(!Main.hasClass(p,plClass))return;
		
		PlayerClass cl = pd.getClass(SkillAPI.getClasses().get(plClass).getGroup());
		cl.giveExp(amount,ExpSource.QUEST );
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
											"&dAction will give",
											"&dthis value of exp",
											"&5&lCurrently:&d<value>"
											))
									, (short)0)
							
							, null, null, "amount", "&dAmount", 1, 99999)
				)));
		
	}
}
