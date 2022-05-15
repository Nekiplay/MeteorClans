package neki.meteor.clans;

import neki.meteor.clans.clan.Clan;
import neki.meteor.clans.client.Client;
import neki.meteor.clans.modules.Creator;
import neki.meteor.clans.modules.Joiner;
import neki.meteor.clans.modules.hud.HudExample;
import meteordevelopment.meteorclient.MeteorClient;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.hud.HUD;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import net.minecraft.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.invoke.MethodHandles;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MeteorClans extends MeteorAddon {
	public static final Logger LOG = LoggerFactory.getLogger(MeteorClans.class);
	public static final Category CATEGORY = new Category("Clans", Items.GLOWSTONE_DUST.getDefaultStack());
    public static Client client;
    public static PrintStream out = new PrintStream(System.out, true, UTF_8);

    public static Clan clan = new Clan();

	@Override
	public void onInitialize() {
		LOG.info("Initializing Meteor Addon Template");
        client = new Client("130.255.42.224", 8888);
        Thread serverth = new Thread(() -> {
            while (true) {
                try {
                    client.reconecter();
                    client.getData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        serverth.start();
		// Required when using @EventHandler
		MeteorClient.EVENT_BUS.registerLambdaFactory("meteor.clans", (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));

		// Modules
		Modules.get().add(new Creator());
		Modules.get().add(new Joiner());

		// Commands
		//Commands.get().add(new ExampleCommand());

		// HUD
        HUD.get().elements.add(new HudExample());
        //client.sendData("Hi");
	}
	@Override
	public void onRegisterCategories() {
		Modules.registerCategory(CATEGORY);
	}
}
