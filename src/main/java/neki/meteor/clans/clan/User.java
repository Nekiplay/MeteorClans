package neki.meteor.clans.clan;

import neki.meteor.clans.MeteorClans;
import net.minecraft.client.network.ClientPlayerEntity;

public class User {

    public String clan;

    public String name;
    public float health;

    public Position position;

    public User() {

    }

    public static User getUser(ClientPlayerEntity player) {
        Position newposition = new Position();
        newposition.x = player.getX();
        newposition.y = player.getY();
        newposition.z = player.getZ();

        User newuser = new User();
        newuser.clan = MeteorClans.clan.name;
        newuser.health = player.getHealth();
        newuser.name = player.getName().getString();
        newuser.position = newposition;

        return newuser;
    }
}
