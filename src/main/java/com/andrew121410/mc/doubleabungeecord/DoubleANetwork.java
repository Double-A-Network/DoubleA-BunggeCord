package com.andrew121410.mc.doubleabungeecord;

import com.andrew121410.mc.doubleabungeecord.commands.DiscordCMD;
import com.andrew121410.mc.doubleabungeecord.commands.HubCMD;
import com.andrew121410.mc.doubleabungeecord.listeners.OnProxyPingEvent;
import com.andrew121410.mc.doubleabungeecord.listeners.OnServerConnectedEvent;
import com.andrew121410.simpleruntimepatcher.SimpleRuntimePatcher;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.md_5.bungee.api.plugin.Plugin;

public class DoubleANetwork extends Plugin {

    private static DoubleANetwork plugin;

    private BungeeAudiences adventure;

    @Override
    public void onEnable() {
        plugin = this;
        this.adventure = BungeeAudiences.create(this);

        // Patching
        patchBrandName();
        SimpleRuntimePatcher.create();

        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
        // From https://docs.advntr.dev/platform/bungeecord.html
        if (this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }

    public void registerListeners() {
        new OnProxyPingEvent(this);
        new OnServerConnectedEvent(this);
    }

    private void registerCommands() {
        new HubCMD(this);
        new DiscordCMD(this);
    }

    private void patchBrandName() {
        try {
            Class<?> theClass = Class.forName("net.md_5.bungee.BungeeCord");

            SimpleRuntimePatcher.patch(theClass, (classLoader, bytes) -> {
                ClassPool classPool = new ClassPool();
                classPool.appendClassPath(new LoaderClassPath(classLoader));
                try {
                    CtClass ctClass = classPool.get(theClass.getName());
                    CtMethod ctMethod = ctClass.getDeclaredMethod("getName");
                    ctMethod.insertBefore("if (true) return \"DoubleA\";");
                    byte[] byteCode = ctClass.toBytecode();
                    ctClass.detach();
                    return byteCode;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return bytes;
            });
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public BungeeAudiences getAdventure() {
        return adventure;
    }

    public static DoubleANetwork getInstance() {
        return plugin;
    }
}
