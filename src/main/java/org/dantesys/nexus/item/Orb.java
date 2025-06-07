package org.dantesys.nexus.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Orb extends Item {
    private final int cargaMax = 100;
    private int carga = 0;
    /*
    * 0 - Nenhum
    * 1 - Solar
    * 2 - Lunar
    * 3 - Nether
    * 4 - Ender
    */
    private int tipo = 0;
    public Orb(Properties properties) {
        super(properties);
    }
    public int getCarga(ItemStack stack) {
        return this.carga;
    }
    public void setCarregar(ItemStack stack, int carregar, int tipo) {
        if(this.tipo == 0){
            this.tipo = tipo;
        }
        if(this.tipo == tipo){
            this.carga = Math.min(cargaMax,this.carga+carregar);
        }
        ResourceLocation nome = ModItems.NEXUS_ORB.get().getRegistryName();
        switch (this.tipo){
            case 1:
                this.setRegistryName(nome+" (Solar "+this.carga/cargaMax+"%)");
                break;
            case 2:
                this.setRegistryName(nome+" (Lunar "+this.carga/cargaMax+"%)");
                break;
            case 3:
                this.setRegistryName(nome+" (Nether "+this.carga/cargaMax+"%)");
                break;
            case 4:
                this.setRegistryName(nome+" (Ender "+this.carga/cargaMax+"%)");
                break;
        }
    }
    public void setDescarregar(ItemStack stack, int uso, int tipo) {
        if(this.tipo == tipo){
            this.carga = Math.min(cargaMax,this.carga-uso);
            if(this.carga==0){
                this.tipo=0;
            }
        }
        ResourceLocation nome = ModItems.NEXUS_ORB.get().getRegistryName();
        switch (this.tipo){
            case 0:
                this.setRegistryName(nome);
                break;
            case 1:
                this.setRegistryName(nome+" (Solar "+this.carga/cargaMax+"%)");
                break;
            case 2:
                this.setRegistryName(nome+" (Lunar "+this.carga/cargaMax+"%)");
                break;
            case 3:
                this.setRegistryName(nome+" (Nether "+this.carga/cargaMax+"%)");
                break;
            case 4:
                this.setRegistryName(nome+" (Ender "+this.carga/cargaMax+"%)");
                break;
        }
    }
}
