/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
public class Item extends ModelBase {
    private String name;
    private String code;
    private Byte[] image;
    @OneToOne(targetEntity = SubCategory.class)
    private SubCategory subCategory;
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private Set<ItemInstance> itemInstances;

    public Set<ItemInstance> getItemInstances() {
        return itemInstances;
    }

    public void setItemInstances(Set<ItemInstance> itemInstances) {
        this.itemInstances = itemInstances;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }
}
