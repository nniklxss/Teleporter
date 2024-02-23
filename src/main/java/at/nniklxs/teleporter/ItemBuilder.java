package at.nniklxs.teleporter;

import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.*;
import org.bukkit.enchantments.*;
import java.util.*;

public class ItemBuilder {
    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemBuilder(final Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemBuilder(final Material material, final int amount) {
        this.itemStack = new ItemStack(material, amount);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemBuilder(final Material material, final short subId) {
        this.itemStack = new ItemStack(material, 1, subId);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemBuilder(final Material material, final int amount, final short subId) {
        this.itemStack = new ItemStack(material, amount, subId);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemBuilder setDisplayName(final String displayName) {
        this.itemMeta.setDisplayName(displayName);
        return this;
    }

    public ItemBuilder setLore(final String... lore) {
        this.itemMeta.setLore((List)Arrays.asList(lore));
        return this;
    }

    public ItemBuilder setLore(final List<String> lore) {
        this.itemMeta.setLore((List)lore);
        return this;
    }

    public ItemBuilder setUnbreakable() {
        this.itemMeta.setUnbreakable(true);
        return this;
    }

    public ItemBuilder addEnchantment(final Enchantment enchantment, final int enchantmentLevel) {
        this.itemStack.addUnsafeEnchantment(enchantment, enchantmentLevel);
        return this;
    }

    public ItemBuilder addLore(final String lore) {
        final List<String> aLore = (this.itemMeta.getLore() != null) ? this.itemMeta.getLore() : new ArrayList<String>();
        aLore.add(lore);
        this.itemMeta.setLore((List)aLore);
        return this;
    }

    public ItemBuilder setName(final String name) {
        this.itemMeta.setLocalizedName(name);
        return this;
    }

    public ItemStack create() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }
}
