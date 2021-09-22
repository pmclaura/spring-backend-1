/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.repository;


import com.sales.market.model.ItemInstance;
import org.springframework.data.jpa.repository.Query;

public interface ItemInstanceRepository extends GenericRepository<ItemInstance> {
    @Query("SELECT instance FROM ItemInstance instance WHERE instance.identifier =?1")
    ItemInstance findByIdentifier(String sku);
}
