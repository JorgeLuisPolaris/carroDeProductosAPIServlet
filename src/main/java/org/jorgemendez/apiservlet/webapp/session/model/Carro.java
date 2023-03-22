package org.jorgemendez.apiservlet.webapp.session.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
    private List<ItemCarro> items;

    public Carro() {
        this.items= new ArrayList<>();
    }

    public List<ItemCarro> getItems() {
        return items;
    }

    public void addItem(ItemCarro item){
        if (items.contains(item)){
            Optional<ItemCarro> OptionalItemCarro = items.stream().filter(itemCarro -> {
                return itemCarro.equals(itemCarro);
            }).findAny();
        if (OptionalItemCarro.isPresent()){
            ItemCarro i = OptionalItemCarro.get();
            i.setCantidad(i.getCantidad()+1);
        }
        }else{
            items.add(item);
        }
    }
    public void removeProductos(List<String> productoIds) {
        if (productoIds != null) {
            productoIds.forEach(this::removeProducto);

        }
    }

    public void removeProducto(String productoId) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> items.remove(itemCarro));
    }

    public void updateCantidad(String productoId, int cantidad) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
    }

    private Optional<ItemCarro> findProducto(String productoId) {
        return  items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .findAny();
    }
    public int getTotal(){
        return items.stream().mapToInt(i->i.getImporte()).sum();
    }
}
