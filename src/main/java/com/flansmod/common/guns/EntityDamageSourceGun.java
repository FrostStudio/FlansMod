package com.flansmod.common.guns;

import com.flansmod.common.PlayerHandler;
import com.flansmod.common.teams.Team;
import com.flansmod.common.types.InfoType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.IChatComponent;

public class EntityDamageSourceGun extends EntityDamageSourceIndirect {

	public InfoType weapon;
	public EntityPlayer shooter;
	
	public EntityDamageSourceGun(String s, Entity entity, EntityPlayer player, InfoType wep) 
	{
		super(s, entity, player);
		weapon = wep;
		shooter = player;
	}

	@Override
	public IChatComponent func_151519_b(EntityLivingBase living)
    {
		if(!(living instanceof EntityPlayer) || shooter == null || PlayerHandler.getPlayerData(shooter) == null)
		{
			return super.func_151519_b(living);
		}
		EntityPlayer player = (EntityPlayer)living;
    	Team killedTeam = PlayerHandler.getPlayerData(player).team;
    	Team killerTeam = PlayerHandler.getPlayerData(shooter).team;
    	
        return new ChatComponentText("flanDeath." + weapon.shortName + "." + (killedTeam == null ? "f" : killedTeam.textColour) + player.getCommandSenderName() + "." + (killerTeam == null ? "f" : killerTeam.textColour) + shooter.getCommandSenderName());
    }
}