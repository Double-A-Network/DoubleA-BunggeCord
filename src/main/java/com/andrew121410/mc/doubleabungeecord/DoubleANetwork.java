package com.andrew121410.mc.doubleabungeecord;

import com.andrew121410.mc.doubleabungeecord.commands.DiscordCMD;
import com.andrew121410.mc.doubleabungeecord.commands.HubCMD;
import com.andrew121410.mc.doubleabungeecord.listeners.OnServerConnectedEvent;
import com.andrew121410.simpleruntimepatcher.SimpleRuntimePatcher;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;
import net.md_5.bungee.api.plugin.Plugin;

public class DoubleANetwork extends Plugin {

    private static DoubleANetwork plugin;

    @Override
    public void onEnable() {
        plugin = this;

        patchUp();
        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
    }

    public void registerListeners() {
        new OnServerConnectedEvent(this);
    }

    private void registerCommands() {
        new HubCMD(this);
        new DiscordCMD(this);
    }

    private void patchUp() {
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

        SimpleRuntimePatcher.create();
    }

    public static DoubleANetwork getInstance() {
        return plugin;
    }
}
