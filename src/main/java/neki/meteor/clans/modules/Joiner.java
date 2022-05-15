package neki.meteor.clans.modules;

import com.google.gson.Gson;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.settings.StringSetting;
import neki.meteor.clans.MeteorClans;
import meteordevelopment.meteorclient.systems.modules.Module;
import neki.meteor.clans.clan.Clan;
import neki.meteor.clans.clan.Position;
import neki.meteor.clans.clan.User;

public class Joiner extends Module {
    public Joiner() {
        super(MeteorClans.CATEGORY, "Clan joiner", "");
    }
    private final SettingGroup defaultGroup = settings.getDefaultGroup();
    private final Setting<String> clanName = defaultGroup.add(new StringSetting.Builder()
        .name("Name")
        .description("Clan name.")
        .defaultValue("")
        .build()
    );
    private final Setting<String> clanPassword = defaultGroup.add(new StringSetting.Builder()
        .name("Password")
        .description("Clan password.")
        .defaultValue("")
        .build()
    );

    @Override
    public void onActivate() {
        Position position = new Position();
        position.x = mc.player.getX();
        position.y = mc.player.getY();
        position.z = mc.player.getZ();

        User user = new User();
        user.clan = clanName.get();
        user.name = mc.player.getName().getString();
        user.health = mc.player.getHealth();
        user.position = position;

        Clan clan = new Clan();
        clan.name = clanName.get();
        clan.password = clanPassword.get();
        clan.users.add(user);

        MeteorClans.clan = clan;
        Gson gson = new Gson();
        String json = gson.toJson(clan);
        MeteorClans.client.sendData(json);
        toggle();
    }
}
