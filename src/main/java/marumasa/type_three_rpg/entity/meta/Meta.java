package marumasa.type_three_rpg.entity.meta;

import marumasa.type_three_rpg.entity.player.StatusEffect;
import marumasa.type_three_rpg.minecraft;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class Meta {
    public static int get(Entity entity, minecraft minecraft, String key, int value) {
        if (entity.hasMetadata(key)) {
            List<MetadataValue> values = entity.getMetadata(key);
            for (MetadataValue v : values) {
                final Plugin plugin = v.getOwningPlugin();
                if (plugin == minecraft) {
                    return (int) v.value();
                }
            }
        } else {
            set(entity, minecraft, key, value);
        }
        return value;
    }

    public static int set(Entity entity, minecraft minecraft, String key, int value) {
        entity.setMetadata(key, new FixedMetadataValue(
                minecraft,
                value
        ));
        return value;
    }

    public static String get(Entity entity, minecraft minecraft, String key, String value) {
        if (entity.hasMetadata(key)) {
            List<MetadataValue> values = entity.getMetadata(key);
            for (MetadataValue v : values) {
                final Plugin plugin = v.getOwningPlugin();
                if (plugin == minecraft) {
                    return (String) v.value();
                }
            }
        } else {
            set(entity, minecraft, key, value);
        }
        return value;
    }

    public static List<String> set(Entity entity, minecraft minecraft, String key, List<String> value) {
        entity.setMetadata(key, new FixedMetadataValue(
                minecraft,
                value
        ));
        return value;
    }
    public static List<String> get(Entity entity, minecraft minecraft, String key, List<String> value) {
        if (entity.hasMetadata(key)) {
            List<MetadataValue> values = entity.getMetadata(key);
            for (MetadataValue v : values) {
                final Plugin plugin = v.getOwningPlugin();
                if (plugin == minecraft) {
                    return (List<String>) v.value();
                }
            }
        } else {
            set(entity, minecraft, key, value);
        }
        return value;
    }

    public static String set(Entity entity, minecraft minecraft, String key, String value) {
        entity.setMetadata(key, new FixedMetadataValue(
                minecraft,
                value
        ));
        return value;
    }
    public static StatusEffect get(Entity entity, minecraft minecraft, String key, StatusEffect value) {
        if (entity.hasMetadata(key)) {
            List<MetadataValue> values = entity.getMetadata(key);
            for (MetadataValue v : values) {
                final Plugin plugin = v.getOwningPlugin();
                if (plugin == minecraft) {
                    return (StatusEffect) v.value();
                }
            }
        } else {
            set(entity, minecraft, key, value);
        }
        return value;
    }

    public static StatusEffect set(Entity entity, minecraft minecraft, String key, StatusEffect value) {
        entity.setMetadata(key, new FixedMetadataValue(
                minecraft,
                value
        ));
        return value;
    }
}