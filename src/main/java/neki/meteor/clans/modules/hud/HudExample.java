package neki.meteor.clans.modules.hud;

import meteordevelopment.meteorclient.systems.hud.HUD;
import meteordevelopment.meteorclient.systems.hud.modules.DoubleTextHudElement;
import neki.meteor.clans.MeteorClans;
import neki.meteor.clans.clan.User;

public class HudExample extends DoubleTextHudElement {
    public HudExample() {
        super(HUD.get(), "clan-info", "Description", "", true);
    }

    @Override
    protected String getRight() {
        if (!MeteorClans.clan.name.equals("")) {
            StringBuilder done = new StringBuilder();
            done.append(MeteorClans.clan.name).append("\n").append("\n");
            for (User user : MeteorClans.clan.users) {
                done.append(user.name).append(" | ").append(user.health).append("\n");
            }
            return done.toString();
        }
        else
        {
            return "Clan not found";
        }
    }
}
