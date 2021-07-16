package com.NuclearFusion.api;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

public class ModItemTier implements IItemTier {

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Item repairMaterial;

    /**
     * ������ǻ���ֵ �ڹ��ߵĹ��캯���е����ڻ���ֵ���޸ĵ�����
     * @param harvestLevelIn   �ھ�ȼ�
     * @param maxUsesIn        ����;�
     * @param efficiencyIn     Ч��
     * @param attackDamageIn   �����˺�
     * @param enchantabilityIn ��ħ�ȼ�(Խ��Խ���׳��ø�ħ)
     * @param repairMaterialIn �޸�����
     */
    public ModItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Item repairMaterialIn) {
        this.harvestLevel = harvestLevelIn;
        this.maxUses = maxUsesIn;
        this.efficiency = efficiencyIn;
        this.attackDamage = attackDamageIn;
        this.enchantability = enchantabilityIn;
        this.repairMaterial = repairMaterialIn;
    }

    @Override
    public int getMaxUses() {
        return this.maxUses;
    }

    @Override
    public float getEfficiency() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(repairMaterial);
    }

}
