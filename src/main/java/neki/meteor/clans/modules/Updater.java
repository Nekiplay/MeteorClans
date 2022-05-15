package neki.meteor.clans.modules;

import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import neki.meteor.clans.MeteorClans;
import neki.meteor.clans.clan.User;
import net.minecraft.util.math.Vec3d;

public class Updater extends Module {
    public Updater() {
        super(MeteorClans.CATEGORY, "Updater", "");
    }
    float health = 0;

    Vec3d pos = Vec3d.ZERO;

    @EventHandler
    public void tickEventPre(TickEvent.Pre event) {
        if (pos != mc.player.getPos() || health != mc.player.getHealth()) {
            MeteorClans.client.sendData(User.getUser(mc.player));
            pos = mc.player.getPos();
            health = mc.player.getHealth();
        }
    }
}
