/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.model;

import com.sales.market.dto.InventoryEntryDto;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class ItemInventoryEntry extends ModelBase<InventoryEntryDto> {

    @ManyToOne
    private ItemInventory itemInventory;
    private MovementType movementType;
    private BigDecimal quantity; // represent sale or buy instances quantity
    private String itemInstanceSkus; //represents a list of the sku of the involved item instances

    public ItemInventory getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(ItemInventory itemInventory) {
        this.itemInventory = itemInventory;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getItemInstanceSkus() {
        return itemInstanceSkus;
    }

    public void setItemInstanceSkus(String itemInstanceSkus) {
        this.itemInstanceSkus = itemInstanceSkus;
    }
/*
    Take into account sku cannot be duplicated
    In the service make possible:
       register buy item instances -> Si no existe el producto crearlo, registrar instancias,
                                        crear y actualizar el ItemInventory correspondiente con sus totalizados
                                        Generar los ItemInventoryEntry para reflejar la operacion de entrada o salida
                                         de almacen

       vender un producto
       desechar un producto similar a una venta pero a costo 0. Debe reflejar el totalizado correctamente de
       ItemInventory

       Debe haber tests unitarios que muestren escenarios para estas operaciones en casos de exito y de error.
    */
}
