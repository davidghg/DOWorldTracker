@Override
    public void onEnable() {
        saveDefaultConfig();
        connectToDatabase();
        createTableIfNotExists();
        Bukkit.getPluginManager().registerEvents(this, this);
    }